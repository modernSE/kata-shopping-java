import java.util.ArrayList;
import java.util.List;

import api.info.ItemInfoProvider;

public class ShoppingCart {

    List<String> items = new ArrayList<>();

    public void add(String item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(item);
        }
    }

    public List<String> getItems() {
        return items;
    }

	public double checkout(ItemInfoProvider infoProvider) {
		var price = 0;
        for (String item : getItems()) {
            price += infoProvider.getPrice(item);
        }

        return price;
	}
}
