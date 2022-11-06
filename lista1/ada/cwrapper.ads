-- Marek Traczyński (261748)
-- Języki i Paradygmaty Programowania
-- Lista 1 Zadanie 5


with Interfaces.C; use Interfaces.C;


package CWrapper is

    type Dio_Result is record
        X : Long_Integer;
        Y : Long_Integer;
    end record;

    pragma Convention(C, Dio_Result);    


    function My_Factorial_C (N : Integer) return Integer;
    function My_GCD_C (X : Integer; Y : Integer) return Integer;
    function Dio_Equation_C (A : Integer; B : Integer; C : Integer) return Dio_Result;

    pragma Import(C, My_Factorial_C, "my_factorial");
    pragma Import(C, My_GCD_C, "my_gcd");
    pragma Import(C, Dio_Equation_C, "dio_equation");

end CWrapper;
