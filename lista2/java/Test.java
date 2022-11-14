//  Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 2 Zadanie 2


import java.util.Formatter;


public class Test {
    
    private static long randomValue() {
        return (long)(Math.random() * Galois.FIELD_ORDER + 1);
    }


    public static void main(String[] args) {
        long random1 = randomValue();
        long random2 = -(randomValue());
        long expected = 0;
        long real = 0;
        Boolean logicExpected = false;
        Boolean logicReal = false;

        Galois result = new Galois();
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

        Galois field1 = new Galois(random1);
        System.out.print("\n-> First random value (positive):");
        System.out.print("\n \\--> Field1: " + field1 + "\n"); 
        assert(field1.getValue() == (random1 % Galois.FIELD_ORDER));

        
        Galois field2 = new Galois(random2);
        System.out.print("\n-> Second random value (negative):");
        System.out.print("\n \\--> Field2: " + field2 + "\n"); 
        assert(field2.getValue() == 
                    ((Galois.FIELD_ORDER +
                    (random2 % Galois.FIELD_ORDER)) %
                    Galois.FIELD_ORDER));
    


        System.out.print("\n\n=====================[ ARITHMETIC TEST ]====================\n");

        System.out.print("\n-> Expected result:");
        System.out.print("\n \\--> (order + ((random1 @ random2) % order)) % order"); 
        System.out.print("\n-> Real result:");
        System.out.print("\n \\--> Field1 @ Field2\n"); 


        expected = (Galois.FIELD_ORDER + 
                        ((random1 + random2) % Galois.FIELD_ORDER)) % 
                        Galois.FIELD_ORDER;
        result = Galois.gAddTwo(field1, field2);
        real = result.getValue();
        System.out.print("\n-> Addition test:");
        System.out.print("\n|--> Expected result: " + expected); 
        System.out.print("\n\\--> Real result: " + real + "\n"); 
        assert(real == expected);


        expected = (Galois.FIELD_ORDER + 
                        ((random1 - random2) % Galois.FIELD_ORDER)) % 
                        Galois.FIELD_ORDER;
        result = Galois.gSubTwo(field1, field2);
        real = result.getValue();
        System.out.print("\n-> Subtraction test:");
        System.out.print("\n|--> Expected result: " + expected); 
        System.out.print("\n\\--> Real result: " + real + "\n"); 
        assert(real == expected);


        expected = (Galois.FIELD_ORDER + 
                        ((random1 * random2) % Galois.FIELD_ORDER)) % 
                        Galois.FIELD_ORDER;
        result = Galois.gMulTwo(field1, field2);
        real = result.getValue();
        System.out.print("\n-> Multiplication test:");
        System.out.print("\n|-> Expected result: " + expected); 
        System.out.print("\n\\--> Real result: " + real + "\n"); 
        assert(real == expected);

        
        try {
            expected = (Galois.FIELD_ORDER + 
                            ((random1 * random1) % Galois.FIELD_ORDER)) % 
                            Galois.FIELD_ORDER;
            result = Galois.gMulTwo(Galois.gDivTwo(field1, field2),
                                    Galois.gMulTwo(field1, field2));
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
            ((Galois.FIELD_ORDER + (random1 % Galois.FIELD_ORDER)) % Galois.FIELD_ORDER) ==
            ((Galois.FIELD_ORDER + (random2 % Galois.FIELD_ORDER)) % Galois.FIELD_ORDER); 
        logicReal = Galois.gEqual(field1, field2);
        System.out.print("\n-> Equality operator test (Field1 == Field2):");
        System.out.print("\n|--> Expected result: " + logicExpected); 
        System.out.print("\n\\--> Real result: " + logicReal + "\n"); 
        assert(logicReal == logicExpected);

        logicExpected = 
            ((Galois.FIELD_ORDER + (random1 % Galois.FIELD_ORDER)) % Galois.FIELD_ORDER) > 
            ((Galois.FIELD_ORDER + (random2 % Galois.FIELD_ORDER)) % Galois.FIELD_ORDER); 
        logicReal = Galois.gGreater(field1, field2);
        System.out.print("\n-> Relational operator test (Field1 > Field2):");
        System.out.print("\n|--> Expected result: " + logicExpected); 
        System.out.print("\n\\--> Real result: " + logicReal + "\n"); 
        assert(logicReal == logicExpected);
    
    
        logicExpected = 
            ((Galois.FIELD_ORDER + (random1 % Galois.FIELD_ORDER)) % Galois.FIELD_ORDER) < 
            ((Galois.FIELD_ORDER + (random2 % Galois.FIELD_ORDER)) % Galois.FIELD_ORDER); 
        logicReal = Galois.gLess(field1, field2);
        System.out.print("\n-> Relational operator test (Field1 < Field2):");
        System.out.print("\n|--> Expected result: " + logicExpected); 
        System.out.print("\n\\--> Real result: " + logicReal + "\n"); 
        assert(logicReal == logicExpected);
    
    

        System.out.print("\n\n=====================[ ASSIGNMENT TEST ]====================\n");

        Galois newField = new Galois();
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
        assert(((Galois.FIELD_ORDER + 
                    (testInteger % Galois.FIELD_ORDER)) % 
                    Galois.FIELD_ORDER) == newField.getValue());
    
    
        
        System.out.print("\n\t      >----------------------------<");
        System.out.print("\n\t      >>                          <<");
        System.out.print("\n\t      >>     ALL TESTS PASSED     <<");
        System.out.print("\n\t      >>                          <<");
        System.out.print("\n\t      >----------------------------<\n\n\n");
    }
}
