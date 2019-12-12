package io.file;

import io.Writer;

public class ScannerDataWriter implements Writer {

	public ScannerDataWriter() {
	}

	@Override
	public void write(StringBuilder output) {
		System.out.println(output);
	}

	@Override
	public void write(String output) {
		System.out.println(output);

	}

	@Override
	public void close() {
	}

}
