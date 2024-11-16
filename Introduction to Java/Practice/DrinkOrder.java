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
        }
        else if (choice == 3) {
            drinkDetails = "Tea";
        }
        else {
            System.out.println("Sorry, not a valid drink selection.");
        }

        System.out.println("Your drink selection is: " + drinkDetails + ".");
    }
}