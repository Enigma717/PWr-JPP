# Marek Traczyński (261748)
# Lista 1 Zadanie 3


include("./mylib.jl")


function test()
    factorialvalues::Vector{Int} = [10, 0, -1]
    gcdvalues::Vector{Tuple{Int, Int}} = [(42, 56), (0, 16), (-2, 10)]
    diovalues::Vector{Tuple{Int, Int, Int}} = [(4, 18, 10), (10, 0, 0), (-5, -3, 4)]
    dioresult::Tuple{Int, Int} = (0, 0)


    print("\n+--------------------------------------------------------+")
    print("\n|                   JULIA LIBRARY TEST                   |")
    print("\n+--------------------------------------------------------+")


    print("\n\n===============[ ITERATIVE FACTORIAL TEST ]===============\n")

    print("\n-> Iterative factorial results:")

    for value in factorialvalues
        print("\n|--> myfactorial($value) result: $(myfactorial(value))")
    end


    print("\n\n===============[ RECURSIVE FACTORIAL TEST ]===============\n")

    print("\n-> Recursive factorial results:")

    for value in factorialvalues
        print("\n|--> myfactorial_rec($value) result: $(myfactorial_rec(value))")
    end


    print("\n\n+--------------------------------------------------------+\n")


    print("\n==================[ ITERATIVE GCD TEST ]==================\n")

    print("\n-> Iterative GCD results:")

    for value in gcdvalues
        print("\n|--> mygcd$value result: $(mygcd(value[1], value[2]))")
    end
    
    
    print("\n\n==================[ RECURSIVE GCD TEST ]==================\n")

    print("\n-> Recursive GCD results:")

    for value in gcdvalues
        print("\n|--> mygcd_rec$value result: $(mygcd_rec(value[1], value[2]))")
    end


    print("\n\n+--------------------------------------------------------+\n")


    print("\n==============[ ITERATIVE DIOPHANTINE TEST ]==============\n")

    print("\n-> Iterative diophantine equation results:")

    for value in diovalues
        dioresult = dioequation(value[1], value[2], value[3])
        print("\n|--> dioequation$value result: x = $(dioresult[1]), y = $(dioresult[2])")
    end

    print("\n\n==============[ RECURSIVE DIOPHANTINE TEST ]==============\n")

    print("\n-> Recursive diophantine equation results:")

    for value in diovalues
        dioresult = dioequation_rec(value[1], value[2], value[3])
        print("\n|--> dioequation_rec$value result: x = $(dioresult[1]), y = $(dioresult[2])")
    end

    print("\n\n+--------------------------------------------------------+\n\n")
end

test()
