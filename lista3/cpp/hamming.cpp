// Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 5


#include <iostream>
#include <cassert>
#include <map>
#include "gfield.cpp"
#include "polynomial.cpp"


using std::cout;

typedef Galois<2> GF;

const int CODEWORD_LENGTH = 7;
const int MESSAGE_LENGTH = 4;
const GF GF_ZERO = GF(0);
const GF GF_ONE = GF(1);


Polynomial<GF> gfPolynomialFromNum(int value)
{
    if (value == 0)
    {
        return Polynomial<GF>(GF_ZERO);
    }

    vector<GF> coeffs;

    while (value > 0)
    {
        coeffs.push_back(GF(value));
        value /= 2;
    }

    Polynomial<GF> result(coeffs);

    return result;
}


Polynomial<GF> degreePolynomial(int degree)
{
    vector<GF> coeffs(degree + 1, GF_ZERO);
    coeffs[degree] = GF_ONE;
    Polynomial<GF> result(coeffs);

    return result;
}


Polynomial<GF> encode(Polynomial<GF> message, 
                      Polynomial<GF> generator,
                      Polynomial<GF> modulo)
{

    return (message * generator) % modulo;
}


std::map<Polynomial<GF>, Polynomial<GF>> codewordBase(Polynomial<GF> generator)
{
    std::map<Polynomial<GF>, Polynomial<GF>> base;

    for (int i = 0; i < pow(2, MESSAGE_LENGTH); i++)
    {
        Polynomial<GF> message = gfPolynomialFromNum(i);
        Polynomial<GF> codeword = message * generator; 
        base.insert(std::pair<Polynomial<GF>, Polynomial<GF>>(codeword, message));

        for (int j = 0; j < CODEWORD_LENGTH; j++)
        {
            Polynomial<GF> error_codeword = codeword;
            error_codeword += degreePolynomial(j);
            base[error_codeword] = message;
        }
    }

    return base;
}


void printPolyBits(Polynomial<GF> poly)
{
    vector<GF> bits = poly.getCoefficients();

    while (bits.size() <= CODEWORD_LENGTH)
    {
        bits.push_back(GF_ZERO);
    } 

    for (GF b: bits)
    {
        cout << b.getValue();
    }

}


int main()
{
    int random_int = randomLLInt(pow(2, MESSAGE_LENGTH) - 1);
    Polynomial<GF> message = gfPolynomialFromNum(random_int);
    Polynomial<GF> generator({GF_ONE, GF_ONE, GF_ZERO, GF_ONE});
    Polynomial<GF> modulo_poly({GF_ONE, GF_ZERO, GF_ZERO, GF_ZERO, GF_ZERO, GF_ZERO, GF_ZERO, GF_ONE});

    std::map<Polynomial<GF>, Polynomial<GF>> codeword_base = codewordBase(generator);    


    cout << "\n\n==========[ Cyclic Hamming (7, 4) code test ]=========\n";

    cout << "\n--> Generator polynomial: " << generator;
    cout << "\n \\--> In bits: ";
    printPolyBits(generator);

    cout << "\n\n--> Modulo polynomial: " << modulo_poly;
    cout << "\n \\--> In bits: ";
    printPolyBits(modulo_poly);
    
    cout << "\n\n--> Given message: " << message;
    cout << "\n \\--> In bits: ";
    printPolyBits(message);

    Polynomial<GF> codeword = encode(message, generator, modulo_poly);
    cout << "\n\n--> Generated codeword: " << codeword;
    cout << "\n \\--> In bits: ";
    printPolyBits(codeword);


    cout << "\n\n\n=========[ Distorting codeword with 0010000 ]==========\n";

    Polynomial<GF> codeword_distorted = codeword + degreePolynomial(2);
    cout << "\n--> Distorted codeword: " << codeword_distorted;
    cout << "\n \\--> In bits: ";
    printPolyBits(codeword_distorted);

    Polynomial<GF> message_distorted = codeword_base[codeword_distorted];
    cout << "\n\n--> Distorted message: " << message_distorted;
    cout << "\n \\--> In bits: ";
    printPolyBits(message_distorted);

    assert(message_distorted == message);


    cout << "\n\n\n\n\t    >> Test passed successfully! <<\n\n\n";



    return 0;
}
