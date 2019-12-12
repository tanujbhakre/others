package io;

import com.sun.xml.internal.ws.Closeable;

public interface Writer extends Closeable {
	void write(StringBuilder output);

	void write(String output);

	@Override
	void close();
}
