package framework;


import framework.logger.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Browser {

    public static String browserType;
    private static WebDriver driver;
    protected static Browser browser;
    Config config = new Config();
    File file = new File("src/main/resources/yale/");

    protected Browser() {

        String path = null;
        try {
            path = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (config.getProperty("browser")) {
            case "chrome": {
                String pathChrome = path + "/chromedriver 3";
                System.setProperty(
                        "webdriver.chrome.driver", pathChrome);
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
                break;
            }
            case "firefox": {
                String pathFirefox = path + "/geckodriver";
                System.setProperty(
                        "webdriver.gecko.driver", pathFirefox);
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
                break;
            }
            case "ie": {
                String pathIE = path + "/IEDriverServer.exe";
                System.setProperty(
                        "webdriver.ie.driver", pathIE);
                driver = new InternetExplorerDriver();
                driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
                break;
            }
            default: {
                System.out.println("I don't know such browser type");
            }
        }
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
