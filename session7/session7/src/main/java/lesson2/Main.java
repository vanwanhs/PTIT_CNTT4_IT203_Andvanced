package lesson2;
public class Main {

    public static void main(String[] args) {

        double totalAmount = 1000000;

        // Test 1: PercentageDiscount 10%
        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng PercentageDiscount 10%");
        OrderCalculator calc1 = new OrderCalculator(new PercentageDiscount(10));
        double result1 = calc1.calculateFinalAmount(totalAmount);
        System.out.println("Số tiền sau giảm: " + (int) result1);

        System.out.println();

        // Test 2: FixedDiscount 50.000
        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng FixedDiscount 50.000");

        OrderCalculator calc2 = new OrderCalculator(new FixedDiscount(50000));

        double result2 = calc2.calculateFinalAmount(totalAmount);

        System.out.println("Số tiền sau giảm: " + (int) result2);

        System.out.println();

        // Test 3: NoDiscount
        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng NoDiscount");
        OrderCalculator calc3 = new OrderCalculator(new NoDiscount());
        double result3 = calc3.calculateFinalAmount(totalAmount);
        System.out.println("Số tiền sau giảm: " + (int) result3);

        System.out.println();

        // Test 4: HolidayDiscount 15% (thêm mới)
        System.out.println("Thêm HolidayDiscount 15% (không sửa code cũ)");
        OrderCalculator calc4 = new OrderCalculator(new HolidayDiscount());
        double result4 = calc4.calculateFinalAmount(totalAmount);
        System.out.println("Số tiền sau giảm: " + (int) result4);
    }
}