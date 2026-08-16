package com.SauceDemo.tests;

import com.SauceDemo.DataProvider.dataProvider;
import com.SauceDemo.baseTest.CommonToAllTest;
import com.SauceDemo.pages.CartPage;
import com.SauceDemo.pages.ProductPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class T3_CartTest extends CommonToAllTest {

    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void before_test() throws InterruptedException {
        init_login();

        productPage = new ProductPage(getDriver());
        cartPage = new CartPage(getDriver());
    }

    @Test(description = "Verify cart page is displayed")
    public void verify_cart_page()
    {
        productPage.go_to_cart();
        Assert.assertEquals(cartPage.isCartPageDisplayed(),"Your Cart");
    }

    @Test(dataProvider = "productData", dataProviderClass = dataProvider.class, description = "verify product in cart")
    public void verify_prod_cart(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {
        productPage.add_product(ProductName);
        productPage.go_to_cart();

        Assert.assertTrue(cartPage.is_item_present(ProductName),"product not found");

    }

    @Test(description = "verify multiple products in cart")
    public void verify_mult_prod()
    {
        List<WebElement> prods = productPage.getAllProducts();
        List<String> prod_names = new ArrayList<>();

        for(WebElement ele: prods)
        {
            prod_names.add(ele.getText());
            productPage.add_product(ele.getText());
        }

        productPage.go_to_cart();

        for(String ele: prod_names)
        {
            Assert.assertTrue(cartPage.is_item_present(ele),"product not found");
        }
    }
}
