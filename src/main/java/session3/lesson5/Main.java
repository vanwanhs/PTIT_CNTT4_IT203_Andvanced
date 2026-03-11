package session3.lesson5;

import java.util.List;
import java.util.Comparator;

public class Main {

    record User(String name, String email) {}

    public static void main(String[] args) {

        List<User> users = List.of(
                new User("alexander", "a@gmail.com"),
                new User("bob", "b@gmail.com"),
                new User("charlotte", "c@gmail.com"),
                new User("benjamin", "d@gmail.com"),
                new User("tom", "t@gmail.com"),
                new User("david", "d@gmail.com")
        );

        users.stream()
                .sorted(Comparator.comparingInt((User u) -> u.name().length()).reversed())
                .limit(3)
                .map(User::name)
                .forEach(System.out::println);
    }
}