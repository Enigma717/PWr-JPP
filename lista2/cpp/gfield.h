// Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 2 Zadanie 1


#ifndef GALOISFIELD_H
#define GALOISFIELD_H

#include <iostream>


inline constexpr long long int FIELD_ORDER = 1234567891;

long long int randomValue();
long long int modInverse(const long long int x);


class Galois {
private:
    long long int mOrder;
    long long int mValue;
public:
    Galois();
    Galois(long long int value);
        
    long long int getOrder() const;
    long long int getValue() const;

    Galois operator + (const Galois field) const;
    Galois operator - (const Galois field) const;
    Galois operator * (const Galois field) const;
    Galois operator / (const Galois field) const;

    Galois operator + (const long long int value) const;
    Galois operator - (const long long int value) const;
    Galois operator * (const long long int value) const;
    Galois operator / (const long long int value) const;


    bool operator == (const Galois field) const;
    bool operator != (const Galois field) const;
    bool operator >= (const Galois field) const;
    bool operator <= (const Galois field) const;
    bool operator > (const Galois field) const;
    bool operator < (const Galois field) const;

    bool operator == (const long long int value) const;
    bool operator != (const long long int value) const;
    bool operator >= (const long long int value) const;
    bool operator <= (const long long int value) const;
    bool operator > (const long long int value) const;
    bool operator < (const long long int value) const;


    Galois& operator = (const long long int value);
    operator int();


    friend std::ostream& operator << (std::ostream& out, const Galois field);
};


#endif
