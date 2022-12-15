//  Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 1, 3


public class Galois implements CoeffType<Galois> {
    
    /////////////
    // Fields  //
    /////////////

    private final long order;
    private long value;

    
    //////////////////
    // Constructors //
    //////////////////

    Galois(long ord) {
        if (checkPrime(ord) != true) {
            throw new IllegalArgumentException(">> ERROR: Order must be prime! <<");
        }
        
        order = ord;
        value = 0;
    }

    Galois(long ord, long val) {
        if (checkPrime(ord) != true) {
            throw new IllegalArgumentException(">> ERROR: Order must be prime! <<");
        }

        order = ord;
        if (val < 0) {
            value = ord + (val % ord);
        } else {
            value = val % ord;
        }
    }


    /////////////////////////////
    // Miscellaneous functions //
    /////////////////////////////

    private static long modInverse(final long value, final long order) {
        long x = value;
        long y = 1;
        long n = order - 2;

        while (n > 0) {
            if (n % 2 == 1) {
                y = (y * x) % order;
            }
            
            n = n / 2;
            x = (x * x) % order;
        }

        return y;
    }

    private static Boolean checkPrime(final long number) {
        if (number <= 1) {
            return false;
        }

        if (number <= 3) {
            return true;
        }

        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }


    /////////////
    // Getters //
    /////////////

    public long getOrder() {
        return order;
    }

    public long getValue() {
        return value;
    }


    //////////////////////////////////
    // Arithmetic operators methods //
    //////////////////////////////////

    @Override
    public Galois tAdd(final Galois field) {
        long result = (this.value + field.value) % this.order;
        
        return new Galois(this.order, result);
    }

    @Override
    public Galois tSub(final Galois field) {
        long result = (this.order + (this.value - field.value) % this.order) % this.order;
        
        return new Galois(this.order, result);
    }

    @Override
    public Galois tMul(final Galois field) {
        long result = (this.value * field.value) % this.order;
        
        return new Galois(this.order, result);
    }

    @Override
    public Galois tDiv(final Galois field) throws IllegalArgumentException {
        if (field.value == 0) {
            throw new IllegalArgumentException(">> ERROR: Can't divide by 0! <<");
        }

        Galois result = new Galois(this.order, this.value);
        result.tMul(field.tInverse());
        
        return result;
    }

    @Override
    public Galois tInverse() {
        return new Galois(order, modInverse(this.value, order));
    }


    //////////////////////////////
    // CoeffType implementation //
    //////////////////////////////

    @Override
    public Galois tAbsolute() {
        return new Galois(this.order, Math.abs(this.value));
    }

    @Override
    public Galois tNegate() {
        return new Galois(this.order, -(this.value));
    }

    @Override
    public Galois tZero() {
        return new Galois(this.order, 0);
    }

    @Override
    public Galois tOne() {
        return new Galois(this.order, 1);
    }

    @Override
    public Boolean tIsZero() {
        return this.value == 0;
    }

    @Override
    public Boolean tIsOne() {
        return this.value == 1;
    }


    ///////////////////////////////
    // Logical operators methods //
    ///////////////////////////////

    @Override
    public Boolean tEqual(final Galois field) {
        return this.value == field.value;    
    }
    @Override
    public Boolean tNotEqual(final Galois field) {
        return this.value != field.value;    
    }
    @Override
    public Boolean tGreaterEq(final Galois field) {
        return this.value >= field.value;    
    }
    @Override
    public Boolean tLessEq(final Galois field) {
        return this.value <= field.value;    
    }
    @Override
    public Boolean tGreater(final Galois field) {
        return this.value > field.value;    
    }
    @Override
    public Boolean tLess(final Galois field) {
        return this.value < field.value;    
    }


    //////////////////////////////////
    // Assignment operators methods //
    //////////////////////////////////

    public Galois assignToObject(final long value) {
        this.value = (this.order + (value % this.order)) % this.order;

        return this;
    }

    public static Galois assignToObject(Galois field, final long value) {
        field.value = (field.order + (value % field.order)) % field.order;

        return field;
    }

    public static long assignToInt(final Galois field) {
        return field.value;
    }


    /////////////////////
    // toString method //
    /////////////////////

    public String toString() {
        String result = "";
        result += this.value;
        
        return result;
    }
}