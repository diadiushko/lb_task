package com.diadiushko.cli;

import com.diadiushko.cli.services.DatabaseService;
import com.diadiushko.cli.services.FilesService;
import com.diadiushko.entities.User;
import com.diadiushko.enums.CliCommands;
import com.diadiushko.enums.CliMessages;
import com.diadiushko.enums.LoggerMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public final class CLI {
    private static final Logger logger = LoggerFactory.getLogger(CLI.class);
    private static final Scanner TERMINAL_SCANNER = new Scanner(System.in);
    private static final FilesService<User> filesUserService = new FilesService<>();

    public static void launch() {
        logger.info(LoggerMessages.START_CLI.getMessage());
        DatabaseService.setConnection(false);
        System.out.println(CliMessages.WELCOME);
        receiveInput();
    }

    private static void receiveInput() {
        while (true) {
            System.out.println(CliMessages.ENTER_FILEPATH);
            final String userInput = TERMINAL_SCANNER.nextLine();
            handleInput(userInput);
        }
    }

    private static void handleInput(String userInput) {
        final boolean userWantsToExit = userInput.equalsIgnoreCase(String.valueOf(CliCommands.QUIT));
        if (userWantsToExit) {
            logger.warn(LoggerMessages.APP_EXIT.getMessage());
            System.exit(0);
        }

        if (!filesUserService.doesFileExist(userInput)) {
            logger.info(LoggerMessages.PATH_DOESNT_EXIST.getMessage(), userInput);
            System.out.println(CliMessages.INVALID_PATH);
            return;
        }

        final User fileValue = filesUserService.getUsersFromFile(userInput);
        if (fileValue == null) {
            System.out.println(CliMessages.NO_USER_DATA_FOUND);
            return;
        }
        logger.info(LoggerMessages.USER_FOUND_INFO.getMessage(), fileValue);
        final boolean userSaved = DatabaseService.insertUser(fileValue);
        if (userSaved) {
            logger.info(LoggerMessages.USER_WAS_SAVED_INTO_DATABASE.getMessage());
            return;
        }
        logger.info(LoggerMessages.USER_WAS_NOT_SAVED_INTO_DATABASE.getMessage());
    }
}
