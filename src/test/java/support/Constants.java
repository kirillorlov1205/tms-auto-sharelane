package support;

import org.openqa.selenium.By;

public class Constants {

    //    Common
    public static final By WARNING_MESSAGE_LOCATOR = By.cssSelector("span.error_message");
    public static final By CONFORMATION_MESSAGE_LOCATOR = By.cssSelector("span.confirmation_message");

    //    Sign up
    public static final By ZIP_CODE_FIELD_LOCATOR = By.xpath("//input[@name='zip_code']");
    public static final By ZIP_CODE_SUBMIT_BUTTON_LOCATOR = By.xpath("//input[@value='Continue']");
    public static final String INVALID_ZIP_CODE_WARNING_MESSAGE
            = "Oops, error on page. ZIP code should have 5 digits";
    public static final By SIGN_UP_BUTTON_LOCATOR = By.xpath("//a[text()='Sign up']");
    public static final By SIGN_UP_FIRST_NAME_FIELD_LOCATOR = By.name("first_name");
    public static final By SIGN_UP_LAST_NAME_FIELD_LOCATOR = By.name("last_name");
    public static final By SIGN_UP_EMAIL_FIELD_LOCATOR = By.name("email");
    public static final By SIGN_UP_PASSWORD_FIELD_LOCATOR = By.name("password1");
    public static final By SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR = By.name("password2");
    public static final By SIGN_UP_SUBMIT_BUTTON_LOCATOR = By.cssSelector("input[value='Register']");
    public static final String SIGN_UP_INVALID_DATA_WARNING_MESSAGE
            = "Oops, error on page. Some of your fields have invalid data or email was previously used";
    public static final String SUCCESSFUL_SIGN_UP_MESSAGE = "Account is created!";

    //    Sign in
    public static final By SIGN_IN_LOGIN_FIELD_LOCATOR = By.name("email");
    public static final By SIGN_IN_PASSWORD_FIELD_LOCATOR = By.name("password");
    public static final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//input[@value='Login']");
    public static final String SIGN_IN_INVALID_CREDENTIALS_VALIDATION_MESSAGE
            = "Oops, error. Email and/or password don't match our records";

    //    Search
    public static final By SEARCH_FIELD_LOCATOR = By.xpath("//input[@name='keyword']");
    public static final By SEARCH_BUTTON = By.xpath("//input[@value='Search']");
    public static final String EMPTY_SEARCH_WARNING_MESSAGE = "Oops, error. No keyword is provided";
    public static final String NO_BOOKS_FOUND_SEARCH_WARNING_MESSAGE = "Nothing is found :(";
    public static final String WORD_WITH_LESS_THAN_MIN_LIMIT_CHARACTERS_WARNING_MESSAGE
            = "Please, note that current MySQL settings don't allow searches for words containing less than 4 chars";
}
