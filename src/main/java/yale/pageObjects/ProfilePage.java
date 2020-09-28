package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import org.openqa.selenium.By;

public class ProfilePage extends BasePage {

    private final By PROFILE_SIDEBAR_NAME = By.xpath("//span[@class='profile-details-sidebar__name']");
    private final By PROFILE_IMAGE = By.xpath("//img[@class='profile-details-thumbnail__image']");
    private final By PROFILE_NAME = By.xpath("//h1[@class='profile-details-header__name']");
    private static final By EDIT_PROFILE_LINK = By.xpath("//div[@class='profile-details-content__edit-profile-link-container']");
    private final By SHARE_PANEL = By.xpath("//ul[@class='share-panel share-panel--horizontal profile-details-content__share-panel']");
    private final By TAB = By.xpath("//li[@role='tab']");
    private final By DOWNLOAD_PHOTO_BUTTON = By.xpath("//div[@aria-label='Download Hi-Res Photo']");
    private final By DOWNLOADED_IMAGE = By.xpath("//img[@style]");
    private final By PROFILE_TITLE = By.xpath("//div[@class='LinesEllipsis  text-overflow profile-details-header__title']");
    private final By CONTACT_INFORMATION_SECTION = By.xpath("//section[@aria-label='Contact Information']");
    private final By RESEARCH_PUBLICATION_TAB = By.xpath("//h2[contains(text(), 'Research & Publications')]");
    private final By RESEARCH_INTERESTS = By.xpath("//section[@aria-label='Research Interests']");

    public ProfilePage() {
        super(EDIT_PROFILE_LINK);
    }

    public String getName() {
        BaseElement.waitForElementToBePresent(PROFILE_SIDEBAR_NAME);
        return baseElement.getTitle();
    }

    public String getPeopleName() {
        BaseElement.waitForElementToBeClickable(EDIT_PROFILE_LINK);
        return baseElement.getText(PROFILE_NAME);
    }

    public boolean isImageDisplayed() {
        Log.logInfo("Check image displayed");
        BaseElement.waitForElementToBePresent(PROFILE_IMAGE);
        return baseElement.isElementDisplayed(PROFILE_IMAGE);
    }

    public boolean isNameIsDisplayed() {
        Log.logInfo("Check name is displayed");
        BaseElement.waitForElementToBePresent(PROFILE_NAME);
        return baseElement.isElementDisplayed(PROFILE_NAME);
    }

    public boolean isEditProfileLinkDisplayed() {
        Log.logInfo("Check Edit Profile Link is displayed");
        BaseElement.waitForElementToBePresent(EDIT_PROFILE_LINK);
        return baseElement.isElementDisplayed(EDIT_PROFILE_LINK);
    }

    public boolean isSharePanelDisplayed() {
        Log.logInfo("Check Edit Profile Link is displayed");
        BaseElement.waitForElementToBePresent(SHARE_PANEL);
        return baseElement.isElementDisplayed(SHARE_PANEL);
    }

    public boolean isTabNotExist() {
        Log.logInfo("Check tabs");
        return baseElement.isArrayEmpty(TAB);
    }

    public ProfilePage clickDownloadPhotoButton() {
        Log.logInfo("Click downloadPhotoButton");
        baseElement.clickElement(DOWNLOAD_PHOTO_BUTTON);
        return this;
    }

    public void switchToNewTab() {
        browser.switchToNewTab();
    }

    public boolean isDownLoadedImageDisplayed() {
        Log.logInfo("Check downloaded image");
        BaseElement.waitForElementToBePresent(DOWNLOADED_IMAGE);
        return baseElement.isElementDisplayed(DOWNLOADED_IMAGE);
    }

    public boolean isTitleDisplayed() {
        Log.logInfo("Check For Title");
        return baseElement.isElementDisplayed(PROFILE_TITLE);
    }

    public boolean isContactInformationDisplayed() {
        Log.logInfo("Check the contact Information Section");
        return baseElement.isElementDisplayed(CONTACT_INFORMATION_SECTION);
    }

    public ProfilePage openResearchAndPublicationTab() {
        Log.logInfo("Open Research and Publications Tab");
        BaseElement.waitForElementToBeClickable(EDIT_PROFILE_LINK);
        baseElement.clickWithJS(RESEARCH_PUBLICATION_TAB);
        return this;
    }

    public boolean isResearchInterestContainInputResearchArea(String researchArea) {
        Log.logInfo("Check research interests contain inputted research area - " + researchArea);
        return baseElement.isElementContainsText(RESEARCH_INTERESTS, researchArea);
    }
}
