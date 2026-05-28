package com.pluralsight.util;

import java.util.Scanner;

/**
 * Shared input helper for all screen classes.
 *
 */
public class Console {

    private static final Scanner scanner = new Scanner(System.in);

    /** Prompts the user and returns a trimmed String. */
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Prompts the user for an integer. Re-prompts on invalid input
     * so callers never have to handle InputMismatchException.
     */
    public static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a number.");
            }
        }
    }

    /**
     * Prompts for a yes/no answer. Accepts y/Y/n/N.
     * Returns true for yes, false for no.
     */
    public static boolean getYesNo(String prompt) {
        while (true) {
            String input = getString(prompt + " (y/n): ");
            if (input.equalsIgnoreCase("y")) return true;
            if (input.equalsIgnoreCase("n")) return false;
            System.out.println("  Please enter y or n.");
        }
    }
}
