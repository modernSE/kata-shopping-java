import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import api.auth.AlwaysOkAuthProvider;
import api.auth.AuthProvider;
import api.info.ConstantItemInfoProvider;
import api.info.ItemInfoProvider;
import api.payment.AlwaysOkPaymentProcessor;
import api.payment.PaymentProcessor;

import static junit.framework.TestCase.fail;

public class TestShop {

    private static final double PRICE_OF_ONE_ITEM = 1337;
    private final AuthProvider authProvider = new AlwaysOkAuthProvider();
    private final ItemInfoProvider infoProvider = new ConstantItemInfoProvider(PRICE_OF_ONE_ITEM);
    private final PaymentProcessor paymentProcessor = new AlwaysOkPaymentProcessor();

    
    @Test
    public void testBuyOnePayOne() {
        Shop shop = new Shop(authProvider, infoProvider, paymentProcessor);
        shop.authenticate();
        shop.addToCart("One Item", 1);
        try {
            var totalPrice = shop.checkout();
            assertEquals(PRICE_OF_ONE_ITEM, totalPrice, 0);

        } catch (Exception e) {
            Assert.assertEquals("Unknown payment provider!", e.getMessage());
        }
    }

    
    @Test
    public void testBuyTwoPayTwo() {
        Shop shop = new Shop(authProvider, infoProvider, paymentProcessor);
        shop.authenticate();
        shop.addToCart("Two Items", 2);
        try {
            var totalPrice = shop.checkout();
            assertEquals(PRICE_OF_ONE_ITEM * 2, totalPrice, 0);
            
        } catch (Exception e) {
            Assert.assertEquals("Unknown payment provider!", e.getMessage());
        }
    }



}
