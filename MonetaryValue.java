public class MonetaryValue {
    private int cents;

    public MonetaryValue() {
        cents = 0;
    }

    public MonetaryValue(double dollars) {
        double noDecDollars = dollars * 100;
        cents = (int) noDecDollars;
    }

    public MonetaryValue(MonetaryValue oldMonetaryValue) {
        this.cents = oldMonetaryValue.cents;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MonetaryValue) {
            MonetaryValue other = (MonetaryValue) obj;
            return this.cents == other.cents;
        }
        return false;
    }

    @Override
    public String toString() {
        double dollars = (double) cents / 100;
        return String.format("$%.2f", dollars);
    }

    public boolean isLessThan(MonetaryValue compareMonetaryValue) {
        return this.cents < compareMonetaryValue.cents;

    }

    public boolean isGreaterThan(MonetaryValue compareMonetaryValue) {
        return this.cents > compareMonetaryValue.cents;
    }

    public boolean isNegative() {
        return this.cents < 0;
    }

    public double doubleValue() {
        return (double) cents / 100;
    }

    public boolean add(MonetaryValue money) {
        if (money.cents < 0) {
            return false;
        } else {
            this.cents += money.cents;
            return true;
        }
    }

    public boolean subtract(MonetaryValue money) {
        if (money.cents < 0) {
            return false;
        } else {
            this.cents -= money.cents;
            return true;
        }
    }

    public static MonetaryValue read(java.util.Scanner sc) {
        if (sc.hasNextDouble()) {
            MonetaryValue newValue = new MonetaryValue(sc.nextDouble());
            return newValue;
        }
        return null;
    }
}