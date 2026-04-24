public class QuantityMeasurementApp {

    // ===== ENUM for Units (Base: FEET) =====
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),          // 1 inch = 1/12 feet
        YARD(3.0),                 // 1 yard = 3 feet
        CENTIMETER(0.0328084);     // 1 cm ≈ 0.0328084 feet (derived from 0.393701 inch)

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

        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            // Exact comparison (can switch to tolerance if needed)
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

        System.out.println(compare(1.0, LengthUnit.YARD, 3.0, LengthUnit.FEET));   // true
        System.out.println(compare(1.0, LengthUnit.YARD, 36.0, LengthUnit.INCH)); // true
        System.out.println(compare(2.0, LengthUnit.YARD, 2.0, LengthUnit.YARD));  // true
        System.out.println(compare(2.0, LengthUnit.CENTIMETER, 2.0, LengthUnit.CENTIMETER)); // true
        System.out.println(compare(1.0, LengthUnit.CENTIMETER, 0.393701, LengthUnit.INCH)); // true
    }
}