# Marek Traczyński (261748)
# Języki i Paradygmaty Programowania
# Lista 3 Zadanie 1, 4


import sys
from math import sqrt

def galois_field(order):
    class Galois(object):
        def check_prime(self, number):
            for i in range(2, int(sqrt(number)) + 1):
                if number % i == 0:
                    return False

            return True


        def __init__(self, value):
            if self.check_prime(order) == False:
                sys.tracebacklimit = 0
                raise Exception(">> ERROR: Order must be prime! <<\n")
            
            self.order = order
            self.value = value % order

        def __int__(self):
            return self.value

        def __repr__(self):
            return f"Galois: {self.value}"

        def __str__(self):
            return f"GF({self.order:,})[{self.value:,}]"


        def __add__(self, field):
            return Galois(self.value + field.value)

        def __sub__(self, field):
            return Galois(self.value - field.value)

        def __mul__(self, field):
            return Galois(self.value * field.value)

        def __truediv__(self, field):
            if field.value == 0:
                sys.tracebacklimit = 0
                raise Exception(">> ERROR: Can't divide by 0! <<\n")

            return Galois(self.value * pow(field.value, -1, self.order))

        def __abs__(self):
            return abs(self.value)
        
        def __eq__(self, field):
            return self.value == field.value

        def __ne__(self, field):
            return self.value != field.value

        def __ge__(self, field):
            return self.value >= field.value

        def __le__(self, field):
            return self.value <= field.value

        def __gt__(self, field):
            return self.value > field.value

        def __lt__(self, field):
            return self.value < field.value
        
    return Galois