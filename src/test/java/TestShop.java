import org.junit.Assert;
import org.junit.Test;
import static junit.framework.TestCase.fail;

public class TestShop {

    @Test
    public void testShopWithBuyBuddy() {
        Shop shop = new Shop();
        shop.authenticate("Robert Glaser", "hash");
        shop.addToCartOrWishlist("D&D Player's Handbook", 1, null);
        try {
            shop.checkout(null, "buybuddy:Robert.Glaser@cas.de");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        shop.ship();
    }

    @Test
    public void testShopViaJBAN() {
        Shop shop = new Shop();
        shop.authenticate("Robert Glaser", "hash");
        shop.addToCartOrWishlist("D&D Dungeon Master's Guide", 1, null);
        try {
            shop.checkout(null, "jban:1234567890");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        shop.ship();
    }

    @Test
    public void testShopViaInvoice() {
        Shop shop = new Shop();
        shop.authenticate("Robert Glaser", "hash");
        shop.addToCartOrWishlist("D&D Dungeon Master's Guide", 1, null);
        try {
            shop.checkout(null, "invoice:Robert Glaser, CAS-Weg 1-5, 76131 Karlsruhe");
        } catch (Exception e) {
            Assert.assertEquals("Unknown payment provider!", e.getMessage());
        }
    }
}
