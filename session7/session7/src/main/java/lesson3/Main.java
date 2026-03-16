package lesson3;

public class Main {

    public static void main(String[] args) {

        PaymentProcessor processor = new PaymentProcessor();

        // COD Payment
        System.out.println("COD");
        CODPayment cod = new CODPayment();
        processor.processPayment(cod, 500000);

        System.out.println();

        // Credit Card Payment
        System.out.println("Thẻ tín dụng");
        CreditCardPayment card = new CreditCardPayment();
        processor.processPayment(card, 1000000);

        System.out.println();

        // Momo Payment
        System.out.println("Ví MoMo");
        MomoPayment momo = new MomoPayment();
        processor.processPayment(momo, 750000);

        System.out.println();

        // Kiểm tra LSP
        System.out.println("Kiểm tra LSP");

        EWalletPayable payment = new MomoPayment(); // thay implementation

        payment.processMomo(750000);
    }
}