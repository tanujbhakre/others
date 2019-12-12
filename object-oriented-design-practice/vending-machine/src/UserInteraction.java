
import java.util.ArrayList;
import java.util.List;

import io.Reader;
import io.Writer;
import io.file.FileDataReader;
import io.file.FileDataWriter;
import machine.Inventory;
import machine.Product;
import machine.VendingMachine;

public class UserInteraction {
	public static void main(String[] args) {
		Product candy = new Product(1, "CANDY", .1f);
		Product snack = new Product(2, "SNACK", .25f);
		Product nuts = new Product(3, "NUTS", .1f);
		Product coke = new Product(4, "COKE", .1f);
		Product pepsi = new Product(5, "PEPSI", .1f);
		Product soda = new Product(6, "SODA", .1f);

		List<Inventory> inventory = new ArrayList<>();
		inventory.add(new Inventory(candy, 1));
		inventory.add(new Inventory(snack, 2));
		inventory.add(new Inventory(nuts, 1));
		inventory.add(new Inventory(coke, 1));
		inventory.add(new Inventory(pepsi, 1));
		inventory.add(new Inventory(soda, 1));

		VendingMachine machine = new VendingMachine(inventory);
		// Reader reader = new ScannerDataReader();
		// Writer writer = new ScannerDataWriter();
		Reader reader = new FileDataReader(args[0]);
		Writer writer = new FileDataWriter(args[1]);

		try {
			StringBuilder options = new StringBuilder();
			options.append("Please Choose from below options \n");
			options.append("To insert money INSERT (cents)\n");
			options.append("To display products DISPLAY\n");
			options.append("To select products PRODUCT (id)\n");
			options.append("To dispense DISPENSE\n");
			options.append("To cancel CANCEL\n");
			options.append("To quit QUIT\n");
			// writer.write(options);

			boolean takingInput = true;
			while (takingInput && reader.hasNext()) {
				List<String> input = reader.read();
				writer.write("Command:" + input + "\n");
				StringBuilder response = new StringBuilder();
				if (input.size() < 1)
					writer.write("Invalid input.\n");
				switch (input.get(0)) {
				case "INSERT":
					response = machine.feedMoney(Float.parseFloat(input.get(1)));
					break;
				case "DISPLAY":
					break;
				case "PRODUCT":
					response = machine.selectProduct(Integer.parseInt(input.get(1)));
					break;
				case "DISPENSE":
					response = machine.dispenseProduct();
					break;
				case "CANCEL":
					response = machine.cancel();
					break;
				case "QUIT":
					takingInput = false;
					response = new StringBuilder("Bye!!!");
					break;
				default:
					response = new StringBuilder("Invalid input");
				}
				writer.write(response);
			}
		} finally {
			reader.close();
			writer.close();
		}
	}

}
