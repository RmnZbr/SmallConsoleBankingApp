package ru.application.operations;

import org.springframework.stereotype.Component;
import ru.application.user.UserService;

import static ru.application.engine.Engine.config;

@Component
public class UserCreateStarter implements OperationRun {

    @Override
    public void runOperation() {

        config.getBean(UserService.class).userCreate();

    }
}
