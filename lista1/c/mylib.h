// Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 1 Zadanie 1


typedef struct DioGcdResult {
    long long int g;
    long long int x;
    long long int y;
} DioGcdResult;

typedef struct DioResult {
    long long int x;
    long long int y;
} DioResult;


int my_factorial(int n);
int my_factorial_rec(int n);

int my_gcd(int x, int y);
int my_gcd_rec(int x, int y);

DioGcdResult dio_gcd(int x, int y);
DioGcdResult dio_gcd_rec(int x, int y);
DioResult dio_equation(int a, int b, int c);
DioResult dio_equation_rec(int a, int b, int c);
