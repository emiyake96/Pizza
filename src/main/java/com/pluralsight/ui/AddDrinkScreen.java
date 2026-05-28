package com.pluralsight.ui;

import com.pluralsight.model.Drink;
import com.pluralsight.util.Console;

/**
 * Lets the customer pick a drink size and enter a flavor.
 */
public class AddDrinkScreen {

    public Drink show() {
        System.out.println("\n── Add a Drink ─────────────────────");

        // ── Size ──────────────────────────────────────────────────────────
        System.out.println("  1) Small  - $2.00");
        System.out.println("  2) Medium - $2.50");
        System.out.println("  3) Large  - $3.00");

        String size;
        int choice = Console.getInt("Select size: ");
        switch (choice) {
            case 1:  size = Drink.SMALL;  break;
            case 2:  size = Drink.MEDIUM; break;
            case 3:  size = Drink.LARGE;  break;
            default: size = Drink.MEDIUM; break;
        }

        // ── Flavor ────────────────────────────────────────────────────────
        String flavor = Console.getString("  Enter flavor: ");

        Drink drink = new Drink(size, flavor);
        System.out.printf("  Added: %s %s ($%.2f)%n", size, flavor, drink.getPrice());
        return drink;
    }
}
