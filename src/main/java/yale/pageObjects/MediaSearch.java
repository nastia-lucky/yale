package yale.pageObjects;

import org.openqa.selenium.By;
import framework.logger.Log;
import framework.utilities.Browser;

public class MediaSearch extends SearchPage {

    private final By ACTIVE_MEDIA_FILTER = By.xpath("//div[@id='accordion__panel-Media']//button[@tabindex='0']");

    public String addActiveMediaFilter() {
        Log.logInfo("Add active media filter");
        return browser.clickFirstElementFromArrayAndGetTitleText(ACTIVE_MEDIA_FILTER);
    }

    public int getBracketsMediaTypeResultNumber() {
        Log.logInfo("Get Media Types numbers in brackets");
        Browser.waitForTheFirstElementFromArrayIsClickable(ACTIVE_MEDIA_FILTER);
        return browser.clickFirstElementFromArrayAndGetNumberText(ACTIVE_MEDIA_FILTER);
    }
}
