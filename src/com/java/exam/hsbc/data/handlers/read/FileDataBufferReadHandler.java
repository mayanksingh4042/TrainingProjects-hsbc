package com.java.exam.hsbc.data.handlers.read;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileDataBufferReadHandler implements DataReadHandler<String> {

	private String inputResourceFilePath;// "inputstring/testinputstr.txt"
	private int byteBufferLength;
	
	

	public FileDataBufferReadHandler(String filePath) {
		this(filePath, 10000);// limiting the size to 1000 bytes of each byte block so as to avoid any failure
																// for very very large input string in input file
	}

	/**
	 * @param filePath
	 * @param readOption
	 * @param byteBufferLength
	 */
	protected FileDataBufferReadHandler(String filePath, int byteBufferLength)
		 {
		super();
		this.inputResourceFilePath = filePath;
		
		this.byteBufferLength = byteBufferLength;
		
	}

	/**
	 * 
	 * @return List of string blocks per new line in the file
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Override
	public List<String> readData() throws URISyntaxException, IOException {
		try (BufferedReader reader = new BufferedReader(getFileReader(), getByteBufferLength())) {
			List<String> stringBlocks = new ArrayList<>();
			String line;
			while ((line = reader.readLine()) != null) {
                // Process each line of the file
				stringBlocks.add(line);
            }
			return stringBlocks;
		}
	}

	
	public Reader getFileReader() throws FileNotFoundException{
		return new FileReader(inputResourceFilePath);
	}
	
	public String getInputResourceFilePath() {
		return inputResourceFilePath;
	}

	public void setInputResourceFilePath(String inputResourceFilePath) {
		this.inputResourceFilePath = inputResourceFilePath;
	}

	public int getByteBufferLength() {
		return byteBufferLength;
	}

	public void setByteBufferLength(int byteBufferLength) {
		this.byteBufferLength = byteBufferLength;
	}

}
