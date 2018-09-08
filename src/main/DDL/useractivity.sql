
CREATE EXTERNAL TABLE `user_activity`(`ts` timestamp, `user_id` string)
STORED AS PARQUET
location '<s3-path>'
