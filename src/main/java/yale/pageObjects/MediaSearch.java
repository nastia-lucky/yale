package yale.pageObjects;

import framework.BaseElement;
import org.openqa.selenium.By;
import framework.logger.Log;

public class MediaSearch extends SearchPage {

    private final By ACTIVE_MEDIA_FILTER = By.xpath("//div[@id='accordion__panel-Media']//button[@tabindex='0']");

    public String addActiveMediaFilter() {
        Log.logInfo("Add Active Media filter");
        String firstText = SearchPage.getSearchResultText();
        String titleText= baseElement.clickFirstElementFromArrayAndGetTitleText(ACTIVE_MEDIA_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return  titleText;
    }

    public int getBracketsMediaTypeResultNumber() {
        Log.logInfo("Get Media Types numbers in brackets");
        String firstText = SearchPage.getSearchResultText();
        int numberText= baseElement.clickFirstElementFromArrayAndGetNumberText(ACTIVE_MEDIA_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return numberText;
    }
}
