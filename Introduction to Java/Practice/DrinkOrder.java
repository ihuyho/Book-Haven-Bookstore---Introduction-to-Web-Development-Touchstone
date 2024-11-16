import java.util.Scanner;

public class DrinkOrder {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("What type of drink would you like to order?");
        System.out.println("1. Water\n2. Coffee\n3. Tea");
        System.out.print("Drink Selection Number: ");
        String drinkDetails = "No drink chosen.";
        int choice = input.nextInt();
        
        if (choice == 1) {
            drinkDetails = "Water";
            System.out.println("Do you want hot or cold water?");
            System.out.println("1. Hot\n2. Cold");
            int waterChoice = input.nextInt();

            if (waterChoice == 1) {
                drinkDetails = "Hot Water";
            }
            else if (waterChoice == 2) {
                System.out.println("Do you want ice?");
                System.out.println("1. Ice\n2. No Ice");
                int iceChoice = input.nextInt();

                if (iceChoice == 1) {
                    drinkDetails = "Ice Water";
                }
                else if (iceChoice == 2) {
                    drinkDetails = "Cold Water";
                }
                else {
                    System.out.println("Sorry, not a valid option.");
                }
            }
            else {
                System.out.println("Sorry, not a valid option.");
            }
            
        }
        else if (choice == 2) {
            drinkDetails = "Coffee";
            System.out.println("Regular or Decaf?");
            System.out.println("1. Regular\n2. Decaf");
            int coffeeChoice = input.nextInt();

            if (coffeeChoice == 1) {
                drinkDetails = drinkDetails + ", Regular";
            }  
            else if (coffeeChoice == 2) {
                drinkDetails = drinkDetails + ". Decaf";
            }
            else {
                System.out.println("Sorry, not a valid option.");
            }
            
            System.out.println("Do you want milk, cream, or none?");
            System.out.println("1. Milk\n2. Cream\n3. None");
            int milkChoice = input.nextInt();

            if (milkChoice == 1) {
                drinkDetails = drinkDetails + ", Milk";
            }
            else if (milkChoice == 2) {
                drinkDetails = drinkDetails + ", Cream";
            }
            else if (milkChoice == 3) {
                drinkDetails = drinkDetails + ", No Milk or Cream";
            }
            else {
                System.out.println("Sorry, not a valid option.");
            }

            System.out.println("Do you want sugar or no sugar?");
            System.out.println("1. Sugar\n2. No Sugar");
            int sugarChoice = input.nextInt();

            if (sugarChoice == 1) {
                drinkDetails = drinkDetails + ", Sugar";
            }
            else if (sugarChoice == 2) {
                drinkDetails = drinkDetails + ", No Sugar";
            }
            else {
                System.out.println("Sorry, not a valid option.");
            }

        }
        else if (choice == 3) {
            drinkDetails = "Tea";
            System.out.println("Do you want green tea or black tea?");
            System.out.println("1. Green\n2. Black");
            int teaChoice = input.nextInt();

            if (teaChoice == 1) {
                drinkDetails = drinkDetails + "Green";
            }
            else if (teaChoice == 2) {
                drinkDetails = drinkDetails + "Black";
            }
            else {
                System.out.println("Sorry, not a valid option.");
            }
        }
        else {
            System.out.println("Sorry, not a valid drink selection.");
        }

        System.out.println("Your drink selection is: " + drinkDetails + ".");
    }
}