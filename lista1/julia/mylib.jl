# Marek Traczyński (261748)
# Języki i Paradygmaty Programowania
# Lista 1 Zadanie 3


#########################
## Factorial functions ##
#########################

function myfactorial(n::Integer)::Int
    result::Int = one(Integer)

    if n < 0 || n > 20
        return zero(Integer)
    end

    for i::Int in 2:n
        result *= i
    end
    
    return result
end

function myfactorial_rec(n::Integer)::Int
    if n < 0 || n > 20
        return zero(Integer)
    end

    if n > 0
        return n * myfactorial_rec(n - 1)
    end
    
    return one(Integer)
end


###################
## GCD functions ##
###################

function mygcd(x::Integer, y::Integer)::Int
    if x < 0 || y < 0
        return zero(Integer)
    end

    while y != 0
        (x, y) = (y, x % y)
    end

    return x
end

function mygcd_rec(x::Integer, y::Integer)::Int
    if x < 0 || y < 0
        return zero(Integer)
    end
    
    if y == 0
        return x
    end

    return mygcd_rec(y, x % y)
end


####################################
## Diophantine equation functions ##
####################################

function diogcd(a::Integer, b::Integer)::Tuple{Int, Int, Int}
    (oldrem::Int, rem::Int) = (a, b) 
    (oldx::Int, x::Int) = (one(Integer), zero(Integer)) 
    (oldy::Int, y::Int) = (zero(Integer), one(Integer))
    quotient::Int = zero(Integer)
    
    while rem != 0
        quotient = div(oldrem, rem, RoundDown)
        (oldrem, rem) = (rem, oldrem - quotient * rem)
        (oldx, x) = (x, oldx - quotient * x)
        (oldy, y) = (y, oldy - quotient * y)
    end

    return (oldrem, oldx, oldy)
end

function diogcd_rec(a::Integer, b::Integer)::Tuple{Int, Int, Int}
    if b == 0
        return (a, one(Integer), zero(Integer))
    end

    gcdresult::Tuple{Integer, Integer, Integer} = diogcd_rec(b, a % b)
    g::Int = gcdresult[1]
    x::Int = gcdresult[3]
    y::Int = gcdresult[2] - div(a, b, RoundDown) * gcdresult[3]

    return (g, x, y)
end

function dioequation(a::Integer, b::Integer, c::Integer)::Tuple{Int, Int}
    if a == 0 && b == 0
        if c == 0
            return (one(Integer), one(Integer))
        else
            return (zero(Integer), zero(Integer))
        end
    end

    gcdresult::Tuple{Integer, Integer, Integer} = diogcd(a, b)
    gcdg::Int = gcdresult[1]
    gcdx::Int = gcdresult[2]
    gcdy::Int = gcdresult[3]

    if c % gcdg != 0
        return (zero(Integer), zero(Integer))
    end

    x::Int = gcdx * div(c, gcdg, RoundDown)
    y::Int = gcdy * div(c, gcdg, RoundDown)

    return (x, y)
end

function dioequation_rec(a::Integer, b::Integer, c::Integer)::Tuple{Int, Int}
    if a == 0 && b == 0
        if c == 0
            return (one(Integer), one(Integer))
        else
            return (zero(Integer), zero(Integer))
        end
    end

    gcdresult::Tuple{Integer, Integer, Integer} = diogcd_rec(a, b)
    gcdg::Int = gcdresult[1]
    gcdx::Int = gcdresult[2]
    gcdy::Int = gcdresult[3]

    if c % gcdg != 0
        return (zero(Integer), zero(Integer))
    end

    x::Int = gcdx * div(c, gcdg, RoundDown)
    y::Int = gcdy * div(c, gcdg, RoundDown)

    return (x, y)
end
