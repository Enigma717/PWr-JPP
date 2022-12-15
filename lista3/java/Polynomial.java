//  Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 3


import java.util.ArrayList;


public class Polynomial<T extends CoeffType<T>> {

    /////////////
    // Fields  //
    /////////////

    private ArrayList<T> coefficients;
    private int degree;


    //////////////////
    // Constructors //
    //////////////////

    Polynomial(final T value) {
        coefficients = new ArrayList<T>();
        coefficients.add(value);
        degree = 0;
    }
    
    Polynomial(final ArrayList<T> coeffs) {
        coefficients = coeffs;
        degree = coeffs.size() - 1;

        this.clearCoeffsZeros();
    }


    /////////////////////////////
    // Miscellaneous functions //
    /////////////////////////////

    private void clearCoeffsZeros()
    {
        for (int i = coefficients.size() - 1; i > degree - 1; i--)
        {
            if (i > 0 && coefficients.get(i).tIsZero())
            {
                coefficients.remove(i);
                degree--;
            }
        }
    }

    public Tuple<Polynomial<T>, Polynomial<T>> divide(final Polynomial<T> divisor) {
        Polynomial<T> divResult = new Polynomial<T>(this.coefficients.get(0).tZero());
        Polynomial<T> remResult = new Polynomial<T>(this.coefficients);

        while (!(remResult.coefficients.size() == 1 && remResult.coefficients.get(0).tIsZero()) &&
               remResult.degree >= divisor.degree) {
            
            T coefficient = remResult.coefficients.get(remResult.degree).tDiv(divisor.coefficients.get(divisor.degree));
            Polynomial<T> temp = new Polynomial<T>(coefficient);

            for (int i = 0; i < remResult.degree - divisor.degree; i++) {
                temp.coefficients.add(0, temp.coefficients.get(0).tZero());
            }

            divResult = divResult.pAdd(temp);
            remResult = remResult.pSub(divisor.pMul(temp));
        }

        Tuple<Polynomial<T>, Polynomial<T>> resultTuple = new Tuple<Polynomial<T>, Polynomial<T>>(divResult, remResult);

        return resultTuple;
    }


    /////////////
    // Getters //
    /////////////

    public ArrayList<T> getCoefficients() {
        return this.coefficients;
    }

    public int getDegree() {
        return this.degree;
    }
    
    //////////////////////////////////
    // Arithmetic operators methods //
    //////////////////////////////////

    public Polynomial<T> pAdd(final Polynomial<T> poly) {
        int p1Size = this.coefficients.size();
        int p2Size = poly.coefficients.size();
        int resultSize = Math.max(p1Size, p2Size);
        int index = 0;

        T temp;
        ArrayList<T> resultCoeffs = new ArrayList<T>();

        for (int i = 0; i < resultSize; i++) {
            temp = this.coefficients.get(i).tAdd(poly.coefficients.get(i));
            resultCoeffs.add(temp);
            index++;
        }

        while (index < p1Size) {
            resultCoeffs.add(this.coefficients.get(index));
        }
        while (index < p2Size) {
            resultCoeffs.add(poly.coefficients.get(index));
        }

        Polynomial<T> result = new Polynomial<T>(resultCoeffs);

        return result;
    }

    public Polynomial<T> pSub(final Polynomial<T> poly) {
        int p1Size = this.coefficients.size();
        int p2Size = poly.coefficients.size();
        int resultSize = Math.max(p1Size, p2Size);
        int index = 0;

        T temp;
        ArrayList<T> resultCoeffs = new ArrayList<T>();

        for (int i = 0; i < resultSize; i++) {
            temp = this.coefficients.get(i).tSub(poly.coefficients.get(i));
            resultCoeffs.add(temp);
            index++;
        }

        while (index < p1Size) {
            resultCoeffs.add(this.coefficients.get(index));
        }
        while (index < p2Size) {
            resultCoeffs.add(poly.coefficients.get(index));
        }

        Polynomial<T> result = new Polynomial<T>(resultCoeffs);

        return result;
    }

    public Polynomial<T> pMul(final Polynomial<T> poly) {
        int p1Size = this.coefficients.size();
        int p2Size = poly.coefficients.size();
        int resultSize = p1Size + p2Size - 1;
        
        T temp;
        ArrayList<T> resultCoeffs = new ArrayList<T>();

        for (int i = 0; i < resultSize; i++) {
            resultCoeffs.add(this.coefficients.get(i).tZero());
        }
        
        for (int i = 0; i < p1Size; i++) {
            for (int j = 0; j < p2Size; j++) {
                temp = resultCoeffs.get(i+j).tAdd(this.coefficients.get(i).tMul(poly.coefficients.get(j)));
                resultCoeffs.set(i+j, temp);
            }
        }


        Polynomial<T> result = new Polynomial<T>(resultCoeffs);

        return result;
    }

