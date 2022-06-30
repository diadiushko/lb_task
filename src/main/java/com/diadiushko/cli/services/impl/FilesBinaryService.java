package com.diadiushko.cli.services.impl;

import com.diadiushko.cli.services.FilesService;

import java.io.*;

public final class FilesBinaryService<T extends Serializable> extends FilesService<T> {
    @Override
    public T getObjectFromFile(String filePath) {
        try (ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            return (T) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }
}
