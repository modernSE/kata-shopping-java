package api.payment;

public class PaymentFailedException extends Exception {

    private static final long serialVersionUID = 1L;

    public PaymentFailedException(String error){
        super(error);
    }

}
