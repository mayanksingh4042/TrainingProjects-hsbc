package com.java.exam.hsbc.data.handlers.write;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileDataParallelWriteHandler implements DataWriteHandler<String> {
	private String outputResourceFilePath;
	private int byteBufferLength;
	
	
	public FileDataParallelWriteHandler(String outputResourceFilePath){
		this(outputResourceFilePath,1024);
		
	}

	public FileDataParallelWriteHandler(String inputResourceFilePath, int byteBufferLength) {
		super();
		this.outputResourceFilePath = inputResourceFilePath;
		this.byteBufferLength = byteBufferLength;
	}


	@Override
	public void writeData(String inputStr) throws IOException {
		int numThreads = Runtime.getRuntime().availableProcessors(); // Number of available processors
		

		try (FileChannel channel = FileChannel.open(Paths.get(outputResourceFilePath), StandardOpenOption.CREATE,
				StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {

			ExecutorService executor = Executors.newFixedThreadPool(numThreads);
			List<CompletableFuture<Integer>> futures = new ArrayList<>();

			ByteBuffer buffer = ByteBuffer.allocate(byteBufferLength); // Allocate a
																// ByteBuffer
			byte[] bytes = inputStr.getBytes(); // Get the bytes of the large
												// string
			int offset = 0; // Initial offset
			while (offset < bytes.length) {
				
				int length = Math.min(buffer.capacity(), bytes.length - offset); // Calculate the remaining length
				buffer.clear(); // Clear the buffer
				buffer.put(bytes, offset, length); // Put bytes into the buffer
				buffer.flip(); // Flip the buffer for writing
				int startFileposition = offset;
				
				CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
					try {

						return channel.write(buffer, startFileposition);
					} catch (IOException e) {
						e.printStackTrace();
						return null;
					}

				}, executor);

				futures.add(future);
				offset += length; // Increment the offset
			}
			
			CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
			try {
				allFutures.get();// Wait for all tasks to complete
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			} 
			
			executor.shutdown(); // Shutdown the executor
			
		} catch (IOException e) {
			e.printStackTrace();
		}

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
