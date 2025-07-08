package Frameworks.AbstractComponents;

import Frameworks.pageObjects.cartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class abstractComponent {
    WebDriver driver;
    public abstractComponent(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (css = "button[routerlink*='cart']")
    WebElement CartButton;


    public void waitForElementToAppear(By productList){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(productList));
    }

    public cartPage gotoCart()
    {
        CartButton.click();
        cartPage cartpage = new cartPage(driver);
        return cartpage;

    }


    public void waitForElementToDisappear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

}
