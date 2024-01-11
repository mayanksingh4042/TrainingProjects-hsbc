package com.java.exam.hsbc.data.factory.readers;

import com.java.exam.hsbc.data.handlers.read.DataReadHandler;
import com.java.exam.hsbc.data.handlers.read.FileDataBufferReadHandler;
import com.java.exam.hsbc.data.handlers.read.FileDataReadHandler;

public class FileDataReaderFactory extends DataReaderFactory<String> {

	@Override
	public DataReadHandler<String> createDataReadHandler() {
		DataReadHandler<String> fileDataReadHandler = new FileDataBufferReadHandler("inputstring/testinputstr.txt");
		return fileDataReadHandler;
	}

}
