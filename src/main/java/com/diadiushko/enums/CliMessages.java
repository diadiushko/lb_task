package com.diadiushko.enums;

public enum CliMessages {
    WELCOME("""
            Welcome to the Useless CLI tool.
            For some reason, we are asking for user's files to save
            content that stored within those files into our Database.
            """),
    ENTER_FILEPATH("""
            Enter the path of file that contains VALID BINARY USER DATA
            (for some mysterious reason we will save it into our Database),
            or letter "Q" to exit the app."""),
    INVALID_PATH("HAHAHAHA, very funny mate, now think twice.\n"),
    NO_USER_DATA_FOUND("Dude, stop entering this weird staff. Your file doesn't have any valid user data.\n");

    private final String message;

    CliMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
