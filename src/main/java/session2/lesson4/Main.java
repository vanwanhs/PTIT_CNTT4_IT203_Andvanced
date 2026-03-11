package session2.lesson4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();

        users.add(new User("Anh", "123", "ADMIN"));
        users.add(new User("Binh", "abc", "USER"));
        users.add(new User("Cuong", "xyz", "USER"));

        // 1. (user) -> user.getUsername()
        // Method Reference
        Function<User, String> getUsername = User::getUsername;

        System.out.println("Danh sách username:");
        users.stream()
                .map(getUsername)
                .forEach(System.out::println);

        System.out.println("----------------");

        // 2. (s) -> System.out.println(s)
        // Method Reference
        System.out.println("In danh sách user:");
        users.forEach(System.out::println);

        System.out.println("----------------");

        // 3. () -> new User()
        // Method Reference constructor
        Supplier<User> createUser = User::new;

        User newUser = createUser.get();

        System.out.println("User mới:");
        System.out.println(newUser);
    }
}