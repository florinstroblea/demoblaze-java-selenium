package Pages.LandingPage;

import Pages.BasePage;
import Pages.ProductPage.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage extends BasePage {
    private final String CARD_TITLE_XPATH = "//a[@class='hrefch']";
    private final String PRODUCTS_LIST_ID = "tbodyid";
    private final String PRODUCT_CARD_XPATH = "//div[@class='card h-100']";

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage clickOnDesiredCard(String cardName) {
        List<WebElement> cardItemList = driver.findElements(By.xpath(CARD_TITLE_XPATH));
        for(WebElement cardItemElement : cardItemList) {
            String cardItemText = cardItemElement.getText();
            if(cardItemText.equals(cardName)) {
                scrollIntoElement(cardItemElement);
                moveToElement(cardItemElement);
                cardItemElement.click();
                break;
            }
        }
        return new ProductPage(driver);
    }

    public int getProductList() {
        List<WebElement> listOfAllProducts = driver.findElements(By.xpath(PRODUCT_CARD_XPATH));
        return listOfAllProducts.size();
    }
}
