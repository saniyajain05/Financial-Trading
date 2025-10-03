package financial_trading;

import java.util.Objects;

class Price implements Comparable<Price> {
    private final int cents;


    Price(int cents) {
        // this will only be used by the factory so it there a need to use a set method?
        this.cents = cents;
    }

    public int getCents() {
        return cents;
    }

    public boolean isNegative() {
        return cents < 0;
    }

    public Price add(Price p) throws InvalidPriceOperation {
        if (p == null) {
            throw new InvalidPriceOperation("Invalid Operation: Tried to add null.");
        }
        // do we allow them to add a negative number? because that is what subtract is for.
        int sum = this.cents + p.cents;
        return PriceFactory.makePrice(sum);
    }

    public Price subtract(Price p) throws InvalidPriceOperation{
        if (p == null) {
            throw new InvalidPriceOperation("Invalid operation: Tried to subtract null.");
        }
        int diff = this.cents - p.cents;
        return  PriceFactory.makePrice(diff);
    }

    public Price multiply(int n) throws InvalidPriceOperation {
        if (n<0) {
            throw new InvalidPriceOperation("Invalid operation: Tried to multiply a negative number.");
        }
        // should we allow them to multiply by 0 or 1 because it could have consequences. Allow -ve multiplication?
        return new Price(this.cents * n);
    }

    public boolean greaterOrEqual(Price p) throws InvalidPriceOperation{
        if (p == null) {
            throw new InvalidPriceOperation("Invalid operation: Tried to compare to null.");
        }
        return this.cents >= p.cents;
    }

    public boolean lessOrEqual(Price p) throws InvalidPriceOperation{
        if (p == null) {
            throw new InvalidPriceOperation("Invalid operation: Tried to compare to null.");
        }
        return this.cents <= p.cents;
    }

    public boolean greaterThan(Price p) throws InvalidPriceOperation{
        if (p == null) {
            throw new InvalidPriceOperation("Invalid operation: Tried to compare to null.");
        }
        return this.cents > p.cents;
    }

    public boolean lessThan(Price p) throws InvalidPriceOperation{

        if (p == null) {
            throw new InvalidPriceOperation("Invalid operation: Tried to compare to null.");
        }
        return this.cents < p.cents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return cents == price.cents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cents);
    }

    @Override
    public int compareTo(Price p) {
        if (p == null) {
            return -1;
        }
        return this.cents - p.cents;
    }
    public String toString() {
        int dollars = cents / 100;
        int leftover_cents = cents % 100;
        return String.format("$%d.%02d", dollars, Math.abs(leftover_cents));
    }
}

