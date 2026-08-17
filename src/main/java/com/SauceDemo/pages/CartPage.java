package com.SauceDemo.pages;

import com.SauceDemo.base.CommonToAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends CommonToAll {

    WebDriver driver;

    public CartPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private By cart_page = By.className("title");
    private By item_name = By.xpath("//div[@data-test = 'inventory-item-name']");
    private By item_price = By.xpath("//div[@data-test = 'inventory-item-price']");
    private By remove_bttn = By.xpath("//button[contains(@data-test, 'remove')]");
    private By continue_btn = By.id("continue-shopping");
    private By checkout_btn = By.id("checkout");

    public String isCartPageDisplayed()
    {
        return get_text(cart_page);
    }

    public boolean is_item_present(String productName)
    {

        List<WebElement> cart_items = item_list(item_name);

        for(WebElement ele : cart_items)
        {
            if(ele.getText().equals(productName))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isPriceMatch(String productName, String price)
    {
        List<WebElement> cart_items = item_list(item_name);
        List<WebElement> item_prices = item_list(item_price);

        int flag = 0;

        for(int i = 0; i<cart_items.size(); i++)
        {

            if(cart_items.get(i).getText().equals(productName))
            {
                break;
            }
            flag= flag+1;
        }

        if(item_prices.get(flag).getText().equals(price))
        {
            return true;
        }

        return false;
    }

    public void remove_prod(String productName)
    {
        List<WebElement> cart_items = item_list(item_name);
        List<WebElement> remove_btn = item_list(remove_bttn);

        for(int i = 0; i<cart_items.size(); i++)
        {
            if(cart_items.get(i).getText().equals(productName))
            {
                remove_btn.get(i).click();
            }
        }
    }

    public void continue_shopping()
    {
        Clickbutton(continue_btn);
    }

    public void go_to_checkout()
    {
        Clickbutton(checkout_btn);
    }
}
