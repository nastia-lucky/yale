package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MediaSearch extends SearchPage {

    private final By ACTIVE_MEDIA_FILTER = By.xpath("//div[@id='accordion__panel-Media']//button[@tabindex='0']");

    @Step("Add Active Media filter")
    public String addActiveMediaFilter() {
        Log.logInfo("Add Active Media filter");
        String firstText = SearchPage.getSearchResultText();
        String titleText = baseElement.clickFirstElementFromArrayAndGetTitleText(ACTIVE_MEDIA_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return titleText;
    }

    @Step("Get Media Types numbers in brackets")
    public int getBracketsMediaTypeResultNumber() {
        Log.logInfo("Get Media Types numbers in brackets");
        String firstText = SearchPage.getSearchResultText();
        int numberText = baseElement.clickFirstElementFromArrayAndGetNumberText(ACTIVE_MEDIA_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return numberText;
    }
}
