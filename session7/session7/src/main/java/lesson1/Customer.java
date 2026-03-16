package lesson1;

public class Customer {
    private String name;
    private String email;
    private String address;

    public Customer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;

        System.out.println("Tạo khách hàng: " + name + " - " + email);
        System.out.println("Đã thêm khách hàng");
    }

    public String getEmail() {
        return email;
    }
}