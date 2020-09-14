package yale.pageObjects;

import framework.logger.Log;
import framework.utilities.Browser;
import org.openqa.selenium.By;

public class SearchPage extends BasePage {

    private final By REMOVE_TOP_STORIES_FILTER = By.xpath("//ul[@class='global-search-active-entity-filter__list']//button[@aria-label='Remove top stories filter']");
    private final By SEARCH_INPUT = By.xpath("//input[@aria-label='Search input']");
    private final By PEOPLE_FILTER = By.xpath("//div[@aria-label='Add People filter']");
    private final By PEOPLE_SEARCH_RESULT = By.xpath("//ul[@class='global-search-results']//a[@class='search-result']");
    private final By SEARCH_RESULT_TYPE = By.xpath(".//p[@class='search-result__type']");
    private final By SEARCH_RESULT_IMAGE = By.xpath(".//img[@class='profile-search-result__image']");
    private final By REMOVE_EVENTS_FILTER = By.xpath("//ul[@class='global-search-active-entity-filter__list']//button[@aria-label='Remove Events filter']");
    private final By CLINICAL_TRIAL_FILTER = By.xpath("//div[@aria-label='Add Clinical Trials filter']");
    private final By CLINICAL_TRIAL_SVG = By.xpath("//span[@class='clinical-trial-search-result__conditions']");
    private final By SEARCH_RESULT_TITLE = By.xpath(".//h3[@class='search-result__title']");
    private final By EVENT_FILTER = By.xpath("//div[@aria-label='Add Events filter']");
    private final By EVENT_DATE = By.xpath("//span[@class='event-search-result__time']");
    private final By NEWS_FILTER = By.xpath("//div[@aria-label='Add News filter']");
    private final By SEARCH_RESULT = By.xpath("//span[@class='global-search-active-filter-message__text']");
    private final By KEYWORDS_INPUT = By.xpath("//input[@aria-label='Add keywords']");
    private final By SOURCE_BUTTON = By.xpath("//span[contains(text(), 'Source')]");
    private final By YSM_MEWS_FILTER = By.xpath("//button[@aria-label='Add Yale News to Source filter']");
    private final By PAGINATION_ITEM = By.xpath("//li[@class='pagination__item']//a[@role='button']");
    private final By MEDIA_FILTER = By.xpath("//div[@id='accordion__heading-Media']");
    private final By MEDIA_SEARCH_DETAILS = By.xpath("//span[@class='media-search-result__duration']");
    private final By DOCUMENT_FILTER = By.xpath("//div[@id='accordion__heading-Document']");
    private final By SEARCH_RESULTS = By.xpath("//a[@class='search-result search-result--with-metadata']");
    private final By WEB_PAGE_FILTER = By.xpath("//div[@id='accordion__heading-Page']");
    private final By WEB_RESULT_BREAD_CRUMB = By.xpath(".//span[@class='page-search-result__breadcrumbs']");
    private final By SUGGESTION = By.xpath("//ul[@role='listbox']//li[@role='option']");
    private final By DOCUMENT_RESULT_BREADCRUMB = By.xpath(".//span[@class='document-search-result__breadcrumbs']");
    private final By MEDIA_DURATION = By.xpath(".//span[@class='media-search-result__duration']");
    private final By REMOVE_CLINICAL_TRIAL_FILTER_BUTTON = By.xpath("//button[@aria-label='Remove Clinical Trials filter']");
    private final By REMOVE_EVENT_FILTER_BUTTON = By.xpath("//button[@aria-label='Remove Events filter']");
    private final By EVENT_SEARCH_RESULT_TIME = By.xpath(".//span[@class='event-search-result__time']");
    private final By REMOVE_NEWS_FILTER_BUTTON = By.xpath("//button[@aria-label='Remove News filter']");
    private final By SEARCH_RESULT_DETAILS = By.xpath("//div[@class='search-result__content-container']");
    private final By EXTERNAL_NEWS_SOURCE = By.xpath(".//span[@class='article-source-label article-search-result__source-label']");
    private final By PROFILE_SEARCH_RESULT = By.xpath("//a[@class='search-result']");
    private final By SEARCH_RESULT_SUMMARY = By.xpath(".//p[@class='search-result__summary']");
    private final By NEWS_ARTICLE_THUMBNAIL = By.xpath(".//img[@class='article-search-result__image']");
    private final By SEARCH_RESULT_DATE = By.xpath(".//span[@class='article-search-result__date']");
    private final By SEARCH_RESULT_EVENT_DATE=By.xpath(".//div[@class='event-date event-date--small']");


