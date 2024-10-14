package ru.application.account;

import org.springframework.stereotype.Component;
import ru.application.repository.Repository;
import ru.application.output.OutputMessageDto;
import ru.application.output.OutputService;
import ru.application.parser.DigitParser;
import ru.application.operationConsoleListener.OperationConsoleListener;
import ru.application.validator.AccountIdValidator;

import static ru.application.engine.Engine.config;

@Component
public class AccountService {

    private int accountId;

    private final AccountProperties accountProperties;

    public AccountService(AccountProperties accountProperties) {
        this.accountId = 0;
        this.accountProperties = accountProperties;
    }

    public void createAccount(int userId) {
        Account newAccount = new Account(++accountId, userId, accountProperties.getMoneyAmount());
        config.getBean(Repository.class).getAccountMap().put(accountId,newAccount);
        config.getBean(Repository.class).getUserRepository().get(userId).getAccountList().add(newAccount);
    }

    public void deposit(int accountID, int sum) {
        config.getBean(Repository.class).getAccountMap().get(accountID).setMoneyAmount(
                config.getBean(Repository.class).getAccountMap().get(accountID).getMoneyAmount() + sum
        );
    }

    public void withdraw(int accountId, int sum) {
        config.getBean(Repository.class).getAccountMap().get(accountId).setMoneyAmount(
                config.getBean(Repository.class).getAccountMap().get(accountId).getMoneyAmount() - sum
        );

    }

    public void transfer(int currentAccountId, int targetAccountId, int sum) {

        config.getBean(Repository.class).getAccountMap().get(currentAccountId).setMoneyAmount(
                config.getBean(Repository.class).getAccountMap().get(currentAccountId).getMoneyAmount() - sum
        );

        if (config.getBean(Repository.class).getAccountMap().get(currentAccountId).getUserId() !=
                config.getBean(Repository.class).getAccountMap().get(targetAccountId).getUserId()) {
            sum = (int) ( sum - sum * config.getBean(AccountService.class).accountProperties.getTransferCommission());
        }

        config.getBean(Repository.class).getAccountMap().get(targetAccountId).setMoneyAmount(
                config.getBean(Repository.class).getAccountMap().get(targetAccountId).getMoneyAmount() + sum
        );

    }

    public void closeAccount(int accountId, int userId) {
        int startingMoneyOnAccount = config.getBean(Repository.class)
                .getUserRepository().get(userId).getAccountList().get(0).getMoneyAmount();

        config.getBean(Repository.class).getUserRepository().get(userId).getAccountList().get(0).setMoneyAmount(
                startingMoneyOnAccount + config.getBean(Repository.class).getAccountMap().get(accountId).getMoneyAmount()
        );

        config.getBean(Repository.class).getUserRepository().get(userId).getAccountList().removeIf(
                a -> a.getAccountId() == accountId
        );
        config.getBean(Repository.class).getAccountMap().remove(accountId);
    }

    public int getAndCheckInputAccountId() {

        boolean loop = true;
        int accountId = -1;

        while (loop) {
            try {
                String userInput = config.getBean(OperationConsoleListener.class).getUserInput();
                int inputAccountId = config.getBean(DigitParser.class).parse(userInput);
                accountId = config.getBean(AccountIdValidator.class).validate(inputAccountId);
                loop = false;
            } catch (IllegalArgumentException illegalArgumentException) {
                config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                        illegalArgumentException.getMessage()
                ));
            }
        }

        return accountId;
    }

    public int getAndCheckInputSum() {

        boolean loop = true;
        int inputSum = -1;

        while (loop) {
            try {
                String userInput = config.getBean(OperationConsoleListener.class).getUserInput();
                inputSum = config.getBean(DigitParser.class).parse(userInput);
                loop = false;
            } catch (IllegalArgumentException illegalArgumentException) {
                config.getBean(OutputService.class).printMessage(new OutputMessageDto(
                        illegalArgumentException.getMessage()
                ));
            }
        }

        return inputSum;
    }

}
