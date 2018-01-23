package org.jandas.io.parquet;

import org.jandas.Dataset;
import org.jandas.io.Writer;

import java.util.Map;

public class ParquetWriter implements Writer {

    private Map<String, String> options;
    private Dataset dataset;

    public ParquetWriter(Map<String, String> options, Dataset dataset) {
        this.options = options;
        this.dataset = dataset;
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException();
    }
}
