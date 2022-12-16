// Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 2


#include <algorithm>
#include <chrono>
#include <random>
#include "polynomial.h"


/////////////////////////////
// Miscellaneous functions //
/////////////////////////////

template <typename T>
void Polynomial<T>::clearCoeffsZeros()
{
    for (int i = mCoefficients.size() - 1; i > mDegree - 1; i--)
    {
        if (i > 0 && mCoefficients[i] == T(0))
        {
            mCoefficients.pop_back();
            mDegree--;
        }
    }
}

template <typename T>
bool Polynomial<T>::isZeroPoly() const
{
    if (mCoefficients.size() == 1 && mCoefficients[0] == T(0))
    {
        return true;
    }

    return false;
}

template <typename T>
std::tuple<Polynomial<T>, Polynomial<T>> Polynomial<T>::divide(Polynomial<T> divisor) const
{
    if (divisor.isZeroPoly() == true)
    {
        throw "\n\n>> ERROR: Can't divide by 0 polynomial! <<";
    }

    Polynomial<T> result;
    Polynomial<T> remainder = *this;

    while (remainder.isZeroPoly() == false && remainder.mDegree >= divisor.mDegree)
    {
        T coefficient = remainder.mCoefficients.back() / divisor.mCoefficients.back();
        int degree = remainder.mDegree - divisor.mDegree;

        std::vector<T> temp_coeffs(degree + 1);
        temp_coeffs[degree] = coefficient;

        Polynomial<T> temp_poly(temp_coeffs);
        if (temp_poly.isZeroPoly() == true)
        {
            throw "\n\n>> ERROR: Can't divide with the remainder in this type! <<";
        }

        result = result + temp_poly;
        remainder = remainder - (divisor * temp_poly);
    }


    return std::make_tuple(result, remainder);
}


//////////////////
// Constructors //
//////////////////

template <typename T>
Polynomial<T>::Polynomial() : mCoefficients({T(0)}), 
                              mDegree(0) {}

template <typename T>
Polynomial<T>::Polynomial(T value) : mCoefficients({T(value)}), 
                                     mDegree(0) {}

template <typename T>
Polynomial<T>::Polynomial(vector<T> coeffs) : mCoefficients(coeffs),
                                              mDegree(coeffs.size() - 1)
{
    if (coeffs.empty() == true)
    {
        mCoefficients = {T(0)};
        mDegree = 0;
    }

    clearCoeffsZeros();
}


/////////////
// Getters //
/////////////

template <typename T>
vector<T> Polynomial<T>::getCoefficients() const
{
    return mCoefficients;
}

template <typename T>
int Polynomial<T>::getDegree() const
{
    return mDegree;
}


//////////////////////////
// Arithmetic operators //
//////////////////////////

template <typename T>
Polynomial<T> Polynomial<T>::operator + (const Polynomial<T> poly) const
{
    int p1_size = mCoefficients.size();
    int p2_size = poly.mCoefficients.size();
    int result_size = std::max(p1_size, p2_size);

    vector<T> result_coeffs;
    T sum;


    for (int i = 0; i < result_size; i++)
    {
        sum = 0;

        if (i < p1_size)
        {
            sum = sum + mCoefficients[i];
        }
        if (i < p2_size)
        {
            sum = sum + poly.mCoefficients[i];
        }

        result_coeffs.push_back(sum);
    }


    Polynomial<T> result(result_coeffs);

    return result;
}

template <typename T>
Polynomial<T> Polynomial<T>::operator - (const Polynomial<T> poly) const
{
    vector<T> temp_coeffs = poly.mCoefficients;
    std::for_each(std::begin(temp_coeffs), std::end(temp_coeffs), [](T& c){ c = c * T(-1);});
        
    Polynomial<T> temp_poly(temp_coeffs);
    Polynomial<T> result = *this + temp_poly;

    return result;
}

template <typename T>
Polynomial<T> Polynomial<T>::operator * (const Polynomial<T> poly) const
{
    if (this->isZeroPoly() == true || poly.isZeroPoly() == true)
    {
        Polynomial<T> zero_poly;
        return zero_poly;
    }

    int p1_size = mCoefficients.size();
    int p2_size = poly.mCoefficients.size();
    int result_size = p1_size + p2_size - 1;

    vector<T> result_coeffs(result_size);

    for (int i = 0; i < p1_size; i++)
    {
        for (int j = 0; j < p2_size; j++)
        {
            result_coeffs[i+j] = result_coeffs[i+j] + (mCoefficients[i] * poly.mCoefficients[j]);
        }
    }


    Polynomial<T> result(result_coeffs);

    return result;
}

