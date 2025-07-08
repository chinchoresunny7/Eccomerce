package Frameworks.Tests;

import Frameworks.pageObjects.*;
import Frameworks.testComponents.baseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder extends baseTest {
    @Test(dataProvider = "loginDetails")
    public void submitOrder(HashMap<String,String> user){
        landingPage l = new landingPage(driver);
        l.goTo();
        productCatologue p = l.login(user.get("username"),user.get("password"));
        p.selectProductbyName(user.get("product"));
        cartPage c =  p.gotoCart();
        boolean match = c.isProductAdded(user.get("product"));
        Assert.assertTrue(match);
        checkoutPage co= c.gotoCheckout();
        co.selectCountryOnCheckout("india");
        confirmationPage con  = co.placeOrder();
        String message = con.getConfirmationMessage();
        Assert.assertTrue (message.equalsIgnoreCase( "Thankyou for the order."));
    }

    @DataProvider(name = "loginDetails")
    public Object[][] loginDataProvider() throws IOException {

//        HashMap <String,String> user1 = new HashMap<>();
//        HashMap <String,String> user2 = new HashMap<>();
//
//        user1.put("username","Punu@xyz.com");
//        user1.put("password","Abc@1234");
//        user1.put("product","ZARA COAT 3");
//        user2.put("username","sunnyd1998@gmail.com");
//        user2.put("password","Abc@1234");
//        user2.put("product","ADIDAS ORIGINAL");
        List<HashMap<String,String>> user = getJsonToMap(System.getProperty("user.dir") + "//src//test//java//Frameworks//Data//userdetails.json");
        return new Object[][]{
                {user.get(0)},{user.get(1)}
        };

    }
}
