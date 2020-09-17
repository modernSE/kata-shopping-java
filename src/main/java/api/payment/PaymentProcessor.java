package api.payment;

public interface PaymentProcessor {
    
    public void pay(double price) throws PaymentFailedException;
}
