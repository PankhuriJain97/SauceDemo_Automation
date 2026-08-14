package com.SauceDemo.pages;

import com.SauceDemo.base.CommonToAll;
import com.SauceDemo.utils.WaitHelpers;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class ProductPage extends CommonToAll {

    WebDriver driver;

    public ProductPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private By ham_burg_menu = By.id("react-burger-menu-btn");
    private By log_out_link = By.id("logout_sidebar_link");
    private By title = By.xpath("//div[@class = 'app_logo']");
    private By title_2 = By.xpath("//span[@data-test = 'title']");
    private By inventory_item = By.xpath("//div[@data-test = 'inventory-item']");
    private By item_name = By.xpath("//div[@data-test = 'inventory-item-name']");

    public void logout()
    {
        Clickbutton(ham_burg_menu);
        Clickbutton(log_out_link);
    }

    public boolean isProductPageDisplayed()
    {
        return WaitHelpers.presenceOfElement(getDriver(),title).getText().equals("Swag Labs")
                && WaitHelpers.presenceOfElement(getDriver(),title_2).getText().equals("Products");
    }

    public int getProductcount()
    {
        List<WebElement> number = item_list(inventory_item);

        return number.size();
    }

//    public List<WebElement> getAllProductsName()
//    {
//        List<WebElement> prod_names = item_list(item_name);
//        return prod_names;
//    }

    public boolean isProductDisplayed(String productName)
    {
        List<WebElement> prod_names = item_list(item_name);

        for(WebElement ele : prod_names)
        {
            if(ele.getText().equals(productName))
            {
                return true;
            }
        }

        return false;
    }
}
