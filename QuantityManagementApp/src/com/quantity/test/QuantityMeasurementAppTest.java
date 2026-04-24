package com.quantity.test;

import com.quantity.weight.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    // -------- Equality Tests --------

    @Test
    public void testKgToGramEquality() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(g));
    }

    @Test
    public void testPoundToKgEquality() {
        QuantityWeight lb = new QuantityWeight(2.20462, WeightUnit.POUND);
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertTrue(lb.equals(kg));
    }

    // -------- Conversion Tests --------

    @Test
    public void testKgToGramConversion() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight result = kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 0.0001);
    }

    @Test
    public void testGramToKgConversion() {
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight result = g.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 0.0001);
    }

    @Test
    public void testPoundToKgConversion() {
        QuantityWeight lb = new QuantityWeight(2.20462, WeightUnit.POUND);
        QuantityWeight result = lb.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 0.0001);
    }

    // -------- Addition Tests --------

    @Test
    public void testAddSameUnit() {
        QuantityWeight w1 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(3.0, WeightUnit.KILOGRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(5.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddDifferentUnits() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = kg.add(g);

        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    public void testAddWithTargetUnit() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight g = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = kg.add(g, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    // -------- Negative + Zero --------

    @Test
    public void testAddNegative() {
        QuantityWeight w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(-2.0, WeightUnit.KILOGRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(3.0, result.getValue(), 0.0001);
    }

    @Test
    public void testAddZero() {
        QuantityWeight w1 = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(0.0, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(5.0, result.getValue(), 0.0001);
    }
}