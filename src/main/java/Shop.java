import java.util.List;

import api.BankeinzugService;
import api.BuyBuddy;
import api.ItemInformationProvider;
import api.JBANConnection;
import api.JBANConnectionException;
import api.JBANValidationException;
import api.UserConnection;
import api.UsernamePasswordAuthenticationProvider;

//Dependency-Inversion-Principle und Open-Closed-Prinzip verletzt:
// new()-Konstruktor bei 
// - bankeinzugService
// - usernamePasswordAuthenticationProvider
// - itemInformationProvider



//1.1
//Single-Responsibility-Prinzip
//1.2
//Open-Closed-Prinzip
//1.3
//Liskovsches Substitutionsprinzip: hier nicht relevant, da keine Vererbung
//1.4
//Interface-Segregation-Prinzip
//1.5
//Dependency-Inversion-Prinzip

public class Shop {

    private ShoppingCart shoppingCart = new ShoppingCart();
 
    private AuthenticationProvider authenticationProvider;
    
    private IItemInformationProvider itemInformationProvider;
    
    private String address;

    private UserConnection userConnection;

	private List<PaymentService> paymentServices;
    
    public Shop(AuthenticationProvider authenticationProvider, IItemInformationProvider itemInformationProvider, List<PaymentService> paymentServices) {
    	this.authenticationProvider = authenticationProvider;
    	this.itemInformationProvider = itemInformationProvider;
    	this.paymentServices = paymentServices;
    }


    public void addToCart(String item, int quantity) {
        shoppingCart.add(item, quantity);
        System.out.println("Added " + quantity + " of " + item + " to cart.");
    }

    // Was passiert, wenn sich mehrere user authenticaten?
    // Sollte nicht jeder User einen ShoppingCart erhalten? (return new ShoppingCard()?)
    public void authenticate() {
        this.userConnection = authenticationProvider.authenticate();
    }

    public double checkout(String address, String paymentIdentifier) throws Exception {
        double price = 0;
        for (String item : shoppingCart.getItems()) {
            price += itemInformationProvider.getPrice(item);
        }
        
        // TODO: remove :-)
        final double finalPrice = price;
        
       paymentServices.stream() //
       	.filter(paymentService -> paymentService.appliedToPaymentIdentifier(paymentIdentifier)) //
       	.findAny() //
       	.ifPresent(paymentService -> {
			try {
				paymentService.pay(paymentIdentifier, finalPrice);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
       // TODO: exception if 

        this.address = address;
        return price;
    }
}
