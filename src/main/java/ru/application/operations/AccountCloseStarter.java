package ru.application.operations;

import org.springframework.stereotype.Component;
import ru.application.repository.Repository;
import ru.application.enums.errors.Error;
import ru.application.enums.titles.Title;
import ru.application.output.ConsoleOutputService;
import ru.application.output.OutputMessageDto;
import ru.application.output.OutputService;
import ru.application.account.AccountService;

import static ru.application.engine.Engine.config;

@Component
public class AccountCloseStarter implements OperationRun {

    @Override
    public void runOperation() {

        config.getBean(ConsoleOutputService.class).printMessage(new OutputMessageDto(
                Title.ACCOUNT_ID_FOR_CLOSE.getTitle()
        ));

        int inputAccountIdForClose = config.getBean(AccountService.class).getAndCheckInputAccountId();
        int userId = config.getBean(Repository.class).getAccountMap().get(inputAccountIdForClose).getUserId();

        if (config.getBean(Repository.class).getUserRepository().get(userId).getAccountList().size() == 1) {
            config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                    Error.ONE_ACCOUNT_FOR_CLOSE_ERROR.getError()
            ));
        } else  if (config.getBean(Repository.class).getUserRepository().get(userId)
                .getAccountList().get(0).getAccountId() == inputAccountIdForClose) {
            config.getBean(ConsoleOutputService.class).printMessage(new OutputMessageDto(
                Error.CLOSE_FIRST_ACCOUNT_ERROR.getError()
            ));
        } else {
            config.getBean(AccountService.class).closeAccount(inputAccountIdForClose, userId);
            config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                    Title.ACCOUNT_CLOSED_SUCCESSFULLY.getTitle()
            ));
        }
    }
}
