package com.SauceDemo.tests;

import com.SauceDemo.DataProvider.dataProvider;
import com.SauceDemo.baseTest.CommonToAllTest;
import com.SauceDemo.pages.ProductPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class ProductTest extends CommonToAllTest {

    private ProductPage productPage;

    @BeforeMethod
    public void before_test() throws InterruptedException {
        init_login();

         productPage = new ProductPage(getDriver());
    }

    @Test(description = "verify product page is displayed")
    public void pageDisplay()
    {
        Assert.assertTrue(productPage.isProductPageDisplayed(),"product page loading failed");
    }

    @Test (description = "Verify Product count")
    public void product_count()
    {
       int count = productPage.getProductcount();
       Assert.assertEquals(count,6);
    }

    @Test(dataProvider = "productData", dataProviderClass = dataProvider.class,description = "Verify product names")
    public void product_name(String ProductName, String expectedPrice)
    {

        Assert.assertTrue(productPage.isProductDisplayed(ProductName), "product not found");

    }

}
