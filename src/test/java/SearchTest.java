import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import support.Constants;

public class SearchTest {

    private WebDriver driver;

    @BeforeSuite
    private void beforeSuite() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--incognito", "--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
    }

    @BeforeTest
    private void beforeTest() {
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
    }

    @AfterSuite
    public void afterTest() {
        driver.close();
    }

    @Test(testName = "Search by one-word book name")
    public void searchByOneWordBookName() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("Gitanjali");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        driver.findElement(By.xpath("//p[text()='Gitanjali ']")).isDisplayed();
    }

    @Test(testName = "Search by multiple-word book name")
    public void searchByMultipleWordBookName() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("The Power of Positive Thinking");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        driver.findElement(By.xpath("//p[text()='The Power of Positive Thinking ']")).isDisplayed();
    }

    @Test(testName = "Search by one word from multiple-word book name")
    public void searchByOneWordFromMultipleWordBookName() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("Power");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        driver.findElement(By.xpath("//p[text()='The Power of Positive Thinking ']")).isDisplayed();
    }

    @Test(testName = "Search by a part of word from book name")
    public void searchByPartOfWordFromBookName() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("Powe");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        Assert.assertEquals(driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR)
                .getText(), Constants.NO_BOOKS_FOUND_SEARCH_WARNING_MESSAGE);
    }

    @Test(testName = "Search by word with less than minimal limit characters")
    public void searchByWordWithLessThanMinLimitCharacters() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("Pow");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        Assert.assertEquals(driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR)
                .getText(), Constants.WORD_WITH_LESS_THAN_MIN_LIMIT_CHARACTERS_WARNING_MESSAGE);
    }

    @Test(testName = "Search by empty string")
    public void searchByEmptyString() {
        driver.findElement(Constants.SEARCH_BUTTON).click();
        Assert.assertEquals(driver.findElement(Constants.WARNING_MESSAGE_LOCATOR)
                .getText(), Constants.EMPTY_SEARCH_WARNING_MESSAGE);
    }

    @Test(testName = "Search by author first name")
    public void searchByAuthorFirstName() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("Twain");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        Assert.assertEquals(driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR)
                .getText(), Constants.NO_BOOKS_FOUND_SEARCH_WARNING_MESSAGE);
    }

    @Test(testName = "Search by author last name")
    public void searchByAuthorLastname() {
        driver.findElement(Constants.SEARCH_FIELD_LOCATOR).sendKeys("Mark");
        driver.findElement(Constants.SEARCH_BUTTON).click();
        Assert.assertEquals(driver.findElement(Constants.CONFORMATION_MESSAGE_LOCATOR)
                .getText(), Constants.NO_BOOKS_FOUND_SEARCH_WARNING_MESSAGE);
    }
}
