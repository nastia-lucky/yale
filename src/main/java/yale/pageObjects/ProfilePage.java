package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yale.pageObjects.ProfilePageTabs.LocationsTab;
import yale.pageObjects.ProfilePageTabs.NewsTab;
import yale.pageObjects.ProfilePageTabs.PatientCareTab;
import yale.pageObjects.ProfilePageTabs.ResearchTab;

public class ProfilePage extends BasePage {

    private final By PROFILE_IMAGE = By.xpath("//img[@class='profile-details-thumbnail__image']");
    private final By PROFILE_NAME = By.xpath("//h1[@class='profile-details-header__name']");
    private static final By EDIT_PROFILE_LINK = By.xpath("//div[@class='profile-details-content__edit-profile-link-container']");
    private final By SHARE_PANEL = By.xpath("//ul[@class='share-panel share-panel--horizontal profile-details-content__share-panel']");
    private final By TAB = By.xpath("//li[@role='tab']");
    private final By DOWNLOAD_PHOTO_BUTTON = By.xpath("//div[@aria-label='Download Hi-Res Photo']");
    private final By DOWNLOADED_IMAGE = By.xpath("//img[@style]");
    private final By PROFILE_TITLE = By.xpath("//div[@class='LinesEllipsis  text-overflow profile-details-header__title']");
    private final By RESEARCH_PUBLICATION_TAB = By.xpath("//h2[contains(text(), 'Research & Publications')]");
    private final By RESEARCH_INTERESTS = By.xpath("//section[@aria-label='Research Interests']");
    private final By DOWNLOAD_CV = By.xpath("//span[contains(text(), 'Download CV')]");
    private static final By BIOGRAPHY_TAB = By.xpath("//h2[contains(text(), 'Biography')]");
    private final By CONTACT_INFORMATION_TITLE = By.xpath("//h2[contains(text(),'Contact Information')]");
    private final By CONTACT_INFORMATION_SECTION = By.xpath("//span[@class='profile-details-sidebar__name']");
    private final By GENERAL_INFO = By.xpath("//li[@class='profile-general-contact-list__item']");
    private final By PATIENT_CARE_LOCATION_TITLE = By.xpath("//h2[contains(text(), 'Patient Care Location')]");
    private final By MAILING_ADDRESS_TITLE = By.xpath("//h2[contains(text(),'Mailing Address')]");
    private final By MAILING_ADDRESS_SECTION = By.xpath("//div[@class='profile-mailing-address']");
    private final By LAB_WEBSITE_BUTTON = By.xpath("//div[@class='profile-details-sidebar__lab-website-container']//span[@class='button__label']");
    private final By RELATED_LINKS_SECTION_TITLE = By.xpath("//h2[contains(text(),'Related Links')]");
    private final By RELATED_LINKS_ITEM = By.xpath("//div[@class='link-with-description']");
    private final By YM_LINK = By.xpath("//section[@class='profile-details-sidebar__ym-profile-link']//span[contains(text(),'View Doctor Profile')]");
    private final By BIOGRAPHY_SECTION_TITLE = By.xpath("//h3[contains(text(),'Biography')]");
    private final By BIOGRAPHY_SECTION = By.xpath("//div[@class='profile-details-biography-tab__biography']");
    private final By EDUCATION_AND_TRAINING_SECTION_TITLE = By.xpath("//h3[contains(text(), 'Education & Training')]");
    private final By EDUCATION_ITEM = By.xpath("//li[@class='profile-education-list-item']");
    private final By DEGREE_ITEM = By.xpath(".//div[@class='profile-education-list-item__degree']");
    private final By INSTITUTION_ITEM = By.xpath(".//div[@class='profile-education-list-item__institution']");
    private final By ACTIVITIES_SECTION_TITLE = By.xpath("//h3[contains(text(), 'Activities')]");
    private final By ACTIVITY_ITEM = By.xpath("//li[@class='profile-activity-list-item']");
    private final By ACTIVITY_ITEM_TITLE = By.xpath(".//div[@class='profile-activity-list-item__title']");
    private final By ACTIVITY_ITEM_PLACE = By.xpath(".//div[@class='profile-activity-list-item__place']");
    private final By ACTIVITY_ITEM_DESCRIPTION = By.xpath(".//div[@class='profile-activity-list-item__description']");
    private final By HONORS_AND_RECOGNITION_SECTION_TITLE = By.xpath("//h3[contains(text(),'Honors & Recognition')]");
    private final By DEPARTMENTS_AND_ORGANIZATIONS_SECTION_TITLE = By.xpath("//h3[contains(text(),'Departments & Organizations')]");
    private final By ORGANIZATION_LINK = By.xpath("//li[@class='organization-hyperlink-list__item']");
    private final By RELATED_MEDIA_SECTION_TITLE = By.xpath("//h3[contains(text(), 'Related Media')]");
    private final By MEDIA_LIST = By.xpath(".//div[@class='slick-list']");
    private final By MEDIA_PREVIEW = By.xpath(".//ul[@class='media-gallery__preview-gallery']");
    private final By PATIENT_CARE_TAB = By.xpath("//li[@role='tab']//h2[contains(text(),'Patient Care')]");
    private final By LOCATIONS_TAB = By.xpath("//li[@role='tab']//h2[contains(text(), 'Locations')]");
    private final By NEWS_TAB = By.xpath("//li[@role='tab']//h2[contains(text(), 'News')]");


