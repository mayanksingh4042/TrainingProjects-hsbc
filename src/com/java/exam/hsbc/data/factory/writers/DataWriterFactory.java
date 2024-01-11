package com.java.exam.hsbc.data.factory.writers;

import com.java.exam.hsbc.data.handlers.write.DataWriteHandler;

public abstract class DataWriterFactory<T> {

	public DataWriteHandler<T> getDataWriteHandler() {
		DataWriteHandler<T> dataWriteHandler = this.createDataWriteHandler();
		return dataWriteHandler;
	}

	public abstract DataWriteHandler<T> createDataWriteHandler();

}
