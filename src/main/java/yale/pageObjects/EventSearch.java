package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class EventSearch extends SearchPage {

    private final By AUDIENCE_FILTER = By.xpath("//span[contains(text(), 'Audience')]");
    private final By ACTIVE_AUDIENCE = By.xpath("//div[@id='accordion__panel-audience']//button[@tabindex='0']");
    private final By ACTIVE_EVENT_TYPE = By.xpath("//div[@id='accordion__panel-eventType']//button[@tabindex='0']");
    private final By EVENT_TYPE_FILTER = By.xpath("//span[contains(text(), 'Event Type')]");

    @Step("Add Audience Filter")
    public EventSearch addAudienceFilter() {
        Log.logInfo("Add Audience Filter");
        baseElement.clickElement(AUDIENCE_FILTER);
        return this;
    }

    @Step("Add Active Audience")
    public String addActiveAudience() {
        Log.logInfo("Add Active Audience");
        String firstText = SearchPage.getSearchResultText();
        String chosenAudience = baseElement.clickFirstElementFromArrayAndGetTitleText(ACTIVE_AUDIENCE);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return chosenAudience;
    }

    @Step("Add Event Type Filter")
    public EventSearch addEventTypeFilter() {
        Log.logInfo("Add Event Type Filter");
        baseElement.clickElement(EVENT_TYPE_FILTER);
        return this;
    }

    @Step("Add Active Event Type")
    public String addActiveEventType() {
        Log.logInfo("Add Active Event Type");
        String firstText = SearchPage.getSearchResultText();
        String textFirstElementFromArray = baseElement.clickFirstElementFromArrayAndGetTitleText(ACTIVE_EVENT_TYPE);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return textFirstElementFromArray;
    }

    @Step("Get Events Numbers in brackets")
    public int getAudienceBracketsResultNumber() {
        Log.logInfo("Get Events Numbers in brackets");
        String firstText = SearchPage.getSearchResultText();
        int numberText = baseElement.clickFirstElementFromArrayAndGetNumberText(ACTIVE_AUDIENCE);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return numberText;
    }
}
