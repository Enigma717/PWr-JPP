-- Marek Traczyński (261748)
-- Lista 1 Zadanie 2


with Ada.Text_IO; use Ada.Text_IO;
with MyLib;


procedure Adalibtest is
    type One_Dim_Array is 
        array (Natural range <>) of Long_Integer;
    type Two_Dim_Array is 
        array (Natural range <>, Natural range <>) of Long_Integer;

    Factorial_Values : One_Dim_Array(1..3) := (10, 0, -1);
    GCD_Values : Two_Dim_Array(1..3, 1..2) := ((42, 56), (0, 16), (-2, 10));
    Dio_Values : Two_Dim_Array(1..3, 1..3) := ((4, 18, 10), (10, 0, 0), (-5, -3, 4));

    N_Arg, X_Arg, Y_Arg, A_Arg, B_Arg, C_Arg : Long_Integer := 0;
    Func_Result : Long_Integer := 0;
    Dio_Eq_Result : MyLib.Dio_Result := (0, 0);
begin
    New_Line;
    Put_Line("+--------------------------------------------------------+");
    Put_Line("|                    ADA LIBRARY TEST                    |");
    Put_Line("+--------------------------------------------------------+");


    New_Line;
    Put_Line("===============[ ITERATIVE FACTORIAL TEST ]===============");
    New_Line;
    Put_Line ("-> Iterative factorial results:");

    for I in Factorial_Values'Range loop
        N_Arg := Factorial_Values(I);
        Func_Result := MyLib.My_Factorial(N_Arg);

        Put_Line("|--> My_Factorial(" & Long_Integer'Image(N_Arg) 
                & ") result: " & Long_Integer'Image(Func_Result));
    end loop;


    New_Line;
    Put_Line("===============[ RECURSIVE FACTORIAL TEST ]===============");
    New_Line;
    Put_Line ("-> Recursive factorial results:");

    for I in Factorial_Values'Range loop
        N_Arg := Factorial_Values(I);
        Func_Result := MyLib.My_Factorial_Rec(N_Arg);

        Put_Line("|--> My_Factorial_Rec(" & Long_Integer'Image(N_Arg) 
                & ") result: " & Long_Integer'Image(Func_Result));
    end loop;
    

    New_Line;
    Put_Line("+--------------------------------------------------------+");
    

    New_Line;
    Put_Line("==================[ ITERATIVE GCD TEST ]==================");
    New_Line;
    Put_Line ("-> Iterative GCD results:");

    for I in GCD_Values'Range loop
        X_Arg := GCD_Values(I, 1);
        Y_Arg := GCD_Values(I, 2);
        Func_Result := MyLib.My_GCD(X_Arg, Y_Arg);

        Put_Line("|--> My_GCD(" & Long_Integer'Image(X_Arg) & ", " & Long_Integer'Image(Y_Arg) 
                & ") result: " & Long_Integer'Image(Func_Result));
    end loop;


    New_Line;
    Put_Line("==================[ RECURSIVE GCD TEST ]==================");
    New_Line;
    Put_Line ("-> Recursive GCD results:");

    for I in GCD_Values'Range loop
        X_Arg := GCD_Values(I, 1);
        Y_Arg := GCD_Values(I, 2);
        Func_Result := MyLib.My_GCD_Rec(X_Arg, Y_Arg);

        Put_Line("|--> My_GCD_Rec(" & Long_Integer'Image(X_Arg) & ", " & Long_Integer'Image(Y_Arg) 
                & ") result: " & Long_Integer'Image(Func_Result));
    end loop;


    New_Line;
    Put_Line("+--------------------------------------------------------+");


    New_Line;
    Put_Line("==============[ ITERATIVE DIOPHANTINE TEST ]==============");
    New_Line;
    Put_Line ("-> Iterative diophantine equation results:");

    for I in Dio_Values'Range loop
        A_Arg := Dio_Values(I, 1);
        B_Arg := Dio_Values(I, 2);
        C_Arg := Dio_Values(I, 3);
        Dio_Eq_Result := MyLib.Dio_Equation(A_Arg, B_Arg, C_Arg);

        Put_Line("|--> Dio_Equation(" 
                & Long_Integer'Image(A_Arg) & ", " 
                & Long_Integer'Image(B_Arg) & ", " 
                & Long_Integer'Image(C_Arg) & ") result: X = "
                & Long_Integer'Image(Dio_Eq_Result.X) & ", Y = " 
                & Long_Integer'Image(Dio_Eq_Result.Y));
    end loop;


    New_Line;
    Put_Line("==============[ RECURSIVE DIOPHANTINE TEST ]==============");
    New_Line;
    Put_Line ("-> Recursive diophantine equation results:");

    for I in GCD_Values'Range loop
        A_Arg := Dio_Values(I, 1);
        B_Arg := Dio_Values(I, 2);
        C_Arg := Dio_Values(I, 3);
        Dio_Eq_Result := MyLib.Dio_Equation(A_Arg, B_Arg, C_Arg);

        Put_Line("|--> Dio_Equation_Rec(" 
                & Long_Integer'Image(A_Arg) & ", " 
                & Long_Integer'Image(B_Arg) & ", " 
                & Long_Integer'Image(C_Arg) & ") result: X = "
                & Long_Integer'Image(Dio_Eq_Result.X) & ", Y = " 
                & Long_Integer'Image(Dio_Eq_Result.Y));

    end loop;


    New_Line;
    Put_Line("+--------------------------------------------------------+");
    New_Line;

end Adalibtest;
