import csv
import sys

FILENAME = "employee.csv"

#exit the program
def exitProgram():
    print('Terminating program')
    sys.exit()

#read employees from the file
def readEmployees():
    try:
        employees = []
        with open(FILENAME, newline="") as file:
            reader = csv.reader(file)
            for row in reader:
                employees.append(row)
        return employees
    except FileNotFoundError as error:
        print(f"Could not find {FILENAME} file.")
        exitProgram()
    except Exception as e:
        print(type(e), e)
        exitProgram()

#write employees to file
def writeEmployees(employees):
    try:
        with open(FILENAME, 'w', newline='') as file:
            writer = csv.writer(file)
            writer.writerows(employees)
    except Exception as e:
        print(type(e), e)
        exitProgram()

#add employees to the list
def addEmployee(employees):
    empID = input("Enter the employee ID: ")
    sal = input("Enter the salary of the employee: ")
    employee = [empID,sal]
    employees.append(employee)
    writeEmployees(employees)
    print(f'Employee {empID}: {sal} was added.\n')

def listEmployees(employees):
    for i,employee in enumerate(employees, start = 0):
        print(f"{i}. Employee ID: {employee[0]} (${employee[1]})")
    print()

def deleteEmployee(employees):
    found = False
    number = input('What is the Employee ID?: ')
    for i, employee in enumerate(employees, start = 0):
        if employee[0] == number:
            print(f'Employee was deleted.\n')
            employee = employees.pop(i)
            found = True
    if found == False:
        print('Employee was not found.')
    else:
        writeEmployees(employees)

def displayMenu():
    print("The Employee Salary List program")
    print()
    print("LIST OF COMMANDS")
    print("list - List all employees")
    print("add -  Add an employee")
    print("del -  Delete an employee")
    print("exit - Exit program")
    print()    

employee = readEmployees()
print(employee[0][1])




