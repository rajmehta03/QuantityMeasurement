import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    // Same Unit Equality
    @Test
    void testEquality_FeetToFeet_SameValue() {
        assertTrue(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        assertTrue(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.INCH,
                1.0,
                QuantityMeasurementApp.LengthUnit.INCH));
    }

    // Cross Unit Equality
    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                12.0,
                QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.compare(12.0,
                QuantityMeasurementApp.LengthUnit.INCH,
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET));
    }

    // Different Values
    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                2.0,
                QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.INCH,
                2.0,
                QuantityMeasurementApp.LengthUnit.INCH));
    }

    // Null Handling
    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q.equals(null));
    }

    // Same Reference
    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q.equals(q));
    }

    // Invalid Unit
    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.QuantityLength(1.0, null);
        });
    }
}