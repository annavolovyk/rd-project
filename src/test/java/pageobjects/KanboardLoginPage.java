package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class KanboardLoginPage {
    private WebDriver driver;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By errorMessageText = By.cssSelector("p.alert.alert-error");
    private final static int DURATION_TO_WAIT_DEFAULT = 15;


    public KanboardLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
    }

    public String getErrorNotification() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DURATION_TO_WAIT_DEFAULT));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageText));
        return errorElement.getText();
    }
}