package io.avec.filewatcher;

import lombok.extern.slf4j.Slf4j;

/**
 * Simple example of FileRepository implementing FileListener
 */
@Slf4j
public class MyFileRepository implements FileListener {

    // some file reference bla, bla, bla

    @Override
    public void onChange() {
        log.debug("File {} is changed!", getDirectory() + getFileName() );
        // read/load the changed file
    }

    @Override
    public String getDirectory() {
        return "files/";
    }

    @Override
    public String getFileName() {
        return "test.txt";
    }
}
