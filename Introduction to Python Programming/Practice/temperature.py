"""
This module contains functions for converting temperatures between Fahrenheit and Celsius
"""
def toCelsius(fahrenheit):
  """
  Accepts degrees Fahrenheit (as argument) and returns celsius
  """
  celsius = (fahrenheit - 32) * 5/9 
  return celsius
  
def toFahrenheit(celsius): 
  """
  Accepts degrees Celsius (as argument) and returns Fahrenheit
  """
  fahrenheit = celsius * 9/5 + 32 
  return fahrenheit