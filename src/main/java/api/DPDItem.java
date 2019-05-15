package api;

public class DPDItem {
    private String address;

    public DPDItem(String shippingAddress) {
        this.address = shippingAddress;
    }

    public void dispatch() {
        //ok. Parcel will be picked up.
    }
}