    public SearchPage chooseFirstSuggestion() {
        Log.logInfo("Choose first suggestion");
        Browser.waitForTheFirstElementFromArrayIsClickable(SUGGESTION);
        browser.clickFirstElementFromArray(SUGGESTION);
        return this;
    }

    public boolean isRemoveTopStoriesFilterDisplayed() {
        Log.logInfo("Check that Remove Top Stories Filter Button is displayed");
        Browser.waitForElementToBeClickable(REMOVE_TOP_STORIES_FILTER);
        return browser.isElementDisplayed(REMOVE_TOP_STORIES_FILTER);
    }

    public ProfileSearch addPeopleFilter() {
        Log.logInfo("Add people filter");
        Browser.waitForElementToBePresent(PEOPLE_FILTER);
        browser.clickElement(PEOPLE_FILTER);
        return new ProfileSearch();
    }

    public boolean isSearchResultPeopleTypeIsDisplayed() {
        Log.logInfo("Check people type");
        Browser.waitForElementToBePresent(SEARCH_RESULT_TYPE);
        return browser.checkAllElementsFromArray(PEOPLE_SEARCH_RESULT, SEARCH_RESULT_TYPE);
    }

    public boolean isSearchResultPeopleImageIsDisplayed() {
        Log.logInfo("Check People Image");
        Browser.waitForElementToBePresent(SEARCH_RESULT_IMAGE);
        return browser.checkAllElementsFromArray(PEOPLE_SEARCH_RESULT, SEARCH_RESULT_IMAGE);
    }

    public ProfilePage clickFirstPeopleName() {
        Log.logInfo("Click first People name");
        Browser.waitForTheFirstElementFromArrayIsClickable(SEARCH_RESULT_IMAGE);
        browser.clickFirstElementFromArray(SEARCH_RESULT_IMAGE);
        return new ProfilePage();
    }

    public boolean isRemoveEventsFilterIsDisplayed() {
        Log.logInfo("Check that Remove Events Filter Button is displayed");
        Browser.waitForElementToBeClickable(REMOVE_EVENTS_FILTER);
        return browser.isElementDisplayed(REMOVE_EVENTS_FILTER);
    }

    public ClinicalTrialSearch addClinicalTrialFilter() {
        Log.logInfo("Add Clinical Trial Filter");
        Browser.waitForElementToBePresent(CLINICAL_TRIAL_FILTER);
        browser.clickElement(CLINICAL_TRIAL_FILTER);
        return new ClinicalTrialSearch();
    }

    public ClinicalTrialPage openFirstTrial() {
        Log.logInfo("Open first Trial");
        Browser.waitForElementsFromArrayArePresent(CLINICAL_TRIAL_SVG);
        browser.clickFirstElementFromArray(CLINICAL_TRIAL_SVG);
        return new ClinicalTrialPage();
    }

    public String getClinicalTrialSearchTitle() {
        Browser.waitForElementsFromArrayArePresent(SEARCH_RESULT_TITLE);
        return browser.getTextFirstElementFromArray(SEARCH_RESULT_TITLE);
    }

    public EventSearch addEventFilter() {
        Log.logInfo("Add Event Filter");
        Browser.waitForElementToBePresent(EVENT_FILTER);
        browser.clickElement(EVENT_FILTER);
        return new EventSearch();
    }

    public EventPage openFirstEvent() {
        Log.logInfo("Open first event");
        Browser.waitForElementsFromArrayArePresent(EVENT_DATE);
        browser.clickFirstElementFromArray(EVENT_DATE);
        return new EventPage();
    }

    public SearchPage addNewsFilter() {
        Log.logInfo("Add News Filter");
        Browser.waitForElementToBePresent(NEWS_FILTER);
        browser.clickElement(NEWS_FILTER);
        Browser.waitForElementToBeClickable(REMOVE_NEWS_FILTER_BUTTON);
        return this;
    }

    public NewsPage openFirstNews() {
        Log.logInfo("Open First News");
        Browser.waitForElementsFromArrayArePresent(NEWS_ARTICLE_THUMBNAIL);
        browser.clickElement(NEWS_ARTICLE_THUMBNAIL);
        return new NewsPage();
    }

