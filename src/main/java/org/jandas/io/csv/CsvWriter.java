package org.jandas.io.csv;

import org.jandas.Dataset;
import org.jandas.io.Writer;

import java.util.Map;

public class CsvWriter implements Writer {

    private Map<String, String> options;
    private Dataset dataset;


    public CsvWriter(Map<String, String> options, Dataset dataset) {
        this.options = options;
        this.dataset = dataset;
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException();
    }
}
