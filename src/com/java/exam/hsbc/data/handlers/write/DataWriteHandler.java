package com.java.exam.hsbc.data.handlers.write;

import java.io.IOException;

public interface DataWriteHandler<T> {

	void writeData(T t) throws IOException;
}
