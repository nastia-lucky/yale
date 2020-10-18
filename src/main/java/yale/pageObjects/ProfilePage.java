package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import org.openqa.selenium.By;

public class ProfilePage extends BasePage {

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
    private  final By DOWNLOAD_CV=By.xpath("//span[contains(text(), 'Download CV')]");

    public ProfilePage() {
        super(EDIT_PROFILE_LINK);
    }

    public String getName() {
        return baseElement.getTitle();
    }

    public String getPeopleName() {
        BaseElement.waitForElementToBeClickable(EDIT_PROFILE_LINK);
        return baseElement.getText(PROFILE_NAME);
    }

    public boolean isImageDisplayed() {
        Log.logInfo("Check image displayed");
        return baseElement.isElementDisplayed(PROFILE_IMAGE);
    }

    public boolean isNameIsDisplayed() {
        Log.logInfo("Check name is displayed");
        return baseElement.isElementDisplayed(PROFILE_NAME);
    }

    public boolean isEditProfileLinkDisplayed() {
        Log.logInfo("Check Edit Profile Link is displayed");
        return baseElement.isElementDisplayed(EDIT_PROFILE_LINK);
    }

    public boolean isSharePanelDisplayed() {
        Log.logInfo("Check Edit Profile Link is displayed");
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
        return baseElement.isElementDisplayed(DOWNLOADED_IMAGE);
    }

    public boolean isTitleDisplayed() {
        Log.logInfo("Check the Title is displayed");
        return baseElement.isElementDisplayed(PROFILE_TITLE);
    }

    public boolean isContactInformationDisplayed() {
        Log.logInfo("Check the Contact Information Section");
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

    public boolean isTitleContainsSearchValue(String text){
        Log.logInfo("Check that title contains text");
         BaseElement.waitForElementToBeClickable(EDIT_PROFILE_LINK);
         return baseElement.isElementContainsText(PROFILE_NAME, text);
    }

    public ProfilePage clickDownloadCVButton(){
        Log.logInfo("Click Download CV button");
        baseElement.clickElement(DOWNLOAD_CV);
        BaseElement.waitForElementDisappear(DOWNLOAD_CV);
        return this;
    }

    public boolean isURLContainsText(String URL, String text){
        Log.logInfo("Check that URL "+ URL+"  contains text "+ text );
        return URL.contains(text);
    }
}
