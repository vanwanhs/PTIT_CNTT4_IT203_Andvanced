package session3.lesson3;

import java.util.List;
import java.util.Optional;

public class Main {

    // record User
    record User(String username, String email) {}

    // UserRepository
    static class UserRepository {
        List<User> users = List.of(
                new User("alice", "alice@gmail.com"),
                new User("bob", "bob@yahoo.com"),
                new User("charlie", "charlie@gmail.com")
        );

        // tìm user theo username
        Optional<User> findUserByUsername(String username) {
            return users.stream()
                    .filter(u -> u.username().equals(username))
                    .findFirst();
        }
    }

    public static void main(String[] args) {

        UserRepository repo = new UserRepository();

        Optional<User> result = repo.findUserByUsername("alice");
        result.ifPresent(u -> System.out.println("Welcome " + u.username()));
        User user = result.orElse(null);
        if (user == null) {
            System.out.println("Guest login");
        }
    }
}