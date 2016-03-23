package file.writer;

public interface MyFileWriter {
	public void write(String lineContent);
	public void writeLine(String line);
	public void closeFile();
}
