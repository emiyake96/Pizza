package com.pluralsight.model;

import com.pluralsight.enums.CrustType;
import com.pluralsight.enums.PizzaSize;

import java.util.ArrayList;
import java.util.List;

/**
 * Core model for a single customizable pizza.
 * Implements OrderItem so Order can hold it alongside Drink and GarlicKnots.
 *
 * Pricing formula:
 *   total = basePrice (from PizzaSize)
 *         + sum of topping prices (each topping knows its own price by size)
 *         + stuffedCrustFee (if requested)
 *
 * The topping loop uses the Topping abstraction — Pizza doesn't care whether
 * a topping is Premium or Regular. It just calls topping.getPrice(size) and
 * polymorphism handles the rest.
 */
public class Pizza implements OrderItem {

    // Stuffed crust is not priced in the spec — using a standard $1.50 upcharge.
    private static final double STUFFED_CRUST_FEE = 1.50;

    // ── Fields ────────────────────────────────────────────────────────────
    private PizzaSize      size;
    private CrustType      crust;
    private List<Topping>  toppings;
    private boolean        stuffedCrust;

    // ── Constructor ───────────────────────────────────────────────────────
    public Pizza(PizzaSize size, CrustType crust) {
        this.size         = size;
        this.crust        = crust;
        this.toppings     = new ArrayList<>();
        this.stuffedCrust = false;
    }

    // ── OrderItem implementation ──────────────────────────────────────────

    /**
     * Calculates the total price of this pizza.
     *
     * Uses a traditional for-each here.
     * Each topping.getPrice(size) delegates to PremiumTopping or RegularTopping
     */
    @Override
    public double getPrice() {
        double total = size.getBasePrice();

        for (Topping t : toppings) {
            total += t.getPrice(size);
        }

        if (stuffedCrust) {
            total += STUFFED_CRUST_FEE;
        }

        return total;
    }

    /**
     * Builds a full human-readable description for receipts and the order screen.
     *
     * Example output:
     *   Medium 12" Pizza | Regular Crust | Stuffed Crust
     *     + Pepperoni
     *     + Mozzarella (extra)
     *     + Tomatoes
     *     + Marinara
     *   Price: $14.75
     */
    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();

        // ── Header line ───────────────────────────────────────────────────
        sb.append(size.getLabel()).append(" Pizza | ")
          .append(crust.getLabel()).append(" Crust");

        if (stuffedCrust) {
            sb.append(" | Stuffed Crust");
        }

        // ── Toppings list ─────────────────────────────────────────────────
        if (toppings.isEmpty()) {
            sb.append("\n  (no toppings)");
        } else {
            for (Topping t : toppings) {
                sb.append("\n  + ").append(t.toString());
            }
        }

        // ── Price line ────────────────────────────────────────────────────
        sb.append(String.format("%n  Price: $%.2f", getPrice()));

        return sb.toString();
    }

    // ── Getters / Setters ─────────────────────────────────────────────────
    public PizzaSize     getSize()        { return size; }
    public CrustType     getCrust()       { return crust; }
    public List<Topping> getToppings()    { return toppings; }
    public boolean       isStuffedCrust() { return stuffedCrust; }

    public void setStuffedCrust(boolean stuffedCrust) {
        this.stuffedCrust = stuffedCrust;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }
}
