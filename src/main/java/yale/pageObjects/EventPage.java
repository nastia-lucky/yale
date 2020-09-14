package yale.pageObjects;

import framework.logger.Log;
import framework.utilities.Browser;
import org.openqa.selenium.By;

public class EventPage extends BasePage {

    private final By EVENT_TITLE = By.xpath("//h1[@class='event-details-header__title']");
    private final By EVENT_DATE = By.xpath("//div[@class='event-details-date']");
    private final By EVENT_TIME = By.xpath("//div[@class='event-details-time']");
    private final By EVENT_ADMISSION = By.xpath("//section[@aria-label='Admission']");
    private final By SHARE_VIA_TWITTER_LINK = By.xpath("//button[@aria-label='Share via Twitter']");
    private final By EVENT_AUDIENCE = By.xpath("//span[@class='event-details-audience']");
    private final By EVENT_TAGS = By.xpath("//section[@aria-label='Tags']//div[@class='named-section__content-wrapper']");
    private final By EVENT_TAG = By.xpath("//section[@aria-label='Tag']//div[@class='named-section__content-wrapper']");
    private  final By EVENT_MODAL_TITLE=By.xpath("//h2[@class='event-details-header__title']");

    public boolean isEventDateIsDisplayed() {
        Browser.waitForElementToBePresent(EVENT_DATE);
        return browser.isElementDisplayed(EVENT_DATE);
    }

    public boolean isEventTimeIsDisplayed() {
        Log.logInfo("Check that Event Time is displayed ");
        return browser.isElementDisplayed(EVENT_TIME);
    }

    public boolean isEventAdmissionIsDisplayed() {
        Log.logInfo("Check that Event Admission is displayed");
        return browser.isElementDisplayed(EVENT_ADMISSION);
    }

    public boolean isShareViaTwitterLinkDisplayed() {
        Log.logInfo("Check that Share via Twitter Link is displayed");
        return browser.isElementDisplayed(SHARE_VIA_TWITTER_LINK);
    }

    public boolean isEventAudienceDisplayed() {
        Log.logInfo("Check that Event Audience is displayed");
        return browser.isElementDisplayed(EVENT_AUDIENCE);
    }

    public String getAudience() {
        Log.logInfo("Get Event Audience");
        Browser.waitForElementToBeClickable(SHARE_VIA_TWITTER_LINK);
        return browser.getText(EVENT_AUDIENCE);
    }

    public boolean isTagsContainsChosenEventType(String chosenTag) {
        Log.logInfo("Check that Event Tags contain chosen keyword " + chosenTag);
        Browser.waitForElementToBeClickable(SHARE_VIA_TWITTER_LINK);
        return browser.isElementsContainText(EVENT_TAG, EVENT_TAGS, chosenTag);
    }

    public String getEventTitle() {
        Log.logInfo("Get Event Title");
        Browser.waitForElementToBePresent(EVENT_TITLE);
        String title = browser.getText(EVENT_TITLE);
        System.out.println(title);
        return title;
    }

    public  String getEventModalTitle(){
        Log.logInfo("Get Event Modal Title");
        Browser.waitForElementToBePresent(EVENT_MODAL_TITLE);
        String eventModalTitle=browser.getText(EVENT_MODAL_TITLE) ;
        System.out.println(eventModalTitle);
        return eventModalTitle;
    }
}
