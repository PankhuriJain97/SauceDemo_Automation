package com.SauceDemo.tests;

import com.SauceDemo.DataProvider.dataProvider;
import com.SauceDemo.baseTest.CommonToAllTest;
import com.SauceDemo.pages.CartPage;
import com.SauceDemo.pages.CheckoutPage;
import com.SauceDemo.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class T4_CheckoutTest extends CommonToAllTest {

    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void before_test() throws InterruptedException {
        init_login();

        productPage = new ProductPage(getDriver());
        cartPage = new CartPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());
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


}
