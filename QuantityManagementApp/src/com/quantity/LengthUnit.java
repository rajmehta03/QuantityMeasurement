package com.quantity;

public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12),
    YARD(3.0),
    CENTIMETER(1.0 / 30.48);

    private final double toFeet;

    LengthUnit(double toFeet) {
        this.toFeet = toFeet;
    }

    // Convert given value to base unit (FEET)
    public double toBaseUnit(double value) {
        return value * toFeet;
    }

    // Convert from base unit (FEET) to target unit
    public double fromBaseUnit(double baseValue) {
        return baseValue / toFeet;
    }
}