package machine;

public class Inventory {
	private Product product;
	private int units;

	public Inventory(Product product, int units) {
		this.product = product;
		this.units = units;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getUnits() {
		return units;
	}

	public void decrementUnit() {
		this.units--;
	}
}
