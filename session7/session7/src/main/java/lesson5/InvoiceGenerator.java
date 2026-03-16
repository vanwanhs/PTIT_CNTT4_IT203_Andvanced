package lesson5;
public class InvoiceGenerator {

    public void generate(Order order) {

        System.out.println("=== HÓA ĐƠN ===");

        System.out.println("Khách: " + order.getCustomer().getName());

        for(OrderItem item : order.getItems()) {

            System.out.println(
                    item.getProduct().getId() +
                            " - Số lượng: " + item.getQuantity() +
                            " - Đơn giá: " + (int)item.getProduct().getPrice() +
                            " - Thành tiền: " + (int)item.getTotal()
            );
        }

        System.out.println("Cần thanh toán: " + (int)order.getFinalAmount());
    }
}