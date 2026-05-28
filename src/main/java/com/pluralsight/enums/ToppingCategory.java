package com.pluralsight.enums;

/**
 * Classifies toppings into premium (MEAT, CHEESE) and regular (everything else).
 *
 * PremiumTopping.getPrice() checks this category to decide which price
 * tier to apply (meat vs. cheese rates differ from the spec).
 */
public enum ToppingCategory {

    MEAT        ("Meat"),
    CHEESE      ("Cheese"),
    VEGETABLE   ("Vegetable"),
    SAUCE       ("Sauce"),
    SIDE        ("Side");

    // ── Fields ────────────────────────────────────────────────────────────
    private final String label;

    // ── Constructor ───────────────────────────────────────────────────────
    ToppingCategory(String label) {
        this.label = label;
    }

    // ── Accessors ─────────────────────────────────────────────────────────
    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }
}
