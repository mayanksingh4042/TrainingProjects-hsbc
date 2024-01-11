package com.java.exam.hsbc.rules.visitor;

import java.io.UnsupportedEncodingException;

public interface DataElement<T> {
    void accept(DataRuleVisitor<T> visitor) throws UnsupportedEncodingException;
}