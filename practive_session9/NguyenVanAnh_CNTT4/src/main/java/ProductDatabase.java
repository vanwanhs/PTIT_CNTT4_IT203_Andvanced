import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> products;

    private ProductDatabase() {
        products = new ArrayList<>();
    }

    public static ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }

    public void addProduct(Product p) {
        if (findById(p.getId()) != null) {
            System.out.println("ID đã tồn tại!");
            return;
        }
        products.add(p);
        System.out.println("Thêm thành công!");
    }

    public void displayAll() {
        if (products.isEmpty()) {
            System.out.println("Danh sách rỗng!");
            return;
        }
        for (Product p : products) {
            p.displayInfo();
        }
    }

    public Product findById(String id) {
        for (Product p : products) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    public void updateProduct(String id, String newName, double newPrice) {
        Product p = findById(id);
        if (p == null) {
            System.out.println("Không tìm thấy!");
            return;
        }
        p.setName(newName);
        p.setPrice(newPrice);
        System.out.println("Cập nhật thành công!");
    }

    public void deleteProduct(String id) {
        Product p = findById(id);
        if (p == null) {
            System.out.println("Không tìm thấy!");
            return;
        }
        products.remove(p);
        System.out.println("Xóa thành công!");
    }
}