package Frameworks.pageObjects;

import Frameworks.AbstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class checkoutPage extends abstractComponent {
    WebDriver driver;
    public checkoutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy (css = "[placeholder='Select Country']")
    WebElement countryDropdown;
    @FindBy (css = ".ta-item:last-of-type")
    WebElement lastoptionCountry;
    @FindBy (css= ".action__submit")
    WebElement placeOrderButton;


    By countrySearchRes  = By.cssSelector(".ta-results");

    public void selectCountryOnCheckout(String country) {
        Actions a = new Actions(driver);
        a.sendKeys(countryDropdown,country).build().perform();
        waitForElementToAppear(countrySearchRes);
        lastoptionCountry.click();
    }

    public confirmationPage placeOrder() {
        placeOrderButton.click();
        return new confirmationPage(driver);
    }
}


