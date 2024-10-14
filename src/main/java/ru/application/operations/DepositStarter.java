package ru.application.operations;

import org.springframework.stereotype.Component;
import ru.application.repository.Repository;
import ru.application.enums.titles.Title;
import ru.application.output.OutputMessageDto;
import ru.application.output.OutputService;
import ru.application.account.AccountService;

import static ru.application.engine.Engine.config;

@Component
public class DepositStarter implements OperationRun {

    @Override
    public void runOperation() {

        config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                Title.ACCOUNT_ID_FOR_DEPOSIT.getTitle()
        ));

       int accountId = config.getBean(AccountService.class).getAndCheckInputAccountId();

       config.getBean(OutputService.class).printMessage(new OutputMessageDto(
               Title.ENTER_SUM_FOR_DEPOSIT.getTitle()
       ));

        int sum = config.getBean(AccountService.class).getAndCheckInputSum();

        config.getBean(AccountService.class).deposit(accountId,sum);
        config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                Title.DEPOSIT_SUCCESSFUL.getTitle() +
                        config.getBean(Repository.class).getAccountMap().get(accountId).getMoneyAmount()
        ));
    }
}
