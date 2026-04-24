package com.quantity.length;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.toBaseUnit(value);
    }

    // ===== EQUALITY =====
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;
        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    // ===== CONVERT =====
    public QuantityLength convertTo(LengthUnit targetUnit) {
        double base = toBase();
        return new QuantityLength(targetUnit.fromBaseUnit(base), targetUnit);
    }

    // ===== ADD (UC6 + UC7 support) =====
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Null not allowed");
        }

        double sumBase = this.toBase() + other.toBase();
        double result = targetUnit.fromBaseUnit(sumBase);

        return new QuantityLength(result, targetUnit);
    }

    // Optional (for debugging)
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}