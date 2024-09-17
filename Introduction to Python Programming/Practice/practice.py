class employees:

    numOfEmployees = 0
    raiseAmt = 1.05

    def __init__(self, first, last, practice, pay):
        self.first = first
        self.last = last
        self.practice = practice
        self.pay = pay
        self.employeeNum = int(employees.numOfEmployees + 1)

        employees.numOfEmployees =+ 1
    
    def fullName(self):
        return '{} {}'.format(self.first, self.last)
    
    def applyRaise(self):
        self.pay = self.pay * self.raiseAmt
    

emp1 = employees('Duc Huy', 'Ho', 'Architecture', 50000)
emp2 = employees('Hailey', 'Wilkins', 'Architecture', 60000)

print(emp1.pay)
print(emp2.pay)

employees.applyRaise(emp1)

print(emp1.pay)
print(emp2.pay)