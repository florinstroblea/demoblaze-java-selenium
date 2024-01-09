package Pages.ProductPage;

import Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    private final String ADD_TO_CHART_BUTTON_XPATH = "//a[@class='btn btn-success btn-lg']";
    private final String PRODUCT_IMAGE_XPATH = "//div[@class='item active']";

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToChartButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ADD_TO_CHART_BUTTON_XPATH))).click();
    }

    public void accept() {
        if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
            driver.switchTo().alert().accept();
        } else {
            System.out.println("ERROR: Alert is not present");
        }
    }
}
