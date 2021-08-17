#!/bin/sh

if [ -z "${HOST_URL}" ]
  then
    echo "Error: HOST_URL MUST be specified"
    exit 1
fi

if [ -z "${API_KEY}" ]
  then
    echo "Error: API_KEY MUST be specified"
    exit 1
fi

docker-compose build

docker-compose up -d

set +e

docker-compose exec apigoldfile gradle test

docker cp apigoldfileexample_apigoldfile_1:/apigoldfile/build/test-results/test/TEST-goldfiletest.GoldFileTest.xml ./results.xml
docker cp apigoldfileexample_apigoldfile_1:/apigoldfile/build/reports/tests/test ./report
docker-compose down