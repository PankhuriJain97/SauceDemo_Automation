package com.SauceDemo.tests;


import com.SauceDemo.DataProvider.dataProvider;
import com.SauceDemo.baseTest.CommonToAllTest;
import com.SauceDemo.driver.DriverManager;
import com.SauceDemo.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends CommonToAllTest {

    @Test(dataProvider = "loginData", dataProviderClass = dataProvider.class)
    public void valid_login(String username, String password)
    {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    }
}
