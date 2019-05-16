
public class TestItemInformationProvider implements IItemInformationProvider {

	@Override
	public double getPrice(String item) {
        return item.length();
	}
}
