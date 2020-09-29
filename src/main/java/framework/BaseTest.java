package framework;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest  {

    Config config = new Config();

    @BeforeMethod
    public void openBrowser() {
        Browser browser = Browser.getInstance();
        browser.getUrl(config.getProperty("mainPageURL"));
    }

   @AfterMethod
    public void CloseBrowser() {
        Browser.closeBrowser();
    }
}
