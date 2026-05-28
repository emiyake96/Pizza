package com.pluralsight.model;

import com.pluralsight.enums.PizzaSize;
import com.pluralsight.enums.ToppingCategory;

/**
 * Vegetables, sauces, and sides — always free (included in pizza price).
 */
public class RegularTopping extends Topping {

    public RegularTopping(String name, ToppingCategory category) {
        super(name, category);
    }

    @Override
    public double getPrice(PizzaSize size) {
        return 0.0;
    }
}
