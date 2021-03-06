package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.LocalDate;

public class SearchPage extends MainPage {

    private final By REMOVE_TOP_STORIES_FILTER = By.xpath("//ul[@class='global-search-active-entity-filter__list']//button[@aria-label='Remove top stories filter']");
    private static final By SEARCH_INPUT = By.xpath("//input[@aria-label='Search input']");
    protected static final By PEOPLE_FILTER = By.xpath("//div[@aria-label='Add People filter']");
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
    protected static final By SEARCH_RESULT_MESSAGE = By.xpath("//span[@class='global-search-active-filter-message__text']");
    private final By KEYWORDS_INPUT = By.xpath("//input[@aria-label='Add keywords']");
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
    private final By EVENT_SEARCH_RESULT_TIME = By.xpath(".//span[@class='event-search-result__time']");
    private final By SEARCH_RESULT_DETAILS = By.xpath("//div[@class='search-result__content-container']");
    private final By EXTERNAL_NEWS_SOURCE = By.xpath(".//span[@class='article-source-label article-search-result__source-label']");
    private final By PROFILE_SEARCH_RESULT = By.xpath("//a[@class='search-result']");
    private final By SEARCH_RESULT_SUMMARY = By.xpath(".//p[@class='search-result__summary']");
    private final By NEWS_ARTICLE_THUMBNAIL = By.xpath(".//img[@class='article-search-result__image']");
    private final By SEARCH_RESULT_DATE = By.xpath(".//span[@class='article-search-result__date']");
    private final By SEARCH_RESULT_EVENT_DATE = By.xpath(".//div[@class='event-date event-date--small']");
    private final By DEFAULT_IMAGE = By.xpath(".//div[@class='search-result__image-container']//*[name()='svg']");
    private final By CLINICAL_TRIAL_TYPE = By.xpath("//p[contains(text(), 'Clinical Trial')]");
    private final By DATE_PICKER = By.xpath("//button[@class='search-date-range-input__button']");
    private final By DAY = By.xpath("//div[@class='DayPicker-Day']");
    private static final By TODAY_SELECTOR = By.xpath("//div[@class='DayPicker-Day DayPicker-Day--today DayPicker-Day--selected']");
    private static final String DATE_ARIA_LABEL = "aria-label";
    private final By EVENT_DATE_MONTH_YEAR = By.xpath("//span[@class='event-date__month-year']");
    private final By EVENT_DATE_DAY = By.xpath("//span[@class='event-date__day']");
    private final By RECURRING_CIRCLE = By.xpath(".//div[@class='search-result__details']//*[name()='svg']");
    private final By RECURRING_EVENT_TYPE = By.xpath(".//p[@class='search-result__type'][contains(text(),'Recurring Event')]");
    protected final By SECURE_SEARCH_RESULTS_LINK = By.xpath("//section[@aria-label='Secure Search Results']//a[contains(text(), 'Log into CAS')]");
    private final By SEARCH_PAGE_FILTER = By.xpath("//span[@class='global-search-filter-panel__accordion-item-button-text']");
    private final String ATTRIBUTE = "href";
    private final By SEARCH_RESULT_LINK = By.xpath("//ul[@class='global-search-results']//a[@href]");

    public SearchPage() {
        super();
    }

    @Step("Choose First Suggestion")
    public SearchPage chooseFirstSuggestion() {
        Log.logInfo("Choose First Suggestion");
        String firstText = SearchPage.getSearchResultText();
        baseElement.clickFirstElementFromArray(SUGGESTION);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return this;
    }

    @Step("Verify that Remove Top Stories Filter Button is displayed")
    public boolean isRemoveTopStoriesFilterDisplayed() {
        Log.logInfo("Check that Remove Top Stories Filter Button is displayed");
        BaseElement.waitElementToBeClickable(REMOVE_TOP_STORIES_FILTER);
        return baseElement.isElementDisplayed(REMOVE_TOP_STORIES_FILTER);
    }

