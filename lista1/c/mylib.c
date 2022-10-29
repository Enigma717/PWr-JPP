// Marek Traczyński (261748)
// Lista 1 Zadanie 1


#include "mylib.h"


/////////////////////////
// Factorial functions //
/////////////////////////

int my_factorial(int n)
{
    int result = 1;

    if (n < 0 || n > 20)
    {
        return 0;
    }

    for (int i = 2; i <= n; i++)
    {
        result *= i;
    }
    
    return result;
}

int my_factorial_rec(int n)
{
    if (n < 0 || n > 20)
    {
        return 0;
    }

    if (n > 0)
    {
        return n * my_factorial_rec(n - 1);
    }

    return 1;
}


///////////////////
// GCD functions //
///////////////////

int my_gcd(int x, int y)
{
    int temp = 0;

    if (x < 0 || y < 0)
    {
        return 0;

    }

    while (y != 0)
    {
        temp = y;
        y = x % y;
        x = temp;
    }

    return x;
}

int my_gcd_rec(int x, int y)
{
    if (x < 0 || y < 0)
    {
        return 0;
    }    
    
    if (y == 0)
    {
        return x;
    }

    return my_gcd_rec(y, x % y);
}


////////////////////////////////////
// Diophantine equation functions //
////////////////////////////////////

DioGcdResult dio_gcd(int a, int b)
{
    DioGcdResult result = {0, 0, 0};
    int old_rem = a, rem = b;
    int old_x = 1, x = 0;
    int old_y = 0, y = 1;
    int quotient = 0, temp = 0;
    
    while (rem != 0)
    {
        quotient = old_rem / rem;

        temp = old_rem;
        old_rem = rem;
        rem = temp - quotient * rem;
        
        temp = old_x;
        old_x = x;
        x = temp - quotient * x;

        temp = old_y;
        old_y = y;
        y = temp - quotient * y;
    }

    result.g = old_rem;
    result.x = old_x;
    result.y = old_y;

    return result;
}

DioGcdResult dio_gcd_rec(int a, int b)
{
    DioGcdResult result, temp = {0, 0, 0};

    if (b == 0)
    {
        result.g = a;
        result.x = 1;
        result.y = 0;

        return result;
    }

    temp = dio_gcd_rec(b, a % b);
    result.g = temp.g;
    result.x = temp.y;
    result.y = temp.x - (a / b) * temp.y;

    return result;
}

DioResult dio_equation(int a, int b, int c)
{
    DioGcdResult gcd_result = {0, 0, 0};
    DioResult result = {0, 0};

    if (a == 0 && b == 0)
    {
        if (c == 0)
        {
            result.x = 1;
            result.y = 1;
            
            return result;
        }
        else
        {
            result.x = 0;
            result.y = 0;

            return result;
        }
    }

    gcd_result = dio_gcd(a, b);
    
    if (c % gcd_result.g != 0)
    {
        result.x = 0;
        result.y = 0;

        return result;
    }

    result.x = gcd_result.x * (c / gcd_result.g);
    result.y = gcd_result.y * (c / gcd_result.g);

    return result;
}

DioResult dio_equation_rec(int a, int b, int c)
{
    DioGcdResult gcd_result = {0, 0, 0};
    DioResult result = {0, 0};

    if (a == 0 && b == 0)
    {
        if (c == 0)
        {
            result.x = 1;
            result.y = 1;
            
            return result;
        }
        else
        {
            result.x = 0;
            result.y = 0;

            return result;
        }
    }

    gcd_result = dio_gcd_rec(a, b);
    
    if (c % gcd_result.g != 0)
    {
        result.x = 0;
        result.y = 0;

        return result;
    }

    result.x = gcd_result.x * (c / gcd_result.g);
    result.y = gcd_result.y * (c / gcd_result.g);

    return result;
}
