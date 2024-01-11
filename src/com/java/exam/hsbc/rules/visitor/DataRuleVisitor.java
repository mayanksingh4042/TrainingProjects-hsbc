package com.java.exam.hsbc.rules.visitor;

import java.io.UnsupportedEncodingException;

public interface DataRuleVisitor<T> {
    void visit(T dataItem) throws UnsupportedEncodingException;
}