package lesson5;

import java.util.*;

public class Main {

    static List<Product> products = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        OrderRepository repo = new FileOrderRepository();
        NotificationService notify = new EmailNotification();

        OrderService service = new OrderService(repo, notify);

        while(true) {

            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Xem đơn hàng");
            System.out.println("5. Thoát");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1) {

                System.out.print("Mã: ");
                String id = sc.nextLine();

                System.out.print("Tên: ");
                String name = sc.nextLine();

                System.out.print("Giá: ");
                double price = sc.nextDouble();
                sc.nextLine();

                products.add(new Product(id,name,price,"Điện tử"));

                System.out.println("Đã thêm sản phẩm " + id);
            }

            else if(choice == 2) {

                System.out.print("Tên: ");
                String name = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("ĐT: ");
                String phone = sc.nextLine();

                customers.add(new Customer(name,email,phone));

                System.out.println("Đã thêm khách hàng");
            }

            else if(choice == 3) {

                Customer c = customers.get(0);

                Product p = products.get(0);

                Order order = new Order("ORD001", c);

                order.addItem(new OrderItem(p,1));

                DiscountStrategy discount = new PercentageDiscount(10);

                PaymentMethod payment = new CreditCardPayment();

                service.placeOrder(order, discount, payment);

                new InvoiceGenerator().generate(order);
            }

            else if(choice == 4) {

                for(Order o : repo.findAll()) {

                    System.out.println(
                            o.getOrderId() +
                                    " - " +
                                    o.getCustomer().getName() +
                                    " - " +
                                    (int)o.getFinalAmount()
                    );
                }
            }

            else if(choice == 5) {
                break;
            }
        }
    }
}