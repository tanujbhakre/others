package fs.instructions.executors;

import java.util.List;

import fs.Directory;
import fs.FileSystem;
import fs.instructions.BaseInstructionExecutor;

public class ChangeUpDirectoryExecutor extends BaseInstructionExecutor {

	public ChangeUpDirectoryExecutor(FileSystem filesystem) {
		super(filesystem);
	}

	@Override
	public String run(List<String> args) {
		Directory currentDirectory = this.filesystem.getCurrent();
		StringBuilder sb = new StringBuilder();
		if (currentDirectory.getParent() != null) {
			this.filesystem.setCurrent(currentDirectory.getParent());
		} else {
			sb.append("Can not move up from root directory\n");
		}
		return sb.toString();
	}

}
