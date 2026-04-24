public class QuantityMeasurementApp {

    // Inner class representing Feet measurement
    public static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Getter (optional)
        public double getValue() {
            return value;
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {
            // Same reference check (Reflexive)
            if (this == obj) {
                return true;
            }

            // Null check and type check
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            // Cast safely
            Feet other = (Feet) obj;

            // Compare using Double.compare()
            return Double.compare(this.value, other.value) == 0;
        }

        // Override hashCode (best practice when overriding equals)
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Main method for demonstration
    public static void main(String[] args) {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        boolean result = feet1.equals(feet2);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + result + ")");
    }
}