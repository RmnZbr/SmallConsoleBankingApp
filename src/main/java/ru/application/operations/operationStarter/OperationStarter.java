package ru.application.operations.operationStarter;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.application.enums.commands.Command;
import ru.application.operations.*;

import java.util.HashMap;
import java.util.Map;

import static ru.application.engine.Engine.config;

@Component
@Lazy
public class OperationStarter {

    private final Map<Command, OperationRun> operationMap;

    public OperationStarter() {
        this.operationMap = new HashMap<>();
        operationMap.put(Command.ACCOUNT_CREATE, config.getBean(AccountCreateStarter.class));
        operationMap.put(Command.SHOW_ALL_USERS, config.getBean(ShowAllUsersStarter.class));
        operationMap.put(Command.ACCOUNT_CLOSE, config.getBean(AccountCloseStarter.class));
        operationMap.put(Command.ACCOUNT_WITHDRAW, config.getBean(WithdrawStarter.class));
        operationMap.put(Command.ACCOUNT_DEPOSIT, config.getBean(DepositStarter.class));
        operationMap.put(Command.ACCOUNT_TRANSFER, config.getBean(TransferStarter.class));
        operationMap.put(Command.USER_CREATE, config.getBean(UserCreateStarter.class));
    }

    public void runOperation(Command command) {
        operationMap.get(command).runOperation();
    }
}
