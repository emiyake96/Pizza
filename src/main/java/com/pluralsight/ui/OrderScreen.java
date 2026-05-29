package com.pluralsight.ui;

import com.pluralsight.model.GarlicKnots;
import com.pluralsight.model.Order;
import com.pluralsight.model.OrderItem;
import com.pluralsight.signature.MargheritaPizza;
import com.pluralsight.signature.VeggiePizza;
import com.pluralsight.util.Console;
import com.pluralsight.util.PizzaVisualizer;

import java.util.List;

/**
 * Manages an active order. Loops until the customer checks out or cancels.
 *
 *   1) Add Custom Pizza
 *   2) Add Signature Pizza  ← new
 *   3) Add Drink
 *   4) Add Garlic Knots
 *   5) Checkout
 *   0) Cancel Order
 */
public class OrderScreen {

    public void show(Order order) {
        boolean ordering = true;

        while (ordering) {
            displayOrder(order);

            System.out.println("──────────────────────────────");
            System.out.println("  1) Add Custom Pizza");
            System.out.println("  2) Add Signature Pizza");
            System.out.println("  3) Add Drink");
            System.out.println("  4) Add Garlic Knots");
            System.out.println("  5) Checkout");
            System.out.println("  0) Cancel Order");
            System.out.println("──────────────────────────────");

            int choice = Console.getInt("Select: ");

            switch (choice) {
                case 1:
                    order.addItem(new AddPizzaScreen().show());
                    break;

                case 2:
                    addSignaturePizza(order);
                    break;

                case 3:
                    order.addItem(new AddDrinkScreen().show());
                    break;

                case 4:
                    int qty = Console.getInt("  How many garlic knots? ");
                    order.addItem(new GarlicKnots(qty));
                    System.out.println("  Garlic knots added!");
                    break;

                case 5:
                    if (!order.isValid()) {
                        System.out.println("\n  An order with no pizza must include at least a drink or garlic knots.");
                    } else {
                        new CheckoutScreen().show(order);
                        ordering = false;
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

    // ── Signature pizza selection ─────────────────────────────────────────

    private void addSignaturePizza(Order order) {
        System.out.println("\n  ── Signature Pizzas ──────────────────────────");
        System.out.println("  1) Margherita  — 12\" Regular | Mozzarella, Tomatoes, Basil, Marinara, Olive Oil");
        System.out.println("  2) Veggie      —  8\" Regular | Bell Peppers, Spinach, Olives, Onions, Marinara, Mozzarella");
        System.out.println("  0) Back");
        System.out.println("  ────────────────────────────────────────────────");

        int pick = Console.getInt("Select: ");
        switch (pick) {
            case 1:
                MargheritaPizza margherita = new MargheritaPizza();
                PizzaVisualizer.render(margherita);
                order.addItem(margherita);
                System.out.println("  Margherita added!");
                break;
            case 2:
                VeggiePizza veggie = new VeggiePizza();
                PizzaVisualizer.render(veggie);
                order.addItem(veggie);
                System.out.println("  Veggie pizza added!");
                break;
            case 0:
                break;
            default:
                System.out.println("  Invalid option.");
        }
    }

    // ── Order display ─────────────────────────────────────────────────────

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
