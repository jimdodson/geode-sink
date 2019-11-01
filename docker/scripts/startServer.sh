#!/bin/bash

mkdir -p /data/$HOSTNAME

gfsh start server --name=$HOSTNAME --hostname-for-clients=localhost --locators=locator[10334] --dir=/data/$HOSTNAME/ --cache-xml-file=scripts/cache.xml "$@"

while true; do
    sleep 10
  done
done