-- Marek Traczyński (261748)
-- Języki i Paradygmaty Programowania
-- Lista 1 Zadanie 2


package body MyLib is

    -------------------------
    -- Factorial functions --
    -------------------------

    function My_Factorial(N : Long_Integer) return Long_Integer is
        Result : Long_Integer := 1;
    begin
        if N < 0 or N > 20 then
            return 0;
        end if;

        for I in Long_Integer range 2..N loop
            Result := Result * I;
        end loop;

        return Result;
    end My_Factorial;

    function My_Factorial_Rec(N : Long_Integer) return Long_Integer is
    begin
        if N < 0 or N > 20 then
            return 0;
        end if;

        if N > 0 then
            return N * My_Factorial_Rec(N - 1);
        end if;

        return 1;
    end My_Factorial_Rec;


    -------------------
    -- GCD functions --
    -------------------

    function My_GCD(X : Long_Integer; Y : Long_Integer) return Long_Integer is
        Temp : Long_Integer := 0;
        A : Long_Integer := X;
        B : Long_Integer := Y;
    begin
        if A < 0 or B < 0 then
            return 0;
        end if;

        while B > 0 loop
            Temp := A;
            A := B;
            B := Temp mod B;
        end loop;

        return A;
    end My_GCD;

    function My_GCD_Rec(X : Long_Integer; Y : Long_Integer) return Long_Integer is
    begin
        if X < 0 or Y < 0 then
            return 0;
        end if;

        if Y = 0 then
            return X;
        end if;

        return My_GCD_Rec(Y, X mod Y);
    end My_GCD_Rec;


    ------------------------------------
    -- Diophantine equation functions --
    ------------------------------------

    function Dio_GCD(A : Long_Integer; B : Long_Integer) return Dio_GDC_Result is
        Result : Dio_GDC_Result := (0, 0, 0);

        Old_Remainder : Long_Integer := A;
        Remainder : Long_Integer := B;
        
        X : Long_Integer := 0;
        Old_X : Long_Integer := 1;
        
        Y : Long_Integer := 1;
        Old_Y : Long_Integer := 0;

        Quotient : Long_Integer := 0;
        Temp : Long_Integer := 0;
    begin
        while Remainder /= 0 loop
            Quotient := Old_Remainder / Remainder;

            Temp := Old_Remainder;
            Old_Remainder := Remainder;
            Remainder := Temp - Quotient * Remainder;

            Temp := Old_X;
            Old_X := X;
            X := Temp - Quotient * X;

            Temp := Old_Y;
            Old_Y := Y;
            Y := Temp - Quotient * Y;
        end loop;

        Result.G := Old_Remainder;
        Result.X := Old_X;
        Result.Y := Old_Y;

        return Result;
    end Dio_GCD;

    function Dio_GCD_Rec(A : Long_Integer; B : Long_Integer) return Dio_GDC_Result is
        Result : Dio_GDC_Result := (0, 0, 0);
        Temp_Result : Dio_GDC_Result := (0, 0, 0);
    begin
        if B = 0 then
            Result.G := A;
            Result.X := 1;
            Result.Y := 0;
        end if;

        Temp_Result := Dio_GCD_Rec(B, A mod B);
        Result.G := Temp_Result.G;
        Result.X := Temp_Result.Y;
        Result.Y := Temp_Result.X - (A / B) * Temp_Result.Y;

        return Result;
    end Dio_GCD_Rec;

    function Dio_Equation(A : Long_Integer; B : Long_Integer; C : Long_Integer) return Dio_Result is
        GCD_Result : Dio_GDC_Result := (0, 0, 0);
        Result : Dio_Result := (0, 0);
    begin
        if A = 0 and B = 0 then
            if C = 0 then
                Result.X := 1;
                Result.Y := 1;

                return Result;
            else 
                Result.X := 0;
                Result.Y := 0;

                return Result;
            end if;
        end if;

        GCD_Result := Dio_GCD(A, B);

        if (C mod GCD_Result.G) /= 0 then
            Result.X := 0;
            Result.Y := 0;

            return Result;
        end if;

        Result.X := GCD_Result.X * (C / GCD_Result.G);
        Result.Y := GCD_Result.Y * (C / GCD_Result.G);

        return Result;
    end Dio_Equation;

    function Dio_Equation_Rec(A : Long_Integer; B : Long_Integer; C : Long_Integer) return Dio_Result is
        GCD_Result : Dio_GDC_Result := (0, 0, 0);
        Result : Dio_Result := (0, 0);
    begin
        if A = 0 and B = 0 then
            if C = 0 then
                Result.X := 1;
                Result.Y := 1;

                return Result;
            else 
                Result.X := 0;
                Result.Y := 0;

                return Result;
            end if;
        end if;

        GCD_Result := Dio_GCD_Rec(A, B);

        if (C mod GCD_Result.G) /= 0 then
            Result.X := 0;
            Result.Y := 0;

            return Result;
        end if;

        Result.X := GCD_Result.X * (C / GCD_Result.G);
        Result.Y := GCD_Result.Y * (C / GCD_Result.G);

        return Result;
    end Dio_Equation_Rec;

end Mylib;
