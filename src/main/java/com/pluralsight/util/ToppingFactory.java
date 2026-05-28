package com.pluralsight.util;

import com.pluralsight.enums.ToppingCategory;
import com.pluralsight.model.PremiumTopping;
import com.pluralsight.model.RegularTopping;
import com.pluralsight.model.Topping;

import java.util.Arrays;
import java.util.List;

/**
 * Central catalog of every available topping, organized by category.
 *
 * WHY a factory? The UI screens need menus of toppings to display.
 * Rather than hardcoding the same strings in multiple screen classes,
 * we define them once here. Each call returns a fresh list of new
 * Topping objects so the customer's selections stay independent.
 *
 * The screens call these methods, let the customer pick, then call
 * setExtra(true) on any topping the customer wants extra of.
 */
public class ToppingFactory {

    // ── Meats (Premium) ───────────────────────────────────────────────────

    /**
     * Returns the full meat menu as fresh PremiumTopping objects.
     * Spec: pepperoni, sausage, ham, bacon, chicken, meatball
     */
    public static List<Topping> getMeats() {
        return Arrays.asList(
            new PremiumTopping("Pepperoni", ToppingCategory.MEAT),
            new PremiumTopping("Sausage",   ToppingCategory.MEAT),
            new PremiumTopping("Ham",       ToppingCategory.MEAT),
            new PremiumTopping("Bacon",     ToppingCategory.MEAT),
            new PremiumTopping("Chicken",   ToppingCategory.MEAT),
            new PremiumTopping("Meatball",  ToppingCategory.MEAT)
        );
    }

    // ── Cheeses (Premium) ─────────────────────────────────────────────────

    /**
     * Returns the full cheese menu as fresh PremiumTopping objects.
     * Spec: Mozzarella, Parmesan, Ricotta, Goat Cheese, Buffalo
     */
    public static List<Topping> getCheeses() {
        return Arrays.asList(
            new PremiumTopping("Mozzarella", ToppingCategory.CHEESE),
            new PremiumTopping("Parmesan",   ToppingCategory.CHEESE),
            new PremiumTopping("Ricotta",    ToppingCategory.CHEESE),
            new PremiumTopping("Goat Cheese",ToppingCategory.CHEESE),
            new PremiumTopping("Buffalo",    ToppingCategory.CHEESE)
        );
    }

    // ── Vegetables (Regular — free) ───────────────────────────────────────

    /**
     * Spec: onions, mushrooms, bell peppers, olives, tomatoes,
     *       spinach, basil, pineapple, anchovies
     */
    public static List<Topping> getVegetables() {
        return Arrays.asList(
            new RegularTopping("Onions",       ToppingCategory.VEGETABLE),
            new RegularTopping("Mushrooms",    ToppingCategory.VEGETABLE),
            new RegularTopping("Bell Peppers", ToppingCategory.VEGETABLE),
            new RegularTopping("Olives",       ToppingCategory.VEGETABLE),
            new RegularTopping("Tomatoes",     ToppingCategory.VEGETABLE),
            new RegularTopping("Spinach",      ToppingCategory.VEGETABLE),
            new RegularTopping("Basil",        ToppingCategory.VEGETABLE),
            new RegularTopping("Pineapple",    ToppingCategory.VEGETABLE),
            new RegularTopping("Anchovies",    ToppingCategory.VEGETABLE)
        );
    }

    // ── Sauces (Regular — free) ───────────────────────────────────────────

    /**
     * Spec: marinara, alfredo, pesto, bbq, buffalo, olive oil
     */
    public static List<Topping> getSauces() {
        return Arrays.asList(
            new RegularTopping("Marinara",  ToppingCategory.SAUCE),
            new RegularTopping("Alfredo",   ToppingCategory.SAUCE),
            new RegularTopping("Pesto",     ToppingCategory.SAUCE),
            new RegularTopping("BBQ",       ToppingCategory.SAUCE),
            new RegularTopping("Buffalo",   ToppingCategory.SAUCE),
            new RegularTopping("Olive Oil", ToppingCategory.SAUCE)
        );
    }

    // ── Sides (Regular — free) ────────────────────────────────────────────

    /**
     * Spec: red pepper, parmesan
     */
    public static List<Topping> getSides() {
        return Arrays.asList(
            new RegularTopping("Red Pepper", ToppingCategory.SIDE),
            new RegularTopping("Parmesan",   ToppingCategory.SIDE)
        );
    }
}
