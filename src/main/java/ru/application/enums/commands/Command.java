package ru.application.enums.commands;

import java.util.Arrays;
import java.util.Optional;


public enum Command {

    CHOOSE_OPERATION_TYPE,
    ACCOUNT_CREATE,
    SHOW_ALL_USERS,
    ACCOUNT_CLOSE,
    ACCOUNT_WITHDRAW,
    ACCOUNT_DEPOSIT,
    ACCOUNT_TRANSFER,
    USER_CREATE;


    public static Optional<Command> getCommandType(String command) {
        return Arrays.stream(values())
                .filter(c -> c.name().equals(command))
                .findFirst();
    }
}
