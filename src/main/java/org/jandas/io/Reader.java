package org.jandas.io;

import org.jandas.Dataset;

public interface Reader {

    /**
     * Reads from a data source and creates a Dataset object.
     *
     * @return a Dataset object
     */
    Dataset load();
}
