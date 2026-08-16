package com.SauceDemo.base;

import com.SauceDemo.utils.PropertiesReader;
import com.SauceDemo.utils.WaitHelpers;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.SauceDemo.driver.DriverManager.driver;
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
        WaitHelpers.checkVisibility(getDriver(),by);
        return getDriver().findElements(by);
    }

    public void sorting_dropdown(By by, String option)
    {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(option);
    }

    public String get_text(By by)
    {
        WaitHelpers.checkVisibility(getDriver(), by);
        return getDriver().findElement(by).getText();
    }

    public void password_alert()
    {
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }
}