    public ProfilePage() {
        super(BIOGRAPHY_TAB);
    }

    @Step("Get Profile Name")
    public String getPeopleName() {
        Log.logInfo("Get Profile Name");
        BaseElement.waitElementToBeClickable(BIOGRAPHY_TAB);
        return BaseElement.getText(PROFILE_NAME);
    }

    @Step("Verify image displayed")
    public boolean isImageDisplayed() {
        Log.logInfo("Check image displayed");
        return baseElement.isElementDisplayed(PROFILE_IMAGE);
    }

    @Step("Verify name is displayed")
    public boolean isNameIsDisplayed() {
        Log.logInfo("Check name is displayed");
        return baseElement.isAllElementsInArrayContainText(PROFILE_NAME);
    }

    @Step("Verify Edit Profile Link is displayed")
    public boolean isEditProfileLinkDisplayed() {
        Log.logInfo("Check Edit Profile Link is displayed");
        return baseElement.isElementDisplayed(EDIT_PROFILE_LINK);
    }

    @Step("Verify Share Panel Link is displayed")
    public boolean isSharePanelDisplayed() {
        Log.logInfo("Check Share Panel Link is displayed");
        return baseElement.isElementDisplayed(SHARE_PANEL);
    }

    @Step("Verify that there are tabs")
    public boolean isTabExist() {
        Log.logInfo("Check there are tabs");
        return baseElement.isArrayNotEmpty(TAB);
    }

    @Step("Click Download Photo Button")
    public ProfilePage clickDownloadPhotoButton() {
        Log.logInfo("Click Download Photo Button");
        baseElement.clickElement(DOWNLOAD_PHOTO_BUTTON);
        return this;
    }

    public void switchToNewTab() {
        browser.switchToNewTab();
    }

    @Step("Verify that image is downloaded")
    public boolean isDownLoadedImageDisplayed() {
        Log.logInfo("Check that image is downloaded");
        return baseElement.isElementDisplayed(DOWNLOADED_IMAGE);
    }

    @Step("Verify the Title is displayed")
    public boolean isTitleDisplayed() {
        Log.logInfo("Check the Title is displayed");
        return baseElement.isAllElementsInArrayContainText(PROFILE_TITLE);
    }

    @Step("Verify the Contact Information Section")
    public boolean isContactInformationDisplayed() {
        Log.logInfo("Check the Contact Information Section");
        return baseElement.isElementDisplayed(CONTACT_INFORMATION_SECTION);
    }

    @Step("Open Research and Publications Tab")
    public ResearchTab openResearchAndPublicationTab() {
        Log.logInfo("Open Research and Publications Tab");
        BaseElement.waitElementToBeClickable(EDIT_PROFILE_LINK);
        baseElement.clickWithJS(RESEARCH_PUBLICATION_TAB);
        return new ResearchTab();
    }

    @Step("Verify research interests contain inputted research area")
    public boolean isResearchInterestContainInputResearchArea(String researchArea) {
        Log.logInfo("Check research interests contain inputted research area - " + researchArea);
        return baseElement.isElementContainsText(RESEARCH_INTERESTS, researchArea);
    }

