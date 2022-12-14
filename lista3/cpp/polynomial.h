// Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 2


#ifndef POLYNOMIAL_H
#define POLYNOMIAL_H

#include <iostream>
#include <tuple>
#include <vector>


using std::vector;


inline constexpr long long int TEST_ORDER = 1234567891;

long long int randomValue(const long long int order);


template <typename T>
class Polynomial {
private:
    vector<T> mCoefficients;
    int mDegree;

    void clearCoeffsZeros();
    bool isZeroPoly();
    std::tuple<Polynomial<T>, Polynomial<T>> divide(const Polynomial<T> divisor) const;
public:
    Polynomial();
    Polynomial(T value);
    Polynomial(vector<T> coeffs);
        

    vector<T> getCoefficients() const;
    int getDegree() const;


    Polynomial<T> operator + (const Polynomial<T> poly) const;
    Polynomial<T> operator - (const Polynomial<T> poly) const;
    Polynomial<T> operator * (const Polynomial<T> poly) const;
    Polynomial<T> operator / (const Polynomial<T> poly) const;
    Polynomial<T> operator % (const Polynomial<T> poly) const;

    Polynomial<T> operator + (const T value) const;
    Polynomial<T> operator - (const T value) const;
    Polynomial<T> operator * (const T value) const;
    Polynomial<T> operator / (const T value) const;
    Polynomial<T> operator % (const T value) const;


    Polynomial<T>& operator += (const Polynomial<T> poly);
    Polynomial<T>& operator -= (const Polynomial<T> poly);
    Polynomial<T>& operator *= (const Polynomial<T> poly);
    Polynomial<T>& operator /= (const Polynomial<T> poly);
    Polynomial<T>& operator  = (const Polynomial<T> poly);

    Polynomial<T>& operator += (const T value);
    Polynomial<T>& operator -= (const T value);
    Polynomial<T>& operator *= (const T value);
    Polynomial<T>& operator /= (const T value);
    Polynomial<T>& operator  = (const T value);
    Polynomial<T>& operator  = (const vector<T> coeffs);


    bool operator == (const Polynomial<T> poly) const;
    bool operator != (const Polynomial<T> poly) const;
    bool operator >= (const Polynomial<T> poly) const;
    bool operator <= (const Polynomial<T> poly) const;
    bool operator > (const Polynomial<T> poly) const;
    bool operator < (const Polynomial<T> poly) const;

    bool operator == (const T value) const;
    bool operator != (const T value) const;
    bool operator >= (const T value) const;
    bool operator <= (const T value) const;
    bool operator > (const T value) const;
    bool operator < (const T value) const;


    template <T>
    friend std::ostream& operator << (std::ostream& out, const Polynomial<T> field);
};


#endif
