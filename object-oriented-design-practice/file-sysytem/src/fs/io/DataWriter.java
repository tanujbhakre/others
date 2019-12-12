package fs.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {
	private BufferedWriter writer;

	public DataWriter(String file) throws IOException {
		this.writer = new BufferedWriter(new FileWriter(file));
	}

	public void write(String data) throws IOException {
		this.writer.write(data);
	}

	public void close() throws IOException {
		this.writer.close();
	}

}
