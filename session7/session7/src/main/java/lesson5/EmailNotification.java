package lesson5;
public class EmailNotification implements NotificationService {

    public void send(String message) {
        System.out.println("Đã gửi email xác nhận");
    }
}