package com.pluralsight.signature;

import com.pluralsight.enums.CrustType;
import com.pluralsight.enums.PizzaSize;

/** 8" Regular — Bell Peppers, Spinach, Olives, Onions, Marinara, Mozzarella */
public class VeggiePizza extends SignaturePizza {

    public VeggiePizza() {
        super(PizzaSize.PERSONAL_8, CrustType.REGULAR);
    }

    @Override
    protected void applyDefaultToppings() {
        // TODO Step 9
    }
}
