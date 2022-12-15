// Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 2


#ifndef POLYNOMIAL_H
#define POLYNOMIAL_H

#include <iostream>
#include <tuple>
#include <vector>


using std::vector;


template <typename T>
class Polynomial {
private:
    vector<T> mCoefficients;
    int mDegree;

    void clearCoeffsZeros();
    bool isZeroPoly() const;
public:
    Polynomial();
    Polynomial(T value);
    Polynomial(vector<T> coeffs);
        
    std::tuple<Polynomial<T>, Polynomial<T>> divide(const Polynomial<T> divisor) const;

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
    bool operator >  (const Polynomial<T> poly) const;
    bool operator <  (const Polynomial<T> poly) const;

    bool operator == (const T value) const;
    bool operator != (const T value) const;
    bool operator >= (const T value) const;
    bool operator <= (const T value) const;
    bool operator >  (const T value) const;
    bool operator <  (const T value) const;


    T operator () (const T value) const;
    T operator [] (const int index) const;


    friend std::ostream& operator << (std::ostream& out, const Polynomial<T> poly)
    {
        if (poly.getCoefficients().size() == 1 && poly[0] == T(0))
        {
            return out << "0";
        }

        const char *plus = "";
        const char *space = "";

        for(int i = poly.getDegree(); i >= 0; i--)
        {
            if (poly[i] == T(0))
            {
                continue;
            }

            if (poly[i] > T(0))
            {
                out << space << plus << space; 
            }
            else if (poly[i] < T(0))
            {
                out << space << "-" << space; 
            }

            plus = "+";
            space = " ";

            if (i == 0 || fabs(poly[i]) != T(1))
            {
                out << fabs(poly[i]);
            }

            if (i == 0)
            {
                continue;
            }

            out << "x";

            if (i == 1)
            {
                continue;
            }

            out << "^" << i;
        }
        return out;
    }
};


#endif
