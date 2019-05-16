import api.ItemInformationProvider;

public class ApiItemInformationProvider implements IItemInformationProvider {

	private ItemInformationProvider itemInformationProvider;
	
	public ApiItemInformationProvider(String itemDbUrl) {
		this.itemInformationProvider = new ItemInformationProvider(itemDbUrl);
	}

	@Override
	public double getPrice(String item) {
		return itemInformationProvider.getPrice(item);
	}

}
