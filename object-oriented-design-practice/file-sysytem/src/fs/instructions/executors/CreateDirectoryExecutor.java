package fs.instructions.executors;

import java.util.List;

import fs.Directory;
import fs.FileSystem;
import fs.instructions.BaseInstructionExecutor;

public class CreateDirectoryExecutor extends BaseInstructionExecutor {

	public CreateDirectoryExecutor(FileSystem filesystem) {
		super(filesystem);
	}

	@Override
	public String run(List<String> args) {
		String name = args.get(1);
		StringBuilder sb = new StringBuilder();
		if (this.filesystem.getCurrent().getSubDirectory(name) != null) {
			sb.append("Subdirectory already exist\n");
		} else {
			Directory currentDirectory = this.filesystem.getCurrent();
			currentDirectory.createSubDirectory(new Directory(name, currentDirectory));
		}
		return sb.toString();
	}

}
