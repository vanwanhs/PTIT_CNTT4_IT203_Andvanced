package session2.lesson2;

@FunctionalInterface
public interface PasswordValidator {
    boolean validate(String password);
}