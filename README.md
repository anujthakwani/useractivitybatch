# User sessions calculator

## Description

This is  a spark batch processor to calculate sessions of a user based on it activity

DDL's are available in DDL folder.

Main class is com.demo.batch.UserActivity. 

## Compile
```
mvn clean install
```

## Run
```
/usr/lib/spark/bin/spark-submit --class 'com.demo.batch.UserActivity' --name 'UserSessionsBatch' --max-executors 5 --executor-memory 2G --driver-memory 3G --executor-cores 4 user-activity-batch-1.0-SNAPSHOT-jar-with-dependencies.jar <path-to-props-file>

```

## Sample Data

```
Output
+-------------------+-------+-------------------+--------+
|                 ts|user_id|          sessionId|visit_id|
+-------------------+-------+-------------------+--------+
|2018-01-01 01:00:00|    bat|2018-01-01 01:00:00|       1|
|2018-01-01 01:15:00|    bat|2018-01-01 01:00:00|       2|
|2018-01-01 01:16:00|    bat|2018-01-01 01:00:00|       3|
|2018-01-01 15:12:00|    bat|2018-01-01 15:12:00|       1|
|2018-01-01 15:16:00|    bat|2018-01-01 15:12:00|       2|
|2018-01-01 02:00:00|  mouse|2018-01-01 02:00:00|       1|
|2018-01-01 02:00:00|  mouse|2018-01-01 02:00:00|       2|
|2018-01-01 02:00:00|  mouse|2018-01-01 02:00:00|       3|
|2018-01-01 02:31:00|  mouse|2018-01-01 02:31:00|       1|
|2018-01-01 03:01:00|  mouse|2018-01-01 02:31:00|       2|
|2018-01-01 02:29:00|  horse|2018-01-01 02:29:00|       1|
+-------------------+-------+-------------------+--------+


Input
+-------------------+-------+
|                 ts|user_id|
+-------------------+-------+
|2018-01-01 02:00:00|  mouse|
|2018-01-01 02:29:00|  horse|
|2018-01-01 02:00:00|  mouse|
|2018-01-01 01:15:00|    bat|
|2018-01-01 01:16:00|    bat|
|2018-01-01 15:16:00|    bat|
|2018-01-01 15:12:00|    bat|
|2018-01-01 02:00:00|  mouse|
|2018-01-01 02:31:00|  mouse|
|2018-01-01 03:01:00|  mouse|
|2018-01-01 01:00:00|    bat|
+-------------------+-------+
