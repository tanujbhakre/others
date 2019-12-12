package store;

import java.util.Arrays;

import store.rules.PricingRule;
import store.rules.PricingRuleBulk;
import store.rules.PricingRuleNForM;

public class Main {
	public static void main(String args[]) {
		PricingRule nForMRule = new PricingRuleNForM("VOUCHER", 2, 1);
		PricingRule bulkRule = new PricingRuleBulk("TSHIRT", 19f, 3);

		Checkout checkout = new Checkout(Arrays.asList(bulkRule, nForMRule));
		checkout.scan("VOUCHER").scan("TSHIRT").scan("MUG");
		System.out.println("Checkout 1:" + checkout.total());

		checkout.scan("TSHIRT").scan("TSHIRT").scan("TSHIRT").scan("VOUCHER").scan("TSHIRT");
		System.out.println("Checkout 2:" + checkout.total());

		checkout.scan("VOUCHER").scan("TSHIRT").scan("VOUCHER").scan("VOUCHER").scan("MUG").scan("TSHIRT")
				.scan("TSHIRT");
		System.out.println("Checkout 3:" + checkout.total());

	}
}