    @Step("Verify that title contains inputted text")
    public boolean isTitleContainsSearchValue(String text) {
        Log.logInfo("Check that title contains text");
        BaseElement.waitElementToBeClickable(BIOGRAPHY_TAB);
        return baseElement.isElementContainsText(PROFILE_NAME, text);
    }

    @Step("Click Download CV button")
    public ProfilePage clickDownloadCVButton() {
        Log.logInfo("Click Download CV button");
        baseElement.clickElement(DOWNLOAD_CV);
        BaseElement.waitForElementDisappear(DOWNLOAD_CV);
        return this;
    }

    @Step("Verify that URL contains text")
    public boolean isURLContainsText(String URL, String text) {
        Log.logInfo("Check that URL " + URL + "  contains text " + text);
        return URL.contains(text);
    }

    @Step("Verify Contact Information Title Displayed")
    public boolean isContactInformationTitleDisplayed() {
        Log.logInfo("Check Contact Information Title Displayed");
        return baseElement.isArrayNotEmpty(CONTACT_INFORMATION_TITLE);
    }

    @Step("Verify Contact Information Section is not Empty")
    public boolean isContactInformationSectionNotEmpty() {
        Log.logInfo("Check Contact Information Section is Not Empty");
        return baseElement.isAllElementsInArrayContainText(CONTACT_INFORMATION_SECTION);
    }

    @Step("Verify General Info Section is not Empty")
    public boolean isGeneralInfoSectionNotEmpty() {
        Log.logInfo("Check General Info Section is not Empty");
        return baseElement.isArrayNotEmpty(GENERAL_INFO);
    }

    @Step("Verify Patient Care Location Title Displayed")
    public boolean isPatientCareLocationTitleDisplayed() {
        Log.logInfo("Check Patient Care Location Title Displayed");
        return baseElement.isArrayNotEmpty(PATIENT_CARE_LOCATION_TITLE);
    }

    @Step("Verify Mailing Address Title Displayed")
    public boolean isMailingAddressTitleDisplayed() {
        Log.logInfo("Check Mailing Address Title Displayed");
        return baseElement.isArrayNotEmpty(MAILING_ADDRESS_TITLE);
    }

    @Step("Verify Mailing Address Section is not Empty")
    public boolean isMailingAddressSectionNotEmpty() {
        Log.logInfo("Check Mailing Address Section is not Empty");
        return baseElement.isArrayNotEmpty(MAILING_ADDRESS_SECTION);
    }

    @Step("Verify Lab Website Button is Displayed")
    public boolean isLabWebsiteButtonDisplayed() {
        Log.logInfo("Check Lab Website Button is Displayed");
        return baseElement.isArrayNotEmpty(LAB_WEBSITE_BUTTON);
    }

    @Step("Verify Download CV is Displayed")
    public boolean isDownloadCVButtonDisplayed() {
        Log.logInfo("Check Download CV is Displayed");
        return baseElement.isArrayNotEmpty(DOWNLOAD_CV);
    }

    @Step("Verify Related Links Section Title is Displayed")
    public boolean isRelatedLinksSectionTitleDisplayed() {
        Log.logInfo("Check Related Links Section Title is Displayed");
        return baseElement.isArrayNotEmpty(RELATED_LINKS_SECTION_TITLE);
    }

    @Step("Verify Related Links Section is not Empty")
    public boolean isRelatedLinksSectionNotEmpty() {
        Log.logInfo("Check Related Links Section is not Empty");
        return baseElement.isAllElementsInArrayContainText(RELATED_LINKS_ITEM);
    }

    @Step("Verify YM link is Displayed")
    public boolean isYMLInkDisplayed() {
        Log.logInfo("Check YM link is Displayed");
        return baseElement.isArrayNotEmpty(YM_LINK);
    }

    @Step("Verify Biography Section Title is Displayed")
    public boolean isBiographySectionTitleDisplayed() {
        Log.logInfo("Check Biography Section Title is Displayed");
        return baseElement.isArrayNotEmpty(BIOGRAPHY_SECTION_TITLE);
    }

    @Step("Verify Biography Section is not Empty")
    public boolean isBiographySectionNotEmpty(String text) {
        Log.logInfo("Check Biography Section is not Empty");
        return baseElement.isElementContainsText(BIOGRAPHY_SECTION, text);
    }

