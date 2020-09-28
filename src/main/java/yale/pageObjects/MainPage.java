package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import org.openqa.selenium.By;

public class MainPage extends BasePage {

    private final By LINK_TO_PATIENT_CARE_PAGE = By.xpath("//a[@aria-label='Link to Patient Care page']");
    private static By YSM_LOGO = By.xpath("//div[@class='header-top-panel__title-wrapper']");
    private final By SEARCH_PANEL = By.xpath("//div[@class='search-panel search-panel--light search-panel--thin']");
    private final By NAVIGATION_LINK = By.xpath("//div[@class='navigation-link-dropdown__top-link']");
    private final By MAPS_FOOTER_LINK = By.xpath("//a[@aria-label='Maps & Directions']");
    private final By SUPPORT_US_LINK = By.xpath("//a[@aria-label='Support Us']");
    private final By CALENDAR_LINK = By.xpath("//a[@aria-label='Calendar']");
    private final By CONTACT_US_LINK = By.xpath("//a[@aria-label='Contact Us']");
    private final By NEWS_TITLE = By.xpath("//div[@class='article-post__title-wrapper']");
    private final By MORE_TOP_STORIES = By.xpath("//a[@aria-label='More Top Stories']");
    protected final By PERFORM_SEARCH = By.xpath("//button[@aria-label='Perform search']");
    private final By SEE_ALL_EVENTS_BUTTON = By.xpath("//a[@aria-label='See All Upcoming Events']");
    private final By EVENT_TITLE = By.xpath("//h3[@class='event-list-item__title']");
    private final By TWITTER_FEED = By.xpath("//span[contains(text(), 'Follow @YaleMed')]");
    private final By CLINICAL_TRIAL_RESEARCH_LINK = By.xpath("//span[contains(text(), 'Clinical Research at Yale')]");
    private final By ABOUT_YSM_LINK = By.xpath("//a[contains(text(), 'About YSM')]");
    private final By FIND_PEOPLE_LINK = By.xpath("//span[contains(text(), 'Find People')]");
    private final By A_Z_FACULTY_LINK = By.xpath("//span[contains(text(), 'A-Z Faculty List')]");
    private final By ANCHOR_ALPHABET_LINK = By.xpath("//ul[@class='category-anchors-list category-anchors-list--alphabet']//button[@tabindex=0]");

    public MainPage() {
        super(YSM_LOGO);
    }

    public MainPage openMainPage() {
        Log.logInfo("Open Main Page");
        //browser.getUrl(config.getProperty("mainPageURL"));
        return this;
    }

    public void clickPatientCareLink() {
        Log.logInfo("Click Patient Care Link");
        BaseElement.waitForElementToBePresent(LINK_TO_PATIENT_CARE_PAGE);
        baseElement.clickElement(LINK_TO_PATIENT_CARE_PAGE);
    }

    public boolean searchPanelIsDisplayed() {
        Log.logInfo("Check that Search Panel is displayed");
        BaseElement.waitForElementToBePresent(SEARCH_PANEL);
        return baseElement.isElementDisplayed(SEARCH_PANEL);
    }

    public boolean ysmLogoIsDisplayed() {
        Log.logInfo("Check that YSM logo is displayed");
        BaseElement.waitForElementToBePresent(YSM_LOGO);
        return baseElement.isElementDisplayed(YSM_LOGO);
    }

    public String getCurrentURL() {
        Log.logInfo("Get Current URL");
        return browser.getCurrentUrl();
    }

    public void clickSiteIcon() {
        Log.logInfo("Click Site Icon");
        BaseElement.waitForElementToBePresent(YSM_LOGO);
        baseElement.clickElement(YSM_LOGO);
    }

    public void hoverOnNavigationLink() {
        Log.logInfo("Hover on navigation Link");
        BaseElement.waitForElementToBePresent(NAVIGATION_LINK);
        baseElement.hoverOnElement(NAVIGATION_LINK);
    }

    public MainPage clickMapsFooterLink() {
        Log.logInfo("Click Maps Footer Link");
        baseElement.clickElement(MAPS_FOOTER_LINK);
        return this;
    }

    public void clickSupportUsLink() {
        Log.logInfo("Click Support Us Link");
        BaseElement.waitForElementToBePresent(SUPPORT_US_LINK);
        baseElement.clickElement(SUPPORT_US_LINK);
    }

    public MainPage clickCalendarLink() {
        Log.logInfo("Click Calendar Link");
        BaseElement.waitForElementToBePresent(CALENDAR_LINK);
        baseElement.clickElement(CALENDAR_LINK);
        return this;
    }

