// Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 2


#include <cassert>
#include <iomanip>
#include "gfield.cpp"
#include "polynomial.cpp"


using std::cout;


int main()
{
    double dRandom1 = randomDouble(100);
    double dRandom2 = -(randomDouble(100));
    double dRandom3 = randomDouble(100);
    double dRandom4 = -(randomDouble(100));
    double dRandom5 = randomDouble(100);

    long long int lliRandom1 = floor(dRandom1);
    long long int lliRandom2 = floor(dRandom2);
    long long int lliRandom3 = floor(dRandom3);
    long long int lliRandom4 = floor(dRandom4);
    long long int lliRandom5 = floor(dRandom5);

    Galois<2> Field1(lliRandom1);
    Galois<2> Field2(lliRandom2);
    Galois<2> Field3(lliRandom3);
    Galois<2> Field4(lliRandom4);
    Galois<2> Field5(lliRandom5);

    Galois<2> Test_field1(1);
    Galois<2> Test_field2(2);
    Galois<2> Test_field3(3);
    Galois<2> Test_field4(4);
    Galois<2> Test_field5(5);


    Polynomial<double> dResult_poly;
    Polynomial<double> dExpected_poly;
    vector<double> dExpected;
    Polynomial<Galois<2>> gfResult_poly;
    Polynomial<Galois<2>> gfExpected_poly;
    vector<Galois<2>> gfExpected;

    int d_result_size = 0;
    int gf_result_size = 0;
    bool logicExpected = false;
    bool logicReal = false;


    cout.imbue(std::locale(""));
    cout << "\n+---------------------------------------------------------+";
    cout << "\n|                     POLYNOMIAL TEST                     |";
    cout << "\n+---------------------------------------------------------+\n";

    cout << "\n\t      >-----------------------------<";
    cout << "\n\t      >>                           <<";
    cout << "\n\t      >>     GENERATED NUMBERS     <<";
    cout << "\n\t      >>                           <<";
    cout << "\n\t      >>   FIRST: "<< std::setw(14) << dRandom1 << "   <<";
    cout << "\n\t      >>  SECOND: "<< std::setw(14) << dRandom2 << "   <<";
    cout << "\n\t      >>   THIRD: "<< std::setw(14) << dRandom3 << "   <<";
    cout << "\n\t      >>  FOURTH: "<< std::setw(14) << dRandom4 << "   <<";
    cout << "\n\t      >>   FIFTH: "<< std::setw(14) << dRandom5 << "   <<";
    cout << "\n\t      >>                           <<";
    cout << "\n\t      >-----------------------------<";



    cout << "\n\n====================[ CONSTRUCTOR TEST ]====================\n";

    vector<double> real_coeffs = {dRandom1, 
                                  dRandom2, 
                                  dRandom3, 
                                  dRandom4, 
                                  dRandom5};

    vector<Galois<2>> field_coeffs = {Field1,
                                      Field2,
                                      Field3,
                                      Field4,
                                      Field5};

    Polynomial<double> d_poly(real_coeffs);
    cout << "\n-> Polynomial with real numbers:";
    cout << "\n|--> Print: " << d_poly; 
    cout << "\n\\--> Degree: " << d_poly.getDegree() << "\n"; 


    Polynomial<Galois<2>> gf_poly(field_coeffs);
    cout << "\n-> Polynomial with finite fields:";
    cout << "\n|--> Print: " << gf_poly; 
    cout << "\n\\--> Degree: " << gf_poly.getDegree() << "\n"; 



    cout << "\n\n=====================[ ARITHMETIC TEST ]====================\n";

    vector<double> d_test_values = {1.0, 2.0, 3.0, 4.0, 5.0};
    vector<Galois<2>> gf_test_values = {Test_field1,
                                        Test_field2,
                                        Test_field3,
                                        Test_field4,
                                        Test_field5};

    Polynomial<double> d_test_poly(d_test_values);
    Polynomial<Galois<2>> gf_test_poly(gf_test_values);

    cout << "\n>> Test real numbers polynomial: " << d_test_poly;
    cout << "\n>> Test finite field polynomial: " << gf_test_poly << "\n";

    //////////////////////////////////
    cout << "\n-----------------------\n";
    //////////////////////////////////

    cout << "\n-> Addition test:";

    dResult_poly = d_poly + d_test_poly; 
    gfResult_poly = gf_poly + gf_test_poly; 

    dExpected = {dRandom1 + 1.0, 
                 dRandom2 + 2.0, 
                 dRandom3 + 3.0, 
                 dRandom4 + 4.0, 
                 dRandom5 + 5.0};
    gfExpected = {Field1 + Test_field1, 
                  Field2 + Test_field2, 
                  Field3 + Test_field3, 
                  Field4 + Test_field4, 
                  Field5 + Test_field5};

    dExpected_poly = dExpected;
    gfExpected_poly = gfExpected;

    cout << "\n|--> Real numbers:";
    cout << "\n |--> Expected result: " << dExpected_poly; 
    cout << "\n \\--> Real result: " << dResult_poly; 
    cout << "\n|--> Finite fields:"; 
    cout << "\n |--> Expected result: " << gfExpected_poly; 
    cout << "\n \\--> Real result: " << gfResult_poly << "\n"; 
    assert(dResult_poly == dExpected_poly);
    assert(gfResult_poly == gfExpected_poly);

    //////////////////////////////////
    cout << "\n-----------------------\n";
    //////////////////////////////////

    cout << "\n-> Subtraction test:";

    dResult_poly = d_poly - d_test_poly; 
    gfResult_poly = gf_poly - gf_test_poly; 

    dExpected = {dRandom1 - 1.0, 
                 dRandom2 - 2.0, 
                 dRandom3 - 3.0, 
                 dRandom4 - 4.0, 
                 dRandom5 - 5.0};
    gfExpected = {Field1 - Test_field1, 
                  Field2 - Test_field2, 
                  Field3 - Test_field3, 
                  Field4 - Test_field4, 
                  Field5 - Test_field5};

    dExpected_poly = dExpected;
    gfExpected_poly = gfExpected;

    cout << "\n|--> Real numbers:";
    cout << "\n |--> Expected result: " << dExpected_poly; 
    cout << "\n \\--> Real result: " << dResult_poly; 
    cout << "\n|--> Finite fields:"; 
    cout << "\n |--> Expected result: " << gfExpected_poly; 
    cout << "\n \\--> Real result: " << gfResult_poly << "\n"; 
    assert(dResult_poly == dExpected_poly);
    assert(gfResult_poly == gfExpected_poly);

    //////////////////////////////////
    cout << "\n-----------------------\n";
    //////////////////////////////////

    cout << "\n-> Multiplication test:";

    dResult_poly = d_poly * d_test_poly; 
    gfResult_poly = gf_poly * gf_test_poly; 

    d_result_size = d_poly.getCoefficients().size() + d_test_poly.getCoefficients().size() - 1;
    gf_result_size = gf_poly.getCoefficients().size() + gf_test_poly.getCoefficients().size() - 1;
    dExpected.resize(d_result_size);
    gfExpected.resize(gf_result_size);
    std::fill(dExpected.begin(), dExpected.end(), 0);
    std::fill(gfExpected.begin(), gfExpected.end(), 0);

    try
    {
        for (int i = 0; i < d_poly.getCoefficients().size(); i++)
        {
            for (int j = 0; j < d_test_poly.getCoefficients().size(); j++)
            {
                dExpected[i+j] = dExpected[i+j] + (d_poly[i] * d_test_poly[j]);
            }
        }

        for (int i = 0; i < gf_poly.getCoefficients().size(); i++)
        {
            for (int j = 0; j < gf_test_poly.getCoefficients().size(); j++)
            {
                gfExpected[i+j] = gfExpected[i+j] + (gf_poly[i] * gf_test_poly[j]);
            }
        }
    }
    catch (const char *msg)
    {
        std::cerr << msg << "\n";
    }
    

    dExpected_poly = dExpected;
    gfExpected_poly = gfExpected;

    cout << "\n|--> Real numbers:";
    cout << "\n |--> Expected result: " << dExpected_poly; 
    cout << "\n \\--> Real result: " << dResult_poly; 
    cout << "\n|--> Finite fields:"; 
    cout << "\n |--> Expected result: " << gfExpected_poly; 
    cout << "\n \\--> Real result: " << gfResult_poly << "\n"; 
    assert(dResult_poly == dExpected_poly);
    assert(gfResult_poly == gfExpected_poly);

    //////////////////////////////////
    cout << "\n-----------------------\n";
    //////////////////////////////////
 
    try
    {
        cout << "\n-> Division test:";

        dResult_poly = d_poly / d_test_poly; 
        gfResult_poly = gf_poly / gf_test_poly; 

        dExpected_poly = std::get<0>(d_poly.divide(d_test_poly));
        gfExpected_poly = std::get<0>(gf_poly.divide(gf_test_poly));

        cout << "\n|--> Real numbers:";
        cout << "\n |--> Expected result: " << dExpected_poly; 
        cout << "\n \\--> Real result: " << dResult_poly; 
        cout << "\n|--> Finite fields:"; 
        cout << "\n |--> Expected result: " << gfExpected_poly; 
        cout << "\n \\--> Real result: " << gfResult_poly << "\n"; 
        assert(dResult_poly == dExpected_poly);
        assert(gfResult_poly == gfExpected_poly);
    }
    catch (const char* msg)
    {
        std::cerr << msg << "\n";
    }
    
    //////////////////////////////////
    cout << "\n-----------------------\n";
    //////////////////////////////////
 
    try
    {
        cout << "\n-> Modulo test:";

        dResult_poly = d_poly % d_test_poly; 
        gfResult_poly = gf_poly % gf_test_poly; 

        dExpected_poly = std::get<1>(d_poly.divide(d_test_poly));
        gfExpected_poly = std::get<1>(gf_poly.divide(gf_test_poly));

        cout << "\n|--> Real numbers:";
        cout << "\n |--> Expected result: " << dExpected_poly; 
        cout << "\n \\--> Real result: " << dResult_poly; 
        cout << "\n|--> Finite fields:"; 
        cout << "\n |--> Expected result: " << gfExpected_poly; 
        cout << "\n \\--> Real result: " << gfResult_poly << "\n"; 
        assert(dResult_poly == dExpected_poly);
        assert(gfResult_poly == gfExpected_poly);
    }
    catch (const char* msg)
    {
        std::cerr << msg << "\n";
    }



    cout << "\n\n=======================[ LOGIC TEST ]=======================\n";

    cout << "\n>> First real numbers polynomial: " << d_poly;
    cout << "\n>> Second real numbers polynomial: " << d_test_poly;
    cout << "\n>> First finite field polynomial: " << gf_poly;
    cout << "\n>> Second finite field polynomial: " << gf_test_poly << "\n";

    cout << "\n-----------------------\n";

    cout << "\n-> Equality operator test (Polynomial1 == Polynomial1):";
    cout << "\n|--> Real numbers result: " << std::boolalpha << (d_poly == d_poly);
    cout << "\n|--> Finite fields result: " << (gf_poly == gf_poly); 
    assert(d_poly == d_poly);
    assert(gf_poly == gf_poly);

    cout << "\n\n-----------------------\n";

    cout << "\n-> Inequality operator test (Polynomial1 != Polynomial2):";
    cout << "\n|--> Real numbers result: " << std::boolalpha << (d_poly != d_test_poly);
    cout << "\n|--> Finite fields result: " << (gf_poly != gf_test_poly); 
    assert(d_poly != d_test_poly);
    assert(gf_poly != gf_test_poly);

    cout << "\n";


    cout << "\n\n=======================[ EVALUATION TEST ]=======================\n";

    cout << "\n>> Real numbers polynomial: " << d_poly;
    cout << "\n>> Finite field polynomial: " << gf_poly;
    cout << "\n\n-> Evaluating at point x = 5:";

    dResult_poly = d_poly(5.0); 
    gfResult_poly = gf_poly(5); 

    double d_eval_result = d_poly.getCoefficients().back();
    int gf_eval_result = gf_poly.getCoefficients().back();

    for (int i = d_poly.getDegree() - 1; i >= 0; i--)
    {
        d_eval_result = d_eval_result * 5 + d_poly.getCoefficients()[i];
    }
    
    for (int i = gf_poly.getDegree() - 1; i >= 0; i--)
    {
        gf_eval_result = gf_eval_result * 5 + gf_poly.getCoefficients()[i];
    }


    dExpected_poly = d_eval_result;
    gfExpected_poly = gf_eval_result;

    cout << "\n|--> Real numbers:";
    cout << "\n |--> Expected result: " << dExpected_poly; 
    cout << "\n \\--> Real result: " << dResult_poly; 
    cout << "\n|--> Finite fields:"; 
    cout << "\n |--> Expected result: " << gfExpected_poly; 
    cout << "\n \\--> Real result: " << gfResult_poly << "\n"; 
    assert(dResult_poly == dExpected_poly);
    assert(gfResult_poly == gfExpected_poly);

    cout << "\n\n";



    cout << "\n\t      >----------------------------<";
    cout << "\n\t      >>                          <<";
    cout << "\n\t      >>     ALL TESTS PASSED     <<";
    cout << "\n\t      >>                          <<";
    cout << "\n\t      >----------------------------<\n\n\n";


    return 0;
}
