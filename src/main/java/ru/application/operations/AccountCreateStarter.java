package ru.application.operations;

import org.springframework.stereotype.Component;
import ru.application.enums.titles.Title;
import ru.application.output.ConsoleOutputService;
import ru.application.output.OutputMessageDto;
import ru.application.output.OutputService;
import ru.application.account.AccountService;
import ru.application.user.UserService;

import static ru.application.engine.Engine.config;

@Component
public class AccountCreateStarter implements OperationRun {

    @Override
    public void runOperation() {

        config.getBean(ConsoleOutputService.class).printMessage(new OutputMessageDto(
                Title.USER_ID_FOR_NEW_ACCOUNT.getTitle()
        ));

        int inputUserId = config.getBean(UserService.class).getAndCheckInputUserId();

        config.getBean(AccountService.class).createAccount(inputUserId);
        config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                Title.CREATE_ACCOUNT_SUCCESSFUL.getTitle()
        ));
    }


}
