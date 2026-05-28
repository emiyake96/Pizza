# Pizza POS — Class Diagram

```
«interface»
OrderItem
──────────────────────
+ getPrice() : double
+ getDescription() : String
         △
         │ implements
   ┌─────┴──────┬────────────────┐
   │            │                │
 Pizza        Drink        GarlicKnots
─────────    ───────       ───────────
- size       - size        - quantity
- crust      - flavor
- toppings
- stuffed
- sauce


«abstract»
Topping
──────────────────────────────
- name : String
- category : ToppingCategory
- extra : boolean
+ {abstract} getPrice(PizzaSize) : double
         △
         │ extends
   ┌─────┴──────────────┐
   │                    │
PremiumTopping      RegularTopping
  (Meat, Cheese)      (Veggies, Sauce, Sides)
  → charges extra      → always 0.0


«enum» PizzaSize       «enum» CrustType       «enum» ToppingCategory
──────────────         ──────────────         ──────────────────────
PERSONAL_8             THIN                   MEAT
MEDIUM_12              REGULAR                CHEESE
LARGE_16               THICK                  VEGETABLE
                       CAULIFLOWER            SAUCE
                                              SIDE

Order
──────────────────────────────
- items : List<OrderItem>
- orderTime : LocalDateTime
+ addItem(OrderItem)
+ getTotal() : double   ← Stream + lambda
+ getOrderTime()


Pizza ──extends──▶ SignaturePizza (abstract)
                        △
                   ┌────┴────┐
           MargheritaPizza  VeggiePizza
```

## OOP Concepts Applied

| Concept | Where |
|---|---|
| **Abstract class** | `Topping` — shared fields, abstract `getPrice()` |
| **Inheritance** | `PremiumTopping`/`RegularTopping` extend `Topping`; `SignaturePizza` extends `Pizza` |
| **Interface** | `OrderItem` — `Pizza`, `Drink`, `GarlicKnots` all implement it |
| **Polymorphism** | `Order.getTotal()` calls `getPrice()` on each `OrderItem` without knowing the type |
| **Generics** | `List<OrderItem>`, `List<Topping>` |
| **Streams + Lambdas** | `items.stream().mapToDouble(OrderItem::getPrice).sum()` |
| **Enum** | `PizzaSize`, `CrustType`, `ToppingCategory` |
| **Packages** | `enums`, `model`, `ui`, `util`, `signature` |
