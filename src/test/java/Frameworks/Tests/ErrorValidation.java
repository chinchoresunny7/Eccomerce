package Frameworks.Tests;

import Frameworks.pageObjects.landingPage;
import Frameworks.pageObjects.productCatologue;
import Frameworks.testComponents.baseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidation extends baseTest {

    @Test(dataProvider = "loginDetails1")
    public void loginErrorValidation(String username, String password) throws IOException {
        landingPage l = new landingPage(driver);
        l.goTo();
        l.login(username, password);
        String message = l.getErrorMessage();
        Assert.assertTrue(message.equalsIgnoreCase("Incorrect email or password."));
        System.out.println(System.getProperty("user.dir"));
    }

    @DataProvider(name = "loginDetails1")
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {"Punu@xyz.com", "Abc@123"},
                {"Punu@xyz.co", "Abc@123"},
                {"Pnu@xyz.com", "bc@1234"}
        };
    }
}