template <typename T>
Polynomial<T> Polynomial<T>::operator / (const Polynomial<T> poly) const
{
    if (poly.isZeroPoly() == true)
    {
        throw "\n\n>> ERROR: Can't divide by 0 polynomial! <<";
    }
    if (this->isZeroPoly() == true)
    {
        Polynomial<T> zero_poly;
        return zero_poly;
    }
    
    Polynomial<T> result = std::get<0>(this->divide(poly));

    return result;
}

template <typename T>
Polynomial<T> Polynomial<T>::operator % (const Polynomial<T> poly) const
{
    Polynomial<T> result;

    if (poly.isZeroPoly() == true)
    {
        throw "\n\n>> ERROR: Can't divide by 0 polynomial! <<";
    }
    if (this->isZeroPoly() == true)
    {
        Polynomial<T> zero_poly;
        return zero_poly;
    }
    
    try
    {
        result = std::get<1>(this->divide(poly));
    }
    catch (const char* msg)
    {
        std::cerr << msg << "\n";
    }
    

    return result;
}

//////////////////////////////////

template <typename T>
Polynomial<T> Polynomial<T>::operator + (const T value) const
{
    vector<T> result_coeffs = mCoefficients;
    result_coeffs[0] = result_coeffs[0] + value;


    Polynomial<T> result(result_coeffs);
    
    return result;
}

template <typename T>
Polynomial<T> Polynomial<T>::operator - (const T value) const
{
    vector<T> result_coeffs = mCoefficients;
    result_coeffs[0] -= value;


    Polynomial<T> result(result_coeffs);
    
    return result;
}

template <typename T>
Polynomial<T> Polynomial<T>::operator * (const T value) const
{
    Polynomial<T> temp_poly(value);
    Polynomial<T> result = *this * temp_poly;
    
    return result;
}

template <typename T>
Polynomial<T> Polynomial<T>::operator / (const T value) const
{
    Polynomial<T> temp_poly(value);
    Polynomial<T> result = *this / temp_poly;
    
    return result;
}

template <typename T>
Polynomial<T> Polynomial<T>::operator % (const T value) const
{
    Polynomial<T> temp_poly(value);
    Polynomial<T> result = *this % temp_poly;
    
    return result;
}


//////////////////////////
// Assignment operators //
//////////////////////////


template <typename T>
Polynomial<T>& Polynomial<T>::operator += (const Polynomial<T> poly)
{
    *this = *this + poly;    

    return *this;
}

template <typename T>
Polynomial<T>& Polynomial<T>::operator -= (const Polynomial<T> poly)
{
    *this = *this - poly;    

    return *this;
}

template <typename T>
Polynomial<T>& Polynomial<T>::operator *= (const Polynomial<T> poly)
{
    *this = *this * poly;    

    return *this;
}

template <typename T>
Polynomial<T>& Polynomial<T>::operator /= (const Polynomial<T> poly)
{
    *this = *this / poly;    

    return *this;
}

template <typename T>
Polynomial<T>& Polynomial<T>::operator = (const Polynomial<T> poly)
{
    mCoefficients = poly.mCoefficients;
    mDegree = poly.mDegree;

    return *this;
}

//////////////////////////////////

template <typename T>
Polynomial<T>& Polynomial<T>::operator += (const T value)
{
    *this = *this + value;    

    return *this;
}

template <typename T>
Polynomial<T>& Polynomial<T>::operator -= (const T value)
{
    *this = *this - value;    

    return *this;
}

template <typename T>
Polynomial<T>& Polynomial<T>::operator *= (const T value)
{
    *this = *this * value;    

    return *this;
}

template <typename T>
Polynomial<T>& Polynomial<T>::operator /= (const T value)
{
    *this = *this / value;    

    return *this;
}

template <typename T>
Polynomial<T>& Polynomial<T>::operator = (const T value)
{
    Polynomial<T> temp(value);
    *this = temp;

    return *this;
}

