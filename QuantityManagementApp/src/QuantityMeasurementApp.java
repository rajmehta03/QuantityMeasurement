/**
 * UC6: Addition of Length Units with conversion support
 */
public class QuantityMeasurementApp {

    // ===== ENUM (Base = FEET) =====
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // ===== VALUE OBJECT =====
    public static class QuantityLength {
        final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            validate(value, unit);
            this.value = value;
            this.unit = unit;
        }

        private static void validate(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // ===== ADD (Instance Method) =====
        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Other quantity cannot be null");
            }

            // Normalize to base (feet)
            double sumFeet = this.toFeet() + other.toFeet();

            // Convert back to THIS unit
            double result = this.unit.fromFeet(sumFeet);

            return new QuantityLength(result, this.unit);
        }

        // ===== STATIC ADD (Flexible API) =====
        public static QuantityLength add(QuantityLength q1,
                                         QuantityLength q2,
                                         LengthUnit targetUnit) {

            if (q1 == null || q2 == null || targetUnit == null) {
                throw new IllegalArgumentException("Inputs cannot be null");
            }

            double sumFeet = q1.toFeet() + q2.toFeet();
            double result = targetUnit.fromFeet(sumFeet);

            return new QuantityLength(result, targetUnit);
        }

        // ===== OVERLOADED ADD =====
        public static QuantityLength add(double v1, LengthUnit u1,
                                         double v2, LengthUnit u2,
                                         LengthUnit targetUnit) {

            return add(new QuantityLength(v1, u1),
                    new QuantityLength(v2, u2),
                    targetUnit);
        }

        // ===== CONVERSION =====
        public QuantityLength convertTo(LengthUnit target) {
            double feet = this.toFeet();
            double result = target.fromFeet(feet);
            return new QuantityLength(result, target);
        }

        // ===== EQUALITY =====
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Math.abs(this.toFeet() - other.toFeet()) < 1e-6;
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

    // ===== MAIN DEMO =====
    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(q1.add(q2)); // 2 feet

        System.out.println(QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.YARD),
                new QuantityLength(3.0, LengthUnit.FEET),
                LengthUnit.YARD)); // 2 yards
    }
}