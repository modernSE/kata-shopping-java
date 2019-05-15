package wrappers;

import api.BuyBuddy;

public class BuyBuddyService implements PaymentService {

    private BuyBuddy buyBuddy;

    public BuyBuddyService(final String buyBuddyId) {
        this.buyBuddy = new BuyBuddy(buyBuddyId);
    }

    @Override
    public void pay(double amount) throws Exception {
        int i = buyBuddy.executeBuyBuddyTransaction(amount);
        if (i != 200) {
            throw new Exception("Error executing api.BuyBuddy payment.");
        }
        System.out.println("Payed " + amount + " via BuyBuddy.");
    }
}
