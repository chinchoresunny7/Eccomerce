package Frameworks.pageObjects;
import Frameworks.AbstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class productCatologue extends abstractComponent {
    WebDriver driver;
    public productCatologue(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (css = ".mb-3")
    List<WebElement> Products;
    @FindBy (css = ".ng-animating")
    WebElement spinner;

    By productList = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");


public List<WebElement> getProductList(){
    waitForElementToAppear (productList);
    List<WebElement> products = driver.findElements(productList);
    return(products);
}

public void selectProductbyName(String name){
    List<WebElement> products = getProductList();
    WebElement p = products.stream().filter(product->
            product.findElement(By.cssSelector("b")).getText().equals(name) ).findFirst().orElse(null);
    p.findElement(addToCart).click();
    waitForElementToAppear(toastMessage);
    waitForElementToDisappear(spinner);

}

}
