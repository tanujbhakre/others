package machine.states;

import machine.Inventory;
import machine.VendingMachine;

public class ProductSelectedState implements Operations {
	private VendingMachine machine;

	public ProductSelectedState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public StringBuilder feedMoney(float money) {
		return new StringBuilder("Money can not be fed. Please cancel if you wish to abort.\n");

	}

	@Override
	public StringBuilder selectProduct(int id) {
		return new StringBuilder("Product already Selected.\n");

	}

	@Override
	public StringBuilder cancel() {
		StringBuilder output = new StringBuilder();
		output.append("Returning " + this.machine.getInsertedMoney() + ". Please collect it.\n");
		this.machine.resetInsertedMoney();
		this.machine.setSelectedProduct(null);
		this.machine.setState(this.machine.getReadyState());
		return output;

	}

	@Override
	public StringBuilder dispenseProduct() {
		StringBuilder output = new StringBuilder();
		Inventory inventory = this.machine.getInventories().get(this.machine.getSelectedProduct().getId());
		inventory.decrementUnit();
		output.append("Dispensing: " + inventory.getProduct().getName());
		output.append(". Returning " + (this.machine.getInsertedMoney() - inventory.getProduct().getCost())
				+ ". Please collect it. \n");
		this.machine.resetInsertedMoney();
		this.machine.setSelectedProduct(null);
		this.machine.setState(this.machine.getReadyState());
		return output;
	}

}
