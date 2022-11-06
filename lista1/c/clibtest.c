// Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 1 Zadanie 1


#include <stdio.h>
#include "mylib.c"

#define VALUES_COUNT 3


int main(void)
{
    DioResult dio_result = {0, 0};
    int factorial_values[VALUES_COUNT] = {10, 0, -1};
    int gcd_values[VALUES_COUNT][2] = {{42, 56}, {0, 16}, {-2, 10}};
    int dio_values[VALUES_COUNT][3] = {{4, 18, 10}, {10, 0, 0}, {-5, -3, 4}};
    int n_arg, x_arg, y_arg, a_arg, b_arg, c_arg = 0;
    int function_result = 0;

    printf("\n+--------------------------------------------------------+");
    printf("\n|                     C LIBRARY TEST                     |");
    printf("\n+--------------------------------------------------------+");


    printf("\n\n===============[ ITERATIVE FACTORIAL TEST ]===============\n");

    printf("\n-> Iterative factorial results:");

    for (int i = 0; i < VALUES_COUNT; i++)
    {
        n_arg = factorial_values[i];
        function_result = my_factorial(n_arg);

        printf("\n|--> my_factorial(%d) result: %d", n_arg, function_result);
    }


    printf("\n\n===============[ RECURSIVE FACTORIAL TEST ]===============\n");

    printf("\n-> Recursive factorial results:");

    for (int i = 0; i < VALUES_COUNT; i++)
    {
        n_arg = factorial_values[i];
        function_result = my_factorial_rec(n_arg);
        
        printf("\n|--> my_factorial_rec(%d) result: %d", n_arg, function_result);
    }


    printf("\n\n+--------------------------------------------------------+\n");


    printf("\n==================[ ITERATIVE GCD TEST ]==================\n");

    printf("\n-> Iterative GCD results:");

    for (int i = 0; i < VALUES_COUNT; i++)
    {
        x_arg = gcd_values[i][0];
        y_arg = gcd_values[i][1];
        function_result = my_gcd(x_arg, y_arg);

        printf("\n|--> my_gcd(%d, %d) result: %d", x_arg, y_arg, function_result);
    }
    
    
    printf("\n\n==================[ RECURSIVE GCD TEST ]==================\n");

    printf("\n-> Recursive GCD results:");

    for (int i = 0; i < VALUES_COUNT; i++)
    {
        x_arg = gcd_values[i][0];
        y_arg = gcd_values[i][1];
        function_result = my_gcd_rec(x_arg, y_arg);

        printf("\n|--> my_gcd_rec(%d, %d) result: %d", x_arg, y_arg, function_result);
    }


    printf("\n\n+--------------------------------------------------------+\n");


    printf("\n==============[ ITERATIVE DIOPHANTINE TEST ]==============\n");

    printf("\n-> Iterative diophantine equation results:");

    for (int i = 0; i < VALUES_COUNT; i++)
    {
        a_arg = dio_values[i][0];
        b_arg = dio_values[i][1];
        c_arg = dio_values[i][2];
        dio_result = dio_equation(a_arg, b_arg, c_arg);

        printf("\n|--> dio_equation(%d, %d, %d) result: x = %lld, y = %lld",
            a_arg, b_arg, c_arg, dio_result.x, dio_result.y);
    }


    printf("\n\n==============[ RECURSIVE DIOPHANTINE TEST ]==============\n");

    printf("\n-> Recursive diophantine equation results:");

    for (int i = 0; i < VALUES_COUNT; i++)
    {
        a_arg = dio_values[i][0];
        b_arg = dio_values[i][1];
        c_arg = dio_values[i][2];
        dio_result = dio_equation_rec(a_arg, b_arg, c_arg);
        
        printf("\n|--> dio_equation_rec(%d, %d, %d) result: x = %lld, y = %lld",
            a_arg, b_arg, c_arg, dio_result.x, dio_result.y);
    }


    printf("\n\n+--------------------------------------------------------+\n\n");

    return 0;
}
