import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class E2ECheckoutFlow {
    WebDriverWait wait;

    @Test
    public void run() {
        try {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String url = "https://ecommerce.artoftesting.com/";
            int numberOfProductToAdd = 5;

            driver.get(url);
            Thread.sleep(500);

            WebElement loginBtn = waitTillVisibilityOfElement("//button[text()='Login']");
            click(loginBtn);

            for (int i = 1; i <= numberOfProductToAdd; i++) {
                WebElement addToCartBtn = waitTillVisibilityOfElement("(//button[text()='Add To Cart'])[" + i + "]");
                click(addToCartBtn);
            }

            WebElement cartBtn = waitTillVisibilityOfElement("//div[@class='Header_detailCart__SrHWm']");
            click(cartBtn);

            WebElement checkoutBtn = waitTillVisibilityOfElement("//button[text()='Checkout']");
            click(checkoutBtn);

            WebElement thankText = waitTillVisibilityOfElement("//span[text()='Thank you For Shopping With Us']");

            driver.quit();

            System.out.println("Test Passed");
        } catch (Exception | AssertionError e) {
            System.out.println("Test Failed");
            Assert.fail();
        }
    }

    public void click(WebElement we) throws InterruptedException {
        we.click();
        Thread.sleep(500);
    }

    public WebElement waitTillVisibilityOfElement(String xpath) throws InterruptedException {
        Thread.sleep(500);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
}