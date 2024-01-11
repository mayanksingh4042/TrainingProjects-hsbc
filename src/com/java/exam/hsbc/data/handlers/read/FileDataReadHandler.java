package com.java.exam.hsbc.data.handlers.read;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileDataReadHandler implements DataReadHandler<String> {

	private String inputResourceFilePath;// "inputstring/testinputstr.txt"
	private StandardOpenOption readOption;
	private int byteBufferLength;
	private String charset = "US-ASCII";// Charset.forName("US-ASCII")

	public FileDataReadHandler(String filePath, String charset) {
		this(filePath, StandardOpenOption.READ, 1024, charset);// limiting the size to 1000 bytes of each byte block so as to avoid any failure
																// for very very large input string in input file
	}

	/**
	 * @param filePath
	 * @param readOption
	 * @param byteBufferLength
	 */
	protected FileDataReadHandler(String filePath, StandardOpenOption readOption, int byteBufferLength,
			String charset) {
		super();
		this.inputResourceFilePath = filePath;
		this.readOption = readOption;
		this.byteBufferLength = byteBufferLength;
		this.charset = charset;
	}

	/**
	 * 
	 * @return List of string blocks whose length is determined by
	 *         byteBufferLength
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Override
	public List<String> readData() throws URISyntaxException, IOException {
		try (SeekableByteChannel ch = java.nio.file.Files.newByteChannel(Paths.get(inputResourceFilePath),
				readOption)) {
			List<String> stringBlocks = new ArrayList<>();
			if (ch.size() > 0) {
				ByteBuffer bf = getByetBuffer(byteBufferLength);
				while (true) {
					int channelReadByteLen = ch.read(bf);
					if (channelReadByteLen <= 0)
						break;
					bf.flip();
					if (ch.size() == ch.position()) {// this condition will only
														// be true for one(for
														// last byte read from
														// channel) time as
														// ByteBuffer's clear()
														// do not actually clear
														// the
														// buffer but just moves
														// start position to
														// beginning.
						byte[] lastReadBufArray = new byte[channelReadByteLen];
						System.arraycopy(bf.array(), 0, lastReadBufArray, 0, channelReadByteLen);
						stringBlocks.add(new String(lastReadBufArray, charset));
					} else {

						stringBlocks.add(new String(bf.array(), charset));
					}

					bf.clear();
				}
			}
			return stringBlocks;

		}
	}

	public ByteBuffer getByetBuffer(int byteBufferLength) {
		return ByteBuffer.allocate(byteBufferLength);
	}

}
