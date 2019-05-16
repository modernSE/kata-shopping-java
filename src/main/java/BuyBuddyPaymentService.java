import api.BuyBuddy;

public class BuyBuddyPaymentService implements PaymentService {
	
	@Override
	public void pay(String paymentIdentifier, double price) throws Exception {
		 BuyBuddy buyBuddy = new BuyBuddy(paymentIdentifier.substring(9));
         int i = buyBuddy.executeBuyBuddyTransaction(price);
         if (i != 200) {
             throw new Exception("Error executing api.BuyBuddy payment.");
         }
         System.out.println("Payed " + price + " via BuyBuddy.");
	}

	@Override
	public boolean appliedToPaymentIdentifier(String paymentIdentifier) {
		return paymentIdentifier.startsWith("buybuddy:");
	}
}
