package machine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import machine.states.AcceptingMoneyState;
import machine.states.Operations;
import machine.states.ProductSelectedState;
import machine.states.ReadyState;

public class VendingMachine implements Operations {
	private Operations readyState;
	private Operations accptingMoneyState;
	private Operations selectProductState;
	private Operations currentState;
	private Map<Integer, Inventory> inventories;
	private float insertedMoney = 0;
	private float totalMoney = 0;
	private Product selectedProduct;

	public VendingMachine(List<Inventory> inventories) {
		this.inventories = new HashMap<>();
		this.readyState = new ReadyState(this);
		this.accptingMoneyState = new AcceptingMoneyState(this);
		this.selectProductState = new ProductSelectedState(this);
		this.currentState = readyState;
		if (inventories != null) {
			for (Inventory inventory : inventories) {
				this.inventories.put(inventory.getProduct().getId(), inventory);
			}
		}
	}

	@Override
	public StringBuilder feedMoney(float money) {
		return this.currentState.feedMoney(money);
	}

	@Override
	public StringBuilder selectProduct(int id) {
		return this.currentState.selectProduct(id);

	}

	@Override
	public StringBuilder cancel() {
		return this.currentState.cancel();

	}

	@Override
	public StringBuilder dispenseProduct() {
		return this.currentState.dispenseProduct();

	}

	public Operations getReadyState() {
		return readyState;
	}

	public Operations getAccptingMoneyState() {
		return accptingMoneyState;
	}

	public Operations getSelectProductState() {
		return selectProductState;
	}

	public float getInsertedMoney() {
		return insertedMoney;
	}

	public void resetInsertedMoney() {
		insertedMoney = 0;
	}

	public void addInsertedMoney(float money) {
		insertedMoney += money;
	}

	public void setState(Operations operations) {
		this.currentState = operations;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public Map<Integer, Inventory> getInventories() {
		return this.inventories;
	}

	public void addToTotal(float money) {
		this.totalMoney += money;
	}

	public float getTotalMoney() {
		return this.totalMoney;
	}
}
