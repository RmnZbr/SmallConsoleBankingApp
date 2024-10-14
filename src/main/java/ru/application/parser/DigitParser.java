package ru.application.parser;

import org.springframework.stereotype.Component;
import ru.application.enums.errors.Error;

@Component
public class DigitParser {

    public int parse(String userInput) throws NumberFormatException {

        int userId = 0;
        try {
            userId = Integer.parseInt(userInput);
            if (userId <= 0) {
                throw new IllegalArgumentException(Error.NOT_POSITIVE_NUMBER_ERROR.getError());
            }
        } catch (NumberFormatException exception) {
            throw new NumberFormatException(Error.ENTER_DIGITAL_INPUT_ERROR.getError());
        }

        return userId;
    }

}
