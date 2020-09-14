package yale.pageObjects;

import framework.logger.Log;
import framework.utilities.Browser;
import org.openqa.selenium.By;

public class MediaPage extends BasePage {

    private final By PLAY_BUTTON = By.xpath("//button[@data-plyr='play']");
    private final By PAUSE_BUTTON = By.xpath("//button[@aria-label='Pause']");
    private final By TRANSCRIPT_BUTTON = By.xpath("//div[@title='Transcript']");
    private final By MEDIA_DETAILS_PAGE_TITLE = By.xpath("//h1[@class='media-details-header__title']");
    private final By INFORMATION_TAB = By.xpath("//h2[contains(text(), 'Information')]");

    public MediaPage clickPlayButton() {
        Log.logInfo("Click Play Button");
        Browser.waitForElementToBeClickable(PLAY_BUTTON);
        browser.clickElement(PLAY_BUTTON);
        return this;
    }

    public boolean isPauseButtonIsDisplayed() {
        Log.logInfo("Check Pause button is displayed");
        Browser.waitForElementToBeClickable(PAUSE_BUTTON);
        return browser.isElementDisplayed(PAUSE_BUTTON);
    }

    public MediaPage clickTranscriptButton() {
        Log.logInfo("Click Transcript Button");
        Browser.waitForElementToBeClickable(TRANSCRIPT_BUTTON);
        browser.clickElement(TRANSCRIPT_BUTTON);
        return this;
    }

    public boolean idMediaTitleDisplayed() {
        Log.logInfo("Check that Media Title is displayed");
        Browser.waitForElementToBePresent(MEDIA_DETAILS_PAGE_TITLE);
        return browser.isElementDisplayed(MEDIA_DETAILS_PAGE_TITLE);
    }

    public boolean isInformationTabDisplayed() {
        Log.logInfo("Check that Information Tab is displayed");
        return browser.isElementDisplayed(INFORMATION_TAB);
    }
}
