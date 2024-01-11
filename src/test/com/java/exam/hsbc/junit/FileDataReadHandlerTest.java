package test.com.java.exam.hsbc.junit;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.java.exam.hsbc.data.handlers.read.FileDataReadHandler;

public class FileDataReadHandlerTest {

	private FileDataReadHandler fileDataReadHandler;

	@Before
	public void setup() {
		// Initialize the FileDataReadHandler with test input file and charset
		String filePath = "inputstring/testinputstr.txt";
		String charset = "US-ASCII";
		fileDataReadHandler = new FileDataReadHandler(filePath, charset);
	}

	@Test
	public void testReadDataWithValidFile() throws URISyntaxException, IOException {
		// Read data from a valid file
		List<String> data = fileDataReadHandler.readData();

		// Assert that data is not null and contains expected content
		Assert.assertNotNull(data);
		Assert.assertEquals(1, data.size());
		Assert.assertEquals("abcdefghijklmnoprstuvwxyz12346789", data.get(0));

	}

	@Test(expected = NoSuchFileException.class)
	public void testReadDataWithInvalidFile() throws URISyntaxException, IOException {
		// Attempt to read data from a nonexistent file
		String filePath = "nonexistent.txt";
		FileDataReadHandler invalidFileDataReadHandler = new FileDataReadHandler(filePath, "US-ASCII");
		invalidFileDataReadHandler.readData();
	}

}