# Funnel Metrics Process 

## Description

This is a realtime streaming application based on Spark & Kafka to calculate user analysis metrics 

dataSinks DDL's are available in resources/MysqlDDL.sql file

## Compile
```
mvn clean package
```

## Run
```
/usr/lib/spark/bin/spark-submit --class 'com.demo.streaming.FunnelMetrics' --name 'ExecutiveSummary'  --max-executors 5 --executor-memory 2G --driver-memory 3G --executor-cores 4 /home/athakwani/demo/target/times-net-1.0-SNAPSHOT-jar-with-dependencies.jar  <path-to-props-file>
  

