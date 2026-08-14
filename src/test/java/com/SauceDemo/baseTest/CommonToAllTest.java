package com.SauceDemo.baseTest;

import com.SauceDemo.driver.DriverManager;
import com.SauceDemo.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class CommonToAllTest {

    @BeforeMethod
    public void setUp()
    {
        DriverManager.init();
    }

    public void init_login() throws InterruptedException {
        LoginPage p = new LoginPage(getDriver());
        p.login("standard_user","secret_sauce");
    }

    @AfterMethod
    public void tearDown()
    {
        DriverManager.down();
    }
}
