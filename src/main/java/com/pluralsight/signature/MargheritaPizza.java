package com.pluralsight.signature;

import com.pluralsight.enums.CrustType;
import com.pluralsight.enums.PizzaSize;

/** 12" Regular — Mozzarella, Tomatoes, Basil, Marinara, Olive Oil */
public class MargheritaPizza extends SignaturePizza {

    public MargheritaPizza() {
        super(PizzaSize.MEDIUM_12, CrustType.REGULAR);
    }

    @Override
    protected void applyDefaultToppings() {
        // TODO Step 9
    }
}
