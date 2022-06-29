package com.diadiushko.cli;

import java.io.*;

public final class FilesService<T> {
    public boolean doesFileExist(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    public T getUsersFromFile(String filePath) {
        try (ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            return (T) input.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }
}
