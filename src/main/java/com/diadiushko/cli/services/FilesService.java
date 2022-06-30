package com.diadiushko.cli.services;

import java.io.File;
import java.io.Serializable;

public abstract class FilesService<T extends Serializable> {
    public boolean doesFileExist(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    public abstract T getObjectFromFile(String filePath);
}
