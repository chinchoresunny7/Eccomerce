package Frameworks.Steps;

import Frameworks.pageObjects.*;
import Frameworks.testComponents.baseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitions extends baseTest {
    public landingPage l;
    public productCatologue p;
    public cartPage c;
    public checkoutPage co;
    public confirmationPage con;

    @Given("^Launch a ecommerce website$")
    public void Launch_ecommerce_website() throws IOException {
        initializeDriver();
        l = new landingPage(driver);
        l.goTo();
    }

    @Given ("^Logged in with username (.+) and Password (.+)$")
    public void login_with_username_and_password(String username,String password){
        p = l.login(username,password);

    }

    @When("^I add the (.+) to cart$")
    public void add_product(String Product)throws InterruptedException
    {
        p.selectProductbyName(Product);
    }

    @When("^I checkout (.+) and submit order$")
    public void submit_order(String Product){
        c =  p.gotoCart();
        boolean match = c.isProductAdded(Product);
        Assert.assertTrue(match);
        co= c.gotoCheckout();
        co.selectCountryOnCheckout("india");
        con  = co.placeOrder();
    }
    @Then("Verify {string} message is displayed on confirmation page")
    public void Verify_confirmation_message(String string){
        String message = con.getConfirmationMessage();
        Assert.assertTrue (message.equalsIgnoreCase(string));
        driver.close();
    }

    @Then ("Verify Error message is displayed")
    public void verify_error_message(){
        String message = l.getErrorMessage();
        Assert.assertTrue(message.equalsIgnoreCase("Incorrect email or password."));
    }


}
