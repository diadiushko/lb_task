package com.diadiushko.enums;

public enum LoggerMessages {
    START_CLI("Starting the CLI app.."),
    APP_EXIT("User exits the app."),
    PATH_DOESNT_EXIST("User's input {} didn't match any existing files on the PC."),
    USER_FOUND_INFO("User found: {}"),
    SETTING_CONNECTION("Initializing DB connection to {} as {}"),
    USER_WAS_SAVED_INTO_DATABASE("User has been saved into database!"),
    USER_WAS_NOT_SAVED_INTO_DATABASE("User hasn't been saved into database!"),
    CONNECTION_IS_ALREADY_CLOSE("The connection is closed. No operations can be executed.");

    private final String message;

    LoggerMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
