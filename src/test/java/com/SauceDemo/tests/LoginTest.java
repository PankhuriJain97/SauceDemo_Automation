package com.SauceDemo.tests;


import com.SauceDemo.DataProvider.dataProvider;
import com.SauceDemo.baseTest.CommonToAllTest;
import com.SauceDemo.driver.DriverManager;
import com.SauceDemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends CommonToAllTest {

    @Test(dataProvider = "loginData", dataProviderClass = dataProvider.class)
    public void valid_login(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        loginPage.login(username,password);

        Assert.assertEquals(DriverManager.getDriver().getCurrentUrl(),"https://www.saucedemo.com/inventory.html");

    }


}
