package store.data;

import java.util.HashMap;
import java.util.Map;

import store.bean.Product;

public class InMemoryProductRepository implements ProductRepository {
	private Map<String, Product> map;
	private ProductsLoader loader;

	public InMemoryProductRepository() {
		map = new HashMap<>();

		loader = new InMemoryProductsLoader();
		loader.getProducts().forEach(product -> map.put(product.getCode(), product));
	}

	@Override
	public Product getByCode(String code) {
		return map.get(code);
	}

}
