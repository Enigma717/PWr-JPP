-- Marek Traczyński (261748)
-- Lista 1 Zadanie 5


with Ada.Text_IO; use Ada.Text_IO;
with CWrapper; use CWrapper;


procedure CWrapTest is
    type One_Dim_Array is 
        array (Natural range <>) of Integer;
    type Two_Dim_Array is 
        array (Natural range <>, Natural range <>) of Integer;

    Factorial_Values : One_Dim_Array(1..3) := (10, 0, -1);
    GCD_Values : Two_Dim_Array(1..3, 1..2) := ((42, 56), (0, 16), (-2, 10));
    Dio_Values : Two_Dim_Array(1..3, 1..3) := ((4, 18, 10), (10, 0, 0), (-5, -3, 4));

    N_Arg, X_Arg, Y_Arg, A_Arg, B_Arg, C_Arg : Integer := 0;
    Func_Result : Integer := 0;
    Dio_Eq_Result : Dio_Result := (0, 0);
begin
    New_Line;
    Put_Line("+--------------------------------------------------------+");
    Put_Line("|                     C WRAPPER TEST                     |");
    Put_Line("+--------------------------------------------------------+");


    New_Line;
    Put_Line("===============[ ITERATIVE FACTORIAL TEST ]===============");
    New_Line;
    Put_Line ("-> Iterative factorial results:");

    for I in Factorial_Values'Range loop
        N_Arg := Factorial_Values(I);
        Func_Result := My_Factorial_C(N_Arg);

        Put_Line("|--> My_Factorial(" & Integer'Image(N_Arg) 
                & ") result: " & Integer'Image(Func_Result));
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
        Func_Result := My_GCD_C(X_Arg, Y_Arg);

        Put_Line("|--> My_GCD(" & Integer'Image(X_Arg) & ", " & Integer'Image(Y_Arg) 
                & ") result: " & Integer'Image(Func_Result));
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
        Dio_Eq_Result := Dio_Equation_C(A_Arg, B_Arg, C_Arg);

        Put_Line("|--> Dio_Equation(" 
                & Integer'Image(A_Arg) & ", " 
                & Integer'Image(B_Arg) & ", " 
                & Integer'Image(C_Arg) & ") result: X = "
                & Long_Integer'Image(Dio_Eq_Result.X) & ", Y = " 
                & Long_Integer'Image(Dio_Eq_Result.Y));
    end loop;


    New_Line;
    Put_Line("+--------------------------------------------------------+");
    New_Line;

end CWrapTest;
