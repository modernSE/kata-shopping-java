package api;

import wrappers.ItemInformationProviderService;

import java.util.Random;

public class ItemInformationProvider implements ItemInformationProviderService {

    public ItemInformationProvider(String itemDbUrl) {
    }

    @Override
    public double getPrice(String item) {
        int sum = item.chars().sum();
        if (sum < 0) {
            sum = Math.abs(sum);
        }

        Double sumAsDouble = new Double(sum);
        while (sumAsDouble > 100) {
            sumAsDouble = sumAsDouble / 100.;
        }

        Double offset = new Double(new Random().nextInt(100)) / 100.;
        return sumAsDouble + offset;
    }
}
