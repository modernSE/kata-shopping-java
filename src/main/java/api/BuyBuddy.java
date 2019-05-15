package api;

public class BuyBuddy {
    String buyBuddyEndpoint = "https://buybuddy.org";

    public BuyBuddy(String buyBuddyId) {

    }

    public int executeBuyBuddyTransaction(double amount) {
        if (amount > 0) {
            return 200;
        }
        return 404;
    }
}
