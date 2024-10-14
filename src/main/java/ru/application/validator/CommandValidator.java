package ru.application.validator;

import org.springframework.stereotype.Component;
import ru.application.enums.commands.Command;
import ru.application.enums.errors.Error;

import java.util.Optional;

@Component
public class CommandValidator {

    public Command validate(String parsedInput) {
        Optional<Command> commandType = Command.getCommandType(parsedInput);
        if (commandType.isPresent()) {
            return commandType.get();
        } else {
            throw new RuntimeException(Error.NO_SUCH_COMMAND_ERROR.getError());
        }
    }
}
