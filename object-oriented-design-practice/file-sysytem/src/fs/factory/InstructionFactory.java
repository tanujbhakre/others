package fs.factory;

import fs.FileSystem;
import fs.instructions.InstructionExecutor;
import fs.instructions.executors.ChangeDownDirectoryExecutor;
import fs.instructions.executors.ChangeUpDirectoryExecutor;
import fs.instructions.executors.CreateDirectoryExecutor;
import fs.instructions.executors.ListDirectoryExecutor;

public class InstructionFactory {

	public InstructionExecutor getInstruction(String baseInstruction, FileSystem filesystem) {
		if ("dir".equals(baseInstruction)) {
			return new ListDirectoryExecutor(filesystem);
		} else if ("mkdir".equals(baseInstruction)) {
			return new CreateDirectoryExecutor(filesystem);
		} else if ("cd".equals(baseInstruction)) {
			return new ChangeDownDirectoryExecutor(filesystem);
		} else if ("up".equals(baseInstruction)) {
			return new ChangeUpDirectoryExecutor(filesystem);
		} else {
			return null;
		}
	}
}
