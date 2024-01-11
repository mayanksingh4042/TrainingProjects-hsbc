package test.com.java.exam.hsbc.junit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.java.exam.hsbc.rules.visitor.DataElement;
import com.java.exam.hsbc.rules.visitor.StringItem;
import com.java.exam.hsbc.utilities.StringManipulationUtilities;

public class StringManipulationUtilitiesTest {

	@Test
	public void testMapToStringElement() {

		List<String> inputStrings = Arrays.asList("abc", "def", "ghi");

		List<DataElement<StringItem>> result = StringManipulationUtilities.mapToStringElement(inputStrings);

		assertEquals(3, result.size());
		assertEquals("abc", ((StringItem) result.get(0)).getValue());
		assertEquals("def", ((StringItem) result.get(1)).getValue());
		assertEquals("ghi", ((StringItem) result.get(2)).getValue());
	}

	@Test
	public void testReverseStringList() {

		List<String> inputStrings = Arrays.asList("abc", "def", "ghi");

		String result = StringManipulationUtilities.reverseStringList(inputStrings);

		assertEquals("ihgfedcba", result);
	}
}
