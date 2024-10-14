package ru.application.enums.titles;

public enum Title {

    CHOOSE_OPERATION("Please enter one of operation type:"),
    ENTER_YOUR_LOGIN("Enter your login"),
    LOGIN_EXIST("This login is already exists, try again."),
    CREATE_USER_SUCCESSFUL("Create new user successful."),
    USER_ID_FOR_NEW_ACCOUNT("Enter user id for create new account."),
    ACCOUNT_ID_FOR_CLOSE("Enter account id for close."),
    ACCOUNT_ID_FOR_DEPOSIT("Enter account id for deposit."),
    ACCOUNT_ID_FOR_WITHDRAW("Enter account id for withdraw."),
    ACCOUNT_CLOSED_SUCCESSFULLY("Account closed successfully."),
    ENTER_SUM_FOR_DEPOSIT("Enter sum for deposit."),
    ENTER_SUM_FOR_WITHDRAW("Enter sum for withdraw."),
    DEPOSIT_SUCCESSFUL("Deposit successful. Amount money on account - "),
    WITHDRAW_SUCCESSFUL("Withdraw successful. Amount money on account - "),
    ENTER_SENDER_ACCOUNT_ID("Enter sender`s account id."),
    ENTER_SUM_FOR_TRANSFER("Enter sum for transfer."),
    ENTER_RECIPIENT_ACCOUNT_ID("Enter recipient`s account id."),
    TRANSFER_SUCCESSFUL("Money transfer was successful."),
    CREATE_ACCOUNT_SUCCESSFUL("Create new account successful.");


    private final String title;

    Title(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
