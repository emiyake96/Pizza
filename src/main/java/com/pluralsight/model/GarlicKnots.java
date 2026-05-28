package com.pluralsight.model;

/**
 * Garlic knots — $1.50 flat, quantity-based.
 */
public class GarlicKnots implements OrderItem {

    private static final double PRICE = 1.50;
    private int quantity;

    public GarlicKnots(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getPrice() {
        return PRICE * quantity;
    }

    @Override
    public String getDescription() {
        return "Garlic Knots (x" + quantity + ")";
    }
}
