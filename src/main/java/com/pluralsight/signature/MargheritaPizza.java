package com.pluralsight.signature;

import com.pluralsight.enums.CrustType;
import com.pluralsight.enums.PizzaSize;
import com.pluralsight.util.ToppingFactory;

/** 12" Regular — Mozzarella, Tomatoes, Basil, Marinara, Olive Oil */
public class MargheritaPizza extends SignaturePizza {

    public MargheritaPizza() {
        super(PizzaSize.MEDIUM_12, CrustType.REGULAR);
    }

    @Override
    protected void applyDefaultToppings() {
        // Cheeses (premium)
        ToppingFactory.getCheeses().stream()
                .filter(t -> t.getName().equalsIgnoreCase("Mozzarella"))
                .findFirst().ifPresent(this::addTopping);

        // Vegetables (regular — free)
        ToppingFactory.getVegetables().stream()
                .filter(t -> t.getName().equalsIgnoreCase("Tomatoes"))
                .findFirst().ifPresent(this::addTopping);

        ToppingFactory.getVegetables().stream()
                .filter(t -> t.getName().equalsIgnoreCase("Basil"))
                .findFirst().ifPresent(this::addTopping);

        // Sauces (regular — free)
        ToppingFactory.getSauces().stream()
                .filter(t -> t.getName().equalsIgnoreCase("Marinara"))
                .findFirst().ifPresent(this::addTopping);

        ToppingFactory.getSauces().stream()
                .filter(t -> t.getName().equalsIgnoreCase("Olive Oil"))
                .findFirst().ifPresent(this::addTopping);
    }
}
