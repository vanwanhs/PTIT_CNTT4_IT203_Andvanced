package session2.lesson3;

public class Main {
    public static void main(String[] args) {

        // mã hóa mật khẩu bằng static method
        String encrypted = Authenticatable.encrypt("123456");

        User user = new User("NguyenVanAnh", encrypted);

        System.out.println("User: " + user);
        System.out.println("Password: " + user.getPassword());

        // dùng default method
        System.out.println("Authenticated: " + user.isAuthenticated());
    }
}