package com.pluralsight.enums;

/**
 * The three available pizza sizes.
 *
 * WHY store data in the enum? Each size has a fixed base price that will
 * never change at runtime. Putting the price directly on the constant means
 * we never need a switch/if chain elsewhere — just call size.getBasePrice().
 * This is the "behavior-rich enum" pattern.
 */
public enum PizzaSize {

    PERSONAL_8 ("Personal 8\"",  8.50),
    MEDIUM_12  ("Medium 12\"",  12.00),
    LARGE_16   ("Large 16\"",   16.50);

    // ── Fields ────────────────────────────────────────────────────────────
    private final String label;
    private final double basePrice;

    // ── Constructor ───────────────────────────────────────────────────────
    PizzaSize(String label, double basePrice) {
        this.label     = label;
        this.basePrice = basePrice;
    }

    // ── Accessors ─────────────────────────────────────────────────────────
    public String getLabel()     { return label; }
    public double getBasePrice() { return basePrice; }

    @Override
    public String toString() { return label; }
}
