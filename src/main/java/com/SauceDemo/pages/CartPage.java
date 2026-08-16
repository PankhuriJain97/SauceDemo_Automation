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
}
