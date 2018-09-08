package com.demo.batch

import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.Properties

import com.demo.constants.Constant
import org.apache.spark.sql.{Column, SaveMode, SparkSession}
import org.apache.log4j.Logger


object UserActivity {

  var properties = new Properties()

  var prop = null



  def main(args: Array[String]) {


    val logger = Logger.getLogger(getClass);
    properties.load(new FileInputStream(args(0)))
    val sourceFile=properties.getProperty("sourcePath")
    val tgtTable=properties.getProperty("tgtTable")

    val spark = SparkSession.builder
      .appName(properties.getProperty("jobName")).enableHiveSupport()
      .getOrCreate


    import spark.implicits._

    //read hive tables parquet file path directly( or we can also read hive table with its name as well).
    val userActivityDF=spark.read.parquet(sourceFile).
                        repartition(new Column("user_id")).sortWithinPartitions("user_id","ts").as[(String, String)].
                        mapPartitions(x=>{
                            //get Default date
                            var dateStart = Constant.DEFAULTDATE
                            //initialise session counter for debugging
                            var sessionIncrement=0
                            //initialise visit no
                            var visitNo=0;
                            //get Date Format
                            val format = new SimpleDateFormat(Constant.FORMAT)

                            var dStart=format.parse(dateStart)
                            var dLast=format.parse(dateStart)
                            x.toList.map(x=>{
                              //check new session creation condition 12hrs=43200 seconds && 30min=1800 seconds
                              if(((format.parse(x._1).getTime-dStart.getTime)/1000>Constant.MAXSESSIONTIME)||((format.parse(x._1).getTime-dLast.getTime)/1000>Constant.MAXINACTITYTIME)){

                                //increment session count
                                sessionIncrement=sessionIncrement+1
                                //initialise visit
                                visitNo=0
                                //set new start time
                                dStart=format.parse(x._1)
                                //set new last seen time
                                dLast=format.parse(x._1)

                              }
                              //increment visit for each row
                              visitNo=visitNo+1
                              dLast=format.parse(x._1)

                              //return new row with session(which is nothing but sessionStartTime) & visitNo
                              (x._1,x._2,format.format(dStart),visitNo)
                            }).iterator
                        }).toDF("ts","user_id","session_id","visit_no")


    //write to target. Here we are writing to hive table which in its DDL has file format defined as parquet
    userActivityDF.write.mode(SaveMode.Overwrite).insertInto(tgtTable)


  }

}
