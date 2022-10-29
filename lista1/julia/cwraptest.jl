# Marek Traczyński (261748)
# Lista 1 Zadanie 6


using Libdl


struct Dioresult 
    x::Int
    y::Int
end


function myfactorial_c(n::Integer)::Int
    library = dlopen("./mylib.so")
    wrapfunc = dlsym(library, "my_factorial")

    result::Int = ccall(wrapfunc, Int32, (Int32, ), n)

    return result
end

function mygcd_c(x::Integer, y::Integer)::Int
    library = dlopen("./mylib.so")
    wrapfunc = dlsym(library, "my_gcd")

    result::Int = ccall(wrapfunc, Int32, (Int32, Int32), x, y)

    return result
end

function dioequation_c(a::Integer, b::Integer, c::Integer)::Tuple{Int, Int}
    library = dlopen("./mylib.so")
    wrapfunc = dlsym(library, "dio_equation")

    dioresult::Dioresult = ccall(wrapfunc, Dioresult, (Int32, Int32, Int32), a, b, c)

    return (dioresult.x, dioresult.y)
end


function cwraptest()
    factorialvalues::Vector{Int} = [10, 0, -1]
    gcdvalues::Vector{Tuple{Int, Int}} = [(42, 56), (0, 16), (-2, 10)]
    diovalues::Vector{Tuple{Int, Int, Int}} = [(4, 18, 10), (10, 0, 0), (-5, -3, 4)]
    dioresult::Tuple{Int, Int} = (0, 0)


    print("\n+--------------------------------------------------------+")
    print("\n|                     C WRAPPER TEST                     |")
    print("\n+--------------------------------------------------------+")


    print("\n\n===============[ ITERATIVE FACTORIAL TEST ]===============\n")

    print("\n-> Iterative factorial results:")

    for value in factorialvalues
        print("\n|--> myfactorial($value) result: $(myfactorial_c(value))")
    end


    print("\n\n+--------------------------------------------------------+\n")


    print("\n==================[ ITERATIVE GCD TEST ]==================\n")

    print("\n-> Iterative GCD results:")

    for value in gcdvalues
        print("\n|--> mygcd$value result: $(mygcd_c(value[1], value[2]))")
    end
    

    print("\n\n+--------------------------------------------------------+\n")


    print("\n==============[ ITERATIVE DIOPHANTINE TEST ]==============\n")

    print("\n-> Iterative diophantine equation results:")

    for value in diovalues
        dioresult = dioequation_c(value[1], value[2], value[3])
        print("\n|--> dioequation$value result: x = $(dioresult[1]), y = $(dioresult[2])")
    end

    
    print("\n\n+--------------------------------------------------------+\n\n")
end

cwraptest()
