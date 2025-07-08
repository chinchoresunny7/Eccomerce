package Frameworks.pageObjects;

import Frameworks.AbstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class confirmationPage extends abstractComponent {
    WebDriver driver;
    public confirmationPage(WebDriver driver) {
            super(driver);
            this.driver=driver;
            PageFactory.initElements(driver,this);
    }
    @FindBy (css= ".hero-primary")
    WebElement message;


    public String getConfirmationMessage() {
        waitForElementToAppear(By.cssSelector(".hero-primary"));
        System.out.println(message.getText());
        return message.getText();
    }
}
