package com.pluralsight.ui;

import com.pluralsight.model.GarlicKnots;
import com.pluralsight.model.Order;
import com.pluralsight.model.OrderItem;
import com.pluralsight.util.Console;

import java.util.List;

/**
 * Manages an active order. Loops until the customer checks out or cancels.
 *
 * Spec:
 *   1) Add Pizza
 *   2) Add Drink
 *   3) Add Garlic Knots
 *   4) Checkout
 *   0) Cancel Order
 *
 * Items are displayed newest-first per the spec requirement.
 */
public class OrderScreen {

    public void show(Order order) {
        boolean ordering = true;

        while (ordering) {
            displayOrder(order);

            System.out.println("──────────────────────────────");
            System.out.println("  1) Add Pizza");
            System.out.println("  2) Add Drink");
            System.out.println("  3) Add Garlic Knots");
            System.out.println("  4) Checkout");
            System.out.println("  0) Cancel Order");
            System.out.println("──────────────────────────────");

            int choice = Console.getInt("Select: ");

            switch (choice) {
                case 1:
                    // AddPizzaScreen returns the built Pizza; we add it to the order
                    order.addItem(new AddPizzaScreen().show());
                    break;

                case 2:
                    order.addItem(new AddDrinkScreen().show());
                    break;

                case 3:
                    int qty = Console.getInt("  How many garlic knots? ");
                    order.addItem(new GarlicKnots(qty));
                    System.out.println("  Garlic knots added!");
                    break;

                case 4:
                    if (!order.isValid()) {
                        System.out.println("\n  An order with no pizza must include at least a drink or garlic knots.");
                    } else {
                        new CheckoutScreen().show(order);
                        ordering = false;   // return to home after checkout
                    }
                    break;

                case 0:
                    System.out.println("\n  Order cancelled.");
                    ordering = false;
                    break;

                default:
                    System.out.println("  Invalid option.");
            }
        }
    }

    // ── Private helpers ───────────────────────────────────────────────────

    /** Prints the current order items newest-first with a running total. */
    private void displayOrder(Order order) {
        System.out.println("\n════════════ YOUR ORDER ════════════");

        if (order.isEmpty()) {
            System.out.println("  (no items yet)");
        } else {
            List<OrderItem> items = order.getItemsNewestFirst();
            for (int i = 0; i < items.size(); i++) {
                System.out.printf("  %d. %s%n", i + 1, items.get(i).getDescription());
            }
            System.out.printf("%n  Running total: $%.2f%n", order.getTotal());
        }
        System.out.println("════════════════════════════════════");
    }
}
