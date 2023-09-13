import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import support.Constants;

public class SignUpTest {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--incognito", "--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

    @Test(testName = "Verify successful sign up with valid credentials")
    public void verifySuccessfulSignUpWithValidCredentials() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_FIRST_NAME_FIELD_LOCATOR).sendKeys("firstName");
        driver.findElement(Constants.SIGN_UP_LAST_NAME_FIELD_LOCATOR).sendKeys("lastName");
        driver.findElement(Constants.SIGN_UP_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_UP_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        String expectedConformationMessageText = driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedConformationMessageText, Constants.SUCCESSFUL_SIGN_UP_MESSAGE);
    }

    @Test(testName = "Verify validation error while sign up with 'Zip' contains a letter")
    public void verifyValidationErrorWhileSignUpWithZipContainsALetter() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("A0001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        String expectedWarningMessageText = driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedWarningMessageText, Constants.INVALID_ZIP_CODE_WARNING_MESSAGE);
    }

    @Test(testName = "Verify validation error while sign up with 'Zip' contains less than minimal limit digits")
    public void verifyValidationErrorWhileSignUpWithZipContainsLessThanMinimalLimitCharacters() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("1000");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        String expectedWarningMessageText = driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedWarningMessageText, Constants.INVALID_ZIP_CODE_WARNING_MESSAGE);
    }

    @Test(testName = "Verify validation error while sign up with empty 'Zip' field")
    public void verifyValidationErrorWhileSignUpWithEmptyZip() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        String expectedWarningMessageText = driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedWarningMessageText, Constants.INVALID_ZIP_CODE_WARNING_MESSAGE);
    }

    @Test(testName = "Verify validation error while sign up with empty 'First name' field")
    public void verifyValidationErrorWhileSignUpWithEmptyFirstNameField() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_LAST_NAME_FIELD_LOCATOR).sendKeys("lastName");
        driver.findElement(Constants.SIGN_UP_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_UP_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        String expectedWarningMessageText = driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedWarningMessageText, Constants.SIGN_UP_INVALID_DATA_WARNING_MESSAGE);
    }

    @Test(testName = "Verify successful sign up with empty 'Last name' field")
    public void verifySuccessfulSignUpWithEmptyLastNameField() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_FIRST_NAME_FIELD_LOCATOR).sendKeys("firstName");
        driver.findElement(Constants.SIGN_UP_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_UP_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        String expectedConformationMessageText = driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedConformationMessageText, Constants.SUCCESSFUL_SIGN_UP_MESSAGE);
    }

    @Test(testName = "Verify validation error while sign up with empty 'Email' field")
    public void verifyValidationErrorWhileSignUpWithEmptyEmailField() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_FIRST_NAME_FIELD_LOCATOR).sendKeys("firstName");
        driver.findElement(Constants.SIGN_UP_LAST_NAME_FIELD_LOCATOR).sendKeys("lastName");
        driver.findElement(Constants.SIGN_UP_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        String expectedWarningMessageText = driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedWarningMessageText, Constants.SIGN_UP_INVALID_DATA_WARNING_MESSAGE);
    }

    @Test(testName = "Verify validation error while sign up with empty 'Password' field")
    public void verifyValidationErrorWhileSignUpWithEmptyPasswordField() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_FIRST_NAME_FIELD_LOCATOR).sendKeys("firstName");
        driver.findElement(Constants.SIGN_UP_LAST_NAME_FIELD_LOCATOR).sendKeys("lastName");
        driver.findElement(Constants.SIGN_UP_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        String expectedWarningMessageText = driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedWarningMessageText, Constants.SIGN_UP_INVALID_DATA_WARNING_MESSAGE);
    }

    @Test(testName = "Verify validation error while sign up with empty 'Confirm password' field")
    public void verifyValidationErrorWhileSignUpWithEmptyConfirmPasswordField() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_FIRST_NAME_FIELD_LOCATOR).sendKeys("firstName");
        driver.findElement(Constants.SIGN_UP_LAST_NAME_FIELD_LOCATOR).sendKeys("lastName");
        driver.findElement(Constants.SIGN_UP_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_UP_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        String expectedWarningMessageText = driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedWarningMessageText, Constants.SIGN_UP_INVALID_DATA_WARNING_MESSAGE);
    }

    //Should be failed
    @Test(testName = "Verify validation error while sign up with 'Confirm password' that doesn't match password")
    public void verifyValidationErrorWhileSignUpWithConfirmPasswordThatDoesNotMatchPassword() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_FIRST_NAME_FIELD_LOCATOR).sendKeys("firstName");
        driver.findElement(Constants.SIGN_UP_LAST_NAME_FIELD_LOCATOR).sendKeys("lastName");
        driver.findElement(Constants.SIGN_UP_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_UP_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR).sendKeys("testPass");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        String expectedWarningMessageText = driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedWarningMessageText, Constants.SIGN_UP_INVALID_DATA_WARNING_MESSAGE);
    }
}
