package uitestschrome;
import dataproviders.TestDataProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.KanboardLoginPage;
import io.restassured.response.Response;

import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;

public class KanboardLoginTests {
    private WebDriver driver;
    private final String BASE_URL = "http://localhost/login";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "positiveLoginTests", dataProviderClass = dataproviders.TestDataProvider.class)
    public void testLogin(String username, String password) {
        KanboardLoginPage loginPage = new KanboardLoginPage(driver);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        Assert.assertEquals(username, "annav", "This is the right username");

        driver.close();
        driver.quit();
    }

    @Test(dataProvider = "negativeLoginTests2", dataProviderClass = TestDataProvider.class)
    public void testInvalidUsername(String username, String password) {
        KanboardLoginPage loginPage = new KanboardLoginPage(driver);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        String errorMessage = loginPage.getErrorNotification();
        Assert.assertTrue(errorMessage.contains("Bad username or password"));
        driver.close();
        driver.quit();
    }

    @Test(dataProvider = "negativeLoginTests1", dataProviderClass = TestDataProvider.class)
    public void testInvalidCredentials(String username, String password) {
        KanboardLoginPage loginPage = new KanboardLoginPage(driver);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        String errorMessage = loginPage.getErrorNotification();
        Assert.assertTrue(errorMessage.contains("Bad username or password"));
        driver.close();
        driver.quit();
    }

}
