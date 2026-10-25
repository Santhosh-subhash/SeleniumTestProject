
package completeSeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.google.com");
    }

    // TC01: Verify Google page title
    @Test
    public void verifyGoogleTitle() {
        String actualTitle = driver.getTitle();

        Assert.assertTrue(
            actualTitle.contains("Google"),
            "Google title verification failed"
        );

        System.out.println("TC01 Passed: Google title verified");
    }

    // TC02: Verify search box is displayed
    @Test
    public void verifySearchBoxDisplayed() {
        WebElement searchBox =
            driver.findElement(By.name("q"));

        Assert.assertTrue(
            searchBox.isDisplayed(),
            "Search box is not displayed"
        );

        System.out.println("TC02 Passed: Search box is displayed");
    }

    // TC03: Verify search box is enabled
    @Test
    public void verifySearchBoxEnabled() {
        WebElement searchBox =
            driver.findElement(By.name("q"));

        Assert.assertTrue(
            searchBox.isEnabled(),
            "Search box is not enabled"
        );

        System.out.println("TC03 Passed: Search box is enabled");
    }

    // TC04: Search for Selenium
    @Test
    public void searchForSelenium() {
        WebElement searchBox =
            driver.findElement(By.name("q"));

        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();

        Assert.assertTrue(
            driver.getTitle().toLowerCase().contains("selenium"),
            "Search results page title does not contain Selenium"
        );

        System.out.println("TC04 Passed: Selenium search executed");
    }

    // TC05: Search for TestNG
    @Test
    public void searchForTestNG() {
        WebElement searchBox =
            driver.findElement(By.name("q"));

        searchBox.sendKeys("TestNG Java");
        searchBox.submit();

        Assert.assertTrue(
            driver.getTitle().toLowerCase().contains("testng"),
            "Search results page title does not contain TestNG"
        );

        System.out.println("TC05 Passed: TestNG search executed");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
