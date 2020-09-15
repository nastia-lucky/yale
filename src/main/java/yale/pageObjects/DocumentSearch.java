package yale.pageObjects;

import framework.logger.Log;
import framework.utilities.Browser;
import org.openqa.selenium.By;

public class DocumentSearch extends SearchPage {

    private final By ACTIVE_DOCUMENT = By.xpath("//div[@id='accordion__panel-Document']//button[@tabindex='0']");

    public DocumentSearch addActiveDocumentFilter() {
        Log.logInfo("Add Active Document Filter");
        browser.clickFirstElementFromArray(ACTIVE_DOCUMENT);
        return this;
    }

    public int getBracketsDocumentTypeResultNumber() {
        Log.logInfo("Get Documents number in brackets");
        Browser.waitForTheFirstElementFromArrayIsClickable(ACTIVE_DOCUMENT);
        return browser.clickFirstElementFromArrayAndGetNumberText(ACTIVE_DOCUMENT);
    }
}
