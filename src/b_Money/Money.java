package b_Money;

public class Money implements Comparable<Money> {
    private int amount;
    private Currency currency;

    Money(Integer amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String toString() {
        int wholePart = amount / 100; // Get the whole number part
        int decimalPart = amount % 100; // Get the decimal part
        return wholePart + "." + (decimalPart < 10 ? "0" : "") + decimalPart + " " + currency.getName();
    }

    public Integer universalValue() {
        return currency.universalValue(amount);
    }

    public Boolean equals(Money other) {
        return this.universalValue().equals(other.universalValue());
    }

    public Money add(Money other) {
        int addedAmount = this.amount + other.currency.valueInThisCurrency(other.amount, other.currency) * 10;
        return new Money(addedAmount, this.currency);
    }

    public Money sub(Money other) {
        int subtractedAmount = this.amount - other.currency.valueInThisCurrency(other.amount, other.currency)  * 10;
        return new Money(subtractedAmount, this.currency);
    }

    public Boolean isZero() {
        return amount == 0;
    }

    public Money negate() {
        return new Money(-amount, currency);
    }

    public int compareTo(Money other) {
        return this.universalValue().compareTo(other.universalValue());
    }
}
