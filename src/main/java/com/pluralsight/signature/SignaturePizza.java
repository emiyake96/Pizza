package com.pluralsight.signature;

import com.pluralsight.enums.CrustType;
import com.pluralsight.enums.PizzaSize;
import com.pluralsight.model.Pizza;

/** Abstract base for pre-configured signature pizzas. Extends Pizza. */
public abstract class SignaturePizza extends Pizza {

    public SignaturePizza(PizzaSize size, CrustType crust) {
        super(size, crust);
        applyDefaultToppings();
    }

    protected abstract void applyDefaultToppings();
}
