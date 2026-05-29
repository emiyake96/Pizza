package com.pluralsight.ui;

import com.pluralsight.model.Order;
import com.pluralsight.util.Console;

/**
 * The first screen the user sees. Loops until they choose to exit.
 *
 * Spec:
 *   1) New Order
 *   0) Exit
 */
public class HomeScreen {

    public void show() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║   Welcome to Pizza Marumo!   ║");
        System.out.println("╚══════════════════════════════╝");

        boolean running = true;
        while (running) {
            System.out.println("\n──────────────────────────────");
            System.out.println("  1) New Order");
            System.out.println("  0) Exit");
            System.out.println("──────────────────────────────");

            int choice = Console.getInt("Select: ");

            switch (choice) {
                case 1:
                    Order order = new Order();
                    new OrderScreen().show(order);
                    break;
                case 0:
                    running = false;
                    System.out.println("\nThanks for visiting Pizza! Goodbye.");
                    break;
                default:
                    System.out.println("  Invalid option. Please try again.");
            }
        }
    }
}
