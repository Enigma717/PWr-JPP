//  Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 3


public interface CoeffType<T> {
    T tAdd(T object);
    T tSub(T object);
    T tMul(T object);
    T tDiv(T object);

    T tInverse();
    T tAbsolute();
    T tNegate();
    
    T tZero();
    T tOne();
    Boolean tIsZero();
    Boolean tIsOne();

    Boolean tEqual(T object);
    Boolean tNotEqual(T object);
    Boolean tGreaterEq(T object);
    Boolean tLessEq(T object);
    Boolean tGreater(T object);
    Boolean tLess(T object);
}