    @Step("Add People filter")
    public ProfileSearch addPeopleFilter() {
        Log.logInfo("Add People filter");
        BaseElement.waitElementToBeClickable(PEOPLE_FILTER);
        String firstText = SearchPage.getSearchResultText();
        baseElement.clickElement(PEOPLE_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new ProfileSearch();
    }

    public boolean isSearchResultPeopleTypeIsDisplayed() {
        Log.logInfo("Check People Type");
        return baseElement.isAllElementsFromArrayDisplayed(PEOPLE_SEARCH_RESULT, SEARCH_RESULT_TYPE);
    }

    public boolean isSearchResultPeopleImageIsDisplayed() {
        Log.logInfo("Check People Image");
        return baseElement.isAllElementsFromArrayDisplayed(PEOPLE_SEARCH_RESULT, SEARCH_RESULT_IMAGE);
    }

    @Step("Click first People name")
    public ProfilePage clickFirstPeopleName() {
        Log.logInfo("Click first People name");
        BaseElement.waitFirstElementFromArrayIsClickable(SEARCH_RESULT_IMAGE);
        baseElement.clickTheFirstElementWithJS(SEARCH_RESULT_IMAGE);
        return new ProfilePage();
    }

    @Step("Verify that Remove Events Filter Button is displayed")
    public boolean isRemoveEventsFilterIsDisplayed() {
        Log.logInfo("Check that Remove Events Filter Button is displayed");
        BaseElement.waitElementToBeClickable(REMOVE_EVENTS_FILTER);
        return baseElement.isElementDisplayed(REMOVE_EVENTS_FILTER);
    }

    @Step("Add Clinical Trial Filter")
    public ClinicalTrialSearch addClinicalTrialFilter() {
        Log.logInfo("Add Clinical Trial Filter");
        String firstText = SearchPage.getSearchResultText();
        baseElement.clickElement(CLINICAL_TRIAL_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new ClinicalTrialSearch();
    }

    @Step("Open first Trial")
    public ClinicalTrialPage openFirstTrial() {
        Log.logInfo("Open first Trial");
        baseElement.clickFirstElementFromArray(CLINICAL_TRIAL_SVG);
        return new ClinicalTrialPage();
    }

    @Step("Add Event Filter")
    public EventSearch addEventFilter() {
        Log.logInfo("Add Event Filter");
        String firstText = SearchPage.getSearchResultText();
        baseElement.clickElement(EVENT_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new EventSearch();
    }

    @Step("Open first event")
    public EventPage openFirstEvent() {
        Log.logInfo("Open first event");
        baseElement.clickFirstElementFromArray(EVENT_DATE);
        return new EventPage();
    }

    @Step("Add News Filter")
    public NewsSearch addNewsFilter() {
        Log.logInfo("Add News Filter");
        String firstText = SearchPage.getSearchResultText();
        baseElement.clickElement(NEWS_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new NewsSearch();
    }

    @Step("Open First News")
    public NewsPage openFirstNews() {
        Log.logInfo("Open First News");
        baseElement.clickElement(NEWS_ARTICLE_THUMBNAIL);
        return new NewsPage();
    }

    @Step("Get existing inserted value from Search input")
    public String getExistingSearchValue() {
        Log.logInfo("Get existing inserted value from Search input");
        BaseElement.waitElementToBeClickable(PEOPLE_FILTER);
        return baseElement.getAttribute(SEARCH_INPUT, "value");
    }

    @Step("Get Search Number")
    public int getSearchResult() {
        Log.logInfo("Get Search Number");
        String searchQuery = BaseElement.getText(SEARCH_RESULT_MESSAGE);
        String[] searchResults = searchQuery.split(" ");
        String searchResult = searchResults[1];
        return Integer.parseInt(searchResult);
    }

    @Step("Input keyword")
    public SearchPage inputKeyword(String keyword) {
        Log.logInfo("Input keyword " + keyword);
        baseElement.typeTo(KEYWORDS_INPUT, keyword);
        return this;
    }

    @Step("Get Page Number")
    public int getPageNumbers(String attribute) {
        Log.logInfo("Get Page Number");
        return baseElement.getNumber(PAGINATION_ITEM, attribute);
    }

    @Step("Get Expected Page Number")
    public int getExpectedPageNumber(int searchResult) {
        Log.logInfo("Get Expected Page Number");
        return searchResult / 20 + 1;
    }

    @Step("Add media filter")
    public MediaSearch addMediaFilter() {
        Log.logInfo("Add media filter");
        String firstText = SearchPage.getSearchResultText();
        baseElement.clickElement(MEDIA_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new MediaSearch();
    }

    @Step("Verify that search result media type coincides with chosen")
    public boolean isSearchResultMediaTypeCoincideWithChosen(String chosenMediaType) {
        Log.logInfo("Check that search result media type coincides with chosen " + chosenMediaType);
        return baseElement.isAllElementsInArrayContainsText(SEARCH_RESULT_TYPE, chosenMediaType);
    }

    @Step("Open Media Pop-up")
    public MediaPage openMediaPopUp() {
        Log.logInfo("Open Media Pop-up");
        baseElement.clickFirstElementFromArray(MEDIA_SEARCH_DETAILS);
        return new MediaPage();
    }

    @Step("Add Document filter")
    public DocumentSearch addDocumentFilter() {
        Log.logInfo("Add Document filter");
        String firstText = SearchPage.getSearchResultText();
        baseElement.clickElement(DOCUMENT_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new DocumentSearch();
    }

    @Step("Add Web Page filter")
    public WebSiteSearch addWebPageFilter() {
        Log.logInfo("Add Web Page filter");
        String firstText = SearchPage.getSearchResultText();
        baseElement.clickElement(WEB_PAGE_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new WebSiteSearch();
    }

    @Step("Open first Web Site Result")
    public String openAndGetTextFromFirstWebSiteSearchResult() {
        Log.logInfo("Open first Web Site Result");
        BaseElement.waitFirstElementFromArrayIsClickable(WEB_RESULT_BREAD_CRUMB);
        return baseElement.getTextFromElementInsideOtherElement(SEARCH_RESULTS, WEB_RESULT_BREAD_CRUMB);
    }

    @Step("Get First Search Result Type")
    public String getFirstSearchResultType() {
        Log.logInfo("Get First Search Result Type");
        BaseElement.waitFirstElementFromArrayIsClickable(SEARCH_RESULT_TYPE);
        return baseElement.getTextFirstElementFromArray(SEARCH_RESULT_TYPE);
    }

    @Step("Verify one web page result has breadcrumb")
    public boolean isOneWebPageResultHaveBreadcrumb() {
        Log.logInfo("Check one web page result has breadcrumb");
        return baseElement.isOneOfElementsHaveElementWithText(SEARCH_RESULTS, WEB_RESULT_BREAD_CRUMB);
    }

    @Step("Verify that each web page result has type")
    public boolean isEachWebPageResultHaveType() {
        Log.logInfo("Check that each web page result has type");
        return baseElement.isEachElementHaveElementWithText(SEARCH_RESULTS, SEARCH_RESULT_TYPE);
    }

    @Step("Verify that each result has title")
    public boolean isEachResultHaveTitle() {
        Log.logInfo("Check that each result has title");
        return baseElement.isEachElementHaveElementWithText(SEARCH_RESULTS, SEARCH_RESULT_TITLE);
    }

    @Step("Verify that each result has type")
    public boolean isEachSearchResultHaveType(String text) {
        Log.logInfo("Check that each result has type " + text);
        return baseElement.isAllElementsContainText(SEARCH_RESULTS, SEARCH_RESULT_TYPE, text);
    }

    @Step("Verify one of document result has breadcrumb")
    public boolean isOneDocumentResultHaveBreadcrumb() {
        Log.logInfo("Check one of document result has breadcrumb");
        return baseElement.isOneOfElementsHaveElementWithText(SEARCH_RESULTS, DOCUMENT_RESULT_BREADCRUMB);
    }

    @Step("Verify that each media has duration")
    public boolean isEachMediaHaveDuration() {
        Log.logInfo("Check that each media has duration");
        return baseElement.isEachElementHaveElementWithText(SEARCH_RESULTS, MEDIA_DURATION);
    }

    @Step("Get First Clinical Trial Title")
    public String getFirstClinicalTrialTitle() {
        Log.logInfo("Get First Clinical Trial Title");
        BaseElement.waitFirstElementFromArrayIsClickable(CLINICAL_TRIAL_TYPE);
        return baseElement.getTextFromFirstElement(SEARCH_RESULT_TITLE);
    }

    @Step("Get First Event Title")
    public String getFirstEventTitle() {
        Log.logInfo("Get First Event Title");
        BaseElement.waitFirstElementFromArrayIsClickable(EVENT_SEARCH_RESULT_TIME);
        return baseElement.getTextFromFirstElement(SEARCH_RESULT_TITLE);
    }

    public boolean isEachEventSearchResultHaveTypes(String text, String text2) {
        Log.logInfo("Check that each result has types " + text + " " + text2);
        BaseElement.waitFirstElementFromArrayIsClickable(SEARCH_RESULT_TYPE);
        return baseElement.isAllElementsContainTexts(SEARCH_RESULTS, SEARCH_RESULT_TYPE, text, text2);
    }

    @Step("Verify that each event result has time")
    public boolean isEachEventResultHaveTime() {
        Log.logInfo("Check that each event result has time");
        return baseElement.isEachElementHaveElementWithText(SEARCH_RESULTS, EVENT_SEARCH_RESULT_TIME);
    }

    public String getFirstNewsTitle() {
        //Browser.waitForElementToBeClickable(REMOVE_NEWS_FILTER_BUTTON);
        return baseElement.getTextFromFirstElement(SEARCH_RESULT_TITLE);
    }

    @Step("Open any not external news")
    public String openNotExternalNews() {
        Log.logInfo("Open not external news");
        return baseElement.checkAndOpenElementNotContainAnother(SEARCH_RESULT_DETAILS, EXTERNAL_NEWS_SOURCE, SEARCH_RESULT_TITLE);
    }

    @Step("Verify profile results have photo or default image")
    public boolean isAllElementsHavePhotoOrDefaultImage() {
        Log.logInfo("Check each profile result has photo or default image");
        return baseElement.isOneOfTwoElementPresentInsideAnother(PROFILE_SEARCH_RESULT, SEARCH_RESULT_IMAGE, DEFAULT_IMAGE);
    }

    @Step("Verify each profile result has type")
    public boolean isAllElementsHaveType() {
        Log.logInfo("Check each profile result has type");
        return baseElement.checkPresenceElementInsideOtherElement(PROFILE_SEARCH_RESULT, SEARCH_RESULT_TYPE);
    }

    @Step("Verify one of search results has summary")
    public boolean isOneOfElementsHaveSummary() {
        Log.logInfo("Check one of search results has summary");
        return baseElement.isOneOfElementsHaveElementWithText(SEARCH_RESULTS, SEARCH_RESULT_SUMMARY);
    }

    @Step("Verify each search result has thumbnail or default image")
    public boolean isAllElementsHaveThumbnailOrDefaultImage() {
        Log.logInfo("Check each search result has thumbnail or default image");
        return baseElement.isOneOfTwoElementPresentInsideAnother(SEARCH_RESULTS, NEWS_ARTICLE_THUMBNAIL, DEFAULT_IMAGE);
    }

    @Step("Verify each search result has date")
    public boolean isAllElementsHaveDate() {
        Log.logInfo("Check each search result has date");
        return baseElement.isEachElementHaveElementWithText(SEARCH_RESULTS, SEARCH_RESULT_DATE);
    }

    @Step("Verify each search result has date")
    public boolean isAllElementsHaveEventDate() {
        Log.logInfo("Check each search result has date");
        return baseElement.isEachElementHaveElementWithText(SEARCH_RESULTS, SEARCH_RESULT_EVENT_DATE);
    }

    public String getFirstTitleText() {
        Log.logInfo("Check each search result has date");
        BaseElement.waitFirstElementFromArrayIsClickable(SEARCH_RESULT_TITLE);
        return baseElement.getTextFirstElementFromArray(SEARCH_RESULT_TITLE);
    }

    public static String getSearchResultText() {
        return BaseElement.getText(SEARCH_RESULT_MESSAGE);
    }

    @Step("Click First Document")
    public String clickAndGetTextDocument() {
        Log.logInfo("Click First Document And Get Text");
        return baseElement.clickAndGetTextFirstElement(SEARCH_RESULT_LINK, ATTRIBUTE);
    }

    @Step("Check the document is downloaded")
    public boolean isDocumentDownloaded(String filename) {
        Log.logInfo("Check the document is downloaded");
        return baseElement.isFileDownloaded(filename);
    }

    @Step("Verify search Result Type coincides with the chosen")
    public boolean isSearchResultTypeCoincidesWithTheChosen(String value) {
        Log.logInfo("Check search Result Type coincides with the chosen " + value);
        return baseElement.isAllElementsInArrayContainsText(SEARCH_RESULT_TYPE, value);
    }

    @Step("Open Calendar")
    public SearchPage clickDatePicker() {
        Log.logInfo("Open Calendar");
        baseElement.clickElement(DATE_PICKER);
        return this;
    }

    @Step("Click and Get Chosen Date")
    public LocalDate clickAndGetFirstDate() {
        Log.logInfo("Click and Get Chosen Date");
        String firstText = SearchPage.getSearchResultText();
        LocalDate date = baseElement.getDate(DAY, DATE_ARIA_LABEL);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return date;
    }

    @Step("Get search Result Date")
    public LocalDate getSearchResultDate() {
        Log.logInfo("Get search Result Date");
        return baseElement.getSearchResultDate(SEARCH_RESULT_DETAILS, EVENT_DATE_MONTH_YEAR, EVENT_DATE_DAY);
    }

    @Step("Verify the recurring events have circle")
    public boolean isTheRecurringEventsHaveCircle() {
        Log.logInfo("Check the recurring events have circle");
        return baseElement.isElementPresentInsideAnotherElement(SEARCH_RESULT_DETAILS, RECURRING_EVENT_TYPE, RECURRING_CIRCLE);
    }

    @Step("Verify that Secure Search Results Link Is Displayed")
    public boolean isSecureSearchResultLinkIsDisplayed() {
        Log.logInfo("Check that Secure Search Results Link Is Displayed");
        return baseElement.isElementDisplayed(SECURE_SEARCH_RESULTS_LINK);
    }

    @Step("Verify that Secure Search Results Link Is Displayed")
    public ProfilePage openFirstPeopleProfile() {
        Log.logInfo("Check that Secure Search Results Link Is Displayed");
        baseElement.clickFirstElementFromArray(PEOPLE_SEARCH_RESULT);
        return new ProfilePage();
    }

    @Step("Get sum of values from search page filters")
    public int getSumFiltersNumberValue() {
        Log.logInfo("Get sum of values from search page filters");
        return baseElement.getNumberText(SEARCH_PAGE_FILTER);
    }
}