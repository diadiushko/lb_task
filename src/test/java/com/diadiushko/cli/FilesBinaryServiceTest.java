package com.diadiushko.cli;

import com.diadiushko.cli.services.impl.FilesBinaryService;
import com.diadiushko.entities.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Parameterized.class)
public class FilesBinaryServiceTest {
    private final static String TEST_FILE_PATH = "src/main/resources/users.dat";
    private final static User testUser = new User(1, "Artem", 20);

    private final String path;
    private final boolean fileExists;
    private final FilesBinaryService<User> fs = new FilesBinaryService<>();

    @Before
    public void beforeEach() {
        try (OutputStream bos = new BufferedOutputStream(new FileOutputStream(TEST_FILE_PATH)); ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(testUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public static void afterClass() {
        final File testFile = new File(TEST_FILE_PATH);
        if (testFile.isFile()) {
            testFile.delete();
        }
    }

    public FilesBinaryServiceTest(String path, boolean fileExists) {
        this.path = path;
        this.fileExists = fileExists;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions() {
        final String wrongFilePath = "test/test1";
        return Arrays.asList(new Object[][]{{wrongFilePath, false}, {TEST_FILE_PATH, true}});
    }

    @Test
    public void doesFileExist() {
        assertEquals(fs.doesFileExist(path), fileExists);
    }

    @Test
    public void getUsersFromFile() {
        final User testUser = fs.getObjectFromFile(path);
        if (fileExists) {
            assertEquals(FilesBinaryServiceTest.testUser, testUser);
        } else {
            assertNull(testUser);
        }
    }
}