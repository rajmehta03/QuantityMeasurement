/**
 * UC7: Addition with explicit target unit specification
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
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // ===== PRIVATE UTILITY (DRY CORE LOGIC) =====
        private static double addInFeet(QuantityLength q1, QuantityLength q2) {
            return q1.toFeet() + q2.toFeet();
        }

        // ===== UC6 (DEFAULT: FIRST OPERAND UNIT) =====
        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Other cannot be null");
            }
            return add(this, other, this.unit);
        }

        // ===== UC7 (EXPLICIT TARGET UNIT) =====
        public static QuantityLength add(QuantityLength q1,
                                         QuantityLength q2,
                                         LengthUnit targetUnit) {

            if (q1 == null || q2 == null) {
                throw new IllegalArgumentException("Operands cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumFeet = addInFeet(q1, q2);
            double result = targetUnit.fromFeet(sumFeet);

            return new QuantityLength(result, targetUnit);
        }

        // ===== OVERLOADED VERSION =====
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

    // ===== DEMO =====
    public static void main(String[] args) {

        QuantityLength f = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength i = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(QuantityLength.add(f, i, LengthUnit.FEET));   // 2 FEET
        System.out.println(QuantityLength.add(f, i, LengthUnit.INCH));   // 24 INCH
        System.out.println(QuantityLength.add(f, i, LengthUnit.YARD));   // ~0.667 YARD
    }
}