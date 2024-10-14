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
public class TransferStarter implements OperationRun {

    @Override
    public void runOperation() {

        config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                Title.ENTER_SENDER_ACCOUNT_ID.getTitle()
        ));

        int inputSenderAccountId = config.getBean(AccountService.class).getAndCheckInputAccountId();

        config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                Title.ENTER_RECIPIENT_ACCOUNT_ID.getTitle()
        ));

        int inputRecipientAccountId = config.getBean(AccountService.class).getAndCheckInputAccountId();

        config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                Title.ENTER_SUM_FOR_TRANSFER.getTitle()
        ));

        boolean loop = true;
        int sumForTransfer = -1;

        while (loop) {

            int inputSumForTransfer = config.getBean(AccountService.class).getAndCheckInputSum();

            try {
                if (config.getBean(Repository.class).getAccountMap()
                        .get(inputSenderAccountId).getMoneyAmount() < inputSumForTransfer) {
                    throw new IllegalArgumentException(Error.NOT_ENOUGH_FUNDS_ERROR.getError() +
                            config.getBean(Repository.class).getAccountMap()
                                    .get(inputSenderAccountId).getMoneyAmount());
                }
                sumForTransfer = inputSumForTransfer;
                loop = false;
            } catch (IllegalArgumentException illegalArgumentException) {
                config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                        illegalArgumentException.getMessage()
                ));
            }

        }

        config.getBean(AccountService.class).transfer(inputSenderAccountId, inputRecipientAccountId, sumForTransfer);
        config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                Title.TRANSFER_SUCCESSFUL.getTitle()
        ));
    }
}
