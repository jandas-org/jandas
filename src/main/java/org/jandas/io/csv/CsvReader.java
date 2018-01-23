package org.jandas.io.csv;

import org.jandas.Dataset;
import org.jandas.io.Reader;

import java.util.Map;

public class CsvReader implements Reader {

    private Map<String, String> options;

    public CsvReader(Map<String, String> options) {
        this.options = options;
    }

    @Override
    public Dataset load() {
        throw new UnsupportedOperationException();
    }

}
