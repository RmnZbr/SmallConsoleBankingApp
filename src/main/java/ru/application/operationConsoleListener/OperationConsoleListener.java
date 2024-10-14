package ru.application.operationConsoleListener;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class OperationConsoleListener implements InputService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getUserInput() {
        return scanner.nextLine();
    }
}
