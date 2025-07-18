package Frameworks.testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class baseTest {
    public WebDriver driver;
    @BeforeMethod
    public void initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream readFile = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//Frameworks//Resources//globalData.properties");
        prop.load(readFile);
        String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

        if(browser.contains("Chrome")){
            ChromeOptions o= new ChromeOptions();
            if(browser.contains("headless")){
                o.addArguments("--headless");
                o.addArguments("--window-size=1920,1080");
            }
            driver = new ChromeDriver(o);
            WebDriverManager.chromedriver().setup();

//            driver.manage().window().setSize(new Dimension(1440,900));
//            driver.manage().window().setPosition(new Point(0,0));
        } else if (browser.equalsIgnoreCase("fireFox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterMethod
    public void CloseBrowser(){
        driver.close();
    }

    public List<HashMap<String, String>> getJsonToMap(String filepath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filepath),
                StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>> () {
               });
        return data;


    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//Reports//" + testCaseName +".png");
        FileUtils.copyFile(src,file);
        return System.getProperty("user.dir") + "//Reports//" + testCaseName +".png";
    }


}


