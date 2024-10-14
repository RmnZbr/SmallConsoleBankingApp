package ru.application.account;

public class Account {

    private final int accountId;
    private final int userId;
    private int moneyAmount;

    public Account(int id, int userId, int moneyAmount) {
        this.accountId = id;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getUserId() {
        return userId;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
