package api;

public class DPDItemCreator {
    public static DPDItem create(String shippingAddress) {
        return new DPDItem(shippingAddress);
    }
}
