package ru.application.parser;

import org.springframework.stereotype.Component;
import ru.application.enums.errors.Error;

@Component
public class LoginParser {

    public String parse(String userInput) throws RuntimeException {
        if (userInput.isBlank()) {
            throw new RuntimeException(Error.EMPTY_INPUT_ERROR.getError());
        } else {
            String[] splittedInput = userInput.trim().split("\\s+");
            if (splittedInput.length > 1) {
                throw new RuntimeException(Error.INPUT_WITH_SPACES_ERROR.getError());
            }
            return splittedInput[0];
        }
    }
}
