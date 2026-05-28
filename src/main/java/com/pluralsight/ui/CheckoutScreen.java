package com.pluralsight.ui;

import com.pluralsight.model.Order;
import com.pluralsight.model.OrderItem;
import com.pluralsight.util.Console;
import com.pluralsight.util.ReceiptWriter;

import java.util.List;

/**
 * Displays the full order summary and total price.
 * Customer can confirm (saves receipt) or cancel (discards order).
 *
 * Spec:
 *   Confirm → create receipt file → back to home screen
 *   Cancel  → delete order       → back to home screen
 */
public class CheckoutScreen {

    public void show(Order order) {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║           ORDER SUMMARY              ║");
        System.out.println("╚══════════════════════════════════════╝");

        // ── Print every item ──────────────────────────────────────────────
        List<OrderItem> items = order.getItemsNewestFirst();
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%n%d. %s%n", i + 1, items.get(i).getDescription());
        }

        // ── Total ─────────────────────────────────────────────────────────
        System.out.println("\n══════════════════════════════════════");
        System.out.printf("  ORDER TOTAL: $%.2f%n", order.getTotal());
        System.out.println("══════════════════════════════════════");

        // ── Confirm or cancel ─────────────────────────────────────────────
        System.out.println("\n  1) Confirm Order");
        System.out.println("  0) Cancel Order");

        int choice = Console.getInt("Select: ");

        if (choice == 1) {
            ReceiptWriter.save(order);
            System.out.println("\n  Order confirmed! Your receipt has been saved.");
            System.out.println("  Thank you for ordering from Pizza!");
        } else {
            System.out.println("\n  Order cancelled. Returning to home screen.");
        }
    }
}