template <typename T>
Polynomial<T>& Polynomial<T>::operator = (const vector<T> coeffs)
{
    Polynomial<T> temp(coeffs);
    *this = temp;

    return *this;
}


///////////////////////
// Logical operators //
///////////////////////

template <typename T>
bool Polynomial<T>::operator == (const Polynomial<T> poly) const
{
    return mCoefficients == poly.mCoefficients;
}

template <typename T>
bool Polynomial<T>::operator != (const Polynomial<T> poly) const
{
    return mCoefficients != poly.mCoefficients;
}

template <typename T>
bool Polynomial<T>::operator >= (const Polynomial<T> poly) const
{
    if (mDegree > poly.mDegree)
    {
        return true;
    }

    if (mDegree < poly.mDegree)
    {
        return false;
    }

    
    for (int i = mDegree; i >= 0; i--)
    {
        if (mCoefficients[i] > poly.mCoefficients[i])
        {
            return true;
        }
        else if (mCoefficients[i] < poly.mCoefficients[i])
        {
            return false;
        }
    }
        
    return true; 
}

template <typename T>
bool Polynomial<T>::operator <= (const Polynomial<T> poly) const
{
    if (mDegree < poly.mDegree)
    {
        return true;
    }

    if (mDegree > poly.mDegree)
    {
        return false;
    }

    
    for (int i = mDegree; i >= 0; i--)
    {
        if (mCoefficients[i] < poly.mCoefficients[i])
        {
            return true;
        }
        else if (mCoefficients[i] > poly.mCoefficients[i])
        {
            return false;
        }
    }
        
    return true;
}

template <typename T>
bool Polynomial<T>::operator > (const Polynomial<T> poly) const
{
    if (mDegree > poly.mDegree)
    {
        return true;
    }

    if (mDegree < poly.mDegree)
    {
        return false;
    }

    
    for (int i = mDegree; i >= 0; i--)
    {
        if (mCoefficients[i] > poly.mCoefficients[i])
        {
            return true;
        }
        else if (mCoefficients[i] < poly.mCoefficients[i])
        {
            return false;
        }
    }
        
    return false;    
}

template <typename T>
bool Polynomial<T>::operator < (const Polynomial<T> poly) const
{
    if (mDegree < poly.mDegree)
    {
        return true;
    }

    if (mDegree > poly.mDegree)
    {
        return false;
    }

    
    for (int i = mDegree; i >= 0; i--)
    {
        if (mCoefficients[i] < poly.mCoefficients[i])
        {
            return true;
        }
        else if (mCoefficients[i] > poly.mCoefficients[i])
        {
            return false;
        }
    }
        
    return false;
}

//////////////////////////////////

template <typename T>
bool Polynomial<T>::operator == (const T value) const
{
    if (mCoefficients.size() == 1)
    {
        return mCoefficients[0] == value;
    }

    return false;
}

template <typename T>
bool Polynomial<T>::operator != (const T value) const
{
    if (mCoefficients.size() == 1)
    {
        return mCoefficients[0] != value;
    }

    return false;
}

template <typename T>
bool Polynomial<T>::operator >= (const T value) const
{
    if (mCoefficients.size() == 1)
    {
        return mCoefficients[0] >= value;
    }

    return false;
}

template <typename T>
bool Polynomial<T>::operator <= (const T value) const
{
    if (mCoefficients.size() == 1)
    {
        return mCoefficients[0] <= value;
    }

    return false;
}

template <typename T>
bool Polynomial<T>::operator > (const T value) const
{
    if (mCoefficients.size() == 1)
    {
        return mCoefficients[0] > value;
    }

    return false;
}

template <typename T>
bool Polynomial<T>::operator < (const T value) const
{
    if (mCoefficients.size() == 1)
    {
        return mCoefficients[0] < value;
    }

    return false;
}


//////////////////////////
// Evaluation operators //
//////////////////////////

template <typename T>
T Polynomial<T>::operator () (const T value) const
{
    T result = mCoefficients.back();
    
    for (int i = mDegree - 1; i >= 0; i--)
    {
        result = result * value + mCoefficients[i];
    }

    return result;
}

template <typename T>
T Polynomial<T>::operator [] (const int index) const
{
    if (index > mDegree || index < 0)
    {
        throw "\n\n>> ERROR: Index out of coefficients bounds! <<";
    }

    return mCoefficients[index];
}
