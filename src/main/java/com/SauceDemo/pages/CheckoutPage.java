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
    private By last_name = By.id("last-name");
    private By zip_code = By.id("postal-code");
    private By continue_btn = By.id("continue");
    private By error_msg = By.xpath("//h3[@data-test = 'error']");
    private By price_total_heading = By.xpath("//div[@data-test = 'total-info-label']");

    public boolean isCheckoutPageDisplayed()
    {
        return get_text(title).equals("Checkout: Your Information")
                && driver.findElement(first_name).isDisplayed();
    }

    public boolean isCheckoutOverviewDisplayed()
    {
        return get_text(title).equals("Checkout: Overview")
                && get_text(price_total_heading).equals("Price Total");
    }

    public void enter_info(String firstname, String lastname, String zipcode)
    {
        enterText(first_name,firstname);
        enterText(last_name,lastname);
        enterText(zip_code,zipcode);
        Clickbutton(continue_btn);
    }

    public String get_error_msg()
    {
        return getErrorMessage(error_msg);
    }
}
