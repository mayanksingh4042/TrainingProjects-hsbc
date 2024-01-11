package com.java.exam.hsbc.data.factory.writers;

import com.java.exam.hsbc.data.handlers.write.DataWriteHandler;
import com.java.exam.hsbc.data.handlers.write.FileDataBufferWriteHandler;

public class FileDataWriterFactory extends DataWriterFactory<String> {

	@Override
	public DataWriteHandler<String> createDataWriteHandler() {
		
		DataWriteHandler<String> fileDataWriteHandler = new FileDataBufferWriteHandler("outputstring/testoutputstr.txt");
		return fileDataWriteHandler;
	}

}
