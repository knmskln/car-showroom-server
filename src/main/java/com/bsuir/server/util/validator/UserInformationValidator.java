package com.bsuir.server.util.validator;

public class UserInformationValidator {
    private static final UserInformationValidator INSTANCE = new UserInformationValidator();

    public static UserInformationValidator getInstance() {
        return INSTANCE;
    }

    private UserInformationValidator() {}

    private static final String USERNAME_FORMAT_REGEX = "[A-Za-z][[a-z][_]]{4,13}";
    private static final String NAME_FORMAT_REGEX = ".{2,30}";
    private static final String EMAIL_FORMAT_REGEX = "[a-z][[a-z][0-9][-][_][.]]{3,25}[@][a-z]{2,10}[.][a-z]{2,4}";

    public boolean validate(String login) {
        return login.matches(USERNAME_FORMAT_REGEX);
    }

    public boolean validateEmail(String email) { return email.matches(EMAIL_FORMAT_REGEX); }

    public boolean validate(String login, String name, String surname,
                            String email) {
        return login.matches(USERNAME_FORMAT_REGEX) &&
                name.matches(NAME_FORMAT_REGEX) &&
                surname.matches(NAME_FORMAT_REGEX) &&
                email.matches(EMAIL_FORMAT_REGEX);
    }
}
