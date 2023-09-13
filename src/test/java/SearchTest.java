import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import support.Constants;

public class SearchTest {

    private WebDriver driver;

    @BeforeClass
    private void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--incognito", "--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
    }

    @BeforeMethod
    private void beforeTest() {
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
    }

    @AfterClass
    public void afterTest() {
//        driver.quit();
    }

    @Test(testName = "Verify searching by one-word book name")
    public void verifySearchingByOneWordBookName() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("Gitanjali");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        WebElement expectedBookName = driver.findElement(By.xpath("//p[text()='Gitanjali ']"));
        Assert.assertTrue(expectedBookName.isDisplayed());
    }

    @Test(testName = "Verify searching by multiple-word book name")
    public void verifySearchingByMultipleWordsBookName() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("The Power of Positive Thinking");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        WebElement expectedBookName = driver.findElement(By.xpath("//p[text()='The Power of Positive " +
                "Thinking ']"));
        Assert.assertTrue(expectedBookName.isDisplayed());
    }

    @Test(testName = "Verify searching by one word from multiple-word book name")
    public void verifySearchingByOneWordFromMultipleWordBookName() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("Power");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        WebElement expectedBookName = driver.findElement(By.xpath("//p[text()='The Power of Positive " +
                "Thinking ']"));
        Assert.assertTrue(expectedBookName.isDisplayed());
    }

    @Test(testName = "Verify searching by a part of a word from book name")
    public void verifySearchingByPartOfWordFromBookName() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("Powe");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        String expectedConformationMessageText = driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedConformationMessageText, Constants.NO_BOOKS_FOUND_SEARCH_WARNING_MESSAGE);
    }

    @Test(testName = "Verify searching by word with less than minimal limit characters")
    public void verifySearchingByWordWithLessThanMinLimitCharacters() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("Pow");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        String expectedConformationMessageText = driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedConformationMessageText, Constants.WORD_WITH_LESS_THAN_MIN_LIMIT_CHARACTERS_WARNING_MESSAGE);
    }

    @Test(testName = "Verify searching by empty string")
    public void verifySearchingByEmptyString() {
        driver.findElement(Constants.SEARCH_BUTTON).click();
        String expectedWarningMessageText = driver.findElement(Constants.WARNING_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedWarningMessageText, Constants.EMPTY_SEARCH_WARNING_MESSAGE);
    }

    @Test(testName = "Verify searching by author first name")
    public void verifySearchingByAuthorFirstName() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("Twain");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        String expectedConformationMessageText = driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedConformationMessageText, Constants.NO_BOOKS_FOUND_SEARCH_WARNING_MESSAGE);
    }

    @Test(testName = "Verify searching by author last name")
    public void verifySearchingByAuthorLastname() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("Mark");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        String expectedConformationMessageText = driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR).getText();
        Assert.assertEquals(expectedConformationMessageText, Constants.NO_BOOKS_FOUND_SEARCH_WARNING_MESSAGE);
    }
}
