package fs.instructions;

import java.util.List;

import fs.FileSystem;

public abstract class BaseInstructionExecutor implements InstructionExecutor {
	protected FileSystem filesystem;

	public BaseInstructionExecutor(FileSystem filesystem) {
		this.filesystem = filesystem;
	}

	@Override
	public String execute(List<String> args) {
		StringBuilder sb = new StringBuilder();
		sb.append("Command: ");
		for (String arg : args) {
			sb.append(arg);
			sb.append("\t");
		}
		sb.append("\n");
		sb.append(run(args));
		return sb.toString();
	}

	public abstract String run(List<String> args);
}
