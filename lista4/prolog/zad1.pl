% Marek Traczyński (261748)
% Języki i Paradygmaty Programowania
% Lista 4 Zadanie 1


%%%%%%%%%%%%%%%
%% Factorial %%
%%%%%%%%%%%%%%%

myFactorial(0, 1).
myFactorial(N, Result) :-
      N > 0,
      N1 is N - 1,
      myFactorial(N1, Res1),
      Result is N * Res1.


%%%%%%%%%
%% GCD %%
%%%%%%%%%

myGCD(A, 0, A).
myGCD(A, B, G) :-
      B > 0,
      Temp is A mod B,
      myGCD(B, Temp, G).


%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Diophantine equation %%
%%%%%%%%%%%%%%%%%%%%%%%%%%

dioGCD(A, 0, A, 1, 0).
dioGCD(A, B, G, X, Y) :-
      B > 0,
      Temp is A mod B,
      dioGCD(B, Temp, G, X1, Y1),
      X is Y1,
      Y is X1 - (A div B) * Y1.
                    
myDioEq(0, 0, 0, 1, 1).
myDioEq(A, B, C, X, Y) :-
      dioGCD(A, B, G, X1, Y1),
      C mod G =:= 0,
      C1 is C div G,
      X is X1 * C1,
      Y is Y1 * C1. 
