package Pages.CartPage;

import Pages.BasePage;
import Pages.LandingPage.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final String TOTAL_PRICE_ID = "totalp";
    private final String PRODUCT_ITEM_CLASS = "success";
    private final String PLACE_ORDER_BUTTON_CLASS = "btn-success";
    private final String NAME_ID = "name";
    private final String CARD_ID = "card";
    private final String PURCHASE_BUTTON_XPATH = "//button[starts-with(text(), 'Purchase')]";
    private final String CONFIRM_BUTTON_XPATH = "//button[@class='confirm btn btn-lg btn-primary']";
    private final String CONFIRMATION_MESSAGE_XPATH = "//h2[starts-with(text(), 'Thank you for your purchase!')]";


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getTotalPrice() {
        return Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(TOTAL_PRICE_ID))).getText());
    }

    public int calculateAllProductsPrice() {
        int totalPriceOfAllProducts = 0;
        List<WebElement> listOfAllProducts = driver.findElements(By.className(PRODUCT_ITEM_CLASS));
        if (!listOfAllProducts.isEmpty()) {
            for(WebElement product : listOfAllProducts) {
                int productPrice = Integer.parseInt(product.getText().split(" ")[3]);
                totalPriceOfAllProducts += productPrice;
            }
        }
        return totalPriceOfAllProducts;
    }

    public Boolean isProductListDisplayed() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(PRODUCT_ITEM_CLASS)));
    }

    public Boolean isPlaceOrderButtonDisabled() {
        return driver.findElement(By.className(PLACE_ORDER_BUTTON_CLASS)).isEnabled();
    }

    public List<String> getAllProducts() {
        List<String> listOfAllProducts = new ArrayList<>();
        List<WebElement> listOfItems = driver.findElements(By.className(PRODUCT_ITEM_CLASS));
        for(WebElement item : listOfItems) {
            listOfAllProducts.add(item.getText());
        }
        return listOfAllProducts;
    }

    public void clickPlaceOrderButton() {
        driver.findElement(By.className(PLACE_ORDER_BUTTON_CLASS)).click();
    }

    public void clickPurchaseButton() {
        driver.findElement(By.xpath(PURCHASE_BUTTON_XPATH)).click();
    }

    public void accept() {
        if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
            driver.switchTo().alert().accept();
        } else {
            System.out.println("ERROR: Alert is not present");
        }
    }

    public void placeOrder(String buyerName, String cardNumber){
        driver.findElement(By.id(NAME_ID)).sendKeys(buyerName);
        driver.findElement(By.id(CARD_ID)).sendKeys(cardNumber);
        clickPurchaseButton();
    }

    public LandingPage confirmOrder() {
        driver.findElement(By.xpath(CONFIRM_BUTTON_XPATH)).click();
        return new LandingPage(driver);
    }

    public String getConfirmationMessage(){
        return driver.findElement(By.xpath(CONFIRMATION_MESSAGE_XPATH)).getText();
    }

    public String getTextFromAlert(){
        return driver.switchTo().alert().getText();
    }
}
