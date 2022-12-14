//  Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 2 Zadanie 2


public class GaloisHC {
    
    /////////////
    // Fields  //
    /////////////

    public static final long FIELD_ORDER = 1234567891;
    private long order;
    private long value;

    
    //////////////////
    // Constructors //
    //////////////////

    GaloisHC() {
        order = FIELD_ORDER;
        value = 0;
    }

    GaloisHC(long val) {
        order = FIELD_ORDER;

        if (val < 0) {
            value = FIELD_ORDER + (val % FIELD_ORDER);
        } else {
            value = val % FIELD_ORDER;
        }
    }


    /////////////////////////////
    // Miscellaneous functions //
    /////////////////////////////

    private static long modInverse(final long value) {
        long x = value;
        long y = 1;
        long n = FIELD_ORDER - 2;

        while (n > 0) {
            if (n % 2 == 1) {
                y = (y * x) % FIELD_ORDER;
            }
            
            n = n / 2;
            x = (x * x) % FIELD_ORDER;
        }

        return y;
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

    public GaloisHC gAdd(final GaloisHC field) {
        this.value = (this.value + field.value) % FIELD_ORDER;
        
        return this;
    }

    public GaloisHC gSub(final GaloisHC field) {
        this.value = (FIELD_ORDER + (this.value - field.value) % FIELD_ORDER) % FIELD_ORDER;
        
        return this;
    }

    public GaloisHC gMul(final GaloisHC field) {
        this.value = (this.value * field.value) % FIELD_ORDER;
        
        return this;
    }

    public GaloisHC gDiv(final GaloisHC field) throws IllegalArgumentException {
        if (field.value == 0) {
            throw new IllegalArgumentException(">> ERROR: Can't divide by 0! <<");
        }

        this.value = (this.value * modInverse(field.value)) % FIELD_ORDER;
        
        return this;
    }

    //////////////////////////////////

    public GaloisHC gAdd(final long value) {
        this.value = (this.value + value) % FIELD_ORDER;
        
        return this;
    }

    public GaloisHC gSub(final long value) {
        this.value = (FIELD_ORDER + (this.value - value) % FIELD_ORDER) % FIELD_ORDER;
        
        return this;
    }

    public GaloisHC gMul(final long value) {
        this.value = (this.value * value) % FIELD_ORDER;
        
        return this;
    }

    public GaloisHC gDiv(final long value) throws IllegalArgumentException {
        if (value == 0) {
            throw new IllegalArgumentException(">> ERROR: Can't divide by 0! <<");
        }

        this.value = (this.value * modInverse(value)) % FIELD_ORDER;
        
        return this;
    }

    //////////////////////////////////

    public static GaloisHC gAddTwo(final GaloisHC field1, final GaloisHC field2) {
        GaloisHC result = new GaloisHC();
        result.value = (field1.value + field2.value) % FIELD_ORDER;
        
        return result;
    }

    public static GaloisHC gSubTwo(final GaloisHC field1, final GaloisHC field2) {
        GaloisHC result = new GaloisHC();
        result.value = (FIELD_ORDER + (field1.value - field2.value) % FIELD_ORDER) % FIELD_ORDER;
        
        return result;
    }

    public static GaloisHC gMulTwo(final GaloisHC field1, final GaloisHC field2) {
        GaloisHC result = new GaloisHC();
        result.value = (field1.value * field2.value) % FIELD_ORDER;
        
        return result;
    }

    public static GaloisHC gDivTwo(final GaloisHC field1, final GaloisHC field2) {
        if (field2.value == 0) {
            throw new IllegalArgumentException(">> ERROR: Can't divide by 0! <<");
        }

        GaloisHC result = new GaloisHC();
        result.value = (field1.value * modInverse(field2.value)) % FIELD_ORDER;
        
        return result;
    }


    ///////////////////////////////
    // Logical operators methods //
    ///////////////////////////////

    public static Boolean gEqual(final GaloisHC field1, final GaloisHC field2) {
        return field1.value == field2.value;
    }

    public static Boolean gNotEqual(final GaloisHC field1, final GaloisHC field2) {
        return field1.value != field2.value;
    }

    public static Boolean gGreaterEq(final GaloisHC field1, final GaloisHC field2) {
        return field1.value >= field2.value;
    }

    public static Boolean gLessEq(final GaloisHC field1, final GaloisHC field2) {
        return field1.value <= field2.value;
    }

    public static Boolean gGreater(final GaloisHC field1, final GaloisHC field2) {
        return field1.value > field2.value;
    }

    public static Boolean gLess(final GaloisHC field1, final GaloisHC field2) {
        return field1.value < field2.value;
    }

    //////////////////////////////////

    public static Boolean gEqual(final GaloisHC field, final long value) {
        return field.value == value;
    }

    public static Boolean gNotEqual(final GaloisHC field, final long value) {
        return field.value != value;
    }

    public static Boolean gGreaterEq(final GaloisHC field, final long value) {
        return field.value >= value;
    }

    public static Boolean gLessEq(final GaloisHC field, final long value) {
        return field.value <= value;
    }

    public static Boolean gGreater(final GaloisHC field, final long value) {
        return field.value > value;
    }

    public static Boolean gLess(final GaloisHC field, final long value) {
        return field.value < value;
    }


    //////////////////////////////////
    // Assignment operators methods //
    //////////////////////////////////

    public GaloisHC assignToObject(final long value)
    {
        this.value = (FIELD_ORDER + (value % FIELD_ORDER)) % FIELD_ORDER;

        return this;
    }

    public static GaloisHC assignToObject(GaloisHC field, final long value)
    {
        field.value = (FIELD_ORDER + (value % FIELD_ORDER)) % FIELD_ORDER;

        return field;
    }

    public static long assignToInt(final GaloisHC field)
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