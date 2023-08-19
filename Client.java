package assignment1; // change to your package name

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author smoo
 */
import assignment1.ChangeC;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of persons:");
        int count = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        ChangeC[] persons = new ChangeC[count];

        for (int i = 0; i < count; i++) {
            System.out.println("Enter the name for person " + (i + 1) + ":");
            String name = scanner.nextLine();

            System.out.println("Enter the coin amount for person " + (i + 1) + ":");
            int[] coinAmounts = new int[8];

            System.out.println("Enter the number of 1p coins:");
            coinAmounts[7] = scanner.nextInt();

            System.out.println("Enter the number of 2p coins:");
            coinAmounts[6] = scanner.nextInt();

            System.out.println("Enter the number of 5p coins:");
            coinAmounts[5] = scanner.nextInt();

            System.out.println("Enter the number of 10p coins:");
            coinAmounts[4] = scanner.nextInt();

            System.out.println("Enter the number of 20p coins:");
            coinAmounts[3] = scanner.nextInt();

            System.out.println("Enter the number of 50p coins:");
            coinAmounts[2] = scanner.nextInt();

            System.out.println("Enter the number of £1 coins:");
            coinAmounts[1] = scanner.nextInt();

            System.out.println("Enter the number of £2 coins:");
            coinAmounts[0] = scanner.nextInt();

            scanner.nextLine(); // Consume the newline character

            persons[i] = new ChangeC(name, coinAmounts);
        }

        int choice = 0;

        while (choice != 6) {
            displayMenu();
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayPersonChange(persons);
                    break;
                case 2:
                    displaySmallestChange(persons, count);
                    break;
                case 3:
                    displayLargestChange(persons, count);
                    break;
                case 4:
                    displayTotalCoins(persons, count);
                    break;
                case 5:
                    displayTotalAmount(persons, count);
                    break;
                case 6:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            // adding a back button for the menu incase of accidental inputs from the user
            

            System.out.println();
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Enter a name and display change to be given for each denomination");
        System.out.println("2. Find the name with the smallest amount and display change to be \n" +
"given for each denomination");
        System.out.println("3. Find the name with the largest amount and display change to be given for each denomination");
        System.out.println("4. Calculate and display the total number of coins for each denomination");
        System.out.println("5. Calculate and display the total amount for the sum of all denominations ");
        System.out.println("6. Exit");
    }

        private static void displayPersonChange(ChangeC[] persons) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the person:");
        String name = scanner.nextLine();

        boolean personFound = false;

        for (ChangeC person : persons) {
            if (person.getName().equalsIgnoreCase(name)) {
                personFound = true;
                System.out.println("Customer: " + person.getName() + " " + getTotalCoinAmount(person.getCoinAmounts()) + " pence");
                System.out.println("Change:");
                displayCoinDenominations(person.getCoinAmounts());
                break;
            }
        }

        if (!personFound) {
            System.out.println("Name: " + name);
            System.out.println("Not found");
        }
    }

    private static void displaySmallestChange(ChangeC[] persons, int count) {
        int smallestAmount = Integer.MAX_VALUE;
        ChangeC smallestPerson = null;

        for (int i = 0; i < count; i++) {
            int totalAmount = getTotalCoinAmount(persons[i].getCoinAmounts());
            if (totalAmount < smallestAmount) {
                smallestAmount = totalAmount;
                smallestPerson = persons[i];
            }
        }

        if (smallestPerson != null) {
            System.out.println("Person with the smallest change:");
            System.out.println("Name: " + smallestPerson.getName());
            System.out.println("Change:");
            displayCoinDenominations(smallestPerson.getCoinAmounts());
        } else {
            System.out.println("No persons found.");
        }
    }

    private static void displayLargestChange(ChangeC[] persons, int count) {
        int largestAmount = Integer.MIN_VALUE;
        ChangeC largestPerson = null;

        for (int i = 0; i < count; i++) {
            int totalAmount = getTotalCoinAmount(persons[i].getCoinAmounts());
            if (totalAmount > largestAmount) {
                largestAmount = totalAmount;
                largestPerson = persons[i];
            }
        }

        if (largestPerson != null) {
            System.out.println("Person with the largest change:");
            System.out.println("Name: " + largestPerson.getName());
            System.out.println("Change:");
            displayCoinDenominations(largestPerson.getCoinAmounts());
        } else {
            System.out.println("No persons found.");
        }
    }

    private static void displayTotalCoins(ChangeC[] persons, int count) {
        int[] totalCoins = new int[8];

        for (int i = 0; i < count; i++) {
            int[] coinAmounts = persons[i].getCoinAmounts();
            for (int j = 0; j < coinAmounts.length; j++) {
                totalCoins[j] += coinAmounts[j];
            }
        }

        System.out.println("Total number of coins:");
        displayCoinDenominations(totalCoins);
    }

    private static void displayTotalAmount(ChangeC[] persons, int count) {
        int totalAmount = 0;

        for (int i = 0; i < count; i++) {
            totalAmount += getTotalCoinAmount(persons[i].getCoinAmounts());
        }

        System.out.println("Total amount: " + totalAmount + " pence");
    }

    private static int getTotalCoinAmount(int[] coinAmounts) {
        int totalAmount = 0;
        int[] denominations = { 200, 100, 50, 20, 10, 5, 2, 1 };

                for (int i = 0; i < coinAmounts.length; i++) {
            totalAmount += coinAmounts[i] * denominations[i];
        }

        return totalAmount;
    }

    private static void displayCoinDenominations(int[] coinAmounts) {
        String[] denominations = { "£2", "£1", "50p", "20p", "10p", "5p", "2p", "1p" };

        for (int i = 0; i < coinAmounts.length; i++) {
            if (coinAmounts[i] > 0) {
                System.out.println(denominations[i] + ": " + coinAmounts[i]);
            }
        }
    }
}



