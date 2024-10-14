package ru.application.repository;

import org.springframework.stereotype.Component;
import ru.application.account.Account;
import ru.application.user.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class Repository {

    private final Set<String> userLoginRepository;

    private final Map<Integer, User> userRepository;

    private final Map<Integer, Account> accountMap;

    public Repository() {
        this.userLoginRepository = new HashSet<>();
        this.userRepository = new HashMap<>();
        this.accountMap = new HashMap<>();
    }

    public Set<String> getUserLoginRepository() {
        return userLoginRepository;
    }

    public Map<Integer, User> getUserRepository() {
        return userRepository;
    }

    public Map<Integer, Account> getAccountMap() {
        return accountMap;
    }
}
