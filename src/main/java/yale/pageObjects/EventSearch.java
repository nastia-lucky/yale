package yale.pageObjects;

import framework.logger.Log;
import framework.BaseElement;
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
        Log.logInfo("Add active audience");
        String chosenAudience = baseElement.clickFirstElementFromArrayAndGetTitleText(ACTIVE_AUDIENCE);
        return chosenAudience;
    }

    public EventSearch addEventTypeFilter() {
        Log.logInfo("Add Event Type Filter");
        baseElement.clickElement(EVENT_TYPE_FILTER);
        return this;
    }

    public String addActiveEventType() {
        Log.logInfo("Add Active Event Type");
        return baseElement.clickFirstElementFromArrayAndGetTitleText(ACTIVE_EVENT_TYPE);
    }

    public int getAudienceBracketsResultNumber() {
        Log.logInfo("Get Events numbers in brackets ");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(ACTIVE_AUDIENCE);
        return baseElement.clickFirstElementFromArrayAndGetNumberText(ACTIVE_AUDIENCE);
    }
}
