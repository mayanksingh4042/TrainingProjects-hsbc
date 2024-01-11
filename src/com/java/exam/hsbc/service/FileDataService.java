package com.java.exam.hsbc.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import com.java.exam.hsbc.data.factory.readers.DataReaderFactory;
import com.java.exam.hsbc.data.factory.writers.DataWriterFactory;
import com.java.exam.hsbc.data.handlers.read.DataReadHandler;
import com.java.exam.hsbc.data.handlers.write.DataWriteHandler;
import com.java.exam.hsbc.rules.visitor.DataElement;
import com.java.exam.hsbc.rules.visitor.DataRuleVisitor;
import com.java.exam.hsbc.rules.visitor.StringItem;
import com.java.exam.hsbc.utilities.StringManipulationUtilities;

public class FileDataService {

	public DataReaderFactory<String> dataReaderFactory;
	public DataRuleVisitor<StringItem> fileDataRuleVisitor;
	public DataWriterFactory<String> dataWriterFactory;

	/**
	 * @param dataReaderFactory
	 * @param dataRuleVisitor
	 */
	public FileDataService(DataReaderFactory<String> dataReaderFactory,
			DataRuleVisitor<StringItem> dataRuleVisitor) {
		
		this(dataReaderFactory,dataRuleVisitor,null); 
	}
	
	public FileDataService(DataReaderFactory<String> dataReaderFactory,
			DataRuleVisitor<StringItem> dataRuleVisitor,DataWriterFactory<String> dataWriterFactory) {
		super();
		this.dataReaderFactory = dataReaderFactory;
		this.fileDataRuleVisitor = dataRuleVisitor;
		this.dataWriterFactory = dataWriterFactory;
	}

	public String getReverseStringFileData() throws URISyntaxException, IOException {
		List<String> fileStrData = fetchFileData();
		verifyDataRules(fileStrData);
		return StringManipulationUtilities.reverseStringList(fileStrData);
	}

	public void verifyDataRules(List<String> strDataLst) throws UnsupportedEncodingException {
		List<DataElement<StringItem>> stringElementList = StringManipulationUtilities.mapToStringElement(strDataLst);
		for (DataElement<StringItem> element : stringElementList) {
			element.accept(fileDataRuleVisitor);
		}

	}
	
	public List<String> fetchFileData() throws URISyntaxException, IOException{
		DataReadHandler<String> dataReadHandler = dataReaderFactory.createDataReadHandler();
		List<String> fileStrDataLst = dataReadHandler.readData();
		return fileStrDataLst;
	}
	
	public void writeDataToResourceOutput(String inputStr) throws IOException{
		DataWriteHandler<String> dataWriteHandler= dataWriterFactory.createDataWriteHandler();
		dataWriteHandler.writeData(inputStr);
	}

	
}
