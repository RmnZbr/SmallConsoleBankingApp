package ru.application.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AccountProperties {

    private final int moneyAmount;
    private final double transferCommission;

    public AccountProperties(
            @Value("${this.moneyAmount}") int moneyAmount,
            @Value("${this.transferCommission}") double transferCommission
    ) {
        this.moneyAmount = moneyAmount;
        this.transferCommission = transferCommission;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public double getTransferCommission() {
        return transferCommission;
    }
}
