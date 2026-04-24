package com.quantity.app;

import com.quantity.length.LengthUnit;
import com.quantity.length.QuantityLength;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Equality: " + feet.equals(inches));

        QuantityLength result1 = feet.add(inches, LengthUnit.FEET);
        System.out.println("Add in FEET: " + result1);

        QuantityLength result2 = feet.convertTo(LengthUnit.INCH);
        System.out.println("Convert FEET to INCH: " + result2);
    }
}