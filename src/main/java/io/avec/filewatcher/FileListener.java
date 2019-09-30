package io.avec.filewatcher;

public interface FileListener {
    void onChange();
    String getDirectory();
    String getFileName();
}
