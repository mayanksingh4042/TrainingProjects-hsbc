package com.java.exam.hsbc.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.java.exam.hsbc.rules.visitor.DataElement;
import com.java.exam.hsbc.rules.visitor.StringItem;

public class StringManipulationUtilities {

	

	public static List<DataElement<StringItem>> mapToStringElement(List<String> strDataLst) {
		List<DataElement<StringItem>> stringElementList = new ArrayList<DataElement<StringItem>>();
		for (String strData : strDataLst) {

			stringElementList.add(new StringItem(strData));
		}
		return stringElementList;
	}
	
	public static String reverseStringList(List<String> inputStrList) {
		int numThreads = Runtime.getRuntime().availableProcessors(); // Get number of available threads based on cores available
		ExecutorService executor = Executors.newFixedThreadPool(numThreads);
		List<CompletableFuture<String>> futures = new ArrayList<>();
		for (String inputStr : inputStrList) {
			CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> reverseChunk(inputStr), executor);
			futures.add(future);
		}

		CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

		try {
			allFutures.get();// Wait for all tasks to complete
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} 

		List<String> reversedStrObjs = new ArrayList<>();
		for (CompletableFuture<String> future : futures) {
			try {
				reversedStrObjs.add(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		Collections.reverse(reversedStrObjs); // Reverse the order of the chunks

		StringBuilder reversed = new StringBuilder();
		for (String chunk : reversedStrObjs) {
			reversed.append(chunk);
		}

		executor.shutdown(); // Shutdown the executor

		return reversed.toString();
	}

	public static String reverseChunk(String chunk) {
		return new StringBuilder(chunk).reverse().toString();
	}

}
