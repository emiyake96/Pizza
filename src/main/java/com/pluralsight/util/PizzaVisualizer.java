package com.pluralsight.util;

import com.pluralsight.enums.CrustType;
import com.pluralsight.enums.PizzaSize;
import com.pluralsight.model.Pizza;
import com.pluralsight.model.Topping;

import java.util.Arrays;
import java.util.List;

/**
 * Renders an ASCII art pizza to the terminal.
 *
 * Each topping is drawn as a multi-line ASCII art stamp directly ON the pizza,
 * appearing multiple times spread across the surface so it looks like a real pizza.
 *
 * Slots are pre-defined positions inside the circle.
 *
 * Stamps are clipped to the pizza interior: any stamp character that falls on
 * or outside the crust ring is simply skipped.
 */
public class PizzaVisualizer {

    // ── Slot positions {rowOffset, colOffset} from center ─────────────────
    // Slots are spaced so no two adjacent 5×11 stamps overlap.
    // colOffset is in pizza-units; multiply by 2 for the grid column.

    /** Personal 8" — 4 slots in a 2×2 pattern */
    private static final int[][] SLOTS_SMALL = {
            {-3, -4}, {-3, 4},
            { 3, -4}, { 3, 4},
    };

    /** Medium 12" — 8 slots in a 3×3 pattern (centre column missing middle) */
    private static final int[][] SLOTS_MEDIUM = {
            {-6, -7}, {-6,  0}, {-6,  7},
            { 0, -7},           { 0,  7},
            { 6, -7}, { 6,  0}, { 6,  7},
    };

    /** Large 16" — 12 slots in a 3×4 pattern */
    private static final int[][] SLOTS_LARGE = {
            {-9, -9}, {-9, -3}, {-9,  3}, {-9,  9},
            { 0, -9}, { 0, -3}, { 0,  3}, { 0,  9},
            { 9, -9}, { 9, -3}, { 9,  3}, { 9,  9},
    };

    // ── Render ────────────────────────────────────────────────────────────

