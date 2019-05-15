import api.UserConnection;
import wrappers.AuthenticationService;
import wrappers.ItemInformationProviderService;
import wrappers.PaymentService;

public class Shop {

    private ShoppingCart shoppingCart;

    private AuthenticationService authenticationService;

    private String address;

    private UserConnection userConnection;

    public Shop(ItemInformationProviderService itemInformationProviderService, AuthenticationService authenticationService) {
        this.shoppingCart = new ShoppingCart(itemInformationProviderService);
        this.authenticationService = authenticationService;
    }

    public void addToCart(String item, int quantity) {
        shoppingCart.add(item, quantity);
        System.out.println("Added " + quantity + " of " + item + " to cart.");
    }

    public void authenticate(String username, String passwordHash) {
        this.userConnection = authenticationService.authenticate();

        System.out.println("Authenticated as " + username + ".");
    }

    public void checkout(String address, String paymentIdentifier) throws Exception {
        double price = shoppingCart.summarizePriceOfItems();
        PaymentService paymentService = PaymentServiceFactory.createPaymentService(paymentIdentifier);
        paymentService.pay(price);

        this.address = address;
    }

}
