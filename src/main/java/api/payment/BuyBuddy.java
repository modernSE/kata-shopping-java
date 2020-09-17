package api.payment;

public class BuyBuddy implements PaymentProcessor {
    String buyBuddyEndpoint = "https://buybuddy.org";

    public BuyBuddy(String buyBuddyId) {

    }

    public int executeBuyBuddyTransaction(double amount) {
        if (amount > 0) {
            return 200;
        }
        return 404;
    }

    @Override
    public void pay(double price) throws PaymentFailedException {
        var returnCode = executeBuyBuddyTransaction(price);
        if(returnCode != 200){
            throw new PaymentFailedException("Error executing api.BuyBuddy payment: " + returnCode);
        }

    }
}
