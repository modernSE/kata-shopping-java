package api.payment;

public class AlwaysOkPaymentProcessor implements PaymentProcessor{

    @Override
    public void pay(double price) throws PaymentFailedException {
        // NOOP
    }
    
}
