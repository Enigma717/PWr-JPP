all: galois polynomial hamming

galois: gtest.cpp gfield.cpp
	g++ -o gtest gtest.cpp

polynomial: ptest.cpp polynomial.cpp gfield.cpp
	g++ -o ptest ptest.cpp

hamming: hamming.cpp polynomial.cpp gfield.cpp
	g++ -o hamming hamming.cpp

