package com.pluralsight.signature;

import com.pluralsight.enums.CrustType;
import com.pluralsight.enums.PizzaSize;
import com.pluralsight.util.ToppingFactory;

/** 8" Regular — Bell Peppers, Spinach, Olives, Onions, Marinara, Mozzarella */
public class VeggiePizza extends SignaturePizza {

    public VeggiePizza() {
        super(PizzaSize.PERSONAL_8, CrustType.REGULAR);
    }

    @Override
    protected void applyDefaultToppings() {
        // Vegetables (regular — free)
        ToppingFactory.getVegetables().stream()
                .filter(t -> t.getName().equalsIgnoreCase("Bell Peppers"))
                .findFirst().ifPresent(this::addTopping);

        ToppingFactory.getVegetables().stream()
                .filter(t -> t.getName().equalsIgnoreCase("Spinach"))
                .findFirst().ifPresent(this::addTopping);

        ToppingFactory.getVegetables().stream()
                .filter(t -> t.getName().equalsIgnoreCase("Olives"))
                .findFirst().ifPresent(this::addTopping);

        ToppingFactory.getVegetables().stream()
                .filter(t -> t.getName().equalsIgnoreCase("Onions"))
                .findFirst().ifPresent(this::addTopping);

        // Sauces (regular — free)
        ToppingFactory.getSauces().stream()
                .filter(t -> t.getName().equalsIgnoreCase("Marinara"))
                .findFirst().ifPresent(this::addTopping);

        // Cheese (premium)
        ToppingFactory.getCheeses().stream()
                .filter(t -> t.getName().equalsIgnoreCase("Mozzarella"))
                .findFirst().ifPresent(this::addTopping);
    }
}
