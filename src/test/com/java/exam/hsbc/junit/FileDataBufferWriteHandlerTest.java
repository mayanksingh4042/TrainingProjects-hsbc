package test.com.java.exam.hsbc.junit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.java.exam.hsbc.data.handlers.write.FileDataBufferWriteHandler;

public class FileDataBufferWriteHandlerTest {

    @Mock
    private FileWriter fileWriter;

    @Mock
    private BufferedWriter bufferedWriter;

    private FileDataBufferWriteHandler fileDataBufferWriteHandler;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        fileDataBufferWriteHandler = new FileDataBufferWriteHandler("output1/testoutputstr.txt") {
            @Override
            public Writer getFileWriter() throws IOException {
                return fileWriter;
            }
        };
    }

    @Test
    public void testWriteData() throws IOException {
        String inputStr = "abcde";
        fileDataBufferWriteHandler.writeData(inputStr);
        //verify(fileWriter).write(inputStr);
        verify(fileWriter).close();
    }

    @Test
    public void testGetOutputResourceFilePath() {
        String expectedFilePath = "output1/testoutputstr.txt";
        String actualFilePath = fileDataBufferWriteHandler.getOutputResourceFilePath();
        assertEquals(expectedFilePath, actualFilePath);
    }

    @Test
    public void testSetOutputResourceFilePath() {
        String newFilePath = "output/newoutputstr.txt";
        fileDataBufferWriteHandler.setOutputResourceFilePath(newFilePath);
        String actualFilePath = fileDataBufferWriteHandler.getOutputResourceFilePath();
        assertEquals(newFilePath, actualFilePath);
    }

    @Test
    public void testGetByteBufferLength() {
        int expectedLength = 10000;
        int actualLength = fileDataBufferWriteHandler.getByteBufferLength();
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void testSetByteBufferLength() {
        int newLength = 5000;
        fileDataBufferWriteHandler.setByteBufferLength(newLength);
        int actualLength = fileDataBufferWriteHandler.getByteBufferLength();
        assertEquals(newLength, actualLength);
    }
}