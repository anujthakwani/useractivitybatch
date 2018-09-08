# User sessions calculator

## Description

This is  a spark batch processor to calculate sessions of a user based on it activity

DDL's are available in DDL folder.

Main class is com.demo.batch.UserActivity/. 

## Compile
```
mvn clean install
```

## Run
```
/usr/lib/spark/bin/spark-submit --class 'com.demo.batch.UserActivity' --name 'UserSessionsBatch' --max-executors 5 --executor-memory 2G --driver-memory 3G --executor-cores 4 user-activity-batch-1.0-SNAPSHOT-jar-with-dependencies.jar props