    public MainPage clickLogo() {
        Log.logInfo("Click Logo");
        baseElement.clickElement(YSM_LOGO);
        BaseElement.waitForElementToBePresent(YSM_LOGO);
        return this;
    }

    public MainPage clickContactUsLink() {
        Log.logInfo("Click Contact Us Link");
        BaseElement.waitForElementToBePresent(CONTACT_US_LINK);
        baseElement.clickElement(CONTACT_US_LINK);
        return this;
    }

    public NewsPage clickNewsTitle() {
        Log.logInfo("Click News Title");
        BaseElement.waitForElementToBePresent(NEWS_TITLE);
        baseElement.clickElement(NEWS_TITLE);
        return new NewsPage();
    }

    public String getNewsTitle() {
        Log.logInfo("Get News Title");
        return baseElement.getText(NEWS_TITLE);
    }

    public SearchPage clickMoreTopStoriesButton() {
        Log.logInfo("Click More Top Stories Button");
        BaseElement.waitForElementToBeClickable(MORE_TOP_STORIES);
        baseElement.clickElement(MORE_TOP_STORIES);
        return new SearchPage();
    }

    public SearchPage clickMoreEventsButton() {
        Log.logInfo("Click More Events Button");
        BaseElement.waitForElementToBeClickable(SEE_ALL_EVENTS_BUTTON);
        baseElement.clickElement(SEE_ALL_EVENTS_BUTTON);
        return new SearchPage();
    }

    public SearchOverlay clickPerformSearch() {
        Log.logInfo("Click Perform Search button");
        baseElement.clickElement(PERFORM_SEARCH);
        return new SearchOverlay();
    }

    public String getTitleWebsitePage() {
        Log.logInfo("Get Web Site Title");
        String title = baseElement.getTitle();
        String websiteTitle = title.substring(title.lastIndexOf("<") + 1).trim();
        return websiteTitle;
    }

    public boolean isClinicalTrialHeaderIsDisplayed() {
        Log.logInfo("Check the Clinical Trial Header is displayed");
        BaseElement.waitForElementToBeClickable(CLINICAL_TRIAL_RESEARCH_LINK);
        return baseElement.isElementDisplayed(CLINICAL_TRIAL_RESEARCH_LINK);
    }

    public EventPage openFirstEvent() {
        Log.logInfo("Open first event");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(EVENT_TITLE);
        baseElement.clickFirstElementFromArray(EVENT_TITLE);
        return new EventPage();
    }

    public String getFirstEventTitle() {
        BaseElement.waitForTheFirstElementFromArrayIsClickable(EVENT_TITLE);
        String eventTitle = baseElement.getTextFirstElementFromArray(EVENT_TITLE);
        return eventTitle;
    }

    public MainPage goToTwitter() {
        Log.logInfo("Go to twitter");
        BaseElement.waitForElementToBeClickable(TWITTER_FEED);
        baseElement.clickElement(TWITTER_FEED);
        return this;
    }

    public MainPage goAToAboutYSMPage() {
        Log.logInfo("Go to AboutYSMPage");
        BaseElement.waitForElementToBeClickable(ABOUT_YSM_LINK);
        baseElement.clickWithJS(ABOUT_YSM_LINK);
        return this;
    }

    public MainPage goToFindPeoplePage() {
        Log.logInfo("Go to Find People Page");
        BaseElement.waitForElementToBeClickable(FIND_PEOPLE_LINK);
        baseElement.clickWithJS(FIND_PEOPLE_LINK);
        return this;
    }

    public MainPage goToA_ZFacultyPage() {
        Log.logInfo("Go to A_Z Faculty Page");
        BaseElement.waitForElementToBeClickable(A_Z_FACULTY_LINK);
        baseElement.clickWithJS(A_Z_FACULTY_LINK);
        return this;
    }

    public String clickAnyAnchorLink() {
        Log.logInfo("Click Any Anchor Link");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(ANCHOR_ALPHABET_LINK);
        String anchorLetter = baseElement.chooseValueFromArrayAndGetText(ANCHOR_ALPHABET_LINK);
        return anchorLetter;
    }

    public boolean isTheFirstLetterCoincidesWithTheChosenAnchorLink(String chosenLetter) {
        Log.logInfo("Get Text Active Element");
        return baseElement.isChosenValueCoincidesWithTheFirstLetterOnFocus(chosenLetter);
    }
}
