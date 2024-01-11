package com.java.exam.hsbc.rules.visitor;

import java.io.UnsupportedEncodingException;

public class StringItem implements DataElement<StringItem> {
    private String value;

    public StringItem(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void accept(DataRuleVisitor<StringItem> visitor) throws UnsupportedEncodingException {
        visitor.visit(this);
    }
}