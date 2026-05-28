package com.pluralsight.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A customer order holding any mix of Pizza, Drink, and GarlicKnots.
 * getTotal() will use Java Streams in Step 6.
 */
public class Order {

    private List<OrderItem> items;
    private LocalDateTime orderTime;

    public Order() {
        this.items = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
    }

    public void addItem(OrderItem item) { items.add(item); }
    public List<OrderItem> getItems() { return items; }
    public LocalDateTime getOrderTime() { return orderTime; }

    public double getTotal() {
        // TODO: replace with Stream
        return 0.0;
    }
}
