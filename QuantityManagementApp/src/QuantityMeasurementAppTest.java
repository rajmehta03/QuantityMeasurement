import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0,
                        QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.convertTo(
                QuantityMeasurementApp.LengthUnit.FEET).value, EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                1.0, QuantityMeasurementApp.LengthUnit.FEET,
                12.0, QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(24.0,
                result.convertTo(QuantityMeasurementApp.LengthUnit.INCH).value,
                EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                1.0, QuantityMeasurementApp.LengthUnit.FEET,
                12.0, QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(0.666666,
                result.convertTo(QuantityMeasurementApp.LengthUnit.YARD).value,
                1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        var r1 = QuantityMeasurementApp.QuantityLength.add(a, b,
                QuantityMeasurementApp.LengthUnit.YARD);

        var r2 = QuantityMeasurementApp.QuantityLength.add(b, a,
                QuantityMeasurementApp.LengthUnit.YARD);

        assertTrue(r1.equals(r2));
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTarget() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.QuantityLength.add(
                        1.0, QuantityMeasurementApp.LengthUnit.FEET,
                        12.0, QuantityMeasurementApp.LengthUnit.INCH,
                        null));
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                5.0, QuantityMeasurementApp.LengthUnit.FEET,
                0.0, QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(1.666666,
                result.convertTo(QuantityMeasurementApp.LengthUnit.YARD).value,
                1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Negative() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                5.0, QuantityMeasurementApp.LengthUnit.FEET,
                -2.0, QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(36.0,
                result.convertTo(QuantityMeasurementApp.LengthUnit.INCH).value,
                EPS);
    }
}