-- Marek Traczyński (261748)
-- Lista 1 Zadanie 2


with Interfaces.C; use Interfaces.C;


package MyLib is

    type Dio_GDC_Result is record
        G : Long_Integer;
        X : Long_Integer;
        Y : Long_Integer;
    end record;

    type Dio_Result is record
        X : Long_Integer;
        Y : Long_Integer;
    end record;


    function My_Factorial(N : Long_Integer) return Long_Integer
        with
            Export        => True,
            Convention    => C,
            External_Name => "my_factorial_ada";
    function My_Factorial_Rec(N : Long_Integer) return Long_Integer;


    function My_GCD(X : Long_Integer; Y : Long_Integer) return Long_Integer
        with
            Export        => True,
            Convention    => C,
            External_Name => "my_gcd_ada";
    function My_GCD_Rec(X : Long_Integer; Y : Long_Integer) return Long_Integer;


    function Dio_GCD(A : Long_Integer; B : Long_Integer) return Dio_GDC_Result;
    function Dio_GCD_Rec(A : Long_Integer; B : Long_Integer) return Dio_GDC_Result;
    function Dio_Equation(A : Long_Integer; B : Long_Integer; C : Long_Integer) return Dio_Result
        with
            Export        => True,
            Convention    => C,
            External_Name => "dio_equation_ada";
    function Dio_Equation_Rec(A : Long_Integer; B : Long_Integer; C : Long_Integer) return Dio_Result;

end MyLib;
