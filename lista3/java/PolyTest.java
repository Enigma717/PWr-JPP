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


        final long lRandom1 = (long) Math.floor(dRandom1);
        final long lRandom2 = (long) Math.floor(dRandom2);
        final long lRandom3 = (long) Math.floor(dRandom3);
        final long lRandom4 = (long) Math.floor(dRandom4);
        final long lRandom5 = (long) Math.floor(dRandom5);

        Galois Field1 = new Galois(2, lRandom1);
        Galois Field2 = new Galois(2, lRandom2);
        Galois Field3 = new Galois(2, lRandom3);
        Galois Field4 = new Galois(2, lRandom4);
        Galois Field5 = new Galois(2, lRandom5);

        Galois testField1 = new Galois(2, 1);
        Galois testField2 = new Galois(2, 2);
        Galois testField3 = new Galois(2, 3);
        Galois testField4 = new Galois(2, 4);
        Galois testField5 = new Galois(2, 5);

        Polynomial<NumCoeff> dResultPoly = new Polynomial<NumCoeff>(new NumCoeff(0.0));
        Polynomial<Galois> gfResultPoly = new Polynomial<Galois>(new Galois(2, 0));
        NumCoeff dEvalResult = new NumCoeff(0.0);
        Galois gfEvalResult = new Galois(2, 0);
        Boolean dLogicResult = false;
        Boolean gfLogicResult = false;

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

        ArrayList<NumCoeff> realCoeffs = new ArrayList<NumCoeff>();
        realCoeffs.add(new NumCoeff(dRandom1));
        realCoeffs.add(new NumCoeff(dRandom2));
        realCoeffs.add(new NumCoeff(dRandom3));
        realCoeffs.add(new NumCoeff(dRandom4));
        realCoeffs.add(new NumCoeff(dRandom5));
        
        ArrayList<Galois> fieldCoeffs = new ArrayList<Galois>();
        fieldCoeffs.add(Field1);
        fieldCoeffs.add(Field2);
        fieldCoeffs.add(Field3);
        fieldCoeffs.add(Field4);
        fieldCoeffs.add(Field5);


        Polynomial<NumCoeff> dPoly = new Polynomial<NumCoeff>(realCoeffs);
        System.out.print("\n-> Polynomial with real numbers:");
        System.out.print("\n|--> Print: " + dPoly); 
        System.out.print("\n\\--> Degree: " + dPoly.getDegree() + "\n"); 
        
        Polynomial<Galois> gfPoly = new Polynomial<Galois>(fieldCoeffs);
        System.out.print("\n-> Polynomial with finite fields:");
        System.out.print("\n|--> Print: " + gfPoly); 
        System.out.print("\n\\--> Degree: " + gfPoly.getDegree() + "\n"); 
        


        System.out.print("\n\n=====================[ ARITHMETIC TEST ]====================\n");


        ArrayList<NumCoeff> dTestValues = new ArrayList<NumCoeff>();
        dTestValues.add(new NumCoeff(1.0));
        dTestValues.add(new NumCoeff(2.0));
        dTestValues.add(new NumCoeff(3.0));
        dTestValues.add(new NumCoeff(4.0));
        dTestValues.add(new NumCoeff(5.0));
        
        ArrayList<Galois> gfTestValues = new ArrayList<Galois>();
        gfTestValues.add(testField1);
        gfTestValues.add(testField2);
        gfTestValues.add(testField3);
        gfTestValues.add(testField4);
        gfTestValues.add(testField5);

        Polynomial<NumCoeff> dTestPoly = new Polynomial<NumCoeff>(dTestValues);
        Polynomial<Galois> gfTestPoly = new Polynomial<Galois>(gfTestValues);


        System.out.print("\n>> Test real numbers polynomial: " + dTestPoly);
        System.out.print("\n>> Test finite field polynomial: " + gfTestPoly);

        //////////////////////////////////
        System.out.print("\n\n-----------------------\n");
        //////////////////////////////////

        System.out.print("\n-> Addition test:");

        dResultPoly = dPoly.pAdd(dTestPoly);
        gfResultPoly = gfPoly.pAdd(gfTestPoly);

        System.out.print("\n|--> Real numbers result: " + dResultPoly); 
        System.out.print("\n|--> Finite field result: " + gfResultPoly); 
        assert(dResultPoly.getDegree() == Math.max(dPoly.getDegree(), dTestPoly.getDegree()));
        assert(gfResultPoly.getDegree() <= Math.max(gfPoly.getDegree(), gfTestPoly.getDegree()));

        //////////////////////////////////
        System.out.print("\n\n-----------------------\n");
        //////////////////////////////////

        System.out.print("\n-> Subtraction test:");

        dResultPoly = dPoly.pSub(dTestPoly);
        gfResultPoly = gfPoly.pSub(gfTestPoly);
        
        System.out.print("\n|--> Real numbers result: " + dResultPoly); 
        System.out.print("\n|--> Finite field result: " + gfResultPoly); 
        assert(dResultPoly.getDegree() == Math.max(dPoly.getDegree(), dTestPoly.getDegree()));
        assert(gfResultPoly.getDegree() <= Math.max(gfPoly.getDegree(), gfTestPoly.getDegree()));
        
        //////////////////////////////////
        System.out.print("\n\n-----------------------\n");
        //////////////////////////////////

        System.out.print("\n-> Multiplication test:");

        dResultPoly = dPoly.pMul(dTestPoly);
        gfResultPoly = gfPoly.pMul(gfTestPoly);
        
        System.out.print("\n|--> Real numbers result: " + dResultPoly); 
        System.out.print("\n|--> Finite field result: " + gfResultPoly); 
        assert(dResultPoly.getDegree() == (dPoly.getDegree() + dTestPoly.getDegree()));
        assert(gfResultPoly.getDegree() <= (gfPoly.getDegree() + gfTestPoly.getDegree()));

        //////////////////////////////////
        System.out.print("\n\n-----------------------\n");
        //////////////////////////////////

        try {
            System.out.print("\n-> Division test:");

            dResultPoly = dPoly.pDiv(dTestPoly);
            gfResultPoly = gfPoly.pDiv(gfTestPoly);
            
            System.out.print("\n|--> Real numbers result: " + dResultPoly); 
            System.out.print("\n|--> Finite field result: " + gfResultPoly); 
        } catch (IllegalArgumentException ex) {
            System.out.print("\n" + ex.getMessage() + "\n");
        }

        //////////////////////////////////
        System.out.print("\n\n-----------------------\n");
        //////////////////////////////////

        try {
            System.out.print("\n-> Modulo test:");

            dResultPoly = dPoly.pMod(dTestPoly);
            gfResultPoly = gfPoly.pMod(gfTestPoly);
            
            System.out.print("\n|--> Real numbers result: " + dResultPoly); 
            System.out.print("\n|--> Finite field result: " + gfResultPoly); 
        } catch (IllegalArgumentException ex) {
            System.out.print("\n" + ex.getMessage() + "\n");
        }



        System.out.print("\n\n=======================[ LOGIC TEST ]=======================\n");

        System.out.print("\n>> First real numbers polynomial: " + dPoly); 
        System.out.print("\n>> Second real numbers polynomial: " + dTestPoly);
        System.out.print("\n>> First finite field polynomial: " + gfPoly); 
        System.out.print("\n>> Second finite field polynomial: " + gfTestPoly);
        
        System.out.print("\n\n-----------------------\n");

        System.out.print("\n-> Equality operator test (Polynomial1 == Polynomial1):");
        
        dLogicResult = (dPoly.pEqual(dPoly));
        gfLogicResult = (gfPoly.pEqual(gfPoly));
        
        System.out.print("\n|--> Real numbers result: " + dLogicResult); 
        System.out.print("\n|--> Finite field result: " + gfLogicResult);
        assert(dLogicResult == true);
        assert(gfLogicResult == true);

        System.out.print("\n\n-----------------------\n");

        System.out.print("\n-> Inequality operator test (Polynomial1 != Polynomial2):");
        
        dLogicResult = (dPoly.pNotEqual(dTestPoly));
        gfLogicResult = (gfPoly.pNotEqual(gfTestPoly));
        
        System.out.print("\n|--> Real numbers result: " + dLogicResult); 
        System.out.print("\n|--> Finite field result: " + gfLogicResult);
        assert(dLogicResult == true);
        assert(gfLogicResult == true);
    
    

        System.out.print("\n\n=======================[ EVALUATION TEST ]=======================\n");

        System.out.print("\n>> Real numbers polynomial: " + dPoly); 
        System.out.print("\n>> Finite field polynomial: " + gfPoly); 

        System.out.print("\n\n-----------------------\n");

        System.out.print("\n-> Evaluating at point x = 5:");
        
        dEvalResult = dPoly.evaluate(new NumCoeff(5.0));
        gfEvalResult = gfPoly.evaluate(new Galois(2, 5));
    
        System.out.print("\n|--> Real numbers result: " + dEvalResult); 
        System.out.print("\n|--> Finite field result: " + gfEvalResult + "\n\n\n");

        
        System.out.print("\n\t      >----------------------------<");
        System.out.print("\n\t      >>                          <<");
        System.out.print("\n\t      >>     ALL TESTS PASSED     <<");
        System.out.print("\n\t      >>                          <<");
        System.out.print("\n\t      >----------------------------<\n\n\n");
    }

}
