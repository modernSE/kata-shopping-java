import java.util.ArrayList;
import java.util.List;

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
}
