// Marek Traczyński (261748)
// Lista 2 Zadanie 1


#include <chrono>
#include <random>
#include "gfield.h"


long long int randomValue()
{
    static int seed = std::chrono::system_clock::now().time_since_epoch().count();
    static std::mt19937 generator(seed);
    static std::uniform_int_distribution<long long int> distribution;

    distribution.param(std::uniform_int_distribution<long long int>::param_type(1, FIELD_ORDER));

    return distribution(generator);
}

long long int modInverse(const long long int value)
{
    long long int x = value;
    long long int y = 1;
    long long int n = FIELD_ORDER - 2;

    while (n > 0)
    {
        if (n % 2 == 1)
        {
            y = (y * x) % FIELD_ORDER;
        }
        n = n / 2;
        x = (x * x) % FIELD_ORDER;
    }

    return y;
}


Galois::Galois() : mOrder(FIELD_ORDER), mValue(0) {}
Galois::Galois(long long int value) : mOrder(FIELD_ORDER), mValue(value) 
{
    if (value < 0)
    {
        mValue = (FIELD_ORDER + value) % FIELD_ORDER;
    }
}


long long int Galois::getOrder() const
{
    return mOrder;
}

long long int Galois::getValue() const
{
    return mValue;
}


Galois Galois::operator + (const Galois field) const
{
    Galois result;
    result.mValue = (mValue + field.mValue) % FIELD_ORDER;

    return result;
}

Galois Galois::operator - (const Galois field) const
{
    Galois result;
    result.mValue = (FIELD_ORDER + (mValue - field.mValue) % FIELD_ORDER) % FIELD_ORDER;

    return result;
}

Galois Galois::operator * (const Galois field) const
{
    Galois result;
    result.mValue = (mValue * field.mValue) % FIELD_ORDER;

    return result;
}

Galois Galois::operator / (const Galois field) const
{
    Galois result;
    result.mValue = (mValue * modInverse(field.mValue)) % FIELD_ORDER;

    return result;
}


bool Galois::operator == (const Galois field) const
{
    return mValue == field.mValue;
}

bool Galois::operator != (const Galois field) const
{
    return mValue != field.mValue;
}

bool Galois::operator >= (const Galois field) const
{
    return mValue >= field.mValue;
}

bool Galois::operator <= (const Galois field) const
{
    return mValue <= field.mValue;
}

bool Galois::operator > (const Galois field) const
{
    return mValue > field.mValue;
}

bool Galois::operator < (const Galois field) const
{
    return mValue < field.mValue;
}


Galois& Galois::operator = (const long long int value)
{
    mValue = (FIELD_ORDER + (FIELD_ORDER + value) % FIELD_ORDER) % FIELD_ORDER;

    return *this;
}

Galois::operator int()
{
    return mValue;
}


std::ostream& operator << (std::ostream& out, const Galois field)
{
    return out << "GF(" << FIELD_ORDER << ")[" << field.mValue << "]";
}
