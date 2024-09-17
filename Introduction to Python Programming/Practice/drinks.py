import sys
drinkdetails=""
drink = input("What type of drink do you want?\nwater\ncoffee\ntea\nEnter your choice here: ")
if drink == "water":
  drinkdetails = drink
  temperature = input("Would you like your water cold or warm? ")
  if temperature == "cold":
    drinkdetails += ", " + temperature
  elif temperature == "warm":
    drinkdetails += ", "+ temperature
  else:
    drinkdetails += ", unknown temperature"

elif drink == "coffee":
  drinkdetails = drink
  caffeine = input("Do you want decaf? ")
  if caffeine == "yes":
    drinkdetails += ", decaf"
  elif caffeine == "no":
    drinkdetails += ""
  else:
    print("Try again.")
    sys.exit()
  milkorcream = input("Do you want milk or cream? ")
  if milkorcream == "milk":
    drinkdetails += ", milk"
  elif milkorcream == "cream":
    drinkdetails += ", cream"
  elif milkorcream == "no":
    drinkdetails += ""
  else:
    print("Try again.")
    sys.exit
  sugar = input("Do you want sugar?")
  if sugar ==  "yes":
    drinkdetails += ", sugar"
  elif sugar == "no":
    drinkdetails = ""

elif drink == "tea":
  drinkdetails = drink
  teatype = input("Do you want green or black tea? ")
  if teatype == "green":
    drinkdetails += ", green"
  elif teatype == "black":
    drinkdetails += ", black"
  else:
    drinkdetails = "This tea does not exist."
else:
  print("Sorry, we don't have that drink available for you.")
print("Your drink is:", drinkdetails