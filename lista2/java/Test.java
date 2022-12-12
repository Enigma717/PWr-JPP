//  Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 2 Zadanie 2


import java.util.Formatter;

public class Test {
    private static long randomValue() {
        return (long)(Math.random() * GaloisHC.FIELD_ORDER + 1);
    }


    public static void main(String[] args) {
        long random1 = randomValue();
        long random2 = -(randomValue());
        long expected = 0;
        long real = 0;
        Boolean logicExpected = false;
        Boolean logicReal = false;

        GaloisHC result = new GaloisHC();
        Formatter f = new Formatter();

        
        System.out.print("\n+---------------------------------------------------------+");
        System.out.print("\n|                    GALOIS FIELD TEST                    |");
        System.out.print("\n+---------------------------------------------------------+\n");

        System.out.print("\n\t      >-----------------------------<");
        System.out.print("\n\t      >>                           <<");
        System.out.print("\n\t      >>     GENERATED NUMBERS     <<");
        System.out.print("\n\t      >>                           <<");
        System.out.print(f.format("\n\t      >>   FIRST: %14d", random1));
        System.out.print("   <<");
        f = new Formatter();
        System.out.print(f.format("\n\t      >>  SECOND: %14d", random2));
        System.out.print("   <<");
        System.out.print("\n\t      >>                           <<");
        System.out.print("\n\t      >-----------------------------<");


        
        System.out.print("\n\n====================[ CONSTRUCTOR TEST ]====================\n");

        GaloisHC field1 = new GaloisHC(random1);
        System.out.print("\n-> First random value (positive):");
        System.out.print("\n \\--> Field1: " + field1 + "\n"); 
        assert(field1.getValue() == (random1 % GaloisHC.FIELD_ORDER));

        
        GaloisHC field2 = new GaloisHC(random2);
        System.out.print("\n-> Second random value (negative):");
        System.out.print("\n \\--> Field2: " + field2 + "\n"); 
        assert(field2.getValue() == 
                    ((GaloisHC.FIELD_ORDER +
                    (random2 % GaloisHC.FIELD_ORDER)) %
                    GaloisHC.FIELD_ORDER));
    


        System.out.print("\n\n=====================[ ARITHMETIC TEST ]====================\n");

        System.out.print("\n-> Expected result:");
        System.out.print("\n \\--> (order + ((random1 @ random2) % order)) % order"); 
        System.out.print("\n-> Real result:");
        System.out.print("\n \\--> Field1 @ Field2\n"); 


        expected = (GaloisHC.FIELD_ORDER + 
                        ((random1 + random2) % GaloisHC.FIELD_ORDER)) % 
                        GaloisHC.FIELD_ORDER;
        result = GaloisHC.gAddTwo(field1, field2);
        real = result.getValue();
        System.out.print("\n-> Addition test:");
        System.out.print("\n|--> Expected result: " + expected); 
        System.out.print("\n\\--> Real result: " + real + "\n"); 
        assert(real == expected);


        expected = (GaloisHC.FIELD_ORDER + 
                        ((random1 - random2) % GaloisHC.FIELD_ORDER)) % 
                        GaloisHC.FIELD_ORDER;
        result = GaloisHC.gSubTwo(field1, field2);
        real = result.getValue();
        System.out.print("\n-> Subtraction test:");
        System.out.print("\n|--> Expected result: " + expected); 
        System.out.print("\n\\--> Real result: " + real + "\n"); 
        assert(real == expected);


        expected = (GaloisHC.FIELD_ORDER + 
                        ((random1 * random2) % GaloisHC.FIELD_ORDER)) % 
                        GaloisHC.FIELD_ORDER;
        result = GaloisHC.gMulTwo(field1, field2);
        real = result.getValue();
        System.out.print("\n-> Multiplication test:");
        System.out.print("\n|-> Expected result: " + expected); 
        System.out.print("\n\\--> Real result: " + real + "\n"); 
        assert(real == expected);

        
        try {
            expected = (GaloisHC.FIELD_ORDER + 
                            ((random1 * random1) % GaloisHC.FIELD_ORDER)) % 
                            GaloisHC.FIELD_ORDER;
            result = GaloisHC.gMulTwo(GaloisHC.gDivTwo(field1, field2),
                                    GaloisHC.gMulTwo(field1, field2));
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
            ((GaloisHC.FIELD_ORDER + (random1 % GaloisHC.FIELD_ORDER)) % GaloisHC.FIELD_ORDER) ==
            ((GaloisHC.FIELD_ORDER + (random2 % GaloisHC.FIELD_ORDER)) % GaloisHC.FIELD_ORDER); 
        logicReal = GaloisHC.gEqual(field1, field2);
        System.out.print("\n-> Equality operator test (Field1 == Field2):");
        System.out.print("\n|--> Expected result: " + logicExpected); 
        System.out.print("\n\\--> Real result: " + logicReal + "\n"); 
        assert(logicReal == logicExpected);

        logicExpected = 
            ((GaloisHC.FIELD_ORDER + (random1 % GaloisHC.FIELD_ORDER)) % GaloisHC.FIELD_ORDER) > 
            ((GaloisHC.FIELD_ORDER + (random2 % GaloisHC.FIELD_ORDER)) % GaloisHC.FIELD_ORDER); 
        logicReal = GaloisHC.gGreater(field1, field2);
        System.out.print("\n-> Relational operator test (Field1 > Field2):");
        System.out.print("\n|--> Expected result: " + logicExpected); 
        System.out.print("\n\\--> Real result: " + logicReal + "\n"); 
        assert(logicReal == logicExpected);
    
    
        logicExpected = 
            ((GaloisHC.FIELD_ORDER + (random1 % GaloisHC.FIELD_ORDER)) % GaloisHC.FIELD_ORDER) < 
            ((GaloisHC.FIELD_ORDER + (random2 % GaloisHC.FIELD_ORDER)) % GaloisHC.FIELD_ORDER); 
        logicReal = GaloisHC.gLess(field1, field2);
        System.out.print("\n-> Relational operator test (Field1 < Field2):");
        System.out.print("\n|--> Expected result: " + logicExpected); 
        System.out.print("\n\\--> Real result: " + logicReal + "\n"); 
        assert(logicReal == logicExpected);
    
    

        System.out.print("\n\n=====================[ ASSIGNMENT TEST ]====================\n");

        GaloisHC newField = new GaloisHC();
        long testInteger = GaloisHC.assignToInt(newField);
        System.out.print("\n-> Integer to Object conversion test (int = GaloisHC):");
        System.out.print("\n|-> newField object: " + newField); 
        System.out.print("\n\\--> testInteger value: " + testInteger + "\n"); 
        assert(newField.getValue() == testInteger);
     
    
        testInteger = 256;
        newField.assignToObject(testInteger);
        System.out.print("\n-> Object to Integer conversion test (GaloisHC = int):");
        System.out.print("\n|-> New testInteger value: " + testInteger); 
        System.out.print("\n\\--> Updated newField object : " + newField + "\n\n"); 
        assert(((GaloisHC.FIELD_ORDER + 
                    (testInteger % GaloisHC.FIELD_ORDER)) % 
                    GaloisHC.FIELD_ORDER) == newField.getValue());
    
    
        
        System.out.print("\n\t      >----------------------------<");
        System.out.print("\n\t      >>                          <<");
        System.out.print("\n\t      >>     ALL TESTS PASSED     <<");
        System.out.print("\n\t      >>                          <<");
        System.out.print("\n\t      >----------------------------<\n\n\n");
    }
}