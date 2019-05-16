import api.BankeinzugService;
import api.JBANConnection;
import api.JBANConnectionException;
import api.JBANValidationException;

public class JBANPaymentService implements PaymentService {
	
	private BankeinzugService bankeinzugService = new BankeinzugService();
	
	 private double amount;

	@Override
	public void pay(String paymentIdentifier, double price) throws Exception {
		String jbanResult = null;
		 try {
             JBANConnection jbanConnection = new JBANConnection(paymentIdentifier.substring(5));
             this.amount = price;
             jbanResult = jbanConnection.executeWithJBANToken(this::afterAcquireJBANToken);
             System.out.println("Payed " + price + " via JBAN Bankeinzug.");
         } catch (JBANValidationException e) {
             jbanResult = "Error validation JBAN";
             throw new Exception(jbanResult);
         } catch (JBANConnectionException e) {
             jbanResult = "Failed to execute transaction. Unknown error.";
             throw new Exception(jbanResult);
         }
	}
	
	 private String afterAcquireJBANToken(String token) {
		 bankeinzugService.legitimiereBankeinzug(amount, token); return "JBAN Payment successful";
	  }

	@Override
	public boolean appliedToPaymentIdentifier(String paymentIdentifier) {
		return paymentIdentifier.startsWith("jban:");
	}
}
