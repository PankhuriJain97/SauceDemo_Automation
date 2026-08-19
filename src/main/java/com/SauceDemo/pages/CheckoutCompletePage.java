package com.SauceDemo.pages;

import com.SauceDemo.base.CommonToAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class CheckoutCompletePage extends CommonToAll {

    WebDriver driver;

    public CheckoutCompletePage(WebDriver driver)
    {
        this.driver = driver;
    }

    private By title = By.className("title");
    private By thankyou_text = By.className("complete-header");
    private By dispatch_text = By.className("complete-text");
    private By tick_icon = By.className("pony_express");
    private By back_btn = By.id("back-to-products");


    public boolean isCheckoutCompleteDisplayed()
    {
        return get_text(title).equals("Checkout: Complete!");
    }

    public String get_thankyou_msg()
    {
        return get_text(thankyou_text);
    }

    public String get_dispatch_msg()
    {
        return get_text(dispatch_text);
    }

    public boolean isTickIconDisplayed()
    {
        return getDriver().findElement(tick_icon).isDisplayed();
    }

    public void back_home()
    {
        Clickbutton(back_btn);
    }



}
