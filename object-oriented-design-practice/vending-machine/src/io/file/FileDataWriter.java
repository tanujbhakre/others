package io.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import io.Writer;

public class FileDataWriter implements Writer {
	private BufferedWriter writer;

	public FileDataWriter(String file) {
		try {
			this.writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(String data) {
		try {
			this.writer.write(data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() {
		try {
			this.writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(StringBuilder output) {
		this.write(output.toString());

	}

}