    public Polynomial<T> pDiv(final Polynomial<T> poly) {
        return this.divide(poly).getFirst();
    }

    public Polynomial<T> pMod(final Polynomial<T> poly) {
        return this.divide(poly).getSecond();
    }

    ///////////////////////////////
    // Logical operators methods //
    ///////////////////////////////

    public Boolean pEqual(final Polynomial<T> poly) {
        if (this.degree != poly.degree) {
            return false;
        }

        for (int i = this.degree; i >= 0; i--) {
            if (this.coefficients.get(i).tNotEqual(poly.coefficients.get(i))) {
                return false;
            }
        }
        
        return true;     
    }

    public Boolean pNotEqual(final Polynomial<T> poly) {
        if (this.degree != poly.degree) {
            return true;
        }

        for (int i = this.degree; i >= 0; i--) {
            if (this.coefficients.get(i).tEqual(poly.coefficients.get(i))) {
                return true;
            }
        }
        
        return false;     
    }

    public Boolean pGreaterEq(final Polynomial<T> poly) {
        if (this.degree > poly.degree) {
            return true;
        }

        if (this.degree < poly.degree) {
            return false;
        }


        for (int i = this.degree; i >= 0; i--) {
            if (this.coefficients.get(i).tGreater(poly.coefficients.get(i))) {
                return true;
            } else if (this.coefficients.get(i).tLess(poly.coefficients.get(i))) {
                return false;
            }
        }
        
        return true;    
    }
    
    public Boolean pLessEq(final Polynomial<T> poly) {
        if (this.degree > poly.degree) {
            return true;
        }

        if (this.degree < poly.degree) {
            return false;
        }


        for (int i = this.degree; i >= 0; i--) {
            if (this.coefficients.get(i).tLess(poly.coefficients.get(i))) {
                return true;
            } else if (this.coefficients.get(i).tGreater(poly.coefficients.get(i))) {
                return false;
            }
        }
        
        return true;    
    }
    
    public Boolean pGreater(final Polynomial<T> poly) {
        if (this.degree > poly.degree) {
            return true;
        }

        if (this.degree < poly.degree) {
            return false;
        }


        for (int i = this.degree; i >= 0; i--) {
            if (this.coefficients.get(i).tGreater(poly.coefficients.get(i))) {
                return true;
            } else if (this.coefficients.get(i).tLess(poly.coefficients.get(i))) {
                return false;
            }
        }
        
        return false;    
    }

    public Boolean pLess(final Polynomial<T> poly) {
        if (this.degree > poly.degree) {
            return true;
        }

        if (this.degree < poly.degree) {
            return false;
        }


        for (int i = this.degree; i >= 0; i--) {
            if (this.coefficients.get(i).tLess(poly.coefficients.get(i))) {
                return true;
            } else if (this.coefficients.get(i).tGreater(poly.coefficients.get(i))) {
                return false;
            }
        }
        
        return false;     
    }
    

    //////////////////////////
    // Evaluation operators //
    //////////////////////////

    public T evaluate(final T value) {
        T result = this.coefficients.get(this.degree);
        T temp;
        
        for (int i = this.degree - 1; i >= 0; i--) {
            temp = result.tMul(value);
            result = temp.tAdd(coefficients.get(i));
        }

        return result;
    }

    public T getCoeff(final int index) {
        return this.coefficients.get(index);
    }


    /////////////////////
    // toString method //
    /////////////////////

    public String toString() {
        String result = "";
        String plus = "";
        String space = "";
        
        if (this.coefficients.size() == 1 && this.coefficients.get(0).tIsZero()) {
            return "0";
        }

        for(int i = this.degree; i >= 0; i--) {
            if (this.coefficients.get(i).tIsZero()) {
                continue;
            }

            if (this.coefficients.get(i).tGreater(this.coefficients.get(i).tZero())) {
                result += space + plus + space;
            } else if (this.coefficients.get(i).tLess(this.coefficients.get(i).tZero())) {
                result += space + "-" + space;
            }
            
            plus = "+";
            space = " ";

            if (i == 0 || (this.coefficients.get(i).tAbsolute()).tNotEqual(this.coefficients.get(i).tOne())) {
                result += this.coefficients.get(i).tAbsolute();
            }
            
            if (i == 0)
            {
                continue;
            }

            result += "x";

            if (i == 1)
            {
                continue;
            }

            result += "^" + i;
        }

        return result;
    }
}
