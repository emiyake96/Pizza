package com.pluralsight.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a single customer order.
 *
 * Holds a List<OrderItem> — a heterogeneous collection of Pizza, Drink,
 * and GarlicKnots objects. This is the payoff of the OrderItem interface:
 * one list, one getTotal() call, no type-checking needed anywhere.
 *
 * getTotal() uses a Stream pipeline instead of a manual loop:
 *   items.stream().mapToDouble(OrderItem::getPrice).sum();
 *
 * Spec rule: an order with 0 pizzas must have at least one drink or
 * garlic knots. isValid() enforces this before checkout is allowed.
 */
public class Order {

    // ── Fields ────────────────────────────────────────────────────────────
    private List<OrderItem> items;
    private LocalDateTime   orderTime;
    private List<OrderItem>  items;
    private LocalDateTime    orderTime;

    // ── Constructor ───────────────────────────────────────────────────────
    public Order() {
        this.items     = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
    }

    // ── Item management ───────────────────────────────────────────────────

    /** Adds any OrderItem (Pizza, Drink, or GarlicKnots) to the order. */
    public void addItem(OrderItem item) {
        items.add(item);
    }

    /**
     * Removes an item by its position in the list (0-based index).
     */
    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    /** Returns items newest-first so the order screen matches the spec. */
    /**
     * Returns items in reverse insertion order (newest first) so the
     * order screen always shows the most recent addition at the top.
     */
    public List<OrderItem> getItemsNewestFirst() {
        List<OrderItem> reversed = new ArrayList<>(items);
        Collections.reverse(reversed);
        return reversed;
    }

    // ── Pricing ───────────────────────────────────────────────────────────

    /** Sums all item prices using a Stream + method reference. */
    /**
     * Sums all item prices using a Stream + method reference.
     *
     */
    public double getTotal() {
        return items.stream()
                    .mapToDouble(OrderItem::getPrice)
                    .sum();
    }

    // ── Validation ────────────────────────────────────────────────────────

    /** An order with 0 pizzas must have at least a drink or garlic knots. */
    /**
     * Enforces the spec rule: an order with 0 pizzas must contain
     * at least one drink or garlic knots before checkout is allowed.
     */
    public boolean isValid() {
        boolean hasPizza = items.stream()
                               .anyMatch(item -> item instanceof Pizza);
        if (hasPizza) return true;

        return items.stream()
                    .anyMatch(item -> item instanceof Drink || item instanceof GarlicKnots);
    }

    /** Returns true if the order has no items at all. */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // ── Getters ───────────────────────────────────────────────────────────
    public List<OrderItem> getItems()     { return items; }
    public LocalDateTime   getOrderTime() { return orderTime; }
}