    @Step("Verify Education And Training Section Title is Displayed")
    public boolean isEducationAndTrainingSectionTitleDisplayed() {
        Log.logInfo("Check Education And Training Section Title is Displayed");
        return baseElement.isArrayNotEmpty(EDUCATION_AND_TRAINING_SECTION_TITLE);
    }

    @Step("Verify each Education item has degree")
    public boolean isEducationItemsHaveDegree() {
        Log.logInfo("Check each Education item has degree");
        return baseElement.isElementInsideOtherContainText(EDUCATION_ITEM, DEGREE_ITEM);
    }

    @Step("Verify each Education item has institution")
    public boolean isEducationItemsHaveInstitution() {
        Log.logInfo("Check each Education item has institution");
        return baseElement.isElementInsideOtherContainText(EDUCATION_ITEM, INSTITUTION_ITEM);
    }

    @Step("Verify Activities Section Title is Displayed")
    public boolean isActivitiesSectionTitleDisplayed() {
        Log.logInfo("Check Activities Section Title is Displayed");
        return baseElement.isArrayNotEmpty(ACTIVITIES_SECTION_TITLE);
    }

    @Step("Verify each Activity item has title")
    public boolean isActivityItemsHaveTitle() {
        Log.logInfo("Check each Activity item has title");
        return baseElement.isElementInsideOtherContainText(ACTIVITY_ITEM, ACTIVITY_ITEM_TITLE);
    }

    @Step("Verify each Activity item has place")
    public boolean isActivityItemsHavePlace() {
        Log.logInfo("Check each Activity item has place");
        return baseElement.isElementInsideOtherContainText(ACTIVITY_ITEM, ACTIVITY_ITEM_PLACE);
    }

    @Step("Verify each Activity item has description")
    public boolean isActivityItemsHaveDescription() {
        Log.logInfo("Check each Activity item has description");
        return baseElement.isElementInsideOtherContainText(ACTIVITY_ITEM, ACTIVITY_ITEM_DESCRIPTION);
    }

    @Step("Verify Honors & Recognition Section Title Displayed")
    public boolean isHonorsRecognitionSectionTitleDisplayed() {
        Log.logInfo("Check Honors & Recognition Section Title Displayed");
        return baseElement.isArrayNotEmpty(HONORS_AND_RECOGNITION_SECTION_TITLE);
    }

    @Step("Verify Departments And Organizations Section Title Displayed")
    public boolean isDepartmentsOrganizationsSectionTitleDisplayed() {
        Log.logInfo("Check Departments And Organizations Section Title Displayed");
        return baseElement.isArrayNotEmpty(DEPARTMENTS_AND_ORGANIZATIONS_SECTION_TITLE);
    }

    @Step("Verify Departments And Organizations Section is not Empty")
    public boolean isDepartmentsOrganizationsSectionNotEmpty() {
        Log.logInfo("Check Departments And Organizations Section is not Empty");
        return baseElement.isAllElementsInArrayContainText(ORGANIZATION_LINK);
    }

    @Step("Verify Related Media Section Title Displayed")
    public boolean isRelatedMediaSectionTitleDisplayed() {
        Log.logInfo("Check Related Media Section Title Displayed");
        return baseElement.isArrayNotEmpty(RELATED_MEDIA_SECTION_TITLE);
    }

    @Step("Verify Media List Displayed")
    public boolean isMediaListDisplayed() {
        Log.logInfo("Check Media List Displayed");
        return baseElement.isArrayNotEmpty(MEDIA_LIST);
    }

    @Step("Verify Media Preview Displayed")
    public boolean isMediaPreviewDisplayed() {
        Log.logInfo("Check Media Preview Displayed");
        return baseElement.isArrayNotEmpty(MEDIA_PREVIEW);
    }

    @Step("Open Patient Care Tab")
    public PatientCareTab openPatientCareTab() {
        Log.logInfo("Open Research and Publications Tab");
        baseElement.clickWithJS(PATIENT_CARE_TAB);
        return new PatientCareTab();
    }

    @Step("Open Locations Tab")
    public LocationsTab openLocationsTab() {
        Log.logInfo("Open Locations Tab");
        baseElement.clickWithJS(LOCATIONS_TAB);
        return new LocationsTab();
    }

    @Step("Open News Tab")
    public NewsTab openNewsTab() {
        Log.logInfo("Open News Tab");
        baseElement.clickWithJS(NEWS_TAB);
        return new NewsTab();
    }
}
