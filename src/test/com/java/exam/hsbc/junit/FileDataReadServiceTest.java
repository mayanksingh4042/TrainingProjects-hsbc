package test.com.java.exam.hsbc.junit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.java.exam.hsbc.data.factory.readers.DataReaderFactory;
import com.java.exam.hsbc.data.handlers.read.DataReadHandler;
import com.java.exam.hsbc.rules.visitor.DataRuleVisitor;
import com.java.exam.hsbc.rules.visitor.StringItem;
import com.java.exam.hsbc.service.FileDataService;

public class FileDataReadServiceTest {

	private DataReaderFactory dataReaderFactory;
	private DataRuleVisitor<StringItem> dataRuleVisitor;

	@Before
	public void setUp() {
		dataReaderFactory = mock(DataReaderFactory.class);
		dataRuleVisitor = mock(DataRuleVisitor.class);
	}

	@Test
	public void testGetReverseStringFileData() throws URISyntaxException, IOException {

		DataReadHandler dataReadHandler = mock(DataReadHandler.class);
		when(dataReaderFactory.createDataReadHandler()).thenReturn(dataReadHandler);
		List<String> fileStrData = Arrays.asList("abc", "def", "ghi");
		when(dataReadHandler.readData()).thenReturn(fileStrData);

		FileDataService fileDataReadService = new FileDataService(dataReaderFactory, dataRuleVisitor);

		String result = fileDataReadService.getReverseStringFileData();

		verify(dataReadHandler, times(1)).readData();
		verify(dataRuleVisitor, times(3)).visit(any());

	}

	@Test(expected = UnsupportedEncodingException.class)
	public void testVerifyDataRulesWithUnsupportedEncodingException() throws UnsupportedEncodingException {

		List<String> strDataLst = Arrays.asList("abc", "def", "Non-ASCII String: ©");
		doThrow(UnsupportedEncodingException.class).when(dataRuleVisitor).visit(any());

		FileDataService fileDataReadService = new FileDataService(dataReaderFactory, dataRuleVisitor);

		fileDataReadService.verifyDataRules(strDataLst);
	}
}
