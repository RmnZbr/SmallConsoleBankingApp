package ru.application.engine;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.application.enums.commands.Command;
import ru.application.operations.operationStarter.OperationStarter;
import ru.application.output.ConsoleOutputService;
import ru.application.output.OutputMessageDto;
import ru.application.output.OutputService;
import ru.application.operationConsoleListener.OperationConsoleListener;
import ru.application.validator.CommandValidator;

public class Engine {

    public static AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(
                "ru.application");

    public static void run() {

        boolean loop = true;

        while (loop) {

            try {
                config.getBean(ConsoleOutputService.class).printChoosingTitles();
                String userInput = config.getBean(OperationConsoleListener.class).getUserInput();
                Command inputCommand = config.getBean(CommandValidator.class).validate(userInput);
                config.getBean(OperationStarter.class).runOperation(inputCommand);
            } catch (RuntimeException exception) {
                config.getBean(OutputService.class).printMessage(new OutputMessageDto(exception.getMessage()));
            }
        }
    }

}
