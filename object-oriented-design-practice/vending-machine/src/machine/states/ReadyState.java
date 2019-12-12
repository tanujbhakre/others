package machine.states;

import machine.VendingMachine;

public class ReadyState implements Operations {
	private VendingMachine machine;

	public ReadyState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public StringBuilder feedMoney(float money) {
		this.machine.addInsertedMoney(money);
		this.machine.setState(this.machine.getAccptingMoneyState());
		return new StringBuilder(
				"Inserted money:" + money + ". Total money so far:" + this.machine.getInsertedMoney() + "\n");
	}

	@Override
	public StringBuilder selectProduct(int id) {
		return new StringBuilder("Please insert money and then select the product\n");

	}

	@Override
	public StringBuilder cancel() {
		return new StringBuilder("No money inserted. Operation is canceled\n");

	}

	@Override
	public StringBuilder dispenseProduct() {
		return new StringBuilder("Please insert money and then select the product\n");
	}

}
