package com.pluralsight.model;

import com.pluralsight.enums.CrustType;
import com.pluralsight.enums.PizzaSize;

import java.util.ArrayList;
import java.util.List;

/**
 * Core model for a single customizable pizza.
 * Implements OrderItem so Order can hold it alongside Drink and GarlicKnots.
 */
public class Pizza implements OrderItem {

    private PizzaSize size;
    private CrustType crust;
    private List<Topping> toppings;
    private boolean stuffedCrust;

    public Pizza(PizzaSize size, CrustType crust) {
        this.size = size;
        this.crust = crust;
        this.toppings = new ArrayList<>();
        this.stuffedCrust = false;
    }

    @Override
    public double getPrice() {
        // TODO Step 4: base price + topping prices + stuffed crust
        return 0.0;
    }

    @Override
    public String getDescription() {
        // TODO Step 4: build readable summary
        return size + " pizza, " + crust + " crust";
    }

    public PizzaSize getSize() { return size; }
    public CrustType getCrust() { return crust; }
    public List<Topping> getToppings() { return toppings; }
    public boolean isStuffedCrust() { return stuffedCrust; }
    public void setStuffedCrust(boolean stuffedCrust) { this.stuffedCrust = stuffedCrust; }
    public void addTopping(Topping topping) { toppings.add(topping); }
}
