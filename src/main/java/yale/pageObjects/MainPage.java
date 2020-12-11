package yale.pageObjects;

import framework.BaseElement;
import framework.Browser;
import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.LocalDate;

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
    private final By SIDEBAR_NAVIGATION_GIVING = By.xpath("//span[@class='sidebar-navigation__link-text'][contains(text(), 'Giving')]");
    private final By SELECT_EVENT_BUTTON = By.xpath("//span[contains(text(), 'Select')]");
    private final By CONTACT_US_TITLE = By.xpath("//h1[@class='generic-content__title']");
    private final By BACK_TO_TOP = By.xpath("//button[@class='back-to-top-button__button']");
    private final By EVENT_AUDIENCE = By.xpath("//span[@class='event-list-item-audience event-list-item__audience event-list-item__audience--right-directed']");
    private final By EVENT_ITEM = By.xpath("//li[@class='event-list__item']");
    private final By EVENT_DATE = By.xpath("//div[@class='event-list-item__event-time-container']");
    private final By LATEST_NEWS_SECTION = By.xpath("(//ul[@class='article-list__post-container'])[2]");
    private final By NEWS_DATE = By.xpath(".//span[@class='article-post-date']");
    private final By GOOGLE_LINK = By.xpath(".//a[contains(@href, 'google.com/maps')]");
    private final By TWEET_FEED = By.xpath("//div[@class='tweet twitter-feed__tweet']");
    private final By TWEET_AUTHOR = By.xpath("//span[@class='tweet__author']");
    private final By TWEET_TIME = By.xpath("//span[@class='tweet__time']");
    private final By TWEET_TEXT = By.xpath("//div[@class='tweet twitter-feed__tweet']/span[2]");
    private final By DIVERSITY_LINK = By.xpath("//a[@aria-label='Link to Diversity page']");
    private final By TRAINING_LINK = By.xpath("//a[@aria-label='Link to Diversity page']//[contains(text(), 'Training')]");
    private final By HEADER = By.xpath("//header[@id='navigation-header']");

    public MainPage() {
        super(YSM_LOGO);
    }

    @Step("Open Main Page")
    public MainPage openMainPage() {
        Log.logInfo("Open Main Page");
        return this;
    }

    @Step("Click Patient Care Link")
    public void clickPatientCareLink() {
        Log.logInfo("Click Patient Care Link");
        baseElement.clickElement(LINK_TO_PATIENT_CARE_PAGE);
    }

    @Step("Verify that Search Panel is displayed")
    public boolean searchPanelIsDisplayed() {
        Log.logInfo("Check that Search Panel is displayed");
        return baseElement.isElementDisplayed(SEARCH_PANEL);
    }

    @Step("Verify that YSM logo is displayed")
    public boolean ysmLogoIsDisplayed() {
        Log.logInfo("Check that YSM logo is displayed");
        return baseElement.isElementDisplayed(YSM_LOGO);
    }

    @Step("Get Current URL")
    public String getCurrentURL() {
        Log.logInfo("Get Current URL");
        return Browser.getCurrentUrl();
    }

    @Step("Click Site Icon")
    public void clickSiteIcon() {
        Log.logInfo("Click Site Icon");
        baseElement.clickElement(YSM_LOGO);
    }

    public void hoverOnNavigationLink() {
        Log.logInfo("Hover on navigation Link");
        baseElement.hoverOnElement(NAVIGATION_LINK);
    }

    @Step("Click Maps Footer Link")
    public MainPage clickMapsFooterLink() {
        Log.logInfo("Click Maps Footer Link");
        baseElement.clickElement(MAPS_FOOTER_LINK);
        return this;
    }

    @Step("Click Support Us Link")
    public void clickSupportUsLink() {
        Log.logInfo("Click Support Us Link");
        baseElement.clickElement(SUPPORT_US_LINK);
        BaseElement.waitForElementToBeClickable(SIDEBAR_NAVIGATION_GIVING);
    }

    @Step("Click Calendar Link")
    public MainPage clickCalendarLink() {
        Log.logInfo("Click Calendar Link");
        baseElement.clickElement(CALENDAR_LINK);
        BaseElement.waitForElementToBeClickable(SELECT_EVENT_BUTTON);
        return this;
    }

    @Step("Click Logo")
    public MainPage clickLogo() {
        Log.logInfo("Click Logo");
        baseElement.clickElement(YSM_LOGO);
        return this;
    }

    @Step("Click Contact Us Link")
    public MainPage clickContactUsLink() {
        Log.logInfo("Click Contact Us Link");
        baseElement.clickElement(CONTACT_US_LINK);
        BaseElement.waitForText(CONTACT_US_TITLE, "Contact YSM");
        return this;
    }

    @Step("Click News Title")
    public NewsPage clickNewsTitle() {
        Log.logInfo("Click News Title");
        baseElement.clickElement(NEWS_TITLE);
        return new NewsPage();
    }

    @Step("Get News Title")
    public String getNewsTitle() {
        Log.logInfo("Get News Title");
        return BaseElement.getText(NEWS_TITLE);
    }

    @Step("Click More Top Stories Button")
    public SearchPage clickMoreTopStoriesButton() {
        Log.logInfo("Click More Top Stories Button");
        baseElement.clickElement(MORE_TOP_STORIES);
        return new SearchPage();
    }

    @Step("Click More Top Stories Button")
    public SearchPage clickMoreEventsButton() {
        Log.logInfo("Click More Events Button");
        baseElement.clickElement(SEE_ALL_EVENTS_BUTTON);
        return new SearchPage();
    }

    @Step("Click Perform Search button")
    public SearchOverlay clickPerformSearch() {
        Log.logInfo("Click Perform Search button");
        baseElement.clickElement(PERFORM_SEARCH);
        return new SearchOverlay();
    }

    @Step("Get Web Site Title")
    public String getTitleWebsitePage() {
        Log.logInfo("Get Web Site Title");
        String title = baseElement.getTitle();
        return title.substring(title.lastIndexOf("<") + 1).trim();
    }

    @Step("Verify the Clinical Trial Header is displayed")
    public boolean isClinicalTrialHeaderIsDisplayed() {
        Log.logInfo("Check the Clinical Trial Header is displayed");
        BaseElement.waitForElementToBeClickable(CLINICAL_TRIAL_RESEARCH_LINK);
        return baseElement.isElementDisplayed(CLINICAL_TRIAL_RESEARCH_LINK);
    }

    @Step("Open first event")
    public EventPage openFirstEvent() {
        Log.logInfo("Open first event");
        baseElement.clickFirstElementFromArray(EVENT_TITLE);
        return new EventPage();
    }

    @Step("Get first event info")
    public String getFirstEventTitle() {
        Log.logInfo("Get first event info");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(EVENT_TITLE);
        return baseElement.getTextFirstElementFromArray(EVENT_TITLE);
    }

    @Step("Click Twitter Feed button")
    public MainPage goToTwitter() {
        Log.logInfo("Go to twitter");
        baseElement.clickElement(TWITTER_FEED);
        return this;
    }

    @Step("Go to AboutYSMPage")
    public MainPage goAToAboutYSMPage() {
        Log.logInfo("Go to AboutYSMPage");
        baseElement.clickWithJS(ABOUT_YSM_LINK);
        return this;
    }

    @Step("Click Find People link")
    public MainPage goToFindPeoplePage() {
        Log.logInfo("Go to Find People Page");
        baseElement.clickWithJS(FIND_PEOPLE_LINK);
        return this;
    }

    @Step("Click A_Z Faculty Link")
    public MainPage goToA_ZFacultyPage() {
        Log.logInfo("Go to A_Z Faculty Page");
        baseElement.clickWithJS(A_Z_FACULTY_LINK);
        return this;
    }

    @Step("Click Any Anchor Link")
    public String clickAnyAnchorLink() {
        Log.logInfo("Click Any Anchor Link");
        return baseElement.chooseValueFromArrayAndGetText(ANCHOR_ALPHABET_LINK);
    }

    @Step("Get Text of first Active Element")
    public boolean isTheFirstLetterCoincidesWithTheChosenAnchorLink(String chosenLetter) {
        Log.logInfo("Get Text Active Element");
        return baseElement.isChosenValueCoincidesWithTheFirstLetterOnFocus(chosenLetter);
    }

    @Step("Click Back To Top Button")
    public Long clickBackToTopButton() throws InterruptedException {
        Log.logInfo("Click Back To Top Button");
        baseElement.clickElement(BACK_TO_TOP);
        Thread.sleep(2000);
        return baseElement.getYPosition();
    }

    @Step("Scroll page to the bottom")
    public Long scrollPageToTheBottomAndGetYPosition() {
        Log.logInfo("Scroll page to the bottom and get Y position");
        Long position = baseElement.getYPosition();
        baseElement.scrollToTheBottom();
        return position;
    }

    @Step("Check that amount of Audience elements coincide with expected")
    public boolean isAllEventsContainAudience() {
        Log.logInfo("Check that amount of Audience elements coincide with expected");
        return baseElement.isAmountOfElementsCoincideWithExpected(EVENT_ITEM, EVENT_AUDIENCE);
    }

    @Step("Check that amount of Title  elements  for events coincide with expected")
    public boolean isAllEventsContainTitle() {
        Log.logInfo("Check that amount of Title  elements  for events coincide with expected");
        return baseElement.isAmountOfElementsCoincideWithExpected(EVENT_ITEM, EVENT_TITLE);
    }

    @Step("Check that amount of Date elements  for events coincide with expected")
    public boolean isAllEventsContainDate() {
        Log.logInfo("Check that amount of Date elements for events coincide with expected");
        return baseElement.isAmountOfElementsCoincideWithExpected(EVENT_ITEM, EVENT_DATE);
    }

    @Step("Get News Date")
    public LocalDate getNewsDate(int i) {
        Log.logInfo("Get News Date");
        return baseElement.getDate(LATEST_NEWS_SECTION, NEWS_DATE, i);
    }

    @Step("Open Event With Google Link")
    public void openEventWithGoogleLink() {
        Log.logInfo("Open Event With Google Link");
        baseElement.clickElementContainAnother(EVENT_ITEM, GOOGLE_LINK);
    }

    @Step("Verify that each tweet contain author")
    public boolean isTweetContainAuthor() {
        Log.logInfo("Check that each tweet contain author");
        return baseElement.checkElementInsideOtherContainText(TWEET_FEED, TWEET_AUTHOR);
    }

    @Step("Verify that each tweet contain author")
    public boolean isTweetContainTime() {
        Log.logInfo("Check that each tweet contain time");
        return baseElement.checkElementInsideOtherContainText(TWEET_FEED, TWEET_TIME);
    }

    @Step("Verify that each tweet contain author")
    public boolean isTweetContainText() {
        Log.logInfo("Check that each tweet contain text");
        return baseElement.checkElementInsideOtherContainText(TWEET_FEED, TWEET_TEXT);
    }

    @Step("Verify that there are 3 of twitter feeds")
    public boolean isThreeTwitterFeeds() {
        Log.logInfo("Check that there are 3 of twitter feeds");
        return baseElement.isCountOfElementsCorrect(TWEET_FEED, 3);
    }

    public void hoverOnDiversity() {
        baseElement.hoverOnElement(DIVERSITY_LINK);
    }

    public void clickTrainingLink() {
        baseElement.clickElement(TRAINING_LINK);
    }

    public int getHeaderHeight() {
        Log.logInfo("Get Header Height");
        return baseElement.getHeight(HEADER);
    }

    public void scrollToTheBottom() {
        baseElement.scrollToTheBottom();
    }

    @Step("Verify that there are 4 event items")
    public boolean isEventItemsCountCorrect() {
        Log.logInfo("Verify that there are 4 event items");
        return baseElement.isCountOfElementsCorrect(EVENT_TITLE, 4);
    }
}
