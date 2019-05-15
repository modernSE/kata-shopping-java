import api.BankeinzugService;
import api.BuyBuddy;
import api.DPDItem;
import api.DPDItemCreator;
import api.ItemInformationProvider;
import api.JBANConnection;
import api.JBANConnectionException;
import api.JBANValidationException;
import api.UserConnection;
import api.UsernamePasswordAuthenticationProvider;
import api.WishListStorage;

public class Shop {

    private ShoppingCart shoppingCart = new ShoppingCart();

    private String itemDbUrl = "https://internal.ferdis-brettspiel-emporium.shop/priceDb";

    private BankeinzugService bankeinzugService = new BankeinzugService();

    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    private double amount;

    private String address;

    private UserConnection userConnection;

    public void addToCartOrWishlist(String item, int quantity, String listName) {
        if (listName == null) {
            shoppingCart.add(item, quantity);
            System.out.println("Added " + quantity + " of " + item + " to cart.");
        } else {
            WishListStorage wishListStorage = new WishListStorage(userConnection);
            System.out.println("Added " + quantity + " of " + item + " to wishlist " + listName + ".");
        }
    }

    public void authenticate(String username, String passwordHash) {
        usernamePasswordAuthenticationProvider = new UsernamePasswordAuthenticationProvider(username, passwordHash);
        this.userConnection = usernamePasswordAuthenticationProvider.authenticate();

        System.out.println("Authenticated as " + username + ".");
    }

    public void checkout(String address, String paymentIdentifier) throws Exception {
        double price = 0;
        final ItemInformationProvider itemInformationProvider = new ItemInformationProvider(itemDbUrl);
        for (String item : shoppingCart.getItems()) {
            price += itemInformationProvider.getPrice(item);
        }

        if (paymentIdentifier.startsWith("jban:")) {
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
        } else if (paymentIdentifier.startsWith("buybuddy:")) {
            BuyBuddy buyBuddy = new BuyBuddy(paymentIdentifier.substring(9));
            int i = buyBuddy.executeBuyBuddyTransaction(price);
            if (i != 200) {
                throw new Exception("Error executing api.BuyBuddy payment.");
            }
            System.out.println("Payed " + price + " via BuyBuddy.");
        } else {
            throw new Exception("Unknown payment provider!");
        }

        this.address = address;
    }

    private String afterAcquireJBANToken(String token) {
        bankeinzugService.legitimiereBankeinzug(amount, token); return "JBAN Payment successful";
    }

    public void ship() {
        String addressToShipTo = null;
        if (this.address != null) {
            addressToShipTo = this.address;
        } else {
            addressToShipTo = userConnection.getShippingAddress();
        }
        DPDItem dpdItem = DPDItemCreator.create(addressToShipTo);
        dpdItem.dispatch();

        System.out.println("Shipping to " + addressToShipTo + " with DPD.");
    }

}
