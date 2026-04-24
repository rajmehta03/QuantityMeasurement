public class QuantityMeasurementApp {

    // ===== ENUM for Units =====
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0); // 1 inch = 1/12 feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // ===== Generic Quantity Class =====
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            // Reflexive
            if (this == obj) return true;

            // Null + type check
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            // Compare after conversion
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // ===== Helper Method =====
    public static boolean compare(double v1, LengthUnit u1,
                                  double v2, LengthUnit u2) {
        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);
        return q1.equals(q2);
    }

    // ===== Main Method =====
    public static void main(String[] args) {

        boolean result1 = compare(1.0, LengthUnit.FEET,
                12.0, LengthUnit.INCH);

        System.out.println("Input: Quantity(1.0, feet) and Quantity(12.0, inches)");
        System.out.println("Output: Equal (" + result1 + ")");

        boolean result2 = compare(1.0, LengthUnit.INCH,
                1.0, LengthUnit.INCH);

        System.out.println("\nInput: Quantity(1.0, inch) and Quantity(1.0, inch)");
        System.out.println("Output: Equal (" + result2 + ")");
    }
}