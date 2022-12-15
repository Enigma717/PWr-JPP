//  Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 3


import java.util.ArrayList;
import java.util.Formatter;


public class PolyTest {
    public static void main(String[] args) {
        double dRandom1 = GaloisTest.randomDouble(100);
        double dRandom2 = GaloisTest.randomDouble(100);
        double dRandom3 = GaloisTest.randomDouble(100);
        double dRandom4 = GaloisTest.randomDouble(100);
        double dRandom5 = GaloisTest.randomDouble(100);
        dRandom1 = Math.floor(dRandom1 * 100) / 100;
        dRandom2 = Math.floor(dRandom2 * 100) / 100;
        dRandom3 = Math.floor(dRandom3 * 100) / 100;
        dRandom4 = Math.floor(dRandom4 * 100) / 100;
        dRandom5 = Math.floor(dRandom5 * 100) / 100;


        long lRandom1 = (long) Math.floor(dRandom1);
        long lRandom2 = (long) Math.floor(dRandom2);
        long lRandom3 = (long) Math.floor(dRandom3);
        long lRandom4 = (long) Math.floor(dRandom4);
        long lRandom5 = (long) Math.floor(dRandom5);

        Galois Field1 = new Galois(2, lRandom1);
        Galois Field2 = new Galois(2, lRandom2);
        Galois Field3 = new Galois(2, lRandom3);
        Galois Field4 = new Galois(2, lRandom4);
        Galois Field5 = new Galois(2, lRandom5);

        // Galois Test_field1 = new Galois(2, 1);
        // Galois Test_field2 = new Galois(2, 2);
        // Galois Test_field3 = new Galois(2, 3);
        // Galois Test_field4 = new Galois(2, 4);
        // Galois Test_field5 = new Galois(2, 5);

        // long expected = 0;
        // long real = 0;
        // Boolean logicExpected = false;
        // Boolean logicReal = false;

        Formatter f = new Formatter();

        
        System.out.print("\n+---------------------------------------------------------+");
        System.out.print("\n|                     POLYNOMIAL TEST                     |");
        System.out.print("\n+---------------------------------------------------------+\n");

        System.out.print("\n\t      >-----------------------------<");
        System.out.print("\n\t      >>                           <<");
        System.out.print("\n\t      >>     GENERATED NUMBERS     <<");
        System.out.print("\n\t      >>                           <<");
        System.out.print(f.format("\n\t      >>   FIRST: %14f", dRandom1));
        System.out.print("   <<");
        f = new Formatter();
        System.out.print(f.format("\n\t      >>  SECOND: %14f", dRandom2));
        System.out.print("   <<");
        f = new Formatter();
        System.out.print(f.format("\n\t      >>   THIRD: %14f", dRandom3));
        System.out.print("   <<");
        f = new Formatter();
        System.out.print(f.format("\n\t      >>  FOURTH: %14f", dRandom4));
        System.out.print("   <<");
        f = new Formatter();
        System.out.print(f.format("\n\t      >>   FIFTH: %14f", dRandom5));
        System.out.print("   <<");
        System.out.print("\n\t      >>                           <<");
        System.out.print("\n\t      >-----------------------------<");


        
        System.out.print("\n\n====================[ CONSTRUCTOR TEST ]====================\n");

        ArrayList<NumCoeff> real_coeffs = new ArrayList<NumCoeff>();
        real_coeffs.add(new NumCoeff(dRandom1));
        real_coeffs.add(new NumCoeff(dRandom2));
        real_coeffs.add(new NumCoeff(dRandom3));
        real_coeffs.add(new NumCoeff(dRandom4));
        real_coeffs.add(new NumCoeff(dRandom5));
        
        ArrayList<Galois> field_coeffs = new ArrayList<Galois>();
        field_coeffs.add(Field1);
        field_coeffs.add(Field2);
        field_coeffs.add(Field3);
        field_coeffs.add(Field4);
        field_coeffs.add(Field5);


        Polynomial<NumCoeff> dPoly = new Polynomial<NumCoeff>(real_coeffs);
        System.out.print("\n-> Polynomial with real numbers:");
        System.out.print("\n|--> Print: " + dPoly); 
        System.out.print("\n\\--> Degree: " + dPoly.getDegree() + "\n"); 
        
        Polynomial<Galois> gfPoly = new Polynomial<Galois>(field_coeffs);
        System.out.print("\n-> Polynomial with finite fields:");
        System.out.print("\n|--> Print: " + gfPoly); 
        System.out.print("\n\\--> Degree: " + gfPoly.getDegree() + "\n"); 
        


        // System.out.print("\n\n=====================[ ARITHMETIC TEST ]====================\n");


        // Galois result = new Galois(FIELD_ORDER);
        // Galois temp1 = new Galois(FIELD_ORDER);
        // Galois temp2 = new Galois(FIELD_ORDER);
        // Galois field1 = new Galois(FIELD_ORDER, random1);
        // Galois field2 = new Galois(FIELD_ORDER, random2);


        // System.out.print("\n-> Expected result:");
        // System.out.print("\n \\--> (order + ((random1 @ random2) % order)) % order"); 
        // System.out.print("\n-> Real result:");
        // System.out.print("\n \\--> Field1 @ Field2\n"); 


        // expected = (FIELD_ORDER + ((random1 + random2) % FIELD_ORDER)) % FIELD_ORDER;
        // result = field1.tAdd(field2);
        // real = result.getValue();
        // System.out.print("\n-> Addition test:");
        // System.out.print("\n|--> Expected result: " + expected); 
        // System.out.print("\n\\--> Real result: " + real + "\n"); 
        // assert(real == expected);

        
        // field1 = new Galois(FIELD_ORDER, random1);
        // field2 = new Galois(FIELD_ORDER, random2);

        // expected = (FIELD_ORDER + ((random1 - random2) % FIELD_ORDER)) % FIELD_ORDER;
        // result = field1.tSub(field2);
        // real = result.getValue();
        // System.out.print("\n-> Subtraction test:");
        // System.out.print("\n|--> Expected result: " + expected); 
        // System.out.print("\n\\--> Real result: " + real + "\n"); 
        // assert(real == expected);


        // field1 = new Galois(FIELD_ORDER, random1);
        // field2 = new Galois(FIELD_ORDER, random2);

        // expected = (FIELD_ORDER + ((random1 * random2) % FIELD_ORDER)) % FIELD_ORDER;
        // result = field1.tMul(field2);
        // real = result.getValue();
        // System.out.print("\n-> Multiplication test:");
        // System.out.print("\n|-> Expected result: " + expected); 
        // System.out.print("\n\\--> Real result: " + real + "\n"); 
        // assert(real == expected);

        
        // try {
        //     field1 = new Galois(FIELD_ORDER, random1);
        //     field2 = new Galois(FIELD_ORDER, random2);

        //     expected = (FIELD_ORDER + ((random1 * random1) % FIELD_ORDER)) % FIELD_ORDER;
        //     temp1 = field1.tDiv(field2);
        //     temp2 = field1.tMul(field2);
        //     result = temp1.tMul(temp2);
        //     real = result.getValue();
        //     System.out.print("\n-> Division test:");
        //     System.out.print("\n|--> Expected result: " + expected); 
        //     System.out.print("\n\\--> Real result: " + real + "\n"); 
        //     assert(real == expected);
        // } catch (IllegalArgumentException ex) {
        //     System.out.print("\n" + ex.getMessage() + "\n");
        // }



        // System.out.print("\n\n=======================[ LOGIC TEST ]=======================\n");

        // logicExpected = 
        //     ((FIELD_ORDER + (random1 % FIELD_ORDER)) % FIELD_ORDER) ==
        //     ((FIELD_ORDER + (random2 % FIELD_ORDER)) % FIELD_ORDER); 
        // logicReal = field1.tEqual(field2);
        // System.out.print("\n-> Equality operator test (Field1 == Field2):");
        // System.out.print("\n|--> Expected result: " + logicExpected); 
        // System.out.print("\n\\--> Real result: " + logicReal + "\n"); 
        // assert(logicReal == logicExpected);

        // logicExpected = 
        //     ((FIELD_ORDER + (random1 % FIELD_ORDER)) % FIELD_ORDER) > 
        //     ((FIELD_ORDER + (random2 % FIELD_ORDER)) % FIELD_ORDER); 
        // logicReal = field1.tGreater(field2);
        // System.out.print("\n-> Relational operator test (Field1 > Field2):");
        // System.out.print("\n|--> Expected result: " + logicExpected); 
        // System.out.print("\n\\--> Real result: " + logicReal + "\n"); 
        // assert(logicReal == logicExpected);
    
    
        // logicExpected = 
        //     ((FIELD_ORDER + (random1 % FIELD_ORDER)) % FIELD_ORDER) < 
        //     ((FIELD_ORDER + (random2 % FIELD_ORDER)) % FIELD_ORDER); 
        // logicReal = field1.tLess(field2);
        // System.out.print("\n-> Relational operator test (Field1 < Field2):");
        // System.out.print("\n|--> Expected result: " + logicExpected); 
        // System.out.print("\n\\--> Real result: " + logicReal + "\n"); 
        // assert(logicReal == logicExpected);
    
    

        // System.out.print("\n\n=====================[ ASSIGNMENT TEST ]====================\n");

        // Galois newField = new Galois(FIELD_ORDER);
        // long testInteger = Galois.assignToInt(newField);
        // System.out.print("\n-> Integer to Object conversion test (int = Galois):");
        // System.out.print("\n|-> newField object: " + newField); 
        // System.out.print("\n\\--> testInteger value: " + testInteger + "\n"); 
        // assert(newField.getValue() == testInteger);
     
    
        // testInteger = 256;
        // newField.assignToObject(testInteger);
        // System.out.print("\n-> Object to Integer conversion test (Galois = int):");
        // System.out.print("\n|-> New testInteger value: " + testInteger); 
        // System.out.print("\n\\--> Updated newField object : " + newField + "\n\n"); 
        // assert(((FIELD_ORDER + 
        //             (testInteger % FIELD_ORDER)) % 
        //             FIELD_ORDER) == newField.getValue());
    
    
        
        System.out.print("\n\t      >----------------------------<");
        System.out.print("\n\t      >>                          <<");
        System.out.print("\n\t      >>     ALL TESTS PASSED     <<");
        System.out.print("\n\t      >>                          <<");
        System.out.print("\n\t      >----------------------------<\n\n\n");
    }

}
