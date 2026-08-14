package com.SauceDemo.base;

import com.SauceDemo.utils.PropertiesReader;
import com.SauceDemo.utils.WaitHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class CommonToAll {

    public void openURL()
    {
        getDriver().get(PropertiesReader.readKey("url"));
    }

    public void enterUsername(By by, String username_val)
    {
        getDriver().findElement(by).sendKeys(username_val);
    }

    public void enterPassword(By by, String password_val)
    {
        getDriver().findElement(by).sendKeys(password_val);
    }
    public void Clickbutton(By by)
    {
        WaitHelpers.checkVisibility(getDriver(),by);
        getDriver().findElement(by).click();
    }

    public String getErrorMessage(By by)
    {
        WaitHelpers.checkVisibility(getDriver(), by);
        return getDriver().findElement(by).getText();
    }

    public List<WebElement> item_list(By by)
    {
        return getDriver().findElements(by);
    }
}
