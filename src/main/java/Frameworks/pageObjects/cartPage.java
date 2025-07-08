package Frameworks.pageObjects;

import Frameworks.AbstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class cartPage extends abstractComponent {
    WebDriver driver;
    public cartPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

By cartList =  By.cssSelector(".cartSection h3");

@FindBy (css = ".totalRow button")
WebElement CheckoutButton;

    public boolean isProductAdded(String prod) {
        List<WebElement> cp = driver.findElements(cartList);
       return (cp.stream().anyMatch(pp-> pp.getText().equalsIgnoreCase(prod)));
    }

    public checkoutPage gotoCheckout() {
        CheckoutButton.click();
        return new checkoutPage(driver);

    }
}
