#!/bin/bash

function do_javadoc(){

    java_file=$1;
    folder=`pwd`"/javadoc";

    if [ ! -e $folder ]; then
        mkdir $folder;
    fi

    javadoc -charset 'UTF-8' -quiet -d $folder `pwd`"/src/"*; 2> error.log

    if [ -s error.log ]; then
        cat error.log;
    else
        echo 'Javadoc done with succes !';
    fi

    rm error.log;

}

if [ -z $1 ]; then
    echo 'Critical error : no arguments';
    exit 1;
fi

java_file=$1
for i in $java_file
do
    if [ ${i##*.} = 'java' ]; then
        do_javadoc $java_file;
    else
        echo 'Critical error : is not java file';
        exit 2;
    fi
done

