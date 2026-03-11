package session3.lesson1;
import java.util.List;

public class Main {
    enum Status {
        ACTIVE,
        INACTIVE
    }
    record User(String username, String email, Status status) {}

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alice", "alice@gmail.com", Status.ACTIVE),
                new User("bob", "bob@gmail.com", Status.INACTIVE),
                new User("charlie", "charlie@gmail.com", Status.ACTIVE)
        );
        users.forEach(user ->
                System.out.println(
                        "Username: " + user.username() +
                                ", Email: " + user.email() +
                                ", Status: " + user.status()
                )
        );
    }
}