package api.info;

public class ConstantItemInfoProvider implements ItemInfoProvider {

    private double price;

    public ConstantItemInfoProvider(double price) {
        this.price = price;
    }

    @Override
    public double getPrice(String item) {
        return price;
    }

}
