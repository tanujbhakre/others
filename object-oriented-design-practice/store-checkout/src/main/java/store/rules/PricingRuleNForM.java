package store.rules;

import store.bean.Product;

public class PricingRuleNForM implements PricingRule {
	private String code;
	private Integer n;
	private Integer m;

	public PricingRuleNForM(String code, Integer n, Integer m) {
		this.code = code;
		this.n = n;
		this.m = m;
	}

	@Override
	public Float getTotal(Product product, Integer quantity) {
		if (product == null || !(quantity > 0))
			return 0f;
		Float total = 0f;
		if (code.equals(product.getCode())) {
			while (quantity > 0) {
				total += Math.min(quantity, n) * product.getPrice();
				quantity = quantity - n;
				quantity = quantity - m;
			}
		} else {
			total = quantity * product.getPrice();
		}
		return total;
	}

	@Override
	public String getCode() {
		return code;
	}
}
