package com.SauceDemo.tests;

import com.SauceDemo.DataProvider.dataProvider;
import com.SauceDemo.baseTest.CommonToAllTest;
import com.SauceDemo.pages.CartPage;
import com.SauceDemo.pages.CheckoutCompletePage;
import com.SauceDemo.pages.CheckoutPage;
import com.SauceDemo.pages.ProductPage;
import com.SauceDemo.utils.PropertiesReader;
import com.SauceDemo.utils.WaitHelpers;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class T4_CheckoutTest extends CommonToAllTest {

    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutCompletePage checkoutCompletePage;

    @BeforeMethod
    public void before_test() throws InterruptedException {
        init_login();

        productPage = new ProductPage(getDriver());
        cartPage = new CartPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());
        checkoutCompletePage = new CheckoutCompletePage(getDriver());
    }

    @Test(description = "verify mandatory fields", dataProvider = "checkoutData",
            dataProviderClass = dataProvider.class)
    public void verify_mandatory_fields(String FirstName, String LastName, String ZipCode, String ExpectedError)
    {
        productPage.go_to_cart();
        cartPage.go_to_checkout();
        checkoutPage.enter_info(FirstName,LastName,ZipCode);

        Assert.assertEquals(checkoutPage.get_error_msg(),ExpectedError);
    }

    @Test(description = "verify Valid Data ", dataProvider = "validcheckoutData",
            dataProviderClass = dataProvider.class)
    public void verify_valid_data(String FirstName, String LastName, String ZipCode)
    {
        productPage.go_to_cart();
        cartPage.go_to_checkout();
        checkoutPage.enter_info(FirstName,LastName,ZipCode);

        Assert.assertTrue(checkoutPage.isCheckoutOverviewDisplayed(),"failed");
    }

    @Test(description = "verify added products are visible on checkout overview page",
            dataProvider = "productData",dataProviderClass = dataProvider.class)
    public void verify_added_products(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {
        productPage.add_product(ProductName);
        productPage.go_to_cart();
        cartPage.go_to_checkout();
        checkoutPage.enter_info(PropertiesReader.readKey("firstname"),PropertiesReader.readKey("lastname"),PropertiesReader.readKey("zipcode"));

        Assert.assertTrue(checkoutPage.is_item_present(ProductName));
    }


    @Test(description = "verify total price is matching sum of product prices")
    public void verify_total_price()
    {
        List<WebElement> prods = productPage.getAllProducts();
        List<WebElement> prices = productPage.getAllPrices();

        double total = 0;

        for(WebElement ele: prods)
        {
            productPage.add_product(ele.getText());
        }

        for (WebElement ele : prices)
        {
            total = total + Double.parseDouble(ele.getText().replaceAll("[^\\d.]",""));
        }

        productPage.go_to_cart();
        cartPage.go_to_checkout();
        checkoutPage.enter_info(PropertiesReader.readKey("firstname"),PropertiesReader.readKey("lastname"),
                PropertiesReader.readKey("zipcode"));
        Assert.assertEquals(checkoutPage.get_total_price(),total);

    }

    @Test(description = "verify order completion",
            dataProvider = "productData",dataProviderClass = dataProvider.class)
    public void verify_order_completion(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {
        productPage.add_product(ProductName);
        productPage.go_to_cart();
        cartPage.go_to_checkout();
        checkoutPage.enter_info(PropertiesReader.readKey("firstname"),PropertiesReader.readKey("lastname"),PropertiesReader.readKey("zipcode"));
        checkoutPage.finish();

        Assert.assertTrue(checkoutCompletePage.isCheckoutCompleteDisplayed());
    }



}
