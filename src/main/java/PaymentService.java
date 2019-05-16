
public interface PaymentService {
	
	boolean appliedToPaymentIdentifier(String paymentIdentifier);
	
	void pay(String paymentInformation, double price) throws Exception;
}
