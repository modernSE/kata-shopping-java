
import api.UserConnection;
import api.auth.AuthProvider;
import api.info.ItemInfoProvider;
import api.payment.PaymentProcessor;

public class Shop {

    private ShoppingCart shoppingCart = new ShoppingCart();

    private final AuthProvider authProvider;

    private final ItemInfoProvider infoProvider;

    private final PaymentProcessor paymentProcessor;

    private UserConnection userConnection;

    public void addToCart(String item, int quantity) {
        shoppingCart.add(item, quantity);
        System.out.println("Added " + quantity + " of " + item + " to cart.");
    }

    public void authenticate() {
        this.userConnection = authProvider.authenticate();
    }

    public double checkout() throws Exception {
        double price = shoppingCart.checkout(infoProvider);

        paymentProcessor.pay(price);

        return price;
    }

    public Shop(AuthProvider authProvider, ItemInfoProvider infoProvider, PaymentProcessor paymentProcessor) {
        this.authProvider = authProvider;
        this.infoProvider = infoProvider;
        this.paymentProcessor = paymentProcessor;
    }

}
