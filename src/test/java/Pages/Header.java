package Pages;

import Pages.CartPage.CartPage;
import Pages.LandingPage.LandingPage;
import Tests.Browser;
import org.openqa.selenium.By;

public class Header extends Browser {
    private final String CART_LINK_ID = "cartur";
    private final String HOMEPAGE_LINK_ID = "//a[@href='index.html']";
    private final String LOGIN_LINK_ID = "login2";

    public CartPage clickCartLink() {
        getDriver().findElement(By.id(CART_LINK_ID)).click();
        return new CartPage(getDriver());
    }

    public LandingPage clickHomeLink() {
        getDriver().findElement(By.xpath(HOMEPAGE_LINK_ID)).click();
        return new LandingPage(getDriver());
    }

    public LandingPage login() {
        getDriver().findElement(By.id(LOGIN_LINK_ID)).click();
        getDriver().findElement(By.id("loginusername")).sendKeys("test");
        getDriver().findElement(By.id("loginpassword")).sendKeys("test");
        getDriver().findElement(By.xpath("//button[contains(text(), 'Log in')]")).click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return new LandingPage(getDriver());
    }

    public LandingPage logout() {
        getDriver().findElement(By.id("logout2")).click();
        return new LandingPage(getDriver());
    }

    public String getUsername() {
        return getDriver().findElement(By.id("nameofuser")).getText();
    }
}
