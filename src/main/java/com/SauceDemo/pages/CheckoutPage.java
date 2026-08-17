package com.SauceDemo.pages;

import com.SauceDemo.base.CommonToAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends CommonToAll {

    WebDriver driver;

    public CheckoutPage(WebDriver driver)
    {
        this.driver = driver;
    }


    private By title = By.className("title");
    private By first_name = By.id("first-name");

    public boolean isCheckoutPageDisplayed()
    {
        return get_text(title).equals("Checkout: Your Information")
                && driver.findElement(first_name).isDisplayed();
    }
}