    public String getExistingSearchValue() {
        Log.logInfo("Get existing inserted value from Search input");
        Browser.waitForElementToBeClickable(PEOPLE_FILTER);
        String value = browser.getAttribute(SEARCH_INPUT, "value");
        System.out.println(value);
        return value;
    }

    public int getSearchResult() {
        Log.logInfo("Get Search Number");
        //Browser.waitForJSandJQueryToLoad();
        try {
            Thread.sleep(4000);
        } catch (Exception e) {

        }
        String searchQuery = browser.getText(SEARCH_RESULT);
        System.out.println(searchQuery);
        String[] searchResults = searchQuery.split(" ");
        String searchResult = searchResults[1];
        System.out.println(searchResult);
        int number = Integer.parseInt(searchResult);
        System.out.println(number);
        return number;
    }


    public SearchPage inputKeyword(String keyword) {
        Log.logInfo("Input keyword");
        browser.typeTo(KEYWORDS_INPUT, keyword);
        return this;
    }

    public SearchPage clickSourceButton() {
        Log.logInfo("Сlick Source Button");
        browser.clickElement(SOURCE_BUTTON);
        return this;
    }

    public SearchPage addYsmNewsFilter() {
        Log.logInfo("Add YSM News Filter");
        browser.clickElement(YSM_MEWS_FILTER);
        return this;
    }

    public int getPageNumbers(String attribute) throws InterruptedException {
        Log.logInfo("Get Page Number");
        Thread.sleep(4000);
        int pageNumber = browser.getNumber(PAGINATION_ITEM, attribute);
        System.out.println(pageNumber);
        return pageNumber;
    }

    public int getExpectedPageNumber(int searchResult) {
        Log.logInfo("Get Expected Page Number" + searchResult);
        int expectedPageNumber = searchResult / 20 + 1;
        System.out.println(expectedPageNumber);
        return expectedPageNumber;
    }


    public MediaSearch addMediaFilter() {
        Log.logInfo("Add media filter");
        Browser.waitForElementToBeClickable(MEDIA_FILTER);
        browser.clickElement(MEDIA_FILTER);
        return new MediaSearch();
    }

    public boolean isSearchResultMediaTypeCoincideWithChosen(String chosenMediaType) {
        Log.logInfo("Check that search result media type coincides with chosen" + chosenMediaType);
        Browser.waitForElementsFromArrayArePresent(SEARCH_RESULT_TYPE);
        return browser.isAllElementsInArrayContainsText(SEARCH_RESULT_TYPE, chosenMediaType);
    }

    public MediaPage openMediaPopUp() {
        Log.logInfo("Open media pop-up");
        Browser.waitForTheFirstElementFromArrayIsClickable(MEDIA_SEARCH_DETAILS);
        browser.clickFirstElementFromArray(MEDIA_SEARCH_DETAILS);
        return new MediaPage();
    }

    public DocumentSearch addDocumentFilter() {
        Log.logInfo("Add Document Filter");
        Browser.waitForElementToBeClickable(DOCUMENT_FILTER);
        browser.clickElement(DOCUMENT_FILTER);
        return new DocumentSearch();
    }

    public WebSiteSearch addWebPageFilter() {
        Log.logInfo("Add Web Page Filter");
        Browser.waitForElementToBeClickable(WEB_PAGE_FILTER);
        browser.clickElement(WEB_PAGE_FILTER);
        return new WebSiteSearch();
    }

    public String openAndGetTextFromFirstWebSiteSearchResult() {
        Log.logInfo("Open first web site result");
        Browser.waitForTheFirstElementFromArrayIsClickable(WEB_RESULT_BREAD_CRUMB);
        return browser.getTextFromElementInsideOtherElement(SEARCH_RESULTS, WEB_RESULT_BREAD_CRUMB);
    }

    public String getFirstSearchResultType() {
        Log.logInfo("Get First Search Result Type");
        Browser.waitForTheFirstElementFromArrayIsClickable(SEARCH_RESULT_TYPE);
        String firstSearchResultType = browser.getTextFirstElementFromArray(SEARCH_RESULT_TYPE);
        System.out.println(firstSearchResultType);
        return firstSearchResultType;
    }

