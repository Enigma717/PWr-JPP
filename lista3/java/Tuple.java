//  Marek Traczyński (261748)
// Języki i Paradygmaty Programowania
// Lista 3 Zadanie 3


public class Tuple<T, U> {
    public final T elem1;
    public final U elem2;

    public Tuple(T e1, U e2) {
        elem1 = e1;
        elem2 = e2;
    }

    public T getFirst() {
        return this.elem1;
    }

    public U getSecond() {
        return this.elem2;
    }
}
