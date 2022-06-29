package com.diadiushko;

import com.diadiushko.cli.CLI;
import com.diadiushko.entities.User;

import java.io.*;

public class App {
    static {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("F:/IntelliJ/pachichipupa/src/main/resources/users.dat")))) {
            User user = new User(1, "Artem", 20);
            oos.writeObject(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        CLI.launch();
    }
}
