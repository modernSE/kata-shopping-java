import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.fail;

public class TestShop {

	private Shop shop;
	
	@Before
	public void setUpShop() {
		TestAuthenticationProvider authenticationProvider = new TestAuthenticationProvider();
		TestItemInformationProvider itemInformationProvider = new TestItemInformationProvider();
		shop = new Shop(authenticationProvider, itemInformationProvider);
	}
	
    @Test
    public void testShopWithBuyBuddy() {
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
        shop.authenticate();
        shop.addToCart("D&D Dungeon Master's Guide", 1);
        try {
            shop.checkout(null, "invoice:Robert Glaser, CAS-Weg 1-5, 76131 Karlsruhe");
        } catch (Exception e) {
            Assert.assertEquals("Unknown payment provider!", e.getMessage());
        }
    }
    
    @Test
    public void testShopCheckout() {
    	shop.authenticate();
    	shop.addToCart("Table", 1);
    	shop.addToCart("Volleyball", 1);
    	shop.addToCart("Basketball", 1);
    	shop.addToCart("Baseball", 1);
    	
    	try {
			double price = shop.checkout(null, "jban:1234567890");
			assertEquals("TableVolleyballBasketballBaseball".length(), price, 0.0001);
		} catch (Exception e) {
			  fail(e.getMessage());
		}
    }
}
