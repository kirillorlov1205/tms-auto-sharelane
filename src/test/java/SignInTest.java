import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import support.Constants;

public class SignInTest {

    private WebDriver driver;

    @BeforeSuite
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito", "--remote-allow-origins=*");
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

    @Test(testName = "Verify validation error while login with invalid email")
    public void verifyValidationErrorWhileLoginWithInvalidEmail() {
        driver.findElement(Constants.SIGN_IN_EMAIL_FIELD_LOCATOR).sendKeys("test");
        driver.findElement(Constants.SIGN_IN_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_IN_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(),
                Constants.SIGN_IN_INVALID_CREDENTIALS_VALIDATION_MESSAGE);
    }

    @Test(testName = "Verify validation error while login with invalid password")
    public void verifyValidationErrorWhileLoginWithInvalidPassword() {
        driver.findElement(Constants.SIGN_IN_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_IN_PASSWORD_FIELD_LOCATOR).sendKeys("1");
        driver.findElement(Constants.SIGN_IN_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(),
                Constants.SIGN_IN_INVALID_CREDENTIALS_VALIDATION_MESSAGE);
    }

    @Test(testName = "Verify validation error while login with empty email")
    public void verifyValidationErrorWhileLoginWithEmptyEmail() {
        driver.findElement(Constants.SIGN_IN_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_IN_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(),
                Constants.SIGN_IN_INVALID_CREDENTIALS_VALIDATION_MESSAGE);
    }

    @Test(testName = "Verify validation error while login with empty password")
    public void verifyValidationErrorWhileLoginWithEmptyPassword() {
        driver.findElement(Constants.SIGN_IN_EMAIL_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_IN_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(),
                Constants.SIGN_IN_INVALID_CREDENTIALS_VALIDATION_MESSAGE);
    }
}
