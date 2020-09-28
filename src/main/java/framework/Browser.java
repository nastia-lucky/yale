package framework;


import framework.logger.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Browser {

    public static String browserType;
    private static WebDriver driver;
    protected static Browser browser;
    Config config = new Config();

    protected Browser() {
        System.setProperty("webdriver.chrome.driver", config.getProperty("driverPath"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    public static Browser getInstance() {
        if (browser == null) {
            browser = new Browser();
        }
        return browser;
    }

    public void getUrl(String url) {
        Log.logGetUrl(url);
        driver.get(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void closeBrowser() {
        driver.quit();
        browser = null;
    }

    public void switchToNewTab() {
        Log.logInfo("Switching to the new tab");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public String getCurrentUrl() {
        return Browser.getDriver().getCurrentUrl();
    }

    public void clickBackButton() {
        Log.logInfo("Click Back Button");
        driver.navigate().back();
    }
}
