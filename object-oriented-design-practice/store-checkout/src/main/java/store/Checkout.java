package store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import store.bean.Product;
import store.data.InMemoryProductRepository;
import store.data.ProductRepository;
import store.rules.PricingRule;

public class Checkout {

	private Map<Product, Integer> productsMap = new HashMap<>();
	private List<PricingRule> rule;
	private ProductRepository respository = new InMemoryProductRepository();

	public Checkout(List<PricingRule> rule) {
		this.rule = rule;
	}

	public Checkout scan(String id) {
		Product product = respository.getByCode(id);
		if (product != null) {
			Integer count = productsMap.get(product);
			count = count == null ? 1 : count + 1;
			productsMap.put(product, count);
		}
		return this;
	}

	public Float total() {
		Float total = 0f;

		for (Map.Entry<Product, Integer> entry : productsMap.entrySet()) {
			boolean applied = false;
			Float value = 0f;
			for (PricingRule r : rule) {
				if (r.getCode().equals(entry.getKey().getCode())) {
					// storing maximum total i.e. minimum discount
					value = Math.max(value, r.getTotal(entry.getKey(), entry.getValue()));
					applied = true;
				}
			}
			if (!applied)
				total += entry.getValue() * entry.getKey().getPrice();
			else
				total += value;
		}
		productsMap = new HashMap<>();
		return total;
	}
}