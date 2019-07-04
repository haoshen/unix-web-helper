#!/bin/bash

root_dir=$(cd $(dirname $0)/..;pwd)
service_port=8080

function check_service()
{
    for ((i = 0; i < 3; ++i))
    do
        if [ "$(curl localhost:$service_port/health)" == "OK" ]; then
            return 0
        elif [ $i -ne 2 ]; then
            sleep 1
        fi
    done
    return 1
}

pid=$(cat $root_dir/.pid)
if [ "$pid" != "" ] && [ $(ps -ef | grep $pid | grep $(basename $0) | wc -l) -eq 1 ]; then
    echo $(basename $0) is currently running!
    exit 1
fi
echo $$ > $root_dir/.pid

check_service
if [ $? -ne 0 ]; then
    pid=$(ps -ef | grep java | grep unix-web-helper | awk '{print $2}')
    if [ "$pid" != "" ]; then
        kill -9 $pid
        sleep 5
    fi
    cd $root_dir
    java -jar unix-web-helper-1.0-SNAPSHOT.jar >> unix-web-helper.log 2>&1 &
    sleep 60
fi


# * * * * * source ~/.bashrc && sh ~/unix-web-helper-controller.sh> /dev/null 2&>1

