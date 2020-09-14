package yale.pageObjects;

import framework.logger.Log;
import framework.utilities.Browser;
import org.openqa.selenium.By;

public class WebSiteSearch extends SearchPage {

    private final By ACTIVE_WEB_PAGE_FILTER = By.xpath("//div[@id='accordion__panel-Page']//button[@tabindex='0']");

    public WebSiteSearch addActiveWebPageFilter() {
        Log.logInfo("Add active Web Page Filter");
        Browser.waitForTheFirstElementFromArrayIsClickable(ACTIVE_WEB_PAGE_FILTER);
        browser.clickFirstElementFromArray(ACTIVE_WEB_PAGE_FILTER);
        return this;
    }

    public int getBracketsWebSiteResultNumber() {
        Browser.waitForTheFirstElementFromArrayIsClickable(ACTIVE_WEB_PAGE_FILTER);
        return browser.clickFirstElementFromArrayAndGetNumberText(ACTIVE_WEB_PAGE_FILTER);
    }
}