//  Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 1


public class Galois {
    
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

    public Galois gAdd(final Galois field) {
        this.value = (this.value + field.value) % this.order;
        
        return this;
    }

    public Galois gSub(final Galois field) {
        this.value = (this.order + (this.value - field.value) % this.order) % this.order;
        
        return this;
    }

    public Galois gMul(final Galois field) {
        this.value = (this.value * field.value) % this.order;
        
        return this;
    }

    public Galois gDiv(final Galois field) throws IllegalArgumentException {
        if (field.value == 0) {
            throw new IllegalArgumentException(">> ERROR: Can't divide by 0! <<");
        }

        this.value = (this.value * modInverse(field.value, this.order)) % this.order;
        
        return this;
    }

    //////////////////////////////////

    public Galois gAdd(final long value) {
        this.value = (this.value + value) % this.order;
        
        return this;
    }

    public Galois gSub(final long value) {
        this.value = (this.order + (this.value - value) % this.order) % this.order;
        
        return this;
    }

    public Galois gMul(final long value) {
        this.value = (this.value * value) % this.order;
        
        return this;
    }

    public Galois gDiv(final long value) throws IllegalArgumentException {
        if (value == 0) {
            throw new IllegalArgumentException(">> ERROR: Can't divide by 0! <<");
        }

        this.value = (this.value * modInverse(value, this.order)) % this.order;
        
        return this;
    }

    //////////////////////////////////

    public static Galois gAddTwo(final Galois field1, final Galois field2) {
        Galois result = new Galois(field1.order);
        result.value = (field1.value + field2.value) % field1.order;
        
        return result;
    }

    public static Galois gSubTwo(final Galois field1, final Galois field2) {
        Galois result = new Galois(field1.order);
        result.value = (field1.order + (field1.value - field2.value) % field1.order) % field1.order;
        
        return result;
    }

    public static Galois gMulTwo(final Galois field1, final Galois field2) {
        Galois result = new Galois(field1.order);
        result.value = (field1.value * field2.value) % field1.order;
        
        return result;
    }

    public static Galois gDivTwo(final Galois field1, final Galois field2) {
        if (field2.value == 0) {
            throw new IllegalArgumentException(">> ERROR: Can't divide by 0! <<");
        }

        Galois result = new Galois(field1.order);
        result.value = (field1.value * modInverse(field2.value, field1.order)) % field1.order;
        
        return result;
    }


    ///////////////////////////////
    // Logical operators methods //
    ///////////////////////////////

    public static Boolean gEqual(final Galois field1, final Galois field2) {
        return field1.value == field2.value;
    }

    public static Boolean gNotEqual(final Galois field1, final Galois field2) {
        return field1.value != field2.value;
    }

    public static Boolean gGreaterEq(final Galois field1, final Galois field2) {
        return field1.value >= field2.value;
    }

    public static Boolean gLessEq(final Galois field1, final Galois field2) {
        return field1.value <= field2.value;
    }

    public static Boolean gGreater(final Galois field1, final Galois field2) {
        return field1.value > field2.value;
    }

    public static Boolean gLess(final Galois field1, final Galois field2) {
        return field1.value < field2.value;
    }

    //////////////////////////////////

    public static Boolean gEqual(final Galois field, final long value) {
        return field.value == value;
    }

    public static Boolean gNotEqual(final Galois field, final long value) {
        return field.value != value;
    }

    public static Boolean gGreaterEq(final Galois field, final long value) {
        return field.value >= value;
    }

    public static Boolean gLessEq(final Galois field, final long value) {
        return field.value <= value;
    }

    public static Boolean gGreater(final Galois field, final long value) {
        return field.value > value;
    }

    public static Boolean gLess(final Galois field, final long value) {
        return field.value < value;
    }


    //////////////////////////////////
    // Assignment operators methods //
    //////////////////////////////////

    public Galois assignToObject(final long value)
    {
        this.value = (this.order + (value % this.order)) % this.order;

        return this;
    }

    public static Galois assignToObject(Galois field, final long value)
    {
        field.value = (field.order + (value % field.order)) % field.order;

        return field;
    }

    public static long assignToInt(final Galois field)
    {
        return field.value;
    }


    /////////////////////
    // toString method //
    /////////////////////

    public String toString() {
        return ("GF(" + this.order + ")[" + this.value + "]");
    }
}