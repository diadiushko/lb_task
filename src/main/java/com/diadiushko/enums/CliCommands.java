package com.diadiushko.enums;

public enum CliCommands {
    QUIT("q");

    private final String command;

    CliCommands(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }
}
