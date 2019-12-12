package io;

import java.io.Closeable;
import java.util.List;

public interface Reader extends Closeable {

	boolean hasNext();

	List<String> read();

	@Override
	void close();
}
