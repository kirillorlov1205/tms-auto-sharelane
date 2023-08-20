import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import support.Constants;

public class SignUpTest {

    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--incognito", "--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
    }

    @BeforeTest
    public void beforeTest() {
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
    }

    @AfterSuite
    public void afterSuite() {
        driver.close();
    }

    @Test(testName = "Sign up with valid credentials")
    public void signUpWithValidCredentials() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_FIRST_NAME_FIELD_LOCATOR).sendKeys("firstName");
        driver.findElement(Constants.SIGN_UP_LAST_NAME_FIELD_LOCATOR).sendKeys("lastName");
        driver.findElement(Constants.SIGN_UP_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_UP_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR).getText(), Constants.SUCCESSFUL_SIGN_UP_MESSAGE);
    }

    @Test(testName = "Sign up with 'Zip' contains a letter")
    public void signUpWithZipContainsALetter() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("A0001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(), Constants.INVALID_ZIP_CODE_WARNING_MESSAGE);
    }

    @Test(testName = "Sign up with 'Zip' contains less than minimal limit digits")
    public void signUpWithZipContainsLessThanMinimalLimitCharacters() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("1000");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(), Constants.INVALID_ZIP_CODE_WARNING_MESSAGE);
    }

    @Test(testName = "Sign up with empty 'Zip' field")
    public void signUpWithEmptyZip() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(), Constants.INVALID_ZIP_CODE_WARNING_MESSAGE);
    }

    @Test(testName = "Sign up with empty 'First name' field")
    public void signUpWithEmptyFirstNameField() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_LAST_NAME_FIELD_LOCATOR).sendKeys("lastName");
        driver.findElement(Constants.SIGN_UP_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_UP_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(), Constants.SIGN_UP_INVALID_DATA_WARNING_MESSAGE);
    }

    @Test(testName = "Sign up with empty 'Last name' field")
    public void signUpWithEmptyLastNameField() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_FIRST_NAME_FIELD_LOCATOR).sendKeys("firstName");
        driver.findElement(Constants.SIGN_UP_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_UP_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR).getText(), Constants.SUCCESSFUL_SIGN_UP_MESSAGE);
    }

    @Test(testName = "Sign up with empty 'Email' field")
    public void signUpWithEmptyEmailField() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_FIRST_NAME_FIELD_LOCATOR).sendKeys("firstName");
        driver.findElement(Constants.SIGN_UP_LAST_NAME_FIELD_LOCATOR).sendKeys("lastName");
        driver.findElement(Constants.SIGN_UP_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(), Constants.SIGN_UP_INVALID_DATA_WARNING_MESSAGE);
    }

    @Test(testName = "Sign up with empty 'Password' field")
    public void signUpWithEmptyPasswordField() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_FIRST_NAME_FIELD_LOCATOR).sendKeys("firstName");
        driver.findElement(Constants.SIGN_UP_LAST_NAME_FIELD_LOCATOR).sendKeys("lastName");
        driver.findElement(Constants.SIGN_UP_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(), Constants.SIGN_UP_INVALID_DATA_WARNING_MESSAGE);
    }

    @Test(testName = "Sign up with empty 'Confirm password' field")
    public void signUpWithEmptyConfirmPasswordField() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_FIRST_NAME_FIELD_LOCATOR).sendKeys("firstName");
        driver.findElement(Constants.SIGN_UP_LAST_NAME_FIELD_LOCATOR).sendKeys("lastName");
        driver.findElement(Constants.SIGN_UP_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_UP_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(), Constants.SIGN_UP_INVALID_DATA_WARNING_MESSAGE);
    }

    @Test(testName = "Sign up with 'Confirm password' that doesn't match password")
    public void signUpWithConfirmPasswordThatDoesNotMatchPassword() {
        driver.findElement(Constants.SIGN_UP_BUTTON_LOCATOR).click();
        driver.findElement(Constants.ZIP_CODE_FIELD_LOCATOR).sendKeys("10001");
        driver.findElement(Constants.ZIP_CODE_SUBMIT_BUTTON_LOCATOR).click();
        driver.findElement(Constants.SIGN_UP_FIRST_NAME_FIELD_LOCATOR).sendKeys("firstName");
        driver.findElement(Constants.SIGN_UP_LAST_NAME_FIELD_LOCATOR).sendKeys("lastName");
        driver.findElement(Constants.SIGN_UP_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_UP_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_UP_CONFIRM_PASSWORD_FIELD_LOCATOR).sendKeys("testPass");
        driver.findElement(Constants.SIGN_UP_SUBMIT_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(), Constants.SIGN_UP_INVALID_DATA_WARNING_MESSAGE);
    }
}
