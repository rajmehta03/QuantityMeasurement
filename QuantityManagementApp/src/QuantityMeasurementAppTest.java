import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                1.0, QuantityMeasurementApp.LengthUnit.FEET,
                2.0, QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(3.0, result.convertTo(
                QuantityMeasurementApp.LengthUnit.FEET).toString().contains("3.0") ? 3.0 : 0.0, EPS);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var q2 = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        var result = q1.add(q2);

        assertTrue(result.equals(
                new QuantityMeasurementApp.QuantityLength(2.0,
                        QuantityMeasurementApp.LengthUnit.FEET)));
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                12.0, QuantityMeasurementApp.LengthUnit.INCH,
                1.0, QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(24.0,
                result.convertTo(QuantityMeasurementApp.LengthUnit.INCH).value,
                EPS);
    }

    @Test
    void testAddition_Commutativity() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(a.add(b).equals(b.add(a)));
    }

    @Test
    void testAddition_WithZero() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                5.0, QuantityMeasurementApp.LengthUnit.FEET,
                0.0, QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(5.0,
                result.convertTo(QuantityMeasurementApp.LengthUnit.FEET).value,
                EPS);
    }

    @Test
    void testAddition_NegativeValues() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                5.0, QuantityMeasurementApp.LengthUnit.FEET,
                -2.0, QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(3.0,
                result.convertTo(QuantityMeasurementApp.LengthUnit.FEET).value,
                EPS);
    }

    @Test
    void testAddition_NullSecondOperand() {
        var q = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q.add(null));
    }

    @Test
    void testAddition_LargeValues() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                1e6, QuantityMeasurementApp.LengthUnit.FEET,
                1e6, QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2e6,
                result.convertTo(QuantityMeasurementApp.LengthUnit.FEET).value,
                EPS);
    }

    @Test
    void testAddition_SmallValues() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                0.001, QuantityMeasurementApp.LengthUnit.FEET,
                0.002, QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(0.003,
                result.convertTo(QuantityMeasurementApp.LengthUnit.FEET).value,
                EPS);
    }
}