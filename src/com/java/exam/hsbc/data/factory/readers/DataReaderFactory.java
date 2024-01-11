package com.java.exam.hsbc.data.factory.readers;

import com.java.exam.hsbc.data.handlers.read.DataReadHandler;

public abstract class DataReaderFactory<T> {
	
	public DataReadHandler<T> getDataReadHandler(){
		DataReadHandler<T> dataReadHandler = this.createDataReadHandler();
		return dataReadHandler;
	}

	public abstract DataReadHandler<T> createDataReadHandler();
	

}
