package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import org.openqa.selenium.By;

public class NewsSearch extends SearchPage {

    private final By SOURCE_BUTTON = By.xpath("//span[contains(text(), 'Source')]");
    private final By ACTIVE_SOURCE = By.xpath("//div[@id='accordion__panel-articleSource']//button[@tabindex='0']");

    public NewsSearch clickSourceButton() {
        Log.logInfo("Сlick Source Button");
        baseElement.clickElement(SOURCE_BUTTON);
        return this;
    }

    public SearchPage addSourceNewsFilter() {
        Log.logInfo("Add Active News Source");
        String firstText = SearchPage.getSearchResultText();
        baseElement.clickElement(ACTIVE_SOURCE);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return this;
    }
}
