package com.java.exam.hsbc.data.handlers.write;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileDataBufferWriteHandler implements DataWriteHandler<String> {

	private String outputResourceFilePath;// "output/testoutputstr.txt
	private int byteBufferLength;

	

	public FileDataBufferWriteHandler(String filePath) {
		this(filePath, 10000);// limiting the size to 1000 bytes of each byte
								// block so as to avoid any failure
								// for very very large input string in input
								// file
	}

	public FileDataBufferWriteHandler(String filePath, int byteBufferLength) {
		super();
		this.outputResourceFilePath = filePath;
		this.byteBufferLength = byteBufferLength;

	}

	@Override
	public void writeData(String inputStr) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(getFileWriter(),getByteBufferLength())) {
			writer.write(inputStr);
		}

	}
	
	public Writer getFileWriter() throws IOException {
		return new FileWriter(outputResourceFilePath);
	}
	public String getOutputResourceFilePath() {
		return outputResourceFilePath;
	}

	public void setOutputResourceFilePath(String outputResourceFilePath) {
		this.outputResourceFilePath = outputResourceFilePath;
	}

	public int getByteBufferLength() {
		return byteBufferLength;
	}

	public void setByteBufferLength(int byteBufferLength) {
		this.byteBufferLength = byteBufferLength;
	}
	
}
