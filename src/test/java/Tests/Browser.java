package Tests;

import Pages.LandingPage.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class Browser {
    private static WebDriver driver;
    public static WebDriver getDriver() {return driver;}

    @BeforeClass(alwaysRun = true)
    public void browserInitialization() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass
    public void browserTearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    public LandingPage goToLandingPage() {
        driver.navigate().to(Utilities.Links.BASE_URL.getName());
        return new LandingPage(driver);
    }


}
