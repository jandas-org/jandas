package org.jandas.io.parquet;

import org.jandas.DataFrame;
import org.jandas.io.IWriter;

import java.util.Map;

public class ParquetWriter implements IWriter {

    private Map<String, String> options;
    private DataFrame dataFrame;

    public ParquetWriter(Map<String, String> options, DataFrame dataFrame) {
        this.options = options;
        this.dataFrame = dataFrame;
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException();
    }
}
