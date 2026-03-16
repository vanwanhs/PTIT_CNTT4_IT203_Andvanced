package lesson5;
public class CreditCardPayment implements PaymentMethod {

    public void pay(double amount) {
        System.out.println("Thanh toán thẻ tín dụng: " + (int)amount);
    }
}