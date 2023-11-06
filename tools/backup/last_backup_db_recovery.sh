#!/bin/bash

BACKUPS_DIR="."
LATEST_VERSION=""
LATEST_FILE=""

DB_CONTAINER_NAME=vk-db
DB_NAME=vk-db
DB_ID=root
DB_PWD=0000

# 최신 버전 backup 파일 이름 확인
for file in "$BACKUPS_DIR"/*.sql; do
    filename=$(basename "$file")
    version=$(echo "$filename" | grep -oP '\d+\-\d+\-\d+\-\d+\.\d+\.\d+(?=\.sql)')

    if [[ "$version" > "$LATEST_VERSION" ]]; then
        LATEST_VERSION="$version"
        LATEST_FILE="$filename"
    fi
done

echo "last version backup: $LATEST_FILE"

#docker exec ${DB_CONTAINER_NAME} mysql -u ${DB_ID} -p${DB_PWD} ${DB_NAME} < ${BACKUPS_DIR}/${LATEST_FILE}
cat ${BACKUPS_DIR}/${LATEST_FILE} | docker exec -i ${DB_CONTAINER_NAME}  mysql -u ${DB_ID} --password=${DB_PWD} ${DB_NAME}