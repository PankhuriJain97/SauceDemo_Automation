package com.SauceDemo.tests;

import com.SauceDemo.DataProvider.dataProvider;
import com.SauceDemo.baseTest.CommonToAllTest;
import com.SauceDemo.pages.ProductPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class T2_ProductTest extends CommonToAllTest {

    private ProductPage productPage;

    @BeforeMethod
    public void before_test() throws InterruptedException {
        init_login();

         productPage = new ProductPage(getDriver());
    }

    @Test(description = "verify product page is displayed")
    public void verify_pageDisplay()
    {
        Assert.assertTrue(productPage.isProductPageDisplayed(),"product page loading failed");
    }

    @Test (description = "Verify Product count")
    public void verify_product_count()
    {
       int count = productPage.getProductcount();
       Assert.assertEquals(count,6);
    }

    @Test(dataProvider = "productData", dataProviderClass = dataProvider.class,description = "Verify product names")
    public void verify_product_name(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {

        Assert.assertTrue(productPage.isProductDisplayed(ProductName), "product not found");

    }

    @Test(dataProvider =  "productData", dataProviderClass = dataProvider.class,
            description = "verify prices", dependsOnMethods = {"product_name"})

    public void verify_product_price(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {

        Assert.assertTrue(productPage.isPriceMatch(ProductName,ExpectedPrice), "Price mismatch");
    }

    @Test(description = "verify A-Z sorting")
    public void verify_product_sort_atoz()
    {
        List<WebElement> list = productPage.sorted_list("Name (A to Z)");

        List<String> actual_list = new ArrayList<>();

        for(WebElement ele : list)
        {
            actual_list.add(ele.getText());
        }

        List<String> expected_list = actual_list;
        Collections.sort(expected_list);

        System.out.println(actual_list);
        System.out.println(expected_list);

        Assert.assertEquals(actual_list,expected_list);

    }

    @Test(description = "verify Z-A sorting")
    public void verify_product_sort_ztoa()
    {
        List<WebElement> list = productPage.sorted_list("Name (Z to A)");

        List<String> actual_list = new ArrayList<>();

        for(WebElement ele : list)
        {
            actual_list.add(ele.getText());
        }

        List<String> expected_list = actual_list;
        Collections.sort(expected_list,Collections.reverseOrder());

        System.out.println(actual_list);
        System.out.println(expected_list);

        Assert.assertEquals(actual_list,expected_list);

    }

    @Test(description = "verify Price (low to high) sorting")
    public void verify_price_sort_LtoH()
    {
        List<WebElement> list = productPage.sorted_list("Price (low to high)");

        List<Double> actual_list = new ArrayList<>();

        for(WebElement ele : list)
        {
            actual_list.add(Double.parseDouble(ele.getText().replaceAll("[^\\d.]","")));
        }

        List<Double> expected_list = actual_list;
        Collections.sort(expected_list);

        System.out.println(actual_list);
        System.out.println(expected_list);

        Assert.assertEquals(actual_list,expected_list);

    }

    @Test(description = "verify Price (high to low) sorting")
    public void verify_price_sort_HtoL()
    {
        List<WebElement> list = productPage.sorted_list("Price (high to low)");

        List<Double> actual_list = new ArrayList<>();

        for(WebElement ele : list)
        {
            actual_list.add(Double.parseDouble(ele.getText().replaceAll("[^\\d.]","")));
        }

        List<Double> expected_list = actual_list;
        Collections.sort(expected_list,Collections.reverseOrder());

        System.out.println(actual_list);
        System.out.println(expected_list);

        Assert.assertEquals(actual_list,expected_list);

    }

    @Test(dataProvider = "productData", dataProviderClass = dataProvider.class,
            description = "Verify product description" )
    public void verify_description(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {
        String actual_desc = productPage.getDescription(ProductName);

        Assert.assertEquals(actual_desc,ExpectedDescription);

    }

    @Test(dataProvider = "productData",dataProviderClass = dataProvider.class,
            description = "Verify product addition tp cart" )
    public void verify_prod_add(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {
        productPage.add_product(ProductName);


        Assert.assertEquals(productPage.getCartNum(),"1");

    }

    @Test(dataProvider = "productData",dataProviderClass = dataProvider.class,
            description = "Verify product removal" )
    public void verify_prod_remove(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {
        productPage.add_product(ProductName);
        productPage.remove_product(ProductName);

        Assert.assertEquals(productPage.getCartNum(),"");

    }

}
