package lesson5;
public class SMSNotification implements NotificationService {

    public void send(String message) {
        System.out.println("Đã gửi SMS");
    }
}