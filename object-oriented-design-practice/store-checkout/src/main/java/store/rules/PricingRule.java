package store.rules;

import store.bean.Product;

public interface PricingRule {
	Float getTotal(Product product, Integer quantity);

	String getCode();

}
