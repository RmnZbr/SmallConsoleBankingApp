package ru.application.validator;

import org.springframework.stereotype.Component;
import ru.application.repository.Repository;
import ru.application.enums.errors.Error;

import static ru.application.engine.Engine.config;

@Component
public class UserIdValidator {

    public int validate(int parsedInput) throws IllegalArgumentException {

        if (!config.getBean(Repository.class).getUserRepository().containsKey(parsedInput)) {
            throw new IllegalArgumentException(Error.ENTER_USER_ID_ERROR.getError());
        }

        return parsedInput;
    }
}
