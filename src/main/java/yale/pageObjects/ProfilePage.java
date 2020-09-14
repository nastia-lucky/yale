package yale.pageObjects;

import org.openqa.selenium.By;
import framework.logger.Log;
import framework.utilities.Browser;

public class ProfilePage extends BasePage {

    private final By PROFILE_SIDEBAR_NAME = By.xpath("//span[@class='profile-details-sidebar__name']");
    private final By PROFILE_IMAGE = By.xpath("//img[@class='profile-details-thumbnail__image']");
    private final By PROFILE_NAME = By.xpath("//h1[@class='profile-details-header__name']");
    private final By EDIT_PROFILE_LINK = By.xpath("//div[@class='profile-details-content__edit-profile-link-container']");
    private final By SHARE_PANEL = By.xpath("//ul[@class='share-panel share-panel--horizontal profile-details-content__share-panel']");
    private final By TAB = By.xpath("//li[@role='tab']");
    private final By DOWNLOAD_PHOTO_BUTTON = By.xpath("//div[@aria-label='Download Hi-Res Photo']");
    private final By DOWNLOADED_IMAGE = By.xpath("//img[@style]");
    private final By PROFILE_TITLE = By.xpath("//div[@class='LinesEllipsis  text-overflow profile-details-header__title']");
    private final By CONTACT_INFORMATION_SECTION = By.xpath("//section[@aria-label='Contact Information']");

    public String getName() {
        Browser.waitForElementToBePresent(PROFILE_SIDEBAR_NAME);
        return browser.getTitle();
    }

    public boolean isImageDisplayed() {
        Log.logInfo("Check image displayed");
        Browser.waitForElementToBePresent(PROFILE_IMAGE);
        return browser.isElementDisplayed(PROFILE_IMAGE);
    }

    public boolean isNameIsDisplayed() {
        Log.logInfo("Check name is displayed");
        Browser.waitForElementToBePresent(PROFILE_NAME);
        return browser.isElementDisplayed(PROFILE_NAME);
    }

    public boolean isEditProfileLinkDisplayed() {
        Log.logInfo("Check Edit Profile Link is displayed");
        Browser.waitForElementToBePresent(EDIT_PROFILE_LINK);
        return browser.isElementDisplayed(EDIT_PROFILE_LINK);
    }

    public boolean isSharePanelDisplayed() {
        Log.logInfo("Check Edit Profile Link is displayed");
        Browser.waitForElementToBePresent(SHARE_PANEL);
        return browser.isElementDisplayed(SHARE_PANEL);
    }

    public boolean isTabNotExist() {
        Log.logInfo("Check tabs");
        return browser.isArrayEmpty(TAB);
    }

    public ProfilePage clickDownloadPhotoButton() {
        Log.logInfo("Click downloadPhotoButton");
        browser.clickElement(DOWNLOAD_PHOTO_BUTTON);
        return this;
    }

    public void switchToNewTab() {
        browser.switchToNewTab();
    }

    public boolean isDownLoadedImageDisplayed() {
        Log.logInfo("Check downloaded image");
        Browser.waitForElementToBePresent(DOWNLOADED_IMAGE);
        return browser.isElementDisplayed(DOWNLOADED_IMAGE);
    }

    public boolean isTitleDisplayed() {
        Log.logInfo("Check For Title");
        return browser.isElementDisplayed(PROFILE_TITLE);
    }

    public boolean isContactInformationDisplayed() {
        Log.logInfo("Check the contact Information Section");
        return browser.isElementDisplayed(CONTACT_INFORMATION_SECTION);
    }
}
