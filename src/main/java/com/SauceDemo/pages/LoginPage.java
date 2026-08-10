package com.SauceDemo.pages;

import com.SauceDemo.base.CommonToAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

        enterUsername(username, username_val);
        enterPassword(password, password_val);
        Clickbutton(LoginButton);

    }

    public String getErrorMsg()
    {
        return getErrorMessage(error_msg);
    }

}
