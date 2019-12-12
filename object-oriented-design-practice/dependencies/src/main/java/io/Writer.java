package io;

import java.io.Closeable;

public interface Writer extends Closeable {
	void write(StringBuilder output);

	void write(String output);

	@Override
	void close();
}
