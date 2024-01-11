package com.java.exam.hsbc.app;

import java.io.IOException;
import java.net.URISyntaxException;

import com.java.exam.hsbc.data.factory.readers.DataReaderFactory;
import com.java.exam.hsbc.data.factory.readers.FileDataReaderFactory;
import com.java.exam.hsbc.data.factory.writers.DataWriterFactory;
import com.java.exam.hsbc.data.factory.writers.FileDataWriterFactory;
import com.java.exam.hsbc.rules.visitor.AsciiCharRuleVisitor;
import com.java.exam.hsbc.rules.visitor.DataRuleVisitor;
import com.java.exam.hsbc.rules.visitor.StringItem;
import com.java.exam.hsbc.service.FileDataService;

public class FileReaderApp {

	public static void main(String[] args) {
		try {
			
			DataReaderFactory<String> drf = new FileDataReaderFactory();
			DataRuleVisitor<StringItem> drvst = new AsciiCharRuleVisitor();
			DataWriterFactory<String> dwf = new FileDataWriterFactory();
			FileDataService fds = new FileDataService(drf, drvst,dwf);
			FileReaderApp fileReaderApp= new FileReaderApp();
			
			String outStr = fileReaderApp.getStringRevOrder(fds);
			System.out.println(outStr);
			
			fileReaderApp.writeToResourceFileData(outStr,fds);
		} catch (URISyntaxException | IOException e) {
			// exception occurred
			e.printStackTrace();
		}
	}

	String getStringRevOrder(FileDataService fds) throws URISyntaxException, IOException {
		return fds.getReverseStringFileData();
	}
	
	void writeToResourceFileData(String inputStr,FileDataService fds) throws IOException{
		
		fds.writeDataToResourceOutput(inputStr);
		
	}
	
}
