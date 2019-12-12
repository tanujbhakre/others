package store.rules;

import store.bean.Product;

public class PricingRuleBulk implements PricingRule {

	private String code;
	private Float price;
	private Integer minQuantity;

	public PricingRuleBulk(String code, Float price, Integer minQuantity) {
		this.code = code;
		this.price = price;
		this.minQuantity = minQuantity;
	}

	@Override
	public Float getTotal(Product product, Integer quantity) {
		if (product == null || !(quantity > 0))
			return 0f;
		if (code.equals(product.getCode())) {
			if (quantity >= minQuantity) {
				return quantity * price;
			}
		}
		return quantity * product.getPrice();
	}

	@Override
	public String getCode() {
		return code;
	}

}
