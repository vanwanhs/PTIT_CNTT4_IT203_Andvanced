package session2.lesson6;

public class Main {

    public static void main(String[] args) {

        User user = new User("Nguyen Van Anh", "123456", "ADMIN");
        UserProcessor processor = UserUtils::convertToUpperCase;

        String result = processor.process(user);

        System.out.println("Username sau khi xử lý: " + result);
    }

}