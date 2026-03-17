package lesson6;

public class WebsiteDiscount implements DiscountStrategy {
    public double applyDiscount(double amount) {
        double discount = amount * 0.1;
        System.out.println("Áp dụng giảm giá 10%: " + discount);
        return amount - discount;
    }
}