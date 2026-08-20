package com.SauceDemo.tests;

import com.SauceDemo.DataProvider.dataProvider;
import com.SauceDemo.baseTest.CommonToAllTest;
import com.SauceDemo.pages.CartPage;
import com.SauceDemo.pages.CheckoutCompletePage;
import com.SauceDemo.pages.CheckoutPage;
import com.SauceDemo.pages.ProductPage;
import com.SauceDemo.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class T5_CheckoutCompleteTest extends CommonToAllTest {
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


    @Test(description = "verify checkout complete page is displayed",
            dataProvider = "productData",dataProviderClass = dataProvider.class)
    public void verify_page_display(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {
        productPage.add_product(ProductName);
        productPage.go_to_cart();
        cartPage.go_to_checkout();
        checkoutPage.enter_info(PropertiesReader.readKey("firstname"),PropertiesReader.readKey("lastname"),PropertiesReader.readKey("zipcode"));
        checkoutPage.finish();

        Assert.assertTrue(checkoutCompletePage.isCheckoutCompleteDisplayed());
    }

    @Test(description = "verify thankyou message is correct",
            dataProvider = "productData",dataProviderClass = dataProvider.class)
    public void verify_thankyou_msg(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {
        productPage.add_product(ProductName);
        productPage.go_to_cart();
        cartPage.go_to_checkout();
        checkoutPage.enter_info(PropertiesReader.readKey("firstname"),PropertiesReader.readKey("lastname"),PropertiesReader.readKey("zipcode"));
        checkoutPage.finish();

        Assert.assertEquals(checkoutCompletePage.get_thankyou_msg(),"Thank you for your order!");
    }

    @Test(description = "verify dispatch msg is correct",
            dataProvider = "productData",dataProviderClass = dataProvider.class)
    public void verify_dispatch_msg(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {
        productPage.add_product(ProductName);
        productPage.go_to_cart();
        cartPage.go_to_checkout();
        checkoutPage.enter_info(PropertiesReader.readKey("firstname"),PropertiesReader.readKey("lastname"),PropertiesReader.readKey("zipcode"));
        checkoutPage.finish();

        Assert.assertEquals(checkoutCompletePage.get_dispatch_msg(),"Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    @Test(description = "verify tick icon is displayed")
    public void verify_tick_icon()
    {
        productPage.go_to_cart();
        cartPage.go_to_checkout();
        checkoutPage.enter_info(PropertiesReader.readKey("firstname"),PropertiesReader.readKey("lastname"),PropertiesReader.readKey("zipcode"));
        checkoutPage.finish();

        Assert.assertTrue(checkoutCompletePage.isTickIconDisplayed());
    }

    @Test(description = "verify back button takes to product page")
    public void verify_back_button()
    {
        productPage.go_to_cart();
        cartPage.go_to_checkout();
        checkoutPage.enter_info(PropertiesReader.readKey("firstname"),PropertiesReader.readKey("lastname"),PropertiesReader.readKey("zipcode"));
        checkoutPage.finish();
        checkoutCompletePage.back_home();

        Assert.assertTrue(productPage.isProductPageDisplayed());
    }







}
