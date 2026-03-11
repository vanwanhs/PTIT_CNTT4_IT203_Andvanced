package session2.lesson4;

public class User {

    private String username;
    private String password;
    private String role;

    public User() {
        this.username = "defaultUser";
        this.password = "123456";
        this.role = "USER";
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return username + " - " + role;
    }
}