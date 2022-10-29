# !/bin/bash

gcc -c ../c/mylib.c
gnatmake -c cwraptest.adb
gnatbind cwraptest.ali
gnatlink cwraptest.ali mylib.o -o cwraptest
rm *.o *.ali
./cwraptest
