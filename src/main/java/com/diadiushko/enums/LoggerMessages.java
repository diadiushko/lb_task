package com.diadiushko.enums;

public enum LoggerMessages {
    START_CLI("Starting the CLI app.."),
    APP_EXIT("User exits the app."),
    PATH_DOESNT_EXIST("User's input {} didn't match any existing files on the PC."),
    USER_FOUND_INFO("User found: {}");

    private final String message;

    LoggerMessages(String message) {
        this.message = message + "\n";
    }

    public String getMessage() {
        return message;
    }
}
