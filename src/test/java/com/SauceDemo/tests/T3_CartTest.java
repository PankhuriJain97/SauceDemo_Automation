package com.SauceDemo.tests;

import com.SauceDemo.DataProvider.dataProvider;
import com.SauceDemo.baseTest.CommonToAllTest;
import com.SauceDemo.pages.CartPage;
import com.SauceDemo.pages.CheckoutPage;
import com.SauceDemo.pages.ProductPage;
import com.SauceDemo.utils.WaitHelpers;
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
    private CheckoutPage checkoutPage;

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

    @Test(dataProvider = "productData", dataProviderClass = dataProvider.class,
               description = "verify product prices in cart")
    public void verify_prices(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {
        productPage.add_product(ProductName);
        productPage.go_to_cart();

        Assert.assertTrue(cartPage.isPriceMatch(ProductName,ExpectedPrice),"price mismatch");
    }

    @Test(dataProvider = "productData", dataProviderClass = dataProvider.class,
            description = "verify remove product functionality")
    public void verify_remove_product(String ProductName, String ExpectedPrice, String ExpectedDescription)
    {
        List<WebElement> prods = productPage.getAllProducts();
        List<String> prod_names = new ArrayList<>();

        for(WebElement ele: prods)
        {
            prod_names.add(ele.getText());
            productPage.add_product(ele.getText());
        }

        productPage.go_to_cart();
        cartPage.remove_prod(ProductName);

        Assert.assertFalse(cartPage.is_item_present(ProductName),"unable to remove");
        Assert.assertEquals(Integer.parseInt(productPage.getCartNum()),prod_names.size()-1);

    }

    @Test(description = "verify continue button")
    public void verify_cont_btn()
    {
      productPage.go_to_cart();
      cartPage.continue_shopping();

      Assert.assertTrue(productPage.isProductPageDisplayed());
    }

    @Test(description = "verify checkout button")
    public void verify_checkout_btn()
    {
        productPage.go_to_cart();
        cartPage.go_to_checkout();

        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed());
    }
}
