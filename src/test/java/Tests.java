import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Tests {

    @org.testng.annotations.Test
    public void test01() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String url = "https://web.platform.kwibal.com/";
        driver.get(url);

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search for anything and everything']")));
        searchBox.sendKeys("Books");

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Search']")));
        searchButton.click();

        WebElement resultCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='button' and contains(@class, 'card')]")));
        Assert.assertNotNull(resultCard);

        // Waiting for "0 search results for" text to appear if it does not appear means it has results
        try {
            Thread.sleep(5000);
            WebElement zeroResultText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(., '0 search results for')]")));
            System.out.println("Test Case Failed : No Result Found");
            driver.quit();
            Assert.fail();
        } catch (Exception ignore) {
            driver.quit();
        }

        System.out.println("Test Case Passed");
    }
}