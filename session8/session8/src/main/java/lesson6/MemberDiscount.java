package lesson6;

public class MemberDiscount implements DiscountStrategy {
    public double applyDiscount(double amount) {
        double discount = amount * 0.05;
        System.out.println("Áp dụng giảm giá 5%: " + discount);
        return amount - discount;
    }
}