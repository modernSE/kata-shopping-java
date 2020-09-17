import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import api.auth.AlwaysOkAuthProvider;
import api.auth.AuthProvider;
import api.info.ConstantItemInfoProvider;
import api.info.ItemInfoProvider;

import static junit.framework.TestCase.fail;

public class TestShop {

    private static final double PRICE_OF_ONE_ITEM = 1337;
    private final AuthProvider authProvider = new AlwaysOkAuthProvider();
    private final ItemInfoProvider infoProvider = new ConstantItemInfoProvider(PRICE_OF_ONE_ITEM);

    @Test
    public void testShopWithBuyBuddy() {
        Shop shop = new Shop(authProvider, infoProvider);
        shop.authenticate();
        shop.addToCart("D&D Player's Handbook", 1);
        try {
            shop.checkout(null, "buybuddy:Robert.Glaser@cas.de");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testShopViaJBAN() {
        Shop shop = new Shop(authProvider, infoProvider);
        shop.authenticate();
        shop.addToCart("D&D Dungeon Master's Guide", 1);
        try {
            shop.checkout(null, "jban:1234567890");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testShopViaInvoice() {
        Shop shop = new Shop(authProvider, infoProvider);
        shop.authenticate();
        shop.addToCart("D&D Dungeon Master's Guide", 1);
        try {
            shop.checkout(null, "invoice:Robert Glaser, CAS-Weg 1-5, 76131 Karlsruhe");
        } catch (Exception e) {
            Assert.assertEquals("Unknown payment provider!", e.getMessage());
        }
    }

    
    @Test
    public void testBuyOnePayOne() {
        Shop shop = new Shop(authProvider, infoProvider);
        shop.authenticate();
        shop.addToCart("One Item", 1);
        try {
            var totalPrice = shop.checkout(null, "invoice:Robert Glaser, CAS-Weg 1-5, 76131 Karlsruhe");
            assertEquals(PRICE_OF_ONE_ITEM, totalPrice, 0);

        } catch (Exception e) {
            Assert.assertEquals("Unknown payment provider!", e.getMessage());
        }
    }

    
    @Test
    public void testBuyTwoPayTwo() {
        Shop shop = new Shop(authProvider, infoProvider);
        shop.authenticate();
        shop.addToCart("Two Items", 2);
        try {
            var totalPrice = shop.checkout(null, "invoice:Robert Glaser, CAS-Weg 1-5, 76131 Karlsruhe");
            assertEquals(PRICE_OF_ONE_ITEM * 2, totalPrice, 0);
            
        } catch (Exception e) {
            Assert.assertEquals("Unknown payment provider!", e.getMessage());
        }
    }



}
