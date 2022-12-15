# Marek Traczyński (261748)
# Języki i Paradygmaty Programowania
# Lista 3 Zadanie 4


import random 
from gfield import galois_field
from polynomial import polynomial_type


def random_float(range):
    return random.uniform(1, range)


def main():
    random1 = round(random_float(100), 2)
    random2 = round(random_float(100), 2)
    random3 = round(random_float(100), 2)
    random4 = round(random_float(100), 2)
    random5 = round(random_float(100), 2)

    d_polynomial_builder = polynomial_type(float).builder
    gf_polynomial_builder = polynomial_type(galois_field(2)).builder


    print("\n+---------------------------------------------------------+")
    print("|                     POLYNOMIAL TEST                     |")
    print("+---------------------------------------------------------+\n")
    print("\t      >-----------------------------<")
    print("\t      >>                           <<")
    print("\t      >>     GENERATED NUMBERS     <<")
    print("\t      >>                           <<")
    print(f"\t      >>   FIRST:  {random1:>14,}  <<")
    print(f"\t      >>  SECOND:  {random2:>14,}  <<")
    print(f"\t      >>   THIRD:  {random3:>14,}  <<")
    print(f"\t      >>  FOURTH:  {random4:>14,}  <<")
    print(f"\t      >>   FIFTH:  {random5:>14,}  <<")
    print("\t      >>                           <<")
    print("\t      >-----------------------------<")



    print("\n====================[ CONSTRUCTOR TEST ]====================\n")

    d_coeffs = [random1,
                random2,
                random3,
                random4,
                random5]

    int_coeffs = [int(c) for c in d_coeffs]

    d_poly = d_polynomial_builder(d_coeffs)
    print("\n-> Polynomial with real numbers:")
    print(f"|--> Print: {d_poly}") 
    print(f"\\--> Degree: {d_poly.degree}") 


    gf_poly = gf_polynomial_builder(int_coeffs)
    print("\n-> Polynomial with finite fields:")
    print(f"|--> Print: {gf_poly}") 
    print(f"\\--> Degree: {gf_poly.degree}\n") 



    print("\n=====================[ ARITHMETIC TEST ]====================\n")

    test_coeffs = [1, 2, 3, 4, 5]

    d_test_poly = d_polynomial_builder(test_coeffs)
    gf_test_poly = gf_polynomial_builder(test_coeffs)

    print(f">> Test real numbers polynomial: {d_test_poly}")
    print(f">> Test real numbers polynomial: {gf_test_poly}")

    ##################################
    print("\n-----------------------\n");
    ##################################

    print("-> Addition test:")
    
    d_result_poly = d_poly + d_test_poly 
    gf_result_poly = gf_poly + gf_test_poly

    print(f"|--> Real numbers result: {d_result_poly}") 
    print(f"|--> Finite field result: {gf_result_poly}") 
    assert d_result_poly.degree == max(d_poly.degree, d_test_poly.degree)
    assert gf_result_poly.degree <= max(gf_poly.degree, gf_test_poly.degree)

    ##################################
    print("\n-----------------------\n");
    ##################################

    print("-> Subtraction test:")
    
    d_result_poly = d_poly - d_test_poly 
    gf_result_poly = gf_poly - gf_test_poly

    print(f"|--> Real numbers result: {d_result_poly}") 
    print(f"|--> Finite field result: {gf_result_poly}") 
    assert d_result_poly.degree == max(d_poly.degree, d_test_poly.degree)
    assert gf_result_poly.degree <= max(gf_poly.degree, gf_test_poly.degree)


    ##################################
    print("\n-----------------------\n");
    ##################################

    print("-> Multiplication test:")
    
    d_result_poly = d_poly * d_test_poly 
    gf_result_poly = gf_poly * gf_test_poly

    print(f"|--> Real numbers result: {d_result_poly}") 
    print(f"|--> Finite field result: {gf_result_poly}") 
    assert d_result_poly.degree == (d_poly.degree + d_test_poly.degree)
    assert gf_result_poly.degree <= (gf_poly.degree + gf_test_poly.degree)


    ##################################
    print("\n-----------------------\n");
    ##################################

    print("-> Division test:")
    
    d_result_poly = d_poly / d_test_poly 
    gf_result_poly = gf_poly / gf_test_poly

    print(f"|--> Real numbers result: {d_result_poly}") 
    print(f"|--> Finite field result: {gf_result_poly}")

    ##################################
    print("\n-----------------------\n");
    ##################################

    print("-> Modulo test:")
    
    d_result_poly = d_poly % d_test_poly 
    gf_result_poly = gf_poly % gf_test_poly

    print(f"|--> Real numbers result: {d_result_poly}") 
    print(f"|--> Finite field result: {gf_result_poly}\n") 



    print("\n=======================[ LOGIC TEST ]=======================\n")

    print(f">> First real numbers polynomial: {d_poly}")
    print(f">> Second real numbers polynomial: {d_test_poly}")
    print(f">> First finite field polynomial: {gf_poly}")
    print(f">> Second finite field polynomial: {gf_test_poly}")

    print("\n-----------------------\n");

    print("-> Equality operator test (field1 == field2):")
    print(f"|--> Real numbers result: {d_poly == d_poly}") 
    print(f"|--> Finite fields result: {gf_poly == gf_poly}") 
    assert d_poly == d_poly
    assert gf_poly == gf_poly

    print("\n-----------------------\n");

    print("-> Inquality operator test (field1 != field2):")
    print(f"|--> Real numbers result: {d_poly != d_test_poly}") 
    print(f"|--> Finite fields result: {gf_poly != gf_test_poly}\n") 
    assert d_poly != d_test_poly
    assert gf_poly != gf_test_poly



    print("\n=======================[ EVALUATION TEST ]=======================\n")

    print(f">> Real numbers polynomial: {d_poly}")
    print(f">> Finite field polynomial: {gf_poly}")

    print("\n-----------------------\n");

    print("-> Evaluating at point x = 5:")
    
    d_result_poly = d_poly(5) 
    gf_result_poly = gf_poly(5)

    d_eval_result = d_poly.coefficients[-1]
    gf_eval_result = gf_poly.coefficients[-1]
    val = gf_polynomial_builder(5)

    for i in range(d_poly.degree - 1, 0, -1):
        d_eval_result = d_eval_result * float(5) + d_poly.coefficients[i]

    for i in range(gf_poly.degree - 1, 0, -1):
        gf_eval_result = gf_eval_result * val(5) + gf_poly.coefficients[i]

    print("|--> Real numbers:") 
    print(f" |--> Expected result: {d_eval_result}") 
    print(f" \\--> Real result: {d_result_poly}") 
    print("|--> Finite fields:") 
    print(f" |--> Expected result: {gf_eval_result}") 
    print(f" \\--> Real result: {gf_result_poly}\n\n") 
    assert d_result_poly == d_eval_result
    assert gf_result_poly == gf_eval_result



    print("\n\t      >----------------------------<")
    print("\t      >>                          <<")
    print("\t      >>     ALL TESTS PASSED     <<")
    print("\t      >>                          <<")
    print("\t      >----------------------------<\n\n")


main()