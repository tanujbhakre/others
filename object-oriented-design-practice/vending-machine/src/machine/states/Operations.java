package machine.states;

public interface Operations {
	StringBuilder feedMoney(float money);

	StringBuilder selectProduct(int ind);

	StringBuilder cancel();

	StringBuilder dispenseProduct();
}
