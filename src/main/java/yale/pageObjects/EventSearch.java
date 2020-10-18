package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import org.openqa.selenium.By;

public class EventSearch extends SearchPage {

    private final By AUDIENCE_FILTER = By.xpath("//span[contains(text(), 'Audience')]");
    private final By ACTIVE_AUDIENCE = By.xpath("//div[@id='accordion__panel-audience']//button[@tabindex='0']");
    private final By ACTIVE_EVENT_TYPE = By.xpath("//div[@id='accordion__panel-eventType']//button[@tabindex='0']");
    private final By EVENT_TYPE_FILTER = By.xpath("//span[contains(text(), 'Event Type')]");

    public EventSearch addAudienceFilter() {
        Log.logInfo("Add Audience Filter");
        baseElement.clickElement(AUDIENCE_FILTER);
        return this;
    }

    public String addActiveAudience() {
        Log.logInfo("Add Active Audience");
        String firstText = SearchPage.getSearchResultText();
        String chosenAudience = baseElement.clickFirstElementFromArrayAndGetTitleText(ACTIVE_AUDIENCE);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return chosenAudience;
    }

    public EventSearch addEventTypeFilter() {
        Log.logInfo("Add Event Type Filter");
        baseElement.clickElement(EVENT_TYPE_FILTER);
        return this;
    }

    public String addActiveEventType() {
        Log.logInfo("Add Active Event Type");
        String firstText = SearchPage.getSearchResultText();
        String textFirstElementFromArray = baseElement.clickFirstElementFromArrayAndGetTitleText(ACTIVE_EVENT_TYPE);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return textFirstElementFromArray;
    }

    public int getAudienceBracketsResultNumber() {
        Log.logInfo("Get Events Numbers in brackets ");
        String firstText = SearchPage.getSearchResultText();
        int numberText = baseElement.clickFirstElementFromArrayAndGetNumberText(ACTIVE_AUDIENCE);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return numberText;
    }
}
