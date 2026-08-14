package com.SauceDemo.tests;


import com.SauceDemo.DataProvider.dataProvider;
import com.SauceDemo.baseTest.CommonToAllTest;
import com.SauceDemo.driver.DriverManager;
import com.SauceDemo.pages.LoginPage;
import com.SauceDemo.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class LoginTest extends CommonToAllTest {

    @Test(dataProvider = "loginData", dataProviderClass = dataProvider.class)
    public void valid_login(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());

        loginPage.login(username,password);

       Assert.assertTrue(productPage.isProductPageDisplayed(),"Login failed");

    }

    @Test(dataProvider = "invalidloginData", dataProviderClass = dataProvider.class)
    public void invalid_login(String username, String password, String Expected_error_msg) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.login(username,password);

        Assert.assertEquals(loginPage.getErrorMsg(), Expected_error_msg);
    }

    @Test
    public void logout() throws InterruptedException {
        init_login();

        LoginPage loginPage = new LoginPage(getDriver());

        ProductPage productPage = new ProductPage(getDriver());

        productPage.logout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Logout unsuccessful");

    }


}
