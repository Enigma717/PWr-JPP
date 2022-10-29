# !/bin/bash

gnatmake adalibtest.adb
rm *.o *.ali
./adalibtest
