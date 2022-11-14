// Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 2 Zadanie 1


#include <cassert>
#include <iomanip>
#include "gfield.cpp"

using std::cout;


int main()
{
    long long int random1 = randomValue();
    long long int random2 = -(randomValue());
    long long int expected = 0;
    long long int real = 0;
    bool logicExpected = false;
    bool logicReal = false;


    cout.imbue(std::locale(""));
    cout << "\n+---------------------------------------------------------+";
    cout << "\n|                    GALOIS FIELD TEST                    |";
    cout << "\n+---------------------------------------------------------+\n";

    cout << "\n\t      >-----------------------------<";
    cout << "\n\t      >>                           <<";
    cout << "\n\t      >>     GENERATED NUMBERS     <<";
    cout << "\n\t      >>                           <<";
    cout << "\n\t      >>   FIRST: "<< std::setw(14) << random1 << "   <<";
    cout << "\n\t      >>  SECOND: "<< std::setw(14) << random2 << "   <<";
    cout << "\n\t      >>                           <<";
    cout << "\n\t      >-----------------------------<";



    cout << "\n\n====================[ CONSTRUCTOR TEST ]====================\n";

    Galois Field1(random1);
    cout << "\n-> First random value (positive):";
    cout << "\n \\--> Field1: " << Field1 << "\n"; 
    assert(Field1.getValue() == (random1 % FIELD_ORDER));


    Galois Field2(random2);
    cout << "\n-> Second random value (negative):";
    cout << "\n \\--> Field2: " << Field2 << "\n"; 
    assert(Field2.getValue() == ((FIELD_ORDER + (random2 % FIELD_ORDER)) % FIELD_ORDER));



    cout << "\n\n=====================[ ARITHMETIC TEST ]====================\n";

    cout << "\n-> Expected result:";
    cout << "\n \\--> (order + ((random1 @ random2) % order)) % order"; 
    cout << "\n-> Real result:";
    cout << "\n \\--> Field1 @ Field2\n"; 


    expected = (FIELD_ORDER + ((random1 + random2) % FIELD_ORDER)) % FIELD_ORDER;
    real = Field1 + Field2;
    cout << "\n-> Addition test:";
    cout << "\n|--> Expected result: " << expected; 
    cout << "\n\\--> Real result: " << real << "\n"; 
    assert(real == expected);


    expected = (FIELD_ORDER + ((random1 - random2) % FIELD_ORDER)) % FIELD_ORDER;
    real = Field1 - Field2;
    cout << "\n-> Subtraction test:";
    cout << "\n|--> Expected result: " << expected; 
    cout << "\n\\--> Real result: " << real << "\n"; 
    assert(real == expected);


    expected = (FIELD_ORDER + ((random1 * random2) % FIELD_ORDER)) % FIELD_ORDER;
    real = Field1 * Field2;
    cout << "\n-> Multiplication test:";
    cout << "\n|-> Expected result: " << expected; 
    cout << "\n\\--> Real result: " << real << "\n"; 
    assert(real == expected);

 
    try
    {
        expected = (FIELD_ORDER + ((random1 * random1) % FIELD_ORDER)) % FIELD_ORDER;
        real = (Field1 / Field2) * (Field1 * Field2);
        cout << "\n-> Division test:";
        cout << "\n|--> Expected result: " << expected; 
        cout << "\n\\--> Real result: " << real << "\n"; 
        assert(real == expected);
    }
    catch (const char* msg)
    {
        std::cerr << msg << "\n";
    }
    


    cout << "\n\n=======================[ LOGIC TEST ]=======================\n";

    logicExpected = 
        ((FIELD_ORDER + (random1 % FIELD_ORDER)) % FIELD_ORDER) ==
        ((FIELD_ORDER + (random2 % FIELD_ORDER)) % FIELD_ORDER); 
    logicReal = Field1 == Field2;
    cout << "\n-> Equality operator test (Field1 == Field2):";
    cout << "\n|--> Expected result: " << std::boolalpha << logicExpected; 
    cout << "\n\\--> Real result: " << logicReal << "\n"; 
    assert(logicReal == logicExpected);


    logicExpected = 
        ((FIELD_ORDER + (random1 % FIELD_ORDER)) % FIELD_ORDER) > 
        ((FIELD_ORDER + (random2 % FIELD_ORDER)) % FIELD_ORDER); 
    logicReal = Field1 > Field2;
    cout << "\n-> Relational operator test (Field1 > Field2):";
    cout << "\n|--> Expected result: " << logicExpected; 
    cout << "\n\\--> Real result: " << logicReal << "\n"; 
    assert(logicReal == logicExpected);


    logicExpected = 
        ((FIELD_ORDER + (random1 % FIELD_ORDER)) % FIELD_ORDER) < 
        ((FIELD_ORDER + (random2 % FIELD_ORDER)) % FIELD_ORDER); 
    logicReal = Field1 < Field2;
    cout << "\n-> Relational operator test (Field1 < Field2):";
    cout << "\n|--> Expected result: " << logicExpected; 
    cout << "\n\\--> Real result: " << logicReal << "\n"; 
    assert(logicReal == logicExpected);



    cout << "\n\n=====================[ ASSIGNMENT TEST ]====================\n";

    Galois newField(0);
    long long int testInteger = newField;
    cout << "\n-> Integer to Object conversion test (int = Galois):";
    cout << "\n|-> newField object: " << newField; 
    cout << "\n\\--> testInteger value: " << testInteger << "\n"; 
    assert(newField.getValue() == testInteger);
 

    testInteger = 256;
    newField = testInteger;
    cout << "\n-> Object to Integer conversion test (Galois = int):";
    cout << "\n|-> New testInteger value: " << testInteger; 
    cout << "\n\\--> Updated newField object : " << newField << "\n\n"; 
    assert(((FIELD_ORDER + (testInteger % FIELD_ORDER)) % FIELD_ORDER)  == newField.getValue());


    
    cout << "\n\t      >----------------------------<";
    cout << "\n\t      >>                          <<";
    cout << "\n\t      >>     ALL TESTS PASSED     <<";
    cout << "\n\t      >>                          <<";
    cout << "\n\t      >----------------------------<\n\n\n";


    return 0;
}
