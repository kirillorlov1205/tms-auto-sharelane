import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import support.Constants;

public class SignInTest {

    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito", "--remote-allow-origins=*");
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

    @Test(testName = "Login with invalid email")
    public void checkForValidationErrorWhileProvidingInvalidEmail() {
        driver.findElement(Constants.SIGN_IN_LOGIN_FIELD_LOCATOR).sendKeys("test");
        driver.findElement(Constants.SIGN_IN_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_IN_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(), Constants.SIGN_IN_INVALID_CREDENTIALS_VALIDATION_MESSAGE);
    }

    @Test(testName = "Login with invalid password")
    public void checkForValidationErrorWhileProvidingInvalidPassword() {
        driver.findElement(Constants.SIGN_IN_LOGIN_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_IN_PASSWORD_FIELD_LOCATOR).sendKeys("1");
        driver.findElement(Constants.SIGN_IN_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(), Constants.SIGN_IN_INVALID_CREDENTIALS_VALIDATION_MESSAGE);
    }

    @Test(testName = "Login with empty email")
    public void checkForValidationErrorWhileProvidingEmptyEmail() {
        driver.findElement(Constants.SIGN_IN_PASSWORD_FIELD_LOCATOR).sendKeys("testPass1");
        driver.findElement(Constants.SIGN_IN_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(), Constants.SIGN_IN_INVALID_CREDENTIALS_VALIDATION_MESSAGE);
    }

    @Test(testName = "Login with empty password")
    public void checkForValidationErrorWhileProvidingEmptyPassword() {
        driver.findElement(Constants.SIGN_IN_LOGIN_FIELD_LOCATOR).sendKeys("testEmail@gmail.com");
        driver.findElement(Constants.SIGN_IN_BUTTON_LOCATOR).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText(), Constants.SIGN_IN_INVALID_CREDENTIALS_VALIDATION_MESSAGE);
    }
}
