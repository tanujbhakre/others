package store;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import store.rules.PricingRule;
import store.rules.PricingRuleBulk;
import store.rules.PricingRuleNForM;

public class CheckoutTest {
	private Checkout checkout;

	@Before
	public void setup() {
		PricingRule nForMRule = new PricingRuleNForM("VOUCHER", 2, 1);
		PricingRule bulkRule = new PricingRuleBulk("TSHIRT", 19f, 3);

		checkout = new Checkout(Arrays.asList(bulkRule, nForMRule));
	}

	@Test
	public void whenAllTheProductsAreDifferent() {

		checkout.scan("VOUCHER").scan("TSHIRT").scan("MUG");
		assertTrue(checkout.total().equals(37.5f));
	}

	@Test
	public void whenTshirtAreDiscounted() {
		checkout.scan("TSHIRT").scan("TSHIRT").scan("TSHIRT").scan("VOUCHER").scan("TSHIRT");
		assertTrue(checkout.total().equals(83.0f));
	}

	@Test
	public void whenVoucherAreDiscounted() {
		checkout.scan("VOUCHER").scan("VOUCHER").scan("TSHIRT").scan("VOUCHER").scan("TSHIRT");
		assertTrue(checkout.total().equals(56.0f));
	}

	@Test
	public void whenTshirtAndVoucherAreDiscounted() {
		checkout.scan("VOUCHER").scan("TSHIRT").scan("VOUCHER").scan("VOUCHER").scan("MUG").scan("TSHIRT")
				.scan("TSHIRT");
		assertTrue(checkout.total().equals(80.5f));
	}

}
