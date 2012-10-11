#!/bin/bash

function do_javadoc(){

    java_file=$1;
    folder=`pwd`"/javadoc";

    if [ ! -e $folder ]; then
        mkdir $folder;
    else
        rm -r --force $folder"/*";
    fi

    javadoc -windowtitle 'Projet IK3 : Tortue 1' -footer '<i>Classes créees pour le projet Tortue 1, en IK3</i>' -charset 'UTF-8' -quiet -d $folder `pwd`"/src/"$java_file; 2> error.log

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

