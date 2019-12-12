package fs;

import java.io.IOException;
import java.util.List;

import fs.factory.InstructionFactory;
import fs.instructions.InstructionExecutor;

public class FileSystem {

	private InstructionFactory factory;
	private Directory current;

	public FileSystem() {
		this.factory = new InstructionFactory();
		this.current = new Directory("root", null);
	}

	public String execute(List<String> arguments) throws IOException {

		InstructionExecutor executor = this.factory.getInstruction(arguments.get(0), this);
		return executor.execute(arguments);

	}

	public Directory getCurrent() {
		return current;
	}

	public void setCurrent(Directory current) {
		this.current = current;
	}

}
