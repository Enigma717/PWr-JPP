# Marek Traczyński (261748)
# Języki i Paradygmaty Programowania
# Lista 2 Zadanie 3


from random import randint
from gfield import Galois


def random_value():
    return randint(1, Galois.FIELD_ORDER)


def main():
    random1 = random_value()
    random2 = -(random_value())
    expected = 0
    real = 0
    logic_expected = False
    logic_expected = True
    result = Galois(0)


    print("\n+---------------------------------------------------------+")
    print("|                    GALOIS FIELD TEST                    |")
    print("+---------------------------------------------------------+\n")
    print("\t      >-----------------------------<")
    print("\t      >>                           <<")
    print("\t      >>     GENERATED NUMBERS     <<")
    print("\t      >>                           <<")
    print(f"\t      >>   FIRST:  {random1:>14,}  <<")
    print(f"\t      >>  SECOND:  {random2:>14,}  <<")
    print("\t      >>                           <<")
    print("\t      >-----------------------------<")



    print("\n====================[ CONSTRUCTOR TEST ]====================\n")

    field1 = Galois(random1)
    print("-> First random value (positive):")
    print(f" \\--> field1: {field1}\n") 
    assert field1.value == random1 % Galois.FIELD_ORDER


    field2 = Galois(random2)
    print("-> First random value (positive):")
    print(f" \\--> field2: {field2}\n") 
    assert field2.value == random2 % Galois.FIELD_ORDER



    print("\n=====================[ ARITHMETIC TEST ]====================\n")

    print("-> Expected result:")
    print(" \\--> (random1 @ random2) % order") 
    print("-> Real result:")
    print(" \\--> Field1 @ Field2\n") 


    expected = (random1 + random2) % Galois.FIELD_ORDER
    result = field1 + field2 
    real = result.value
    print("-> Addition test:")
    print(f"|--> Expected result: {expected:,}") 
    print(f"\\--> Real result: {real:,}\n") 
    assert real == expected


    expected = (random1 - random2) % Galois.FIELD_ORDER
    result = field1 - field2 
    real = result.value
    print("-> Subtraction test:")
    print(f"|--> Expected result: {expected:,}") 
    print(f"\\--> Real result: {real:,}\n") 
    assert real == expected


    expected = (random1 * random2) % Galois.FIELD_ORDER
    result = field1 * field2 
    real = result.value
    print("-> Multiplication test:")
    print(f"|--> Expected result: {expected:,}") 
    print(f"\\--> Real result: {real:,}\n") 
    assert real == expected


    try:
        expected = (random1 * random1) % Galois.FIELD_ORDER
        result = (field1 / field2) * (field1 * field2) 
        real = result.value
        print("-> Division test:")
        print(f"|--> Expected result: {expected:,}") 
        print(f"\\--> Real result: {real:,}\n") 
        assert real == expected
    except ZeroDivisionError as zde:
        print(zde)
        exit()



    print("\n=======================[ LOGIC TEST ]=======================\n")


    logic_expected = \
        random1 % Galois.FIELD_ORDER == \
        random2 % Galois.FIELD_ORDER
    logic_real = field1 == field2
    print("-> Equality operator test (field1 == field2):")
    print(f"|--> Expected result: {logic_expected}") 
    print(f"\\--> Real result: {logic_real}\n") 
    assert logic_real == logic_expected


    logic_expected = \
        random1 % Galois.FIELD_ORDER > \
        random2 % Galois.FIELD_ORDER
    logic_real = field1 > field2
    print("\n-> Relational operator test (Field1 > Field2):")
    print(f"|--> Expected result: {logic_expected}") 
    print(f"\\--> Real result: {logic_real}\n") 
    assert logic_real == logic_expected


    logic_expected = \
        random1 % Galois.FIELD_ORDER < \
        random2 % Galois.FIELD_ORDER
    logic_real = field1 < field2
    print("\n-> Relational operator test (Field1 < Field2):")
    print(f"|--> Expected result: {logic_expected}") 
    print(f"\\--> Real result: {logic_real}\n") 
    assert logic_real == logic_expected



    print("\n\t      >----------------------------<")
    print("\t      >>                          <<")
    print("\t      >>     ALL TESTS PASSED     <<")
    print("\t      >>                          <<")
    print("\t      >----------------------------<\n\n")


main()