package Frameworks.pageObjects;

import Frameworks.AbstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landingPage extends abstractComponent {
    WebDriver driver;
    public landingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (id="userEmail")
    WebElement email;

    @FindBy (id="userPassword")
    WebElement Password;

    @FindBy (id="login")
    WebElement loginButton;

public productCatologue login(String mail, String Pass){

    email.sendKeys("Punu@xyz.com");
    Password.sendKeys(Pass);
    loginButton.click();
    productCatologue productcatologue = new productCatologue(driver);
    return productcatologue;
}
public void goTo(){

    driver.get("https://rahulshettyacademy.com/client");
}

    public String getErrorMessage() {
    return driver.findElement(By.cssSelector(".toast-message")).getText();
    }
}
