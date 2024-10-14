package ru.application.user;

import org.springframework.stereotype.Component;
import ru.application.repository.Repository;
import ru.application.account.AccountService;
import ru.application.enums.titles.Title;
import ru.application.output.OutputMessageDto;
import ru.application.output.OutputService;
import ru.application.parser.DigitParser;
import ru.application.parser.LoginParser;
import ru.application.operationConsoleListener.OperationConsoleListener;
import ru.application.validator.UserIdValidator;

import java.util.ArrayList;

import static ru.application.engine.Engine.config;

@Component
public class UserService {

    private int idCounter = 0;

    public void userCreate() {

        config.getBean(OutputService.class).printMessage(new OutputMessageDto(Title.ENTER_YOUR_LOGIN.getTitle()));

        boolean loop = true;

        while (loop) {

            String parsedUserLogin = getAndParseUserInput();
            boolean isContains = config.getBean(Repository.class).getUserLoginRepository().contains(parsedUserLogin);
            if (!isContains) {
                config.getBean(Repository.class).getUserLoginRepository().add(parsedUserLogin);
                config.getBean(Repository.class).getUserRepository().put(++idCounter, new User(
                        idCounter, parsedUserLogin, new ArrayList<>()));
                config.getBean(AccountService.class).createAccount(idCounter);
                config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                        Title.CREATE_USER_SUCCESSFUL.getTitle()));
                loop = false;
            } else {
                config.getBean(OutputService.class).printMessage(new OutputMessageDto(Title.LOGIN_EXIST.getTitle()));
            }
        }

    }

    public void showAllUsers() {
        config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                config.getBean(Repository.class).getUserRepository().values().stream().toList().toString()));
    }

    private String getAndParseUserInput() throws RuntimeException {

        boolean loop = true;
        String parsedUserInput = "user";

        while (loop) {
            try {
                String userInput = config.getBean(OperationConsoleListener.class).getUserInput();
                parsedUserInput = config.getBean(LoginParser.class).parse(userInput);
                loop = false;
            } catch (RuntimeException runtimeException) {
                config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                        runtimeException.getMessage()
                ));
            }
        }
        return parsedUserInput;
    }

    public int getAndCheckInputUserId() {

        boolean loop = true;
        int userId = -1;

        while (loop) {
            try {
                String userInput = config.getBean(OperationConsoleListener.class).getUserInput();
                int inputUserId = config.getBean(DigitParser.class).parse(userInput);
                userId = config.getBean(UserIdValidator.class).validate(inputUserId);
                loop = false;
            } catch (IllegalArgumentException illegalArgumentException) {
                config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                        illegalArgumentException.getMessage()
                ));
            }
        }

        return userId;
    }

}
