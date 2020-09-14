package yale.pageObjects;

import framework.logger.Log;
import framework.utilities.Browser;
import framework.utilities.Config;
import org.openqa.selenium.By;

public class MainPage extends BasePage {

    Config config = new Config();

    private final By LINK_TO_PATIENT_CARE_PAGE = By.xpath("//a[@aria-label='Link to Patient Care page']");
    private final By YSM_LOGO = By.xpath("//div[@class='header-top-panel__title-wrapper']");
    private final By SEARCH_PANEL = By.xpath("//div[@class='search-panel search-panel--light search-panel--thin']");
    private final By ANOUNCEMENT = By.xpath("//article[@class='announcement']");
    private final By CLOSE_POP_UP_BUTTON = By.xpath("//button[@aria-label='Close announcement']");
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
    private final By CLINICAL_TRIAL_HEADER = By.xpath("//div[@class='department-info-panel__department-link']//a[contains(text(), 'Clinical Trials at Yale')]");
    private final By TWITTER_FEED = By.xpath("//span[contains(text(), 'Follow @YaleMed')]");


    public MainPage openMainPage() {
        Log.logInfo("Open Main Page");
        browser.getUrl(config.getProperty("mainPageURL"));
        return this;
    }

    public void clickPatientCareLink() {
        Log.logInfo("Click Patient Care Link");
        Browser.waitForElementToBePresent(LINK_TO_PATIENT_CARE_PAGE);
        browser.clickElement(LINK_TO_PATIENT_CARE_PAGE);
    }

    public boolean searchPanelIsDisplayed() {
        Log.logInfo("Check that Search Panel is displayed");
        Browser.waitForElementToBePresent(SEARCH_PANEL);
        return browser.isElementDisplayed(SEARCH_PANEL);
    }

    public boolean ysmLogoIsDisplayed() {
        Log.logInfo("Check that YSM logo is displayed");
        Browser.waitForElementToBePresent(YSM_LOGO);
        return browser.isElementDisplayed(YSM_LOGO);
    }

    public boolean anouncementDisplayed() {
        Log.logInfo("Check that anouncement is displayed");
        return browser.isElementDisplayed(ANOUNCEMENT);
    }

    public void closePopUp() {
        Log.logInfo("Close Pop-up");
        browser.clickElement(CLOSE_POP_UP_BUTTON);
    }

    public String getCurrentURL() {
        Log.logInfo("Get Current URL");
        return browser.getCurrentUrl();
    }

    public void clickSiteIcon() {
        Log.logInfo("Click Site Icon");
        Browser.waitForElementToBePresent(YSM_LOGO);
        browser.clickElement(YSM_LOGO);
    }

    public void hoverOnNavigationLink() {
        Log.logInfo("Hover on navigation Link");
        Browser.waitForElementToBePresent(NAVIGATION_LINK);
        browser.hoverOnElement(NAVIGATION_LINK);
    }

    public MainPage clickMapsFooterLink() {
        Log.logInfo("Click Maps Footer Link");
        browser.clickElement(MAPS_FOOTER_LINK);
        return this;
    }

    public void clickSupportUsLink() {
        Log.logInfo("Click Support Us Link");
        Browser.waitForElementToBePresent(SUPPORT_US_LINK);
        browser.clickElement(SUPPORT_US_LINK);
    }

    public MainPage clickCalendarLink() {
        Log.logInfo("Click Calendar Link");
        Browser.waitForElementToBePresent(CALENDAR_LINK);
        browser.clickElement(CALENDAR_LINK);
        return this;
    }

    public MainPage clickLogo() {
        Log.logInfo("Click Logo");
        browser.clickElement(YSM_LOGO);
        Browser.waitForElementToBePresent(YSM_LOGO);
        return this;
    }

    public MainPage clickContactUsLink() {
        Log.logInfo("Click Contact Us Link");
        Browser.waitForElementToBePresent(CONTACT_US_LINK);
        browser.clickElement(CONTACT_US_LINK);
        return this;
    }

    public NewsPage clickNewsTitle() {
        Log.logInfo("Click News Title");
        Browser.waitForElementToBePresent(NEWS_TITLE);
        browser.clickElement(NEWS_TITLE);
        return new NewsPage();
    }

    public String getNewsTitle() {
        Log.logInfo("Get News Title");
        return browser.getText(NEWS_TITLE);
    }

    public SearchPage clickMoreTopStoriesButton() {
        Log.logInfo("Click More Top Stories Button");
        Browser.waitForElementToBeClickable(MORE_TOP_STORIES);
        browser.clickElement(MORE_TOP_STORIES);
        return new SearchPage();
    }

    public SearchPage clickMoreEventsButton() {
        Log.logInfo("Click More Events Button");
        Browser.waitForElementToBeClickable(SEE_ALL_EVENTS_BUTTON);
        browser.clickElement(SEE_ALL_EVENTS_BUTTON);
        return new SearchPage();
    }

    public SearchOverlay clickPerformSearch() {
        Log.logInfo("Click Perform Search button");
        browser.clickElement(PERFORM_SEARCH);
        return new SearchOverlay();
    }

    public String getTitleWebsitePage() {
        Log.logInfo("Get Web Site Title");
        String title = browser.getTitle();
        String websiteTitle = title.substring(title.lastIndexOf("<") + 1).trim();
        return websiteTitle;
    }

    public boolean isClinicalTrialHeaderIsDisplayed() {
        Log.logInfo("Check the Clinical Trial Header is displayed");
        Browser.waitForElementToBePresent(CLINICAL_TRIAL_HEADER);
        return browser.isElementDisplayed(CLINICAL_TRIAL_HEADER);
    }

    public EventPage openFirstEvent() {
        Log.logInfo("Open first event");
        Browser.waitForTheFirstElementFromArrayIsClickable(EVENT_TITLE);
        browser.clickFirstElementFromArray(EVENT_TITLE);
        return new EventPage();
    }

    public String getFirstEventTitle() {
        Browser.waitForTheFirstElementFromArrayIsClickable(EVENT_TITLE);
        String eventTitle = browser.getTextFirstElementFromArray(EVENT_TITLE);
        return eventTitle;
    }

    public MainPage goToTwitter() {
        Log.logInfo("Go to twitter");
        Browser.waitForElementToBeClickable(TWITTER_FEED);
        browser.clickElement(TWITTER_FEED);
        return this;
    }
}
