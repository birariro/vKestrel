#!/bin/bash

DB_CONTAINER_NAME=vk-db
DB_NAME=vk-db
DB_ID=root
DB_PWD=0000
FILE_PATH=.

docker exec ${DB_CONTAINER_NAME} mysqldump -u ${DB_ID} -p${DB_PWD} ${DB_NAME} > ${FILE_PATH}/$(date +%Y-%m-%d-%H.%M.%S).sql
