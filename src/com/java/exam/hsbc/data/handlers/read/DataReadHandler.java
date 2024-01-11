package com.java.exam.hsbc.data.handlers.read;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface DataReadHandler<T> {

	List<T> readData() throws URISyntaxException, IOException;

}
