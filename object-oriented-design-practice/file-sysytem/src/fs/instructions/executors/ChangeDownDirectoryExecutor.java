package fs.instructions.executors;

import java.util.List;

import fs.Directory;
import fs.FileSystem;
import fs.instructions.BaseInstructionExecutor;

public class ChangeDownDirectoryExecutor extends BaseInstructionExecutor {
	public ChangeDownDirectoryExecutor(FileSystem filesystem) {
		super(filesystem);
	}

	@Override
	public String run(List<String> args) {
		Directory directory = this.filesystem.getCurrent().getSubDirectory(args.get(1));
		StringBuilder sb = new StringBuilder();
		if (directory != null) {
			this.filesystem.setCurrent(directory);
		} else {
			sb.append("Subdirectory does not exist\n");
		}
		return sb.toString();
	}

}
