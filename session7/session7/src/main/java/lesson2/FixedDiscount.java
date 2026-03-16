package lesson2;

public class FixedDiscount implements DiscountStrategy {

    private double amount;

    // constructor nhận số tiền giảm
    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - amount;
    }
}