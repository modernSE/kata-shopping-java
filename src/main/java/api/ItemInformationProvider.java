package api;

import java.util.Random;

public class ItemInformationProvider {

    public ItemInformationProvider(String itemDbUrl) {
    }

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
