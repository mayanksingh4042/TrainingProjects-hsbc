package com.java.exam.hsbc.rules.visitor;

import java.io.UnsupportedEncodingException;

public class AsciiCharRuleVisitor implements DataRuleVisitor<StringItem> {
	@Override
	public void visit(StringItem stringItem) throws UnsupportedEncodingException {
		String value = stringItem.getValue();

		// Rule: String is ASCII
		boolean isAscii = value.matches("\\A\\p{ASCII}*\\z");
		if (!isAscii) {
			throw new UnsupportedEncodingException(value);
		}
	}
}