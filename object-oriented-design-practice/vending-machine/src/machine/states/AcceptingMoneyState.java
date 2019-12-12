package machine.states;

import machine.Inventory;
import machine.VendingMachine;

public class AcceptingMoneyState implements Operations {
	private VendingMachine machine;

	public AcceptingMoneyState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public StringBuilder feedMoney(float money) {
		this.machine.addInsertedMoney(money);
		return new StringBuilder(
				"Inserted money:" + money + ". Total money so far:" + this.machine.getInsertedMoney() + "\n");
	}

	@Override
	public StringBuilder selectProduct(int id) {
		Inventory inventory = this.machine.getInventories().get(id);
		if (inventory == null) {
			return new StringBuilder("Product not found.\n");
		} else if (inventory.getProduct().getCost() > this.machine.getInsertedMoney()) {
			return new StringBuilder("Please insert more money.\n");
		} else if (inventory.getUnits() == 0) {
			return new StringBuilder("Product out of stock please select another product. \n");
		} else {
			this.machine.setSelectedProduct(inventory.getProduct());
			this.machine.setState(this.machine.getSelectProductState());
		}
		return new StringBuilder("Product selected. \n");
	}

	@Override
	public StringBuilder cancel() {
		StringBuilder output = new StringBuilder();
		output.append("Returning: " + this.machine.getInsertedMoney() + ". Please collect it.\n");
		this.machine.resetInsertedMoney();
		this.machine.setState(this.machine.getReadyState());
		return output;

	}

	@Override
	public StringBuilder dispenseProduct() {
		return new StringBuilder("Product not selected please select the product\n");

	}

}
