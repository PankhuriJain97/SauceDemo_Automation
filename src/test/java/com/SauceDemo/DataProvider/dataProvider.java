package com.SauceDemo.DataProvider;

import com.SauceDemo.utilExcels.UtilExcel;
import org.testng.annotations.DataProvider;

public class dataProvider {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return UtilExcel.getTestData("LoginData.xlsx", "Valid_data");
    }

    @DataProvider(name = "invalidloginData")
    public Object[][] invalidloginData() {
        return UtilExcel.getTestData("InvalidLoginData.xlsx", "Invalid_data");
    }

    @DataProvider(name = "productData")
    public Object[][] productData() {
        return UtilExcel.getTestData("ProductData.xlsx", "Products");
    }

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        return UtilExcel.getTestData("CheckoutData.xlsx", "InvalidData");
    }

    @DataProvider(name = "validcheckoutData")
    public Object[][] validcheckoutData() {
        return UtilExcel.getTestData("CheckoutData.xlsx", "ValidData");
    }

}


