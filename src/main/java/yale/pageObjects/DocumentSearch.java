package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DocumentSearch extends SearchPage {

    private final By ACTIVE_DOCUMENT = By.xpath("//div[@id='accordion__panel-Document']//button[@tabindex='0']");

    @Step("Add Active Document Filter")
    public DocumentSearch addActiveDocumentFilter() {
        Log.logInfo("Add Active Document Filter");
        String firstText = SearchPage.getSearchResultText();
        baseElement.clickFirstElementFromArray(ACTIVE_DOCUMENT);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return this;
    }

    @Step("Add Active Document Filter And Get Document Type")
    public String addActiveDocumentFilterAndGetDocumentType() {
        Log.logInfo("Add Active Document Filter And Get Document Type");
        String firstText = SearchPage.getSearchResultText();
        String filterType = baseElement.clickFirstElementAndGetFilterType(ACTIVE_DOCUMENT);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return filterType;
    }

    @Step("Get Documents Number in brackets")
    public int getBracketsDocumentTypeResultNumber() {
        Log.logInfo("Get Documents Number in brackets");
        String firstText = SearchPage.getSearchResultText();
        int numberText = baseElement.clickFirstElementFromArrayAndGetNumberText(ACTIVE_DOCUMENT);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return numberText;
    }
}
