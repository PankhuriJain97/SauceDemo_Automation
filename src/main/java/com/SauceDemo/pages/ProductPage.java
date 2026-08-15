package com.SauceDemo.pages;

import com.SauceDemo.base.CommonToAll;
import com.SauceDemo.utils.WaitHelpers;
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
    private By item_price = By.xpath("//div[@data-test = 'inventory-item-price']");
    private By sort_dropdown = By.className("product_sort_container");
    private By prod_description = By.xpath("//div[@data-test = 'inventory-item-desc']");


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

    public List<WebElement> getAllProducts()
    {
        return item_list(item_name);
    }

    public List<WebElement> getAllPrices()
    {
        return item_list(item_price);
    }

    public boolean isProductDisplayed(String productName)
    {
        List<WebElement> prod_names = getAllProducts();

        for(WebElement ele : prod_names)
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
        List<WebElement> prod_names = getAllProducts();
        List<WebElement> prices = getAllPrices();

        int flag = 0;

        for(int i = 0; i<prod_names.size(); i++)
        {

            if(prod_names.get(i).getText().equals(productName))
            {
                break;
            }
            flag= flag+1;
        }

        if(prices.get(flag).getText().equals(price))
        {
            return true;
        }

        return false;
    }

    public List<WebElement> sorted_list(String option)
    {
        sorting_dropdown(sort_dropdown, option);

        List<WebElement> prod_list = getAllProducts();
        List<WebElement> price_list = getAllPrices();

        if(option.equals("Name (A to Z)") || option.equals("Name (Z to A)"))
        {
            return prod_list;
        }

        return price_list;
    }

    public String getDescription(String productName)
    {
        List<WebElement> prod_names = getAllProducts();
        List<WebElement> prod_desc = item_list(prod_description);

        for(int i = 0; i<prod_names.size(); i++)
        {

            if(prod_names.get(i).getText().equals(productName))
            {
                return prod_desc.get(i).getText();
            }

        }

        return "product/description not found";
    }
}
