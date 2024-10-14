package ru.application.operations;

import org.springframework.stereotype.Component;
import ru.application.repository.Repository;
import ru.application.enums.errors.Error;
import ru.application.enums.titles.Title;
import ru.application.output.OutputMessageDto;
import ru.application.output.OutputService;
import ru.application.account.AccountService;

import static ru.application.engine.Engine.config;

@Component
public class WithdrawStarter implements OperationRun {

    @Override
    public void runOperation() {

        config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                Title.ACCOUNT_ID_FOR_WITHDRAW.getTitle()
        ));

        int inputAccountId = config.getBean(AccountService.class).getAndCheckInputAccountId();

        config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                Title.ENTER_SUM_FOR_WITHDRAW.getTitle()
        ));

        boolean loop = true;
        int sumForWithdraw = -1;

        while (loop) {

            try {
                int inputSumForWithdraw = config.getBean(AccountService.class).getAndCheckInputSum();
                if (config.getBean(Repository.class).
                        getAccountMap().get(inputAccountId).getMoneyAmount() < inputSumForWithdraw) {
                    throw new IllegalArgumentException(Error.SUM_FOR_WITHDRAW_MORE_THAN_AVAILABLE_ERROR.getError() +
                            config.getBean(Repository.class).getAccountMap().get(inputAccountId).getMoneyAmount());
                }
                sumForWithdraw = inputSumForWithdraw;
                loop = false;
            } catch (IllegalArgumentException illegalArgumentException) {
                config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                        illegalArgumentException.getMessage()
                ));
            }
        }

        config.getBean(AccountService.class).withdraw(inputAccountId,sumForWithdraw);
        config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                Title.WITHDRAW_SUCCESSFUL.getTitle() +
                        config.getBean(Repository.class).getAccountMap().get(inputAccountId).getMoneyAmount()
        ));
    }
}

