package com.pluralsight.model;

import com.pluralsight.enums.PizzaSize;
import com.pluralsight.enums.ToppingCategory;

/**
 * Meats and cheeses — charged per size, with extra-topping upcharge.
 */
public class PremiumTopping extends Topping {

    public PremiumTopping(String name, ToppingCategory category) {
        super(name, category);
    }

    @Override
    public double getPrice(PizzaSize size) {
        // TODO Step 3: implement tier pricing
        return 0.0;
    }
}
