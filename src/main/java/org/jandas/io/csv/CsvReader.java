package org.jandas.io.csv;

import org.jandas.DataFrame;
import org.jandas.io.IReader;

import java.util.Map;

public class CsvReader implements IReader {

    private Map<String, String> options;

    public CsvReader(Map<String, String> options) {
        this.options = options;
    }

    @Override
    public DataFrame load() {
        throw new UnsupportedOperationException();
    }

}
