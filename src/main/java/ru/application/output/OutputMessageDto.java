package ru.application.output;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class OutputMessageDto {

    private final String message;

    public String getMessage() {
        return message;
    }

    public OutputMessageDto(String message) {
        this.message = message;
    }
}
