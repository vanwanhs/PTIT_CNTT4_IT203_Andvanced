package session2.lesson1;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        List<User> users = new ArrayList<>();

        users.add(new User("Nguyen Van Anh","Secret", "ADMIN"));
        users.add(new User("Nguyen Van Anh1","abc", "USER"));
        users.add(new User("Nguyen Van Anh2","def", "USER"));

        // 1. Predicate - kiểm tra user có phải ADMIN không
        Predicate<User> isAdmin = user -> user.getRole().equals("ADMIN");

        System.out.println("Danh sách ADMIN:");
        for (User user : users) {
            if (isAdmin.test(user)) {
                System.out.println(user);
            }
        }

        System.out.println("--------------------");

        // 2. Function - chuyển User -> String (username)
        Function<User, String> getUsername = user -> user.getUserName();

        System.out.println("Danh sách username:");
        for (User user : users) {
            System.out.println(getUsername.apply(user));
        }

        System.out.println("--------------------");

        // 3. Consumer - in thông tin user
        Consumer<User> printUser = user -> System.out.println(user);

        System.out.println("In thông tin user:");
        users.forEach(printUser);

        System.out.println("--------------------");

        // 4. Supplier - tạo user mới mặc định
        Supplier<User> createDefaultUser =
                () -> new User("defaultUser", "123456", "USER");

        User newUser = createDefaultUser.get();

        System.out.println("User mới được tạo:");
        System.out.println(newUser);
    }
}