    public boolean isEachWebPageResultHaveBreadcrumb() {
        Log.logInfo("Check that each web page result has breadcrumb");
        return browser.isEachResultHaveElement(SEARCH_RESULTS, WEB_RESULT_BREAD_CRUMB);
    }

    public boolean isEachWebPageResultHaveType() {
        Log.logInfo("Check that each web page result has type");
        return browser.isEachResultHaveElement(SEARCH_RESULTS, SEARCH_RESULT_TYPE);
    }

    public boolean isEachResultHaveTitle() {
        Log.logInfo("Check that each result has title");
        return browser.isEachResultHaveElement(SEARCH_RESULTS, SEARCH_RESULT_TITLE);
    }

    public boolean isEachSearchResultHaveType(String text) {
        Log.logInfo("Check that each result has type " + text);
        return browser.isAllElementsContainText(SEARCH_RESULTS, SEARCH_RESULT_TYPE, text);
    }

    public boolean isEachDocumentResultHaveBreadcrumb() {
        Log.logInfo("Check that each document result has breadcrumb");
        return browser.isEachResultHaveElement(SEARCH_RESULTS, DOCUMENT_RESULT_BREADCRUMB);
    }

    public boolean isEachMediaHaveDuration() {
        Log.logInfo("Check that each media has duration");
        return browser.isEachResultHaveElement(SEARCH_RESULTS, MEDIA_DURATION);
    }

    public String getFirstClinicalTrialTitle() {
        Log.logInfo("Get First Clinical Trial Title");
        Browser.waitForElementToBeClickable(REMOVE_CLINICAL_TRIAL_FILTER_BUTTON);
        return browser.getTextFromFirstElement(SEARCH_RESULT_TITLE);
    }

    public String getFirstEventTitle() {
        Log.logInfo("Get First Event Title");
        Browser.waitForElementToBeClickable(REMOVE_EVENT_FILTER_BUTTON);
        return browser.getTextFromFirstElement(SEARCH_RESULT_TITLE);
    }

    public boolean isEachEventSearchResultHaveTypes(String text, String text2) {
        Log.logInfo("Check that each result has types " + text + " " + text2);
        return browser.isAllElementsContainTexts(SEARCH_RESULTS, SEARCH_RESULT_TYPE, text, text2);
    }

    public boolean isEachEventResultHaveTime() {
        Log.logInfo("Check that each event result has time");
        return browser.isEachResultHaveElement(SEARCH_RESULTS, EVENT_SEARCH_RESULT_TIME);
    }

    public String getFirstNewsTitle() {
        //Browser.waitForElementToBeClickable(REMOVE_NEWS_FILTER_BUTTON);
        return browser.getTextFromFirstElement(SEARCH_RESULT_TITLE);
    }

    public String openNotExternalNews() {
        return browser.checkAndOpenElementNotContainAnother(SEARCH_RESULT_DETAILS, EXTERNAL_NEWS_SOURCE, SEARCH_RESULT_TITLE);
    }

    public boolean isAllElementsHaveImage() {
        return browser.checkPresenceElementInsideOtherElement(PROFILE_SEARCH_RESULT, SEARCH_RESULT_IMAGE);
    }

    public boolean isAllElementsHaveType() {
        return browser.checkPresenceElementInsideOtherElement(PROFILE_SEARCH_RESULT, SEARCH_RESULT_TYPE);
    }

    public boolean isAllElementsHaveTitle() {
        return browser.checkPresenceElementInsideOtherElement(PROFILE_SEARCH_RESULT, SEARCH_RESULT_TITLE);
    }

    public boolean isAllElementsHaveSummary() {
        return browser.isEachResultHaveElement(SEARCH_RESULTS, SEARCH_RESULT_SUMMARY);
    }


    public boolean isAllElementsHaveThumbnail() {
        return browser.checkPresenceElementInsideOtherElement(SEARCH_RESULTS, NEWS_ARTICLE_THUMBNAIL);
    }

    public boolean isAllElementsHaveDate() {
        return browser.isEachResultHaveElement(SEARCH_RESULTS, SEARCH_RESULT_DATE);
    }

    public boolean isAllElementsHaveEventDate(){
        return browser.isEachResultHaveElement(SEARCH_RESULTS, SEARCH_RESULT_EVENT_DATE);
    }

}