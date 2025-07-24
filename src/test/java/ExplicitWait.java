import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWait {
    public void webDriverWait() {
        WebDriver driver = new ChromeDriver();

        // Using WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Waiting for an element with button tag
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button")));


        // Using FluentWait
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(15L))
                .pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.elementToBeClickable(By.id("button")));
    }
}
