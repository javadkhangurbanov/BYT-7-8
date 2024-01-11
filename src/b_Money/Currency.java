package b_Money;

public class Currency {
    private String name;
    private Double rate;

    Currency(String name, Double rate) {
        this.name = name;
        this.rate = rate;
    }

    public Integer universalValue(Integer amount) {
        // Assuming that amount is in the smallest currency unit, e.g., cents
        return (int) (amount * rate);
    }

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer valueInThisCurrency(Integer amount, Currency othercurrency) {
        // First, convert the amount to the universal value using the other currency's rate
        double universalValue = amount * othercurrency.getRate();
        // Then, convert the universal value to this currency's value
        return (int) (universalValue / this.rate);
    }
}
