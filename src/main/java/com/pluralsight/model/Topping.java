package com.pluralsight.model;

import com.pluralsight.enums.PizzaSize;
import com.pluralsight.enums.ToppingCategory;

/**
 * Abstract base class for all pizza toppings.
 * getPrice() is abstract — subclasses define their own pricing formula.
 */
public abstract class Topping {

    private String name;
    private ToppingCategory category;
    private boolean extra;

    public Topping(String name, ToppingCategory category) {
        this.name = name;
        this.category = category;
        this.extra = false;
    }

    public abstract double getPrice(PizzaSize size);

    public String getName() { return name; }
    public ToppingCategory getCategory() { return category; }
    public boolean isExtra() { return extra; }
    public void setExtra(boolean extra) { this.extra = extra; }

    @Override
    public String toString() {
        return name + (extra ? " (extra)" : "");
    }
}
