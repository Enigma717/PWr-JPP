//  Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 3


public class NumCoeff implements CoeffType<NumCoeff> {
    
    private double number;

    public NumCoeff(final double value) {
        number = value;
    }

    public double getNumber() {
        return this.number;
    }

    
    @Override
    public NumCoeff tAdd(NumCoeff num) {
        return new NumCoeff(this.number + num.getNumber());
    }

    @Override
    public NumCoeff tSub(NumCoeff num) {
        return new NumCoeff(this.number - num.getNumber());
    }

    @Override
    public NumCoeff tMul(NumCoeff num) {
        return new NumCoeff(this.number * num.getNumber());
    }

    @Override
    public NumCoeff tDiv(NumCoeff num) {
        return new NumCoeff(this.number / num.getNumber());
    }

    @Override
    public NumCoeff tInverse() {
        return new NumCoeff(1 / this.number);
    }

    @Override
    public NumCoeff tAbsolute() {
        return new NumCoeff(Math.abs(this.number));
    }

    @Override
    public NumCoeff tNegate() {
        return new NumCoeff(-(this.number));
    }

    @Override
    public NumCoeff tZero() {
        return new NumCoeff(0);
    }

    @Override
    public NumCoeff tOne() {
        return new NumCoeff(1);
    }

    @Override
    public Boolean tIsZero() {
        return this.number == 0;
    }

    @Override
    public Boolean tIsOne() {
        return this.number == 1;
    }


    @Override
    public Boolean tEqual(NumCoeff num) {
        return this.number == num.getNumber();    
    }
    @Override
    public Boolean tNotEqual(NumCoeff num) {
        return this.number != num.getNumber();    
    }
    @Override
    public Boolean tGreaterEq(NumCoeff num) {
        return this.number >= num.getNumber();    
    }
    @Override
    public Boolean tLessEq(NumCoeff num) {
        return this.number <= num.getNumber();    
    }
    @Override
    public Boolean tGreater(NumCoeff num) {
        return this.number > num.getNumber();    
    }
    @Override
    public Boolean tLess(NumCoeff num) {
        return this.number < num.getNumber();    
    }

    
    @Override
    public String toString() {
        return Double.toString(this.number);
    }
}
