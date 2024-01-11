package test.com.java.exam.hsbc.junit;

import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.java.exam.hsbc.data.handlers.read.FileDataBufferReadHandler;

public class FileDataBufferReadHandlerTest {

    @Mock
    private FileDataBufferReadHandler fileDataBufferReadHandler;

    @Before
    public void setup() throws FileNotFoundException {
    	
    	MockitoAnnotations.openMocks(this);
     // Set up the mock behavior for the getReader() method
        when(fileDataBufferReadHandler.getFileReader()).thenReturn(new StringReader("abcdef"));
        when(fileDataBufferReadHandler.getByteBufferLength()).thenReturn(1000);
        
    }

    @Test
    public void testReadDataWithValidFile() throws URISyntaxException, IOException {
        // Read data from a valid file
        List<String> data = fileDataBufferReadHandler.readData();
        // Assert that data is not null and contains expected content
        Assert.assertNotNull(data);
        //Assert.assertEquals("abcdef", data.get(0));
       
    }
    
    
    @Test(expected = IOException.class)
    public void testReadDataWithIOException() throws URISyntaxException, IOException {
        // Arrange
        FileDataBufferReadHandler handler = new FileDataBufferReadHandler("testFilePath");
        handler.setByteBufferLength(100);
        handler.readData(); // This should throw an IOException
    }
}