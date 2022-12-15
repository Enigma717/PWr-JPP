//  Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 1


import java.util.Formatter;


public class GaloisTest {
    public static final long FIELD_ORDER = 1234567891;

    
    public static long randomLong(long range) {
        return (long)(Math.random() * range);
    }

    public static double randomDouble(long range) {
        return (Math.random() * range);
    }


    public static void main(String[] args) {
        long random1 = randomLong(FIELD_ORDER);
        long random2 = -(randomLong(FIELD_ORDER));
        long expected = 0;
        long real = 0;
        Boolean logicExpected = false;
        Boolean logicReal = false;

        Formatter f = new Formatter();

        
        System.out.print("\n+---------------------------------------------------------+");
        System.out.print("\n|                    GALOIS FIELD TEST                    |");
        System.out.print("\n+---------------------------------------------------------+\n");

        System.out.print("\n\t      >-----------------------------<");
        System.out.print("\n\t      >>                           <<");
        System.out.print("\n\t      >>     GENERATED NUMBERS     <<");
        System.out.print("\n\t      >>                           <<");
        System.out.print(f.format("\n\t      >>   FIRST: %,14d", random1));
        System.out.print("   <<");
        f = new Formatter();
        System.out.print(f.format("\n\t      >>  SECOND: %,14d", random2));
        System.out.print("   <<");
        System.out.print("\n\t      >>                           <<");
        System.out.print("\n\t      >-----------------------------<");


        
        System.out.print("\n\n====================[ CONSTRUCTOR TEST ]====================\n");

        try {
            Galois test1 = new Galois(FIELD_ORDER, random1);
            System.out.print("\n-> First random value (positive):");
            System.out.print("\n \\--> Test1: " + test1 + "\n"); 
            assert(test1.getValue() == (random1 % FIELD_ORDER));

            Galois test2 = new Galois(FIELD_ORDER, random2);
            System.out.print("\n-> Second random value (negative):");
            System.out.print("\n \\--> Test2: " + test2 + "\n"); 
            assert(test2.getValue() == ((FIELD_ORDER + (random2 % FIELD_ORDER)) % FIELD_ORDER));

        } catch(IllegalArgumentException ex) {
            System.out.print("\n" + ex.getMessage() + "\n");
        }
    


        System.out.print("\n\n=====================[ ARITHMETIC TEST ]====================\n");


        Galois result = new Galois(FIELD_ORDER);
        Galois temp1 = new Galois(FIELD_ORDER);
        Galois temp2 = new Galois(FIELD_ORDER);
        Galois field1 = new Galois(FIELD_ORDER, random1);
        Galois field2 = new Galois(FIELD_ORDER, random2);


        System.out.print("\n-> Expected result:");
        System.out.print("\n \\--> (order + ((random1 @ random2) % order)) % order"); 
        System.out.print("\n-> Real result:");
        System.out.print("\n \\--> Field1 @ Field2\n"); 


        expected = (FIELD_ORDER + ((random1 + random2) % FIELD_ORDER)) % FIELD_ORDER;
        result = field1.tAdd(field2);
        real = result.getValue();
        System.out.print("\n-> Addition test:");
        System.out.print("\n|--> Expected result: " + expected); 
        System.out.print("\n\\--> Real result: " + real + "\n"); 
        assert(real == expected);


        expected = (FIELD_ORDER + ((random1 - random2) % FIELD_ORDER)) % FIELD_ORDER;
        result = field1.tSub(field2);
        real = result.getValue();
        System.out.print("\n-> Subtraction test:");
        System.out.print("\n|--> Expected result: " + expected); 
        System.out.print("\n\\--> Real result: " + real + "\n"); 
        assert(real == expected);


        expected = (FIELD_ORDER + ((random1 * random2) % FIELD_ORDER)) % FIELD_ORDER;
        result = field1.tMul(field2);
        real = result.getValue();
        System.out.print("\n-> Multiplication test:");
        System.out.print("\n|-> Expected result: " + expected); 
        System.out.print("\n\\--> Real result: " + real + "\n"); 
        assert(real == expected);

        
        try {
            expected = (FIELD_ORDER + ((random1 * random1) % FIELD_ORDER)) % FIELD_ORDER;
            temp1 = field1.tDiv(field2);
            temp2 = field1.tMul(field2);
            result = temp1.tMul(temp2);
            real = result.getValue();
            System.out.print("\n-> Division test:");
            System.out.print("\n|--> Expected result: " + expected); 
            System.out.print("\n\\--> Real result: " + real + "\n"); 
            assert(real == expected);
        } catch (IllegalArgumentException ex) {
            System.out.print("\n" + ex.getMessage() + "\n");
        }



        System.out.print("\n\n=======================[ LOGIC TEST ]=======================\n");

        logicExpected = 
            ((FIELD_ORDER + (random1 % FIELD_ORDER)) % FIELD_ORDER) ==
            ((FIELD_ORDER + (random2 % FIELD_ORDER)) % FIELD_ORDER); 
        logicReal = field1.tEqual(field2);
        System.out.print("\n-> Equality operator test (Field1 == Field2):");
        System.out.print("\n|--> Expected result: " + logicExpected); 
        System.out.print("\n\\--> Real result: " + logicReal + "\n"); 
        assert(logicReal == logicExpected);

        logicExpected = 
            ((FIELD_ORDER + (random1 % FIELD_ORDER)) % FIELD_ORDER) > 
            ((FIELD_ORDER + (random2 % FIELD_ORDER)) % FIELD_ORDER); 
        logicReal = field1.tGreater(field2);
        System.out.print("\n-> Relational operator test (Field1 > Field2):");
        System.out.print("\n|--> Expected result: " + logicExpected); 
        System.out.print("\n\\--> Real result: " + logicReal + "\n"); 
        assert(logicReal == logicExpected);
    
    
        logicExpected = 
            ((FIELD_ORDER + (random1 % FIELD_ORDER)) % FIELD_ORDER) < 
            ((FIELD_ORDER + (random2 % FIELD_ORDER)) % FIELD_ORDER); 
        logicReal = field1.tLess(field2);
        System.out.print("\n-> Relational operator test (Field1 < Field2):");
        System.out.print("\n|--> Expected result: " + logicExpected); 
        System.out.print("\n\\--> Real result: " + logicReal + "\n"); 
        assert(logicReal == logicExpected);
    
    

        System.out.print("\n\n=====================[ ASSIGNMENT TEST ]====================\n");

        Galois newField = new Galois(FIELD_ORDER);
        long testInteger = Galois.assignToInt(newField);
        System.out.print("\n-> Integer to Object conversion test (int = Galois):");
        System.out.print("\n|-> newField object: " + newField); 
        System.out.print("\n\\--> testInteger value: " + testInteger + "\n"); 
        assert(newField.getValue() == testInteger);
     
    
        testInteger = 256;
        newField.assignToObject(testInteger);
        System.out.print("\n-> Object to Integer conversion test (Galois = int):");
        System.out.print("\n|-> New testInteger value: " + testInteger); 
        System.out.print("\n\\--> Updated newField object : " + newField + "\n\n"); 
        assert(((FIELD_ORDER + 
                    (testInteger % FIELD_ORDER)) % 
                    FIELD_ORDER) == newField.getValue());
    
    
        
        System.out.print("\n\t      >----------------------------<");
        System.out.print("\n\t      >>                          <<");
        System.out.print("\n\t      >>     ALL TESTS PASSED     <<");
        System.out.print("\n\t      >>                          <<");
        System.out.print("\n\t      >----------------------------<\n\n\n");
    }
}