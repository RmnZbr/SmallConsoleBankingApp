package ru.application.validator;

import org.springframework.stereotype.Component;
import ru.application.repository.Repository;
import ru.application.enums.errors.Error;

import static ru.application.engine.Engine.config;

@Component
public class AccountIdValidator {

    public int validate(int parsedInput) throws IllegalArgumentException {

        if (!config.getBean(Repository.class).getAccountMap().containsKey(parsedInput)) {
            throw new IllegalArgumentException(Error.ENTER_ACCOUNT_ID_ERROR.getError());
        }

        return parsedInput;
    }
}
