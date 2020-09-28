package yale.pageObjects;

import framework.logger.Log;
import framework.BaseElement;
import org.openqa.selenium.By;

public class DocumentSearch extends SearchPage {

    private final By ACTIVE_DOCUMENT = By.xpath("//div[@id='accordion__panel-Document']//button[@tabindex='0']");

    public DocumentSearch addActiveDocumentFilter() {
        Log.logInfo("Add Active Document Filter");
        baseElement.clickFirstElementFromArray(ACTIVE_DOCUMENT);
        return this;
    }

    public int getBracketsDocumentTypeResultNumber() {
        Log.logInfo("Get Documents number in brackets");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(ACTIVE_DOCUMENT);
        return baseElement.clickFirstElementFromArrayAndGetNumberText(ACTIVE_DOCUMENT);
    }
}
