package yale.pageObjects;

import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MediaPage extends BasePage {

    private static final By PLAY_BUTTON = By.xpath("//button[@data-plyr='play']");
    private final By PAUSE_BUTTON = By.xpath("//button[@aria-label='Pause']");
    private final By TRANSCRIPT_BUTTON = By.xpath("//div[@title='Transcript']");
    private final By MEDIA_DETAILS_PAGE_TITLE = By.xpath("//h1[@class='media-details-header__title']");
    private final By INFORMATION_TAB = By.xpath("//h2[contains(text(), 'Information')]");

    public MediaPage() {
        super(PLAY_BUTTON);
    }

    @Step("Click Play Button")
    public MediaPage clickPlayButton() {
        Log.logInfo("Click Play Button");
        baseElement.clickElement(PLAY_BUTTON);
        return this;
    }

    @Step("Verify Pause button is displayed")
    public boolean isPauseButtonIsDisplayed() {
        Log.logInfo("Check Pause button is displayed");
        return baseElement.isElementDisplayed(PAUSE_BUTTON);
    }

    @Step("Click Transcript Button")
    public MediaPage clickTranscriptButton() {
        Log.logInfo("Click Transcript Button");
        baseElement.clickElement(TRANSCRIPT_BUTTON);
        return this;
    }

    @Step("Verify that Media Title is displayed")
    public boolean idMediaTitleDisplayed() {
        Log.logInfo("Check that Media Title is displayed");
        return baseElement.isElementDisplayed(MEDIA_DETAILS_PAGE_TITLE);
    }

    @Step("Verify that Information Tab is displayed")
    public boolean isInformationTabDisplayed() {
        Log.logInfo("Check that Information Tab is displayed");
        return baseElement.isElementDisplayed(INFORMATION_TAB);
    }
}
