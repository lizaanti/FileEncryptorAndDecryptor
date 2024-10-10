package org.example.cryptojavafx;

import java.nio.file.Path;

public class FileInfo {

    private String fileName;

    public FileInfo(Path path) {
        this.fileName = path.getFileName().toString();
    }

    public Object getFileName() {
        return fileName;
    }
}
