package com.pluralsight.model;

import com.pluralsight.enums.PizzaSize;
import com.pluralsight.enums.ToppingCategory;

/**
 * Meats and cheeses — charged per pizza size, with an upcharge if marked extra.
 *
 * Pricing from spec:
 *
 *   MEAT base:    8"=$1.00  12"=$2.00  16"=$3.00
 *   MEAT extra:   8"=$0.50  12"=$1.00  16"=$1.50
 *
 *   CHEESE base:  8"=$0.75  12"=$1.50  16"=$2.25
 *   CHEESE extra: 8"=$0.30  12"=$0.60  16"=$0.90
 *
 */
public class PremiumTopping extends Topping {

    // ── Meat price tiers [8", 12", 16"] ───────────────────────────────────
    private static final double[] MEAT_BASE  = {1.00, 2.00, 3.00};
    private static final double[] MEAT_EXTRA = {0.50, 1.00, 1.50};

    // ── Cheese price tiers [8", 12", 16"] ─────────────────────────────────
    private static final double[] CHEESE_BASE  = {0.75, 1.50, 2.25};
    private static final double[] CHEESE_EXTRA = {0.30, 0.60, 0.90};

    // ── Constructor ───────────────────────────────────────────────────────
    public PremiumTopping(String name, ToppingCategory category) {
        super(name, category);
    }

    // ── Pricing ───────────────────────────────────────────────────────────
    @Override
    public double getPrice(PizzaSize size) {
        int i = sizeIndex(size);

        if (getCategory() == ToppingCategory.MEAT) {
            return isExtra() ? MEAT_EXTRA[i] : MEAT_BASE[i];
        } else {
            // CHEESE
            return isExtra() ? CHEESE_EXTRA[i] : CHEESE_BASE[i];
        }
    }

    /**
     * Maps PizzaSize to an array index so we can use the price arrays above.
     * PERSONAL_8 → 0, MEDIUM_12 → 1, LARGE_16 → 2
     */
    private int sizeIndex(PizzaSize size) {
        switch (size) {
            case PERSONAL_8: return 0;
            case MEDIUM_12:  return 1;
            case LARGE_16:   return 2;
            default: throw new IllegalArgumentException("Unknown size: " + size);
        }
    }
}
