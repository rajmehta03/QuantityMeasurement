package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ===== UC1 / UC3 / UC8 EQUALITY =====
    @Test
    void testFeetEquality() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(feet1, feet2);
    }

    @Test
    void testFeetInchEquality() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

        assertEquals(feet, inch);
    }

    @Test
    void testFeetInchInequality() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(24.0, LengthUnit.INCH);

        assertNotEquals(feet, inch);
    }

    // ===== UC5 CONVERSION =====
    @Test
    void testConvertFeetToInch() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = feet.convertTo(LengthUnit.INCH);

        assertEquals(new QuantityLength(12.0, LengthUnit.INCH), result);
    }

    @Test
    void testConvertYardToFeet() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);

        QuantityLength result = yard.convertTo(LengthUnit.FEET);

        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), result);
    }

    // ===== UC6 ADDITION =====
    @Test
    void testAddFeetAndInchToFeet() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = feet.add(inch, LengthUnit.FEET);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddFeetAndInchToInch() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = feet.add(inch, LengthUnit.INCH);

        assertEquals(new QuantityLength(24.0, LengthUnit.INCH), result);
    }

    // ===== UC7 TARGET UNIT ADDITION =====
    @Test
    void testAddWithTargetUnitYard() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = feet.add(inch, LengthUnit.YARD);

        assertEquals(new QuantityLength(2.0 / 3.0, LengthUnit.YARD), result);
    }

    // ===== EDGE CASE =====
    @Test
    void testNullCheck() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, null);
        });
    }
}