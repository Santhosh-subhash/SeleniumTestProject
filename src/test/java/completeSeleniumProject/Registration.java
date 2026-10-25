
package completeSeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Registration {

    WebDriver driver;

    // Launch browser and open registration page
    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
               .implicitlyWait(Duration.ofSeconds(10));

        driver.get(
            "file:///C:/Users/sm646/Downloads/registration_demo%20for%20testing.html"
        );
    }

    // Clear the form before each test
    @BeforeMethod
    public void clearForm() {

        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();
    }

    // TC01 - Valid Registration
    @Test
    public void validRegistration() {

        driver.findElement(By.id("username"))
              .sendKeys("santhosh123");

        driver.findElement(By.id("email"))
              .sendKeys("santhosh123@gmail.com");

        driver.findElement(By.id("password"))
              .sendKeys("Password123");

        driver.findElement(By.id("registerBtn")).click();

        Assert.assertTrue(
            driver.getPageSource().contains("Account created successfully"),
            "TC01 Failed: Success message was not displayed"
        );

        System.out.println("TC01 Passed: Valid Registration");
    }

    // TC02 - Mandatory Field Validation
    @Test
    public void mandatoryFieldValidation() {

        driver.findElement(By.id("registerBtn")).click();

        Assert.assertTrue(
            driver.getPageSource().contains("Required"),
            "TC02 Failed: Required field validation was not displayed"
        );

        System.out.println("TC02 Passed: Mandatory Field Validation");
    }

    // TC03 - Invalid Email Format
    @Test
    public void invalidEmailValidation() {

        driver.findElement(By.id("username"))
              .sendKeys("santhosh123");

        driver.findElement(By.id("email"))
              .sendKeys("santhoshgmail.com");

        driver.findElement(By.id("password"))
              .sendKeys("Password123");

        driver.findElement(By.id("registerBtn")).click();

        Assert.assertTrue(
            driver.getPageSource().contains("Invalid email"),
            "TC03 Failed: Invalid email message was not displayed"
        );

        System.out.println("TC03 Passed: Invalid Email Validation");
    }

    // TC04 - Password Length Validation
    @Test
    public void passwordLengthValidation() {

        driver.findElement(By.id("username"))
              .sendKeys("santhosh123");

        driver.findElement(By.id("email"))
              .sendKeys("santhosh456@gmail.com");

        driver.findElement(By.id("password"))
              .sendKeys("Pass12");

        driver.findElement(By.id("registerBtn")).click();

        Assert.assertTrue(
            driver.getPageSource()
                  .contains("Password must be at least 8 characters"),
            "TC04 Failed: Password length validation was not displayed"
        );

        System.out.println("TC04 Passed: Password Length Validation");
    }

    // TC05 - Duplicate Email Registration
    @Test
    public void duplicateEmailValidation() {

        driver.findElement(By.id("username"))
              .sendKeys("santhosh123");

        driver.findElement(By.id("email"))
              .sendKeys("existing@gmail.com");

        driver.findElement(By.id("password"))
              .sendKeys("Password123");

        driver.findElement(By.id("registerBtn")).click();

        Assert.assertTrue(
            driver.getPageSource().contains("User already exists"),
            "TC05 Failed: Duplicate user validation was not displayed"
        );

        System.out.println("TC05 Passed: Duplicate Email Validation");
    }

    // Close browser after all tests
    @AfterClass(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}