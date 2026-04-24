/**
 * QuantityMeasurementApp demonstrates unit comparison and conversion
 * using a generic, scalable design (DRY principle).
 */
public class QuantityMeasurementApp {

    // ===== ENUM: Length Units (Base = FEET) =====
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

        public double getFactor() {
            return toFeetFactor;
        }
    }

    // ===== VALUE OBJECT =====
    public static class QuantityLength {
        private final double value;
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

        /**
         * Converts this quantity to another unit
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {
            validate(this.value, targetUnit);

            double feet = this.toFeet();
            double converted = targetUnit.fromFeet(feet);

            return new QuantityLength(converted, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

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

    // ===== STATIC CONVERSION API =====
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }

        double baseFeet = source.toFeet(value);
        return target.fromFeet(baseFeet);
    }

    // ===== OVERLOADED DEMO METHODS =====

    public static double demonstrateLengthConversion(double value,
                                                     LengthUnit from,
                                                     LengthUnit to) {
        double result = convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
        return result;
    }

    public static double demonstrateLengthConversion(QuantityLength q,
                                                     LengthUnit to) {
        QuantityLength converted = q.convertTo(to);
        System.out.println(q + " -> " + converted);
        return converted.value;
    }

    public static void demonstrateLengthEquality(QuantityLength q1,
                                                 QuantityLength q2) {
        System.out.println(q1 + " equals " + q2 + " ? " + q1.equals(q2));
    }

    public static void demonstrateLengthComparison(double v1, LengthUnit u1,
                                                   double v2, LengthUnit u2) {
        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);
        demonstrateLengthEquality(q1, q2);
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);     // 12
        demonstrateLengthConversion(3.0, LengthUnit.YARD, LengthUnit.FEET);     // 9
        demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARD);    // 1
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH); // ~0.393701

        demonstrateLengthComparison(1.0, LengthUnit.FEET,
                12.0, LengthUnit.INCH);
    }
}