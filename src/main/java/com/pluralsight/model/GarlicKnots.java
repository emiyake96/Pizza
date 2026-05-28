package com.pluralsight.model;

/**
 * Garlic knots — $1.50 flat per order, any quantity.
 * The spec lists garlic knots as a single item with no size tiers.
 */
public class GarlicKnots implements OrderItem {

    private static final double PRICE = 1.50;

    // ── Fields ────────────────────────────────────────────────────────────
    private int quantity;

    // ── Constructor ───────────────────────────────────────────────────────
    public GarlicKnots(int quantity) {
        this.quantity = quantity;
    }

    // ── OrderItem implementation ──────────────────────────────────────────
    @Override
    public double getPrice() {
        return PRICE * quantity;
    }

    @Override
    public String getDescription() {
        return String.format("Garlic Knots x%d .................. $%.2f", quantity, getPrice());
    }

    // ── Getter ────────────────────────────────────────────────────────────
    public int getQuantity() { return quantity; }
}
