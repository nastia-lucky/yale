package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class EventPage extends BasePage {

    private final By EVENT_TITLE = By.xpath("//h1[@class='event-details-header__title']");
    private final By EVENT_DATE = By.xpath("//div[@class='event-details-date']");
    private final By EVENT_TIME = By.xpath("//div[@class='event-details-time']");
    private final By EVENT_ADMISSION = By.xpath("//section[@aria-label='Admission']");
    private static final By SHARE_VIA_TWITTER_LINK = By.xpath("//button[@aria-label='Share via Twitter']");
    private final By EVENT_AUDIENCE = By.xpath("//span[@class='event-details-audience']");
    private final By EVENT_TAGS = By.xpath("//section[@aria-label='Tags']//div[@class='named-section__content-wrapper']");
    private final By EVENT_TAG = By.xpath("//section[@aria-label='Tag']//div[@class='named-section__content-wrapper']");
    private final By EVENT_MODAL_TITLE = By.xpath("//h2[@class='event-details-header__title']");
    private final By EDIT_EVENT_LINK = By.xpath("//a[contains(text(), 'Edit This Event')]");
    private final By SPEAKER_SECTION_TITLE = By.xpath("//h2[contains(text(), 'Speaker')]");
    private final By SPEAKERS_SECTION_TITLE = By.xpath("//h2[contains(text(), 'Speakers')]");
    private final By SPEAKER_ITEM = By.xpath("//span[@class='profile-detailed-info-list-item__name']");
    private final By CONTACT_SECTION_TITLE = By.xpath("//h2[contains(text(),'Contact')]");
    private final By CONTACTS_SECTION_TITLE = By.xpath("//h2[contains(text(),'Contacts')]");
    private final By CONTACTS_ITEM = By.xpath("//section[@aria-label='Contacts']//div[@class='profile-contact__contact-name']");
    private final By CONTACT_ITEM = By.xpath("//section[@aria-label='Contact']//div[@class='profile-contact__contact-name']");
    private final By HOST_SECTION_TITLE = By.xpath("//section[@aria-label='Host']//h2[contains(text(), 'Host')]");
    private final By HOSTS_SECTION_TITLE = By.xpath("//section[@aria-label='Hosts']//h2[contains(text(), 'Hosts')]");
    private final By HOSTS_ITEM = By.xpath("//section[@aria-label='Hosts']//div[@class='profile-contact__contact-name']");
    private final By HOST_ITEM = By.xpath("//section[@aria-label='Host']//div[@class='profile-contact__contact-name']");
    private final By HOST_ORGANIZATION_SECTION_TITLE = By.xpath("//section[@aria-label='Host Organization']//h2[contains(text(),'Host Organization' )]");
    private final By HOST_ORGANIZATIONS_SECTION_TITLE = By.xpath("//section[@aria-label='Host Organizations']//h2[contains(text(),'Host Organizations')]");
    private final By HOST_ORGANIZATIONS_ITEM = By.xpath("//section[@aria-label='Host Organizations']//span[@class='link__label']");
    private final By HOST_ORGANIZATION_ITEM = By.xpath("//section[@aria-label='Host Organization']//span[@class='link__label']");
    private final By TAGS_SECTION_TITLE = By.xpath("//h2[contains(text(), 'Tags')]");
    private final By TAG_SECTION_TITLE = By.xpath("//h2[contains(text(), 'Tag')]");
    private final By TAGS_ITEM = By.xpath("//section[@aria-label='Tags']//div");
    private final By TAG_ITEM = By.xpath("//section[@aria-label='Tag']//div");
    private final By FOOD_SECTION_TITLE = By.xpath("//h2[contains(text(),'Food')]");
    private final By FOOD_ITEM = By.xpath("//section[@aria-label='Food']//div");
    private final By EVENT_RSVP_SECTION_TITLE = By.xpath("//h2[contains(text(),'Event RSVP')]");
    private final By EVENT_RSVP_ITEM = By.xpath("//section[@aria-label='Event RSVP']//span[@class='link__label']");
    private final By RSVP_BUTTON = By.xpath("//div[@class='event-details-info__rsvp-button']");
    private final By RELATED_LINK_SECTION_TITLE = By.xpath("//h2[contains(text(), 'Related Link')]");
    private final By RELATED_LINKS_SECTION_TITLE = By.xpath("//h2[contains(text(), 'Related Links')]");
    private final By RELATED_LINK_ITEM = By.xpath("//section[@aria-label='List of Related Link links']//span[@class='link__label']");
    private final By DOWNLOAD_FLYER_BUTTON = By.xpath("//span[contains(text(),'Download Flyer')]");
    private final By MAP = By.xpath("//div[@aria-label='Map']");
    private final By ADD_SINGLE_EVENT_LINK = By.xpath("//span[contains(text(),'Add single event to Calendar')]");
    private final By EVENT_HEADER_LINK = By.xpath("//div[@class='event-details-header__link-panel']//a[@tabindex='0']");


    public EventPage() {
        super(SHARE_VIA_TWITTER_LINK);
    }

    @Step("Verify that Event date is displayed")
    public boolean isEventDateIsDisplayed() {
        Log.logInfo("Check that Event date is displayed");
        return baseElement.isElementDisplayed(EVENT_DATE);
    }

    @Step("Verify that Event Time is displayed")
    public boolean eventTimeIsDisplayed() {
        Log.logInfo("Check that Event Time is displayed");
        return baseElement.isElementDisplayed(EVENT_TIME);
    }

    @Step("Verify that Event Admission is displayed")
    public boolean isEventAdmissionIsDisplayed() {
        Log.logInfo("Check that Event Admission is displayed");
        return baseElement.isElementDisplayed(EVENT_ADMISSION);
    }

    @Step("Verify that Share via Twitter Link is displayed")
    public boolean isShareViaTwitterLinkDisplayed() {
        Log.logInfo("Check that Share via Twitter Link is displayed");
        return baseElement.isElementDisplayed(SHARE_VIA_TWITTER_LINK);
    }

    @Step("Verify that Event Audience is displayed")
    public boolean isEventAudienceDisplayed() {
        Log.logInfo("Check that Event Audience is displayed");
        return baseElement.isElementDisplayed(EVENT_AUDIENCE);
    }

    @Step("Get Event Audience")
    public String getAudience() {
        Log.logInfo("Get Event Audience");
        BaseElement.waitElementToBeClickable(SHARE_VIA_TWITTER_LINK);
        return BaseElement.getText(EVENT_AUDIENCE);
    }

    @Step("Check that Event Tags contain chosen keyword")
    public boolean isTagsContainsChosenEventType(String chosenTag) {
        Log.logInfo("Check that Event Tags contain chosen keyword " + chosenTag);
        BaseElement.waitElementToBeClickable(SHARE_VIA_TWITTER_LINK);
        return baseElement.isElementsContainText(EVENT_TAG, EVENT_TAGS, chosenTag);
    }

    @Step("Get Event Title")
    public String getEventTitle() {
        Log.logInfo("Get Event Title");
        BaseElement.waitElementToBeClickable(SHARE_VIA_TWITTER_LINK);
        return BaseElement.getText(EVENT_TITLE);
    }

    @Step("Get Event Modal Title")
    public String getEventModalTitle() {
        Log.logInfo("Get Event Modal Title");
        BaseElement.waitElementToBeClickable(EDIT_EVENT_LINK);
        return BaseElement.getText(EVENT_MODAL_TITLE);
    }

    @Step("Verify Speaker Section Title is displayed")
    public boolean isSpeakerSectionTitleDisplayed() {
        Log.logInfo("Check Speaker Section Title is displayed");
        return baseElement.isOneOfTwoArraysIsNotEmpty(SPEAKER_SECTION_TITLE, SPEAKERS_SECTION_TITLE);
    }

    @Step("Verify Speaker Section is not empty")
    public boolean isSpeakerSectionNotEmpty() {
        Log.logInfo("Check Speaker Section is not empty");
        return baseElement.isAllElementsInArrayContainText(SPEAKER_ITEM);
    }

    @Step("Verify Contact Section Title is displayed")
    public boolean isContactSectionTitleDisplayed() {
        Log.logInfo("Check Contact Section Title is displayed");
        return baseElement.isOneOfTwoArraysIsNotEmpty(CONTACT_SECTION_TITLE, CONTACTS_SECTION_TITLE);
    }

    @Step("Verify Contact Section is not empty")
    public boolean isContactSectionNotEmpty() {
        Log.logInfo("Check Contact Section is not empty");
        return baseElement.isAllElementsFromOneOfTheArrayContainText(CONTACT_ITEM, CONTACTS_ITEM);
    }

    @Step("Verify Host Section Title is displayed")
    public boolean isHostSectionTitleDisplayed() {
        Log.logInfo("Check Host Section Title is displayed");
        return baseElement.isOneOfTwoArraysIsNotEmpty(HOST_SECTION_TITLE, HOSTS_SECTION_TITLE);
    }

    @Step("Verify Host Section is not empty")
    public boolean isHostSectionNotEmpty() {
        Log.logInfo("Check Host Section is not empty");
        return baseElement.isAllElementsFromOneOfTheArrayContainText(HOST_ITEM, HOSTS_ITEM);
    }

    @Step("Verify Host Organization Section Title is displayed")
    public boolean isHostOrganizationSectionTitleDisplayed() {
        Log.logInfo("Check Host Organization Section Title is displayed");
        return baseElement.isOneOfTwoArraysIsNotEmpty(HOST_ORGANIZATION_SECTION_TITLE, HOST_ORGANIZATIONS_SECTION_TITLE);
    }

    @Step("Verify Host Organization Section is not empty")
    public boolean isHostOrganizationSectionNotEmpty() {
        Log.logInfo("Check Host Organization Section is not empty");
        return baseElement.isOneOfTwoArraysIsNotEmpty(HOST_ORGANIZATION_ITEM, HOST_ORGANIZATIONS_ITEM);
    }

    @Step("Verify Tags Section Title is displayed")
    public boolean isTagsSectionTitleIsDisplayed() {
        Log.logInfo("Check Tags Section Title is displayed");
        return baseElement.isOneOfTwoArraysIsNotEmpty(TAGS_SECTION_TITLE, TAG_SECTION_TITLE);
    }

    @Step("Verify Tags Section is not empty")
    public boolean isTagsSectionNotEmpty() {
        Log.logInfo("Check Tags Section is not empty");
        return baseElement.isOneOfTwoArraysIsNotEmpty(TAG_ITEM, TAGS_ITEM);
    }

    @Step("Verify Food Section Title is Displayed")
    public boolean isFoodSectionTitleDisplayed() {
        Log.logInfo("Check Food Section Title is Displayed");
        return baseElement.isArrayNotEmpty(FOOD_SECTION_TITLE);
    }

    @Step("Verify Food Section is not empty")
    public boolean isFoodSectionNotEmpty() {
        Log.logInfo("Check Food Section is not empty");
        return baseElement.isAllElementsInArrayContainText(FOOD_ITEM);
    }

    @Step("Verify Event RSVP Section Title is Displayed")
    public boolean isEventRSVPSectionTitleDisplayed() {
        Log.logInfo("Check Event RSVP Section Title is Displayed");
        return baseElement.isArrayNotEmpty(EVENT_RSVP_SECTION_TITLE);
    }

    @Step("Verify Event RSVP Section is not empty")
    public boolean isEventRSVPSectionNotEmpty() {
        Log.logInfo("Check Event RSVP Section is not empty");
        return baseElement.isAllElementsInArrayContainText(EVENT_RSVP_ITEM);
    }

    @Step("Verify RSVP Button is displayed")
    public boolean isEventRSVPButtonDisplayed() {
        Log.logInfo("Check RSVP Button is displayed");
        return baseElement.isArrayNotEmpty(RSVP_BUTTON);
    }

    @Step("Verify Related Links Section Title is displayed")
    public boolean isRelatedLinksSectionTitleIsDisplayed() {
        Log.logInfo("Check Related Links Section Title is displayed");
        return baseElement.isOneOfTwoArraysIsNotEmpty(RELATED_LINKS_SECTION_TITLE, RELATED_LINK_SECTION_TITLE);
    }

    @Step("Verify Related Links Section is not empty")
    public boolean isRelatedLinksSectionNotEmpty() {
        Log.logInfo("Check Related Links Section is not empty");
        return baseElement.isArrayNotEmpty(RELATED_LINK_ITEM);
    }

    @Step("Verify Download Flyer Button is Displayed")
    public boolean isDownloadFlyerButtonDisplayed() {
        Log.logInfo("Check Download Flyer Button is Displayed");
        return baseElement.isArrayNotEmpty(DOWNLOAD_FLYER_BUTTON);
    }

    @Step("Verify Map is Displayed")
    public boolean isMapDisplayed() {
        Log.logInfo("Check Map is Displayed");
        return baseElement.isArrayNotEmpty(MAP);
    }

    @Step("Verify Add Single Event Link is Displayed")
    public boolean isAddSingleEventLinkDisplayed() {
        Log.logInfo("Check Add Single Event Link is Displayed");
        return baseElement.isArrayNotEmpty(ADD_SINGLE_EVENT_LINK);
    }

    @Step("Click first Event Header Link")
    public EventPage clickFirstEventHeaderLink() {
        Log.logInfo("Click first Event Header Link");
        baseElement.clickFirstElementFromArray(EVENT_HEADER_LINK);
        return this;
    }

    @Step("Get Current URL")
    public String getCurrentURL() {
        Log.logInfo("Get Current URL");
        return baseElement.getCurrentURL();
    }

    @Step("Verify that Current URL includes Google Map")
    public boolean isURLIncludeGoogleMapLink(String string) {
        Log.logInfo("Check that Current URL includes Google Map");
        return baseElement.isStringContainsAnotherString(string, "https://www.google.com/maps");
    }
}
