package org.jandas.io.parquet;

import org.jandas.Dataset;
import org.jandas.io.Reader;

import java.util.Map;

public class ParquetReader implements Reader {

    private Map<String, String> options;

    public ParquetReader(Map<String, String> options) {
        this.options = options;
    }

    @Override
    public Dataset load() {
        throw new UnsupportedOperationException();
    }

}
