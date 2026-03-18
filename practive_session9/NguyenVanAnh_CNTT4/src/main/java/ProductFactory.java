import java.util.Scanner;

public class ProductFactory {
    public static Product createProduct(int type, Scanner sc){
        System.out.print("Nhập id sản phẩm: ");
        String id = sc.nextLine();

        System.out.print("Nhập tên sản phẩm: ");
        String name = sc.nextLine();

        double price;
        while (true) {
            try {
                System.out.print("Nhập giá: ");
                price = Double.parseDouble(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Sai! Nhập lại.");
            }
        }

        if(type == 1){
            double weight;
            while (true) {
                try {
                    System.out.print("Nhập trọng lượng (kg): ");
                    weight = Double.parseDouble(sc.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("Sai! Nhập lại.");
                }
            }
            return new PhysicalProduct(id, name, price, weight);

        } else {
            double size;
            while (true) {
                try {
                    System.out.print("Nhập dung lượng (MB): ");
                    size = Double.parseDouble(sc.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("Sai! Nhập lại.");
                }
            }
            return new DigitalProduct(id, name, price, size);
        }
    }
}