package lesson1;

public class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        System.out.println("Tạo sản phẩm: " + id + " - " + name + " - " + (int)price);
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}