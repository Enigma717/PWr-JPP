# Marek Traczyński (261748)
# Języki i Paradygmaty Programowania
# Lista 3 Zadanie 4


import sys


def polynomial_type(coeff_type):
    class Polynomial(object):

        def __init__(self, coeffs):
            self.coefficients = coeffs

            while len(self.coefficients) > 1 and self.coefficients[-1] == coeff_type(0):
                self.coefficients.pop()

            self.degree = len(self.coefficients) - 1


        @classmethod
        def builder(cls, coeffs):
            if not hasattr(coeffs, '__iter__'):
                return Polynomial([coeff_type(coeffs)])
            
            typed_coeffs = [coeff_type(c) for c in coeffs]
            return Polynomial(typed_coeffs)


        ##############################
        
        def is_zero_poly(self):
            if len(self.coefficients) == 1 and self.coefficients[0] == coeff_type(0):
                return True

            return False

        ##############################
        
        def __add__(self, poly):
            p1_size = len(self.coefficients)
            p2_size = len(poly.coefficients)
            result_size = max(p1_size, p2_size)
            result = []

            for i in range(0, result_size):
                sum = coeff_type(0)
                
                if i < p1_size:
                    sum = sum + self.coefficients[i]
                if i < p2_size:
                    sum = sum + poly.coefficients[i]
                
                result.append(sum)
            
            return Polynomial(result)

        def __sub__(self, poly):
            temp = [c * coeff_type(-1) for c in poly.coefficients]
            result = self + Polynomial(temp)  

            return result

        def __mul__(self, poly):
            if self.is_zero_poly() == True or poly.is_zero_poly() == True:
                return Polynomial([coeff_type(0)])
            
            p1_size = len(self.coefficients)
            p2_size = len(poly.coefficients)
            result_size = p1_size + p2_size - 1
            result = [coeff_type(0)] * result_size
            
            for i in range(0, p1_size):
                for j in range(0, p2_size):
                    result[i+j] = result[i+j] + (self.coefficients[i] * poly.coefficients[j])

            return Polynomial(result)

        def __divmod__(self, divisor):
            if divisor.is_zero_poly():
                sys.tracebacklimit = 0
                raise Exception(">> ERROR: Can't divide by 0 polynomial! <<\n")

            result = Polynomial([coeff_type(0)])
            remainder = self 

            while remainder.is_zero_poly() == False and remainder.degree >= divisor.degree:
                degree = remainder.degree - divisor.degree
                coeff = remainder.coefficients[-1] / divisor.coefficients[-1]
                temp_coeffs = [coeff_type(0)] * degree
                temp_coeffs += [coeff]

                temp_poly = Polynomial(temp_coeffs)

                result = result + temp_poly
                remainder = remainder - (divisor * temp_poly)
            
            return (result, remainder)

        def __truediv__(self, poly):
            return divmod(self, poly)[0]

        def __mod__(self, poly):
            return divmod(self, poly)[1]

        ##############################

        def __eq__(self, poly):
            return self.coefficients == poly.coefficients

        def __ne__(self, poly):
            return self.coefficients != poly.coefficients

        def __ge__(self, poly):
            if self.degree > poly.degree:
                return True
            elif self.degree < poly.degree:
                return False
            else:
                return self.coefficients >= poly.coefficients

        def __le__(self, poly):
            if self.degree < poly.degree:
                return True
            elif self.degree > poly.degree:
                return False
            else:
                return self.coefficients <= poly.coefficients
                
        def __gt__(self, poly):
            if self.degree > poly.degree:
                return True
            elif self.degree < poly.degree:
                return False
            else:
                return self.coefficients > poly.coefficients

        def __lt__(self, poly):
            if self.degree > poly.degree:
                return True
            elif self.degree < poly.degree:
                return False
            else:
                return self.coefficients < poly.coefficients

        ##############################
        
        def __call__(self, value):
            result = self.coefficients[-1]

            for i in range(self.degree - 1, 0, -1):
                result = result * coeff_type(value) + self.coefficients[i]
            
            return result
        
        def __repr__(self):
            chars = {(True, True): '-',
                     (True, False): '',
                     (False, True): ' - ',
                     (False, False): ' + '}

            result = []


            for exponent, coeff in reversed(list(enumerate(self.coefficients))):
                j = chars[not result, coeff < coeff_type(0)]
                coeff = abs(coeff)

                if coeff == 0:
                    continue
                
                if coeff == 1 and exponent != 0:
                    coeff = ''
                
                f = {0: '{}{}', 1: '{}{}x'}.get(exponent, '{}{}x^{}')

                result.append(f.format(j, coeff, exponent))
            
            return ''.join(result) or '0'

        def __str__(self):
            return repr(self)

    return Polynomial
