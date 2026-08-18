package com.SauceDemo.pages;

import com.SauceDemo.base.CommonToAll;
import com.SauceDemo.utils.WaitHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class LoginPage extends CommonToAll {

    WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By LoginButton = By.id("login-button");
    private By error_msg = By.xpath("//h3[@data-test= 'error']");

    public void login(String username_val, String password_val) throws InterruptedException {
        openURL();

        enterText(username, username_val);
        enterText(password, password_val);
        Clickbutton(LoginButton);
        Thread.sleep(5000);

    }

    public String getErrorMsg()
    {
        return getErrorMessage(error_msg);
    }

    public boolean isLoginPageDisplayed()
    {
        WaitHelpers.checkVisibility(getDriver(),username);
        return driver.findElement(username).isDisplayed()
                && driver.findElement(password).isDisplayed();

    }

}
