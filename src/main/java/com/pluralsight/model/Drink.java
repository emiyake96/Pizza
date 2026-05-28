package com.pluralsight.model;

/**
 * A drink order item. Size drives the price per the spec:
 *   Small  → $2.00
 *   Medium → $2.50
 *   Large  → $3.00
 */
public class Drink implements OrderItem {

    // ── Size constants — used by AddDrinkScreen to populate the menu ───────
    public static final String SMALL  = "Small";
    public static final String MEDIUM = "Medium";
    public static final String LARGE  = "Large";

    // ── Fields ────────────────────────────────────────────────────────────
    private String size;
    private String flavor;

    // ── Constructor ───────────────────────────────────────────────────────
    public Drink(String size, String flavor) {
        this.size   = size;
        this.flavor = flavor;
    }

    // ── OrderItem implementation ──────────────────────────────────────────
    @Override
    public double getPrice() {
        switch (size) {
            case SMALL:  return 2.00;
            case MEDIUM: return 2.50;
            case LARGE:  return 3.00;
            default:     return 0.00;
        }
    }

    @Override
    public String getDescription() {
        return String.format("%s %s (drink) ............. $%.2f", size, flavor, getPrice());
    }

    // ── Getters ───────────────────────────────────────────────────────────
    public String getSize()   { return size; }
    public String getFlavor() { return flavor; }
}
