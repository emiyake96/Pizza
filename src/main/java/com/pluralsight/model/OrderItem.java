package com.pluralsight.model;

/**
 * Interface implemented by Pizza, Drink, and GarlicKnots.
 * Lets Order hold everything in one List<OrderItem>
 */
public interface OrderItem {
    double getPrice();
    String getDescription();
}
