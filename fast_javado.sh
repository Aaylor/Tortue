#!/bin/bash

function do_javadoc(){

    java_file=$1;
    directory_name='javadoc';

    if [ ! -e $directory_name ]; then
        mkdir javadoc;
    else
        rm -R javadoc/*;
        javadoc $java_file;
    fi

}


if [ ! -e $1 ]; then
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

