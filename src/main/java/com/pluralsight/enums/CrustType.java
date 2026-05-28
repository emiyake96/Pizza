package com.pluralsight.enums;

/**
 * Available crust types.
 * Crust choice is included in the base pizza price — no upcharge per type.
 * The label is used for menus and receipt output.
 */
public enum CrustType {

    THIN        ("Thin"),
    REGULAR     ("Regular"),
    THICK       ("Thick"),
    CAULIFLOWER ("Cauliflower");

    // ── Fields ────────────────────────────────────────────────────────────
    private final String label;

    // ── Constructor ───────────────────────────────────────────────────────
    CrustType(String label) {
        this.label = label;
    }

    // ── Accessors ─────────────────────────────────────────────────────────
    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }
}
