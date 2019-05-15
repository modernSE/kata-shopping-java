import wrappers.ItemInformationProviderService;

public class BogusItemInformationProvider implements ItemInformationProviderService {
    @Override
    public double getPrice(String item) {
        return 42.;
    }
}
