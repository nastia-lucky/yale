package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class WebSiteSearch extends SearchPage {

    private final By ACTIVE_WEB_PAGE_FILTER = By.xpath("//div[@id='accordion__panel-Page']//button[@tabindex='0']");

    @Step("Add active Web Page Filter")
    public WebSiteSearch addActiveWebPageFilter() {
        Log.logInfo("Add active Web Page Filter");
        String firstText = SearchPage.getSearchResultText();
        baseElement.clickFirstElementFromArray(ACTIVE_WEB_PAGE_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return this;
    }

    @Step("Get Events Numbers in brackets")
    public int getBracketsWebSiteResultNumber() {
        Log.logInfo("Get Events Numbers in brackets");
        String firstText = SearchPage.getSearchResultText();
        int numberText = baseElement.clickFirstElementFromArrayAndGetNumberText(ACTIVE_WEB_PAGE_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return numberText;
    }
}
