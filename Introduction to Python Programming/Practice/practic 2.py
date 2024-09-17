import datetime

class Person:

    numberOfEmployees = 0

    def __init__(self, fname, lname):
        self.firstname = fname
        self.lastname = lname
        self.employeeid = Employee.numberOfEmployees + 1

        self.hiredate = datetime.date.today()
        Employee.numberOfEmployees = Employee.numberOfEmployees + 1

    def getFirstName(self):
        return self.firstname
    
    def setFirstName(self, fname):
        if len(fname) > 0:
            self.firstname = fname

    def getLastName(self):
        return self.lastname

    def setLastName(self, lname):
        if len(lname) > 0:
            self.lastname = lname
    
    


class Employee(Person):
    def __init__(self, fname, lname, title, sal):
        super().__init__(fname, lname)
        self.jobtitle = title
        self.salary = sal

    def getEmployeeID(self):
        return "Employee ID: " + str(self.employeeid)
    
    def getSalary(self):
        return '${:,.2f}'.format(self.salary)
    
    def setSalary(self, sal):
        if sal > 0:
            self.salary = sal

    def increaseSalary(self, percent):
        if percent > 0:
            self.setSalary(percent * self.salary + self.salary)
    

hailey = Employee('Hailey','Wilkins','Designer',70000)
jamison = Employee('Jamison','Owen','Designer', 70000)
print(hailey.firstname)
print(hailey.lastname)
print(hailey.employeeid)
print(hailey.jobtitle)
print(hailey.salary)
print('\n')
print('-----')
print('\n')
print(jamison.firstname)
print(jamison.lastname)
print(jamison.employeeid)
print(jamison.jobtitle)
print(jamison.salary)

jamison.increaseSalary(.02)
print(jamison.salary)
