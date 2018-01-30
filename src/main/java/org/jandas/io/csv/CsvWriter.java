package org.jandas.io.csv;

import org.jandas.DataFrame;
import org.jandas.io.Writer;

import java.util.Map;

public class CsvWriter implements Writer {

    private Map<String, String> options;
    private DataFrame dataFrame;


    public CsvWriter(Map<String, String> options, DataFrame dataFrame) {
        this.options = options;
        this.dataFrame = dataFrame;
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException();
    }
}
