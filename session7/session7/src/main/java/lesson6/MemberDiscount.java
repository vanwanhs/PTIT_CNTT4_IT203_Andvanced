package lesson6;
public class MemberDiscount implements DiscountStrategy {

    public double applyDiscount(double total) {

        System.out.println("Áp dụng giảm giá thành viên 5% tại cửa hàng");

        return total * 0.95;
    }
}