package framework;


import framework.logger.Log;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Browser {

    public static String browserType;
    private static WebDriver driver;
    protected static Browser browser;
    Config config = new Config();
    File file = new File("src/main/resources/yale/");
    @Getter
    private final Path downloadPath;

    protected Browser() {

        String path;
        try {
            path = file.getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException("Can't get path", e);
        }
        try {
            downloadPath = Files.createTempDirectory("cd_downloads");
        } catch (IOException e) {
            throw new RuntimeException("Can't create temp directory for downloads", e);
        }
        switch (config.getProperty("browser")) {
            case "chrome": {
                String pathChrome = path + "/chromedriver 4";
                System.setProperty("webdriver.chrome.driver", pathChrome);

                String downloadFilepath = getDownloadPath().toString();
                //String downloadFilepath = "/Users/anastasiyashafalovich/IdeaProjects/yale/downloads";
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadFilepath);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                cap.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(cap);
                break;
            }
            case "firefox": {
                String pathFirefox = path + "/geckodriver";
                System.setProperty(
                        "webdriver.gecko.driver", pathFirefox);
                driver = new FirefoxDriver();
                break;
            }
            case "ie": {
                String pathIE = path + "/IEDriverServer.exe";
                System.setProperty(
                        "webdriver.ie.driver", pathIE);
                driver = new InternetExplorerDriver();
                break;
            }
            default: {
                Log.logInfo("I don't know such browser type");
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
        try {
            FileUtils.deleteDirectory(browser.getDownloadPath().toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        browser = null;
    }

    public void switchToNewTab() {
        Log.logInfo("Switching to the new tab");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public static String getCurrentUrl() {
        return Browser.getDriver().getCurrentUrl();
    }

    public void clickBackButton() {
        Log.logInfo("Click Back Button");
        driver.navigate().back();
    }
}
