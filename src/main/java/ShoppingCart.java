import wrappers.ItemInformationProviderService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart {

    private List<String> items;

    private ItemInformationProviderService itemInformationProviderService;

    public ShoppingCart(ItemInformationProviderService itemInformationProviderService) {
        this.items = new ArrayList<>();
        this.itemInformationProviderService = itemInformationProviderService;
    }

    public void add(String item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(item);
        }
    }

    public List<String> getItems() {
        return items;
    }

    public double summarizePriceOfItems() {
        return getItems().stream().collect(Collectors.summingDouble(itemInformationProviderService::getPrice));
    }
}
