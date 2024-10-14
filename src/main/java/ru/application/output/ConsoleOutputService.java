package ru.application.output;

import org.springframework.stereotype.Component;
import ru.application.enums.commands.Command;
import ru.application.enums.titles.Title;

@Component
public class ConsoleOutputService implements OutputService {

    @Override
    public void printMessage(OutputMessageDto dto) {
        System.out.println(dto.getMessage());
    }

    public void printChoosingTitles() {
        printMessage(new OutputMessageDto(String.format(
                """
                %s
                %s
                %s
                %s
                %s
                %s
                %s
                %s""",
                Title.CHOOSE_OPERATION.getTitle(),
                Command.ACCOUNT_CREATE.name(),
                Command.SHOW_ALL_USERS.name(),
                Command.ACCOUNT_CLOSE.name(),
                Command.ACCOUNT_WITHDRAW.name(),
                Command.ACCOUNT_DEPOSIT.name(),
                Command.ACCOUNT_TRANSFER.name(),
                Command.USER_CREATE.name())
                )
        );
    }
}
