CREATE EXTERNAL TABLE `user_sessions`(`ts` timestamp, `user_id` string,`sessionId` string,`visit_id` bigint)
STORED AS PARQUET
location '<s3-path>'
