package com.diadiushko.cli;

import com.diadiushko.cli.services.FilesService;
import com.diadiushko.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FilesServiceTest {
    private final static String TEST_FILE_PATH = "src/test/resources/users.test.dat";
    private final static User user = new User(1, "Artem", 20);

    private final String path;
    private final boolean fileExists;
    private final FilesService<User> fs = new FilesService<>();

    @Before
    public void beforeEach() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(TEST_FILE_PATH)))) {
            oos.writeObject(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FilesServiceTest(String path, boolean fileExists) {
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
        final User testUser = fs.getUsersFromFile(path);
        if (fileExists) {
            assertEquals(user, testUser);
        } else {
            assertNull(testUser);
        }
    }
}