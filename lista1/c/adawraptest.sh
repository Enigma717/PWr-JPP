# !/bin/bash

gcc -c adawraptest.c
gnatmake ../ada/mylib.adb
gnatbind -n mylib.ali
gnatlink adawraptest.o mylib.ali -o adawraptest
rm *.o *.ali
./adawraptest
