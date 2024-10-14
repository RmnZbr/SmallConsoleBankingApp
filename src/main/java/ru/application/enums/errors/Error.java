package ru.application.enums.errors;

public enum Error {

    EMPTY_INPUT_ERROR("You did not enter something, try again."),
    INPUT_WITH_SPACES_ERROR("You have entered something with spaces, try again."),
    NO_SUCH_COMMAND_ERROR("No such command there, try again."),
    ENTER_USER_ID_ERROR("Not found UserID. Please check entered Id, try again."),
    ENTER_ACCOUNT_ID_ERROR("Not found Account Id. Please check entered Id, try again."),
    ENTER_DIGITAL_INPUT_ERROR("Please enter digital input for User Id, try again."),
    ONE_ACCOUNT_FOR_CLOSE_ERROR("User has only one account. Unable to close account."),
    SUM_FOR_WITHDRAW_MORE_THAN_AVAILABLE_ERROR
            ("Sum for withdraw more than available on account, try again. Money amount on account - "),
    CLOSE_FIRST_ACCOUNT_ERROR("You can not close your very first account."),
    NOT_ENOUGH_FUNDS_ERROR("Not enough funds on account, try again. Money amount on account - "),
    IDENTICAL_ACCOUNT_ID_ERROR("You have entered two identical account id, try again."),
    NOT_POSITIVE_NUMBER_ERROR("You must enter a positive number, try again.");

    private final String error;

    Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
