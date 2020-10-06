package yale.pageObjects;

import framework.BaseElement;
import org.openqa.selenium.By;
import framework.logger.Log;

public class MediaSearch extends SearchPage {

    private final By ACTIVE_MEDIA_FILTER = By.xpath("//div[@id='accordion__panel-Media']//button[@tabindex='0']");

    public String addActiveMediaFilter() {
        Log.logInfo("Add Active Media filter");
        return baseElement.clickFirstElementFromArrayAndGetTitleText(ACTIVE_MEDIA_FILTER);
    }

    public int getBracketsMediaTypeResultNumber() {
        Log.logInfo("Get Media Types numbers in brackets");
        return baseElement.clickFirstElementFromArrayAndGetNumberText(ACTIVE_MEDIA_FILTER);
    }
}
