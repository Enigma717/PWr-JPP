# Marek Traczyński (261748)
# Języki i Paradygmaty Programowania
# Lista 2 Zadanie 3


class Galois:
    FIELD_ORDER = 1234567891

    def __init__(self, value):
        self.value = value % Galois.FIELD_ORDER

    def __str__(self):
        return f"GF({Galois.FIELD_ORDER:,})[{self.value:,}]"


    def __add__(self, field):
        return Galois(self.value + field.value)

    def __sub__(self, field):
        return Galois(self.value - field.value)

    def __mul__(self, field):
        return Galois(self.value * field.value)

    def __truediv__(self, field):
        if field.value == 0:
            raise Exception(">> ERROR: Can't divide by 0! <<")

        return Galois(self.value * pow(field.value, -1, Galois.FIELD_ORDER))

    
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
