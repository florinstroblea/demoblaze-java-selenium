package Tests.Flows;

import Pages.CartPage.CartPage;
import Pages.Header;
import Pages.LandingPage.LandingPage;
import Pages.ProductPage.ProductPage;
import Tests.Browser;
import Utilities.CardItems;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class TestingFlows extends Browser {
    private static LandingPage landingPage;
    private static ProductPage productPage;
    private static CartPage cartPage;
    private static Header header;

    @BeforeMethod
    public void setup(){
        landingPage = goToLandingPage();
        header = new Header();
    }

    @Test
    public void checkIfTheLandingPageLoadsCorrectly() {
        assertTrue(landingPage.getProductList() > 1,
                "ERROR: The product list is not correctly loaded.");
    }

    @Test
    public void checkIfCartIsEmpty() {
        cartPage = header.clickCartLink();
        assertTrue(cartPage.isProductListDisplayed(), "ERROR: The shopping cart is not empty.");
    }

    @Test
    public void checkIfPlaceOrderButtonIsDisabledForNoOrdersPlaced() {
        cartPage = header.clickCartLink();
        // The button should be disabled if there are no items added to the shopping cart.
        assertFalse(cartPage.isPlaceOrderButtonDisabled(), "ERROR: The Place Order button is not disabled.");
    }

    @Test
    public void checkIfTheItemAddedToTheCartIsTheSameWithTheOneAdded() {
        addItemToCart();
        cartPage = header.clickCartLink();
        List<String> allProducts = cartPage.getAllProducts();
        assertTrue(allProducts.contains("Samsung galaxy s6 360 Delete"));
    }

    @Test
    public void checkIfTheOrderCannotBePlacedWithoutFillingTheMandatoryFields(){
        addItemToCart();
        cartPage = header.clickCartLink();
        cartPage.clickPlaceOrderButton();
        cartPage.clickPurchaseButton();
        assertTrue(cartPage.getTextFromAlert().contains("Name"));
        assertTrue(cartPage.getTextFromAlert().contains("Creditcard"));
    }

    @Test
    public void checkIfTheUserCanPlaceAnOrder(){
        addItemToCart();
        cartPage = header.clickCartLink();
        cartPage.clickPlaceOrderButton();
        cartPage.placeOrder("SOME_NAME", "1234");
        assertEquals(cartPage.getConfirmationMessage(), "Thank you for your purchase!",
                "ERROR: No success message was displayed.");
        cartPage.confirmOrder();
    }

    @Test
    public void checkIfTotalPriceIsCorrectlyCalculated() {
        addItemToCart();
        landingPage = header.clickHomeLink();
        addItemToCart();
        cartPage = header.clickCartLink();
        assertEquals(cartPage.getTotalPrice(), cartPage.calculateAllProductsPrice(),
                "ERROR: The Total Price doesn't match with the price of all of the added items.");
    }

    @Test
    public void checkIfTheUserNameFromWelcomeMessageIsTheSameWithAuthenticatedUser() {
        landingPage = header.login();
        assertEquals(header.getUsername().split("Welcome ")[1], "test", "ERROR: The user is logged with other account.");
    }

    @Test
    public void checkCartItemsPersistence() {
        landingPage = header.login();
        addItemToCart();
        landingPage = header.logout();
        landingPage = header.login();
        cartPage = header.clickCartLink();
        assertTrue(cartPage.getAllProducts().size() > 0, "ERROR: The Shopping Cart is empty.");

    }

    private void addItemToCart() {
        productPage = landingPage.clickOnDesiredCard(CardItems.SAMSUNG_GALAXY_PHONE.getName());
        productPage.clickAddToChartButton();
        productPage.accept();
    }
}
