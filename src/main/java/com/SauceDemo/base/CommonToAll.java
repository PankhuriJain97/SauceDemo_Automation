package com.SauceDemo.base;

import com.SauceDemo.utils.PropertiesReader;

import static com.SauceDemo.driver.DriverManager.getDriver;

public class CommonToAll {

    public void openURL()
    {
        getDriver().get(PropertiesReader.readKey("url"));
    }
}