    public static void render(Pizza pizza) {
        int      radius    = getRadius(pizza.getSize());
        int[][]  slots     = getSlots(pizza.getSize());
        char     crustChar = getCrustChar(pizza.getCrust(), pizza.isStuffedCrust());

        int rows = radius * 2 + 1;
        int cols = radius * 4 + 1;
        char[][] grid = new char[rows][cols];
        for (char[] row : grid) Arrays.fill(row, ' ');

        int cx = radius;       // center row
        int cy = radius * 2;   // center col

        // ── Draw circle ───────────────────────────────────────────────────
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                double dy   = r - cx;
                double dx   = (c - cy) * 0.5;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if (Math.abs(dist - radius) <= 0.8)  grid[r][c] = crustChar;
                else if (dist < radius - 0.8)         grid[r][c] = '.';
            }
        }

        // ── Stamp topping art at each slot (round-robin across toppings) ──
        List<Topping> toppings = pizza.getToppings();
        int n = toppings.size();
        if (n > 0) {
            for (int slotIdx = 0; slotIdx < slots.length; slotIdx++) {
                String[] art  = getArt(toppings.get(slotIdx % n));
                int artH = art.length;
                int artW = 0;
                for (String line : art) artW = Math.max(artW, line.length());

                int centerRow = cx + slots[slotIdx][0];
                int centerCol = cy + slots[slotIdx][1] * 2;
                int startRow  = centerRow - artH / 2;
                int startCol  = centerCol - artW / 2;

                for (int ro = 0; ro < artH; ro++) {
                    for (int co = 0; co < art[ro].length(); co++) {
                        int gr = startRow + ro;
                        int gc = startCol + co;
                        if (gr < 0 || gr >= rows || gc < 0 || gc >= cols) continue;
                        // Only place character if inside pizza (not on crust)
                        double dy = gr - cx;
                        double dx = (gc - cy) * 0.5;
                        if (Math.sqrt(dx * dx + dy * dy) < radius - 0.8) {
                            grid[gr][gc] = art[ro].charAt(co);
                        }
                    }
                }
            }
        }

        // ── Print grid ────────────────────────────────────────────────────
        System.out.println();
        for (char[] row : grid) System.out.println("  " + new String(row));

        // ── Print summary line ────────────────────────────────────────────
        System.out.println();
        System.out.printf("  %s  |  %s Crust%s  |  $%.2f%n",
                pizza.getSize().getLabel(),
                pizza.getCrust().getLabel(),
                pizza.isStuffedCrust() ? " | Stuffed" : "",
                pizza.getPrice());

        if (!toppings.isEmpty()) {
            StringBuilder tops = new StringBuilder("  Toppings: ");
            for (int i = 0; i < toppings.size(); i++) {
                if (i > 0) tops.append(", ");
                tops.append(toppings.get(i).toString());
            }
            System.out.println(tops);
        }
        System.out.println();
    }

    // ── Sizing helpers ────────────────────────────────────────────────────

    private static int getRadius(PizzaSize size) {
        switch (size) {
            case PERSONAL_8: return 10;
            case MEDIUM_12:  return 15;
            default:         return 20;
        }
    }

    private static int[][] getSlots(PizzaSize size) {
        switch (size) {
            case PERSONAL_8: return SLOTS_SMALL;
            case MEDIUM_12:  return SLOTS_MEDIUM;
            default:         return SLOTS_LARGE;
        }
    }

    private static char getCrustChar(CrustType crust, boolean stuffed) {
        if (stuffed) return 'S';
        switch (crust) {
            case THIN:        return '-';
            case REGULAR:     return '~';
            case THICK:       return '=';
            case CAULIFLOWER: return 'c';
            default:          return '~';
        }
    }

    // ── ASCII art per topping ─────────────────────────────────────────────

    private static String[] getArt(Topping t) {
        String n = t.getName().toLowerCase();

        if (n.contains("pepperoni")) return new String[]{
                "  .-----.  ",
                " /* * * *\\ ",
                "| *  *  * |",
                " \\* * * */ ",
                "  '-----'  "
        };
        if (n.contains("sausage")) return new String[]{
                " .-------. ",
                " |~~~~~~~| ",
                " |       | ",
                " |~~~~~~~| ",
                " '-------' "
        };
        if (n.contains("ham")) return new String[]{
                " .-------. ",
                " |       | ",
                " |       | ",
                " |       | ",
                " '-------' "
        };
        if (n.contains("bacon")) return new String[]{
                "  _______  ",
                " /=======\\ ",
                "|=========|",
                " \\=======/ ",
                "  -------  "
        };
        if (n.contains("chicken")) return new String[]{
                "   ,----.  ",
                "  (      ) ",
                "  (      ) ",
                "  (      ) ",
                "   '----'  "
        };
        if (n.contains("meatball")) return new String[]{
                "  .-----. ",
                " / . . . \\",
                "|  (   )  |",
                " \\ . . . /",
                "  '-----' "
        };
        if (n.contains("mozzarella")) return new String[]{
                "  /~~~~~\\  ",
                " | ~ZzZ~ | ",
                " |  ~~~  | ",
                " |  ZzZ  | ",
                "  \\~~~~~/ "
        };
        if (n.contains("parmesan")) return new String[]{
                " .  .  .  ",
                "  . . . . ",
                " .  .  .  ",
                "  . . . . ",
                " .  .  .  "
        };
        if (n.contains("ricotta")) return new String[]{
                "  .-----.  ",
                " ( *   * ) ",
                " ( * * * ) ",
                " ( *   * ) ",
                "  '-----'  "
        };
        if (n.contains("goat")) return new String[]{
                "  .-----.  ",
                " ( o O o ) ",
                " (       ) ",
                " ( o O o ) ",
                "  '-----'  "
        };
        if (n.contains("buffalo")) return new String[]{
                " ~~~~~~~~~ ",
                " ~       ~ ",
                " ~       ~ ",
                " ~       ~ ",
                " ~~~~~~~~~ "
        };
        if (n.contains("onion")) return new String[]{
                "  .~~~~~.  ",
                " ( ~~~~~ ) ",
                " (~     ~) ",
                " ( ~~~~~ ) ",
                "  '~~~~~'  "
        };
        if (n.contains("mushroom")) return new String[]{
                "   .___.   ",
                "  / ___ \\  ",
                " (_(   )_) ",
                "    | |    ",
                "   _| |_   ",
                "  '-----'  "
        };
        if (n.contains("bell")) return new String[]{
                "  _|_|_|_  ",
                " ( | | | ) ",
                " ( | | | ) ",
                " ( | | | ) ",
                "  -|-|-|-  "
        };
        if (n.contains("olive oil")) return new String[]{
                "    _||_   ",
                "   /    \\  ",
                "  (      ) ",
                "   \\    /  ",
                "    '~~'   "
        };
        if (n.contains("olive")) return new String[]{
                "   .---.   ",
                "  (  o  )  ",
                "  (     )  ",
                "  (  o  )  ",
                "   '---'   "
        };
        if (n.contains("tomato")) return new String[]{
                "    /\\     ",
                "   /  \\    ",
                "  (    )   ",
                "  (    )   ",
                "   '~~'    "
        };
        if (n.contains("spinach")) return new String[]{
                "   _,^,_   ",
                "  / \\ / \\  ",
                " ( _/ \\_ ) ",
                "  \\     /  ",
                "   '---'   "
        };
        if (n.contains("basil")) return new String[]{
                "     /\\    ",
                "    /  \\   ",
                "   / /\\ \\  ",
                "  (_/  \\_) ",
                "    |  |   "
        };
        if (n.contains("pineapple")) return new String[]{
                "  \\|/|\\|/  ",
                "   \\|||/   ",
                "   .---.   ",
                "  / YYY \\  ",
                "   '---'   "
        };
        if (n.contains("anchov")) return new String[]{
                " ~~~~~~~~~ ",
                " >~ ~~~ ~< ",
                " >~     ~< ",
                " >~ ~~~ ~< ",
                " ~~~~~~~~~ "
        };
        if (n.contains("marinara")) return new String[]{
                "  . ~ ~ .  ",
                " ( : :: : )",
                " (  ~~~~  )",
                " ( : :: : )",
                "  ' ~ ~ '  "
        };
        if (n.contains("alfredo")) return new String[]{
                "  .~~~~.   ",
                " (      )  ",
                " (      )  ",
                " (      )  ",
                "  '~~~~'   "
        };
        if (n.contains("pesto")) return new String[]{
                "   ,^,^,   ",
                "  (     )  ",
                "  ( : : )  ",
                "  (     )  ",
                "   '---'   "
        };
        if (n.contains("bbq")) return new String[]{
                " ######### ",
                " #       # ",
                " #  BBQ  # ",
                " #       # ",
                " ######### "
        };
        return new String[]{
                "  .-----.  ",
                " (   ?   ) ",
                "  '-----'  "
        };
    }
}
