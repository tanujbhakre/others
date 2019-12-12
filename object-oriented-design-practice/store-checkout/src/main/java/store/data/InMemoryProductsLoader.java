package store.data;

import java.util.Arrays;
import java.util.List;

import store.bean.Product;

public class InMemoryProductsLoader implements ProductsLoader {
	@Override
	public List<Product> getProducts() {
		Product voucher = new Product("VOUCHER", "SNOW Voucher", 7.00f);
		Product tShirt = new Product("TSHIRT", "SNOW T-Shirt", 21.00f);
		Product mug = new Product("MUG", "SNOW Coffe Mug", 9.50f);
		return Arrays.asList(voucher, tShirt, mug);
	}
}
