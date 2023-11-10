package golf.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebDriverWrapper {
    private static WebDriver driver = null;


    public static WebDriver getWebDriver() {
        if (driver == null) {
            setUpWebDriver();
        }
        return driver;
    }

    private static void setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
}
