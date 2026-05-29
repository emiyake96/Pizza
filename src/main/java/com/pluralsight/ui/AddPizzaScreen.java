package com.pluralsight.ui;

import com.pluralsight.enums.CrustType;
import com.pluralsight.enums.PizzaSize;
import com.pluralsight.model.Pizza;
import com.pluralsight.model.Topping;
import com.pluralsight.util.Console;
import com.pluralsight.util.PizzaVisualizer;
import com.pluralsight.util.ToppingFactory;

import java.util.List;

/**
 * Walks the customer through building a pizza step by step.
 * PizzaVisualizer.render() is called after every selection so the
 * ASCII art updates live in the terminal.
 *
 * Steps:
 *   1. Choose size
 *   2. Choose crust
 *   3. Add meats
 *   4. Add cheeses
 *   5. Add vegetables
 *   6. Choose sauce
 *   7. Add sides (free)
 *   8. Stuffed crust?
 */
public class AddPizzaScreen {

    public Pizza show() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       Build Your Pizza       ║");
        System.out.println("╚══════════════════════════════╝");

        // ── Step 1: Size ──────────────────────────────────────────────────
        PizzaSize size = chooseSize();
        Pizza pizza = new Pizza(size, CrustType.REGULAR);
        PizzaVisualizer.render(pizza);

        // ── Step 2: Crust ─────────────────────────────────────────────────
        CrustType crust = chooseCrust();
        pizza = new Pizza(size, crust);
        PizzaVisualizer.render(pizza);

        // ── Step 3: Meats ─────────────────────────────────────────────────
        System.out.println("\n── Meats (Premium) ─────────────────");
        addToppingsFromList(pizza, ToppingFactory.getMeats(), true);
        PizzaVisualizer.render(pizza);

        // ── Step 4: Cheeses ───────────────────────────────────────────────
        System.out.println("\n── Cheeses (Premium) ───────────────");
        addToppingsFromList(pizza, ToppingFactory.getCheeses(), true);
        PizzaVisualizer.render(pizza);

        // ── Step 5: Vegetables ────────────────────────────────────────────
        System.out.println("\n── Vegetables (Free) ───────────────");
        addToppingsFromList(pizza, ToppingFactory.getVegetables(), false);
        PizzaVisualizer.render(pizza);

        // ── Step 6: Sauce ─────────────────────────────────────────────────
        System.out.println("\n── Sauce (Free) ────────────────────");
        addToppingsFromList(pizza, ToppingFactory.getSauces(), false);
        PizzaVisualizer.render(pizza);

        // ── Step 7: Sides ─────────────────────────────────────────────────
        System.out.println("\n── Sides / Seasonings (Free) ───────");
        addToppingsFromList(pizza, ToppingFactory.getSides(), false);
        PizzaVisualizer.render(pizza);

        // ── Step 8: Stuffed crust ─────────────────────────────────────────
        boolean stuffed = Console.getYesNo("\nWould you like stuffed crust? (+$1.50)");
        pizza.setStuffedCrust(stuffed);
        PizzaVisualizer.render(pizza);

        System.out.printf("\n  Pizza added! Subtotal: $%.2f%n", pizza.getPrice());
        return pizza;
    }

    // ── Private helpers ───────────────────────────────────────────────────

    private PizzaSize chooseSize() {
        PizzaSize[] sizes = PizzaSize.values();
        System.out.println("\n── Pizza Size ──────────────────────");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("  %d) %s  (+$%.2f)%n",
                i + 1, sizes[i].getLabel(), sizes[i].getBasePrice());
        }
        int choice = Console.getInt("Select size: ") - 1;
        if (choice < 0 || choice >= sizes.length) choice = 0;
        return sizes[choice];
    }

    private CrustType chooseCrust() {
        CrustType[] crusts = CrustType.values();
        System.out.println("\n── Crust Type ──────────────────────");
        for (int i = 0; i < crusts.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, crusts[i].getLabel());
        }
        int choice = Console.getInt("Select crust: ") - 1;
        if (choice < 0 || choice >= crusts.length) choice = 1;
        return crusts[choice];
    }

    /**
     * Displays a topping list and lets the customer pick any number.
     * If allowExtra is true (premium toppings), customer can mark extra for an upcharge.
     * Entering 0 moves to the next section.
     */
    private void addToppingsFromList(Pizza pizza, List<Topping> options, boolean allowExtra) {
        boolean picking = true;

        while (picking) {
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("  %d) %s%n", i + 1, options.get(i).getName());
            }
            System.out.println("  0) Done with this section");

            int choice = Console.getInt("Add topping: ");

            if (choice == 0) {
                picking = false;
            } else if (choice >= 1 && choice <= options.size()) {
                Topping selected = options.get(choice - 1);
                if (allowExtra) {
                    boolean extra = Console.getYesNo("  Extra " + selected.getName() + "?");
                    selected.setExtra(extra);
                }
                pizza.addTopping(selected);
                System.out.println("  Added: " + selected);
            } else {
                System.out.println("  Invalid selection.");
            }
        }
    }
}
