import org.junit.Assert;
import org.junit.Test;

public class ShoppingCartTest {

    @Test
    public void testAddingTwoItemsShouldReturn84() {
        ShoppingCart shoppingCart = new ShoppingCart(new BogusItemInformationProvider());
        shoppingCart.add("D&D Player's handbook", 1);
        shoppingCart.add("D&D Dungeon Master's Guide", 1);
        Assert.assertEquals(84., shoppingCart.summarizePriceOfItems(), 0.);
    }
}
