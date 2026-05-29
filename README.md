# PIZZAlicious 🍕

A point-of-sale console application for a custom pizza shop.  
Built with Java, applying core OOP principles: inheritance, polymorphism, abstract classes, interfaces, generics, and Java Streams.

## Features
- Build a fully custom pizza (size, crust, sauce, toppings, stuffed crust)
- Choose a pre-configured **Signature Pizza** (Margherita or Veggie)
- Add drinks and garlic knots to your order
- **Live ASCII pizza visualizer** — topping art stamps rendered directly on the pizza circle as you build
- Auto-generated receipt files saved by date/time
- Signature pizza templates (Margherita, Veggie)

## How to Run
```bash
cd src/main/java
javac com/pluralsight/Main.java
java com.pluralsight.Main
```

Or open in IntelliJ and run `Main.java`.

## Project Structure
```
Pizza/
├── src/main/java/com/pluralsight/
│   ├── enums/        # PizzaSize, CrustType, ToppingCategory
│   ├── model/        # Pizza, Topping, Order, Drink, GarlicKnots, OrderItem
│   ├── signature/    # SignaturePizza (abstract), MargheritaPizza, VeggiePizza
│   ├── ui/           # HomeScreen, OrderScreen, AddPizzaScreen, AddDrinkScreen, CheckoutScreen
│   └── util/         # Console, ToppingFactory, ReceiptWriter, PizzaVisualizer
├── receipts/         # Generated receipt .txt files (git-ignored)
└── diagrams/         # UML class diagram
```

## Interesting Code

### Stream-based total in `Order.java`
```java
public double getTotal() {
    return items.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();
}
```
`items` is a `List<OrderItem>` — a heterogeneous mix of Pizza, Drink, and GarlicKnots objects.
The Stream iterates all of them and calls `getPrice()` via the `OrderItem` interface — classic polymorphism + generics + lambdas in one line.

### Abstract pricing in `Topping.java`
```java
public abstract double getPrice(PizzaSize size);
```
`PremiumTopping` charges based on size (Meat vs. Cheese tiers). `RegularTopping` always returns `0.0`.
`Pizza.getPrice()` calls `topping.getPrice(size)` on every topping without knowing which subclass it has — polymorphism at work.

### Signature pizzas via Template Method pattern
`SignaturePizza` calls `applyDefaultToppings()` in its constructor. Each subclass overrides that method to add its own toppings using `ToppingFactory` streams. The constructor always runs the hook — subclasses can't forget to call it.

### ASCII pizza visualizer (`PizzaVisualizer.java`)
The pizza circle is drawn with the distance formula (`Math.sqrt(dx²+dy²)`), with a 0.5× horizontal scale factor so it looks round in a terminal (characters are taller than wide). Each topping is a 5-line × 11-char ASCII art stamp placed at pre-defined slot positions inside the circle. Stamps are clipped to the interior — no art overwrites the crust ring.

## Diagrams
See [`diagrams/class-diagram.md`](diagrams/class-diagram.md)

---
*Capstone 2 — Advanced Java OOP*
