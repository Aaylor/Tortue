#!/bin/bash

if [ -z $1 ]; then
    echo 'Critical error : no arguments';
    exit 1;
elif [ ! -f $1 ]; then
    echo 'Critical error : is not a file';
    exit 2;
fi

java_file=$1
for i in $java_file
do
    if [ ${i##*.} = 'java' ]; then
        do_javadoc $java_file;
    else
        echo 'Critical error : is not java file';
        exit 3;
    fi
done


function do_javadoc(){

    java_file=$1;
    folder=`pwd`"/javadoc";

    if [ ! -e $folder ]; then
        mkdir $folder;
    else
        rm -r --force $folder"/*";
    fi

    cd $folder;
    javadoc ../$java_file >> /tmp/log_javadoc.log;

    echo 'Javadoc done with succes !';

}
