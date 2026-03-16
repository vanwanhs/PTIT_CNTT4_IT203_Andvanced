package lesson3;
public interface CardPayable extends PaymentMethod {
    void processCreditCard(double amount);
}