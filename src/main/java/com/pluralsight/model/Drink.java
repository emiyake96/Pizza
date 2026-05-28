package com.pluralsight.model;

/**
 * A drink order item. Price depends on size.
 */
public class Drink implements OrderItem {

    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        // TODO: map size to price (2.00 / 2.50 / 3.00)
        return 0.0;
    }

    @Override
    public String getDescription() {
        return size + " " + flavor + " drink";
    }
}
