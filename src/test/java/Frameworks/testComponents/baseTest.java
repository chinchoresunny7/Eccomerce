package Frameworks.testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        String browser= prop.getProperty("browser");

        if(browser.equalsIgnoreCase("chrome")){
//            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

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


