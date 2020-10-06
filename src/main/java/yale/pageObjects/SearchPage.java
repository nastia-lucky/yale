package yale.pageObjects;

import com.sun.xml.internal.rngom.parse.host.Base;
import framework.BaseElement;
import framework.Browser;
import framework.logger.Log;
import org.openqa.selenium.By;

public class SearchPage extends BasePage {

    private final By REMOVE_TOP_STORIES_FILTER = By.xpath("//ul[@class='global-search-active-entity-filter__list']//button[@aria-label='Remove top stories filter']");
    private static final By SEARCH_INPUT = By.xpath("//input[@aria-label='Search input']");
    protected final By PEOPLE_FILTER = By.xpath("//div[@aria-label='Add People filter']");
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
    private final By SEARCH_RESULT_EVENT_DATE = By.xpath(".//div[@class='event-date event-date--small']");
    private final By DEFAULT_IMAGE = By.xpath(".//div[@class='search-result__image-container']//*[name()='svg']");
    private final By CLINICAL_TRIAL_TYPE = By.xpath("//p[contains(text(), 'Clinical Trial')]");

    public SearchPage() {
        super(SEARCH_INPUT);
    }

    public SearchPage chooseFirstSuggestion() {
        Log.logInfo("Choose First Suggestion");
        String firstText= SearchPage.getSearchResultText();
        baseElement.clickFirstElementFromArray(SUGGESTION);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return this;
    }

    public boolean isRemoveTopStoriesFilterDisplayed() {
        Log.logInfo("Check that Remove Top Stories Filter Button is displayed");
        BaseElement.waitForElementToBeClickable(REMOVE_TOP_STORIES_FILTER);
        return baseElement.isElementDisplayed(REMOVE_TOP_STORIES_FILTER);
    }

    public ProfileSearch addPeopleFilter() {
        Log.logInfo("Add People filter");
        String firstText= SearchPage.getSearchResultText();
        baseElement.clickElement(PEOPLE_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new ProfileSearch();
    }

    public boolean isSearchResultPeopleTypeIsDisplayed() {
        Log.logInfo("Check People Type");
        return baseElement.checkAllElementsFromArray(PEOPLE_SEARCH_RESULT, SEARCH_RESULT_TYPE);
    }

    public boolean isSearchResultPeopleImageIsDisplayed() {
        Log.logInfo("Check People Image");
        return baseElement.checkAllElementsFromArray(PEOPLE_SEARCH_RESULT, SEARCH_RESULT_IMAGE);
    }

    public ProfilePage clickFirstPeopleName() {
        Log.logInfo("Click first People name");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(SEARCH_RESULT_IMAGE);
        baseElement.clickTheFirstElementWithJS(SEARCH_RESULT_IMAGE);
        return new ProfilePage();
    }

    public boolean isRemoveEventsFilterIsDisplayed() {
        Log.logInfo("Check that Remove Events Filter Button is displayed");
        BaseElement.waitForElementToBeClickable(REMOVE_EVENTS_FILTER);
        return baseElement.isElementDisplayed(REMOVE_EVENTS_FILTER);
    }

    public ClinicalTrialSearch addClinicalTrialFilter() {
        Log.logInfo("Add Clinical Trial Filter");
        String firstText= SearchPage.getSearchResultText();
        baseElement.clickElement(CLINICAL_TRIAL_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new ClinicalTrialSearch();
    }

    public ClinicalTrialPage openFirstTrial() {
        Log.logInfo("Open first Trial");
        baseElement.clickFirstElementFromArray(CLINICAL_TRIAL_SVG);
        return new ClinicalTrialPage();
    }

    public EventSearch addEventFilter() {
        Log.logInfo("Add Event Filter");
        String firstText= SearchPage.getSearchResultText();
        baseElement.clickElement(EVENT_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new EventSearch();
    }

    public EventPage openFirstEvent() {
        Log.logInfo("Open first event");
        baseElement.clickFirstElementFromArray(EVENT_DATE);
        return new EventPage();
    }

    public NewsSearch addNewsFilter() {
        Log.logInfo("Add News Filter");
        String firstText= SearchPage.getSearchResultText();
        baseElement.clickElement(NEWS_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new NewsSearch();
    }

    public NewsPage openFirstNews() {
        Log.logInfo("Open First News");
        baseElement.clickElement(NEWS_ARTICLE_THUMBNAIL);
        return new NewsPage();
    }

    public String getExistingSearchValue() {
        Log.logInfo("Get existing inserted value from Search input");
        BaseElement.waitForElementToBeClickable(PEOPLE_FILTER);
        return baseElement.getAttribute(SEARCH_INPUT, "value");
    }

    public int getSearchResult() {
        Log.logInfo("Get Search Number");
        String searchQuery = baseElement.getText(SEARCH_RESULT_MESSAGE);
        String[] searchResults = searchQuery.split(" ");
        String searchResult = searchResults[1];
        return Integer.parseInt(searchResult);
    }


    public SearchPage inputKeyword(String keyword) {
        Log.logInfo("Input keyword");
        baseElement.typeTo(KEYWORDS_INPUT, keyword);
        return this;
    }

    public int getPageNumbers(String attribute)  {
        Log.logInfo("Get Page Number");
        return baseElement.getNumber(PAGINATION_ITEM, attribute);
    }

    public int getExpectedPageNumber(int searchResult) {
        Log.logInfo("Get Expected Page Number");
        return searchResult / 20 + 1;
    }

    public MediaSearch addMediaFilter() {
        Log.logInfo("Add media filter");
        baseElement.clickElement(MEDIA_FILTER);
        return new MediaSearch();
    }

    public boolean isSearchResultMediaTypeCoincideWithChosen(String chosenMediaType) {
        Log.logInfo("Check that search result media type coincides with chosen " + chosenMediaType);
        return baseElement.isAllElementsInArrayContainsText(SEARCH_RESULT_TYPE, chosenMediaType);
    }

    public MediaPage openMediaPopUp() {
        Log.logInfo("Open Media Pop-up");
        baseElement.clickFirstElementFromArray(MEDIA_SEARCH_DETAILS);
        return new MediaPage();
    }

    public DocumentSearch addDocumentFilter() {
        Log.logInfo("Add Document filter");
        String firstText= SearchPage.getSearchResultText();
        baseElement.clickElement(DOCUMENT_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new DocumentSearch();
    }

    public WebSiteSearch addWebPageFilter() {
        Log.logInfo("Add Web Page filter");
        String firstText=SearchPage.getSearchResultText();
        baseElement.clickElement(WEB_PAGE_FILTER);
        BaseElement.waitForInvisibility(SEARCH_RESULT_MESSAGE, firstText);
        return new WebSiteSearch();
    }

    public String openAndGetTextFromFirstWebSiteSearchResult() {
        Log.logInfo("Open first Web Site Result");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(WEB_RESULT_BREAD_CRUMB);
        return baseElement.getTextFromElementInsideOtherElement(SEARCH_RESULTS, WEB_RESULT_BREAD_CRUMB);
    }

    public String getFirstSearchResultType() {
        Log.logInfo("Get First Search Result Type");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(SEARCH_RESULT_TYPE);
        return baseElement.getTextFirstElementFromArray(SEARCH_RESULT_TYPE);
    }

    public boolean isEachWebPageResultHaveBreadcrumb() {
        Log.logInfo("Check that each web page result has breadcrumb");
        return baseElement.isEachResultHaveElement(SEARCH_RESULTS, WEB_RESULT_BREAD_CRUMB);
    }

    public boolean isEachWebPageResultHaveType() {
        Log.logInfo("Check that each web page result has type");
        return baseElement.isEachResultHaveElement(SEARCH_RESULTS, SEARCH_RESULT_TYPE);
    }

    public boolean isEachResultHaveTitle() {
        Log.logInfo("Check that each result has title");
        return baseElement.isEachResultHaveElement(SEARCH_RESULTS, SEARCH_RESULT_TITLE);
    }

    public boolean isEachSearchResultHaveType(String text) {
        Log.logInfo("Check that each result has type " + text);
        return baseElement.isAllElementsContainText(SEARCH_RESULTS, SEARCH_RESULT_TYPE, text);
    }

    public boolean isEachDocumentResultHaveBreadcrumb() {
        Log.logInfo("Check that each document result has breadcrumb");
        return baseElement.isEachResultHaveElement(SEARCH_RESULTS, DOCUMENT_RESULT_BREADCRUMB);
    }

    public boolean isEachMediaHaveDuration() {
        Log.logInfo("Check that each media has duration");
        return baseElement.isEachResultHaveElement(SEARCH_RESULTS, MEDIA_DURATION);
    }

    public String getFirstClinicalTrialTitle() {
        Log.logInfo("Get First Clinical Trial Title");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(CLINICAL_TRIAL_TYPE);
        return baseElement.getTextFromFirstElement(SEARCH_RESULT_TITLE);
    }

    public String getFirstEventTitle() {
        Log.logInfo("Get First Event Title");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(EVENT_SEARCH_RESULT_TIME);
        return baseElement.getTextFromFirstElement(SEARCH_RESULT_TITLE);
    }

    public boolean isEachEventSearchResultHaveTypes(String text, String text2) {
        Log.logInfo("Check that each result has types " + text + " " + text2);
        BaseElement.waitForTheFirstElementFromArrayIsClickable(SEARCH_RESULT_TYPE);
        return baseElement.isAllElementsContainTexts(SEARCH_RESULTS, SEARCH_RESULT_TYPE, text, text2);
    }

    public boolean isEachEventResultHaveTime() {
        Log.logInfo("Check that each event result has time");
        return baseElement.isEachResultHaveElement(SEARCH_RESULTS, EVENT_SEARCH_RESULT_TIME);
    }

    public String getFirstNewsTitle() {
        //Browser.waitForElementToBeClickable(REMOVE_NEWS_FILTER_BUTTON);
        return baseElement.getTextFromFirstElement(SEARCH_RESULT_TITLE);
    }

    public String openNotExternalNews() {
        return baseElement.checkAndOpenElementNotContainAnother(SEARCH_RESULT_DETAILS, EXTERNAL_NEWS_SOURCE, SEARCH_RESULT_TITLE);
    }

    public boolean isAllElementsHavePhotoOrDefaultImage() {
        return baseElement.checkPresenceOneOfTwoElementInsideAnother(PROFILE_SEARCH_RESULT, SEARCH_RESULT_IMAGE, DEFAULT_IMAGE);
    }

    public boolean isAllElementsHaveType() {
        return baseElement.checkPresenceElementInsideOtherElement(PROFILE_SEARCH_RESULT, SEARCH_RESULT_TYPE);
    }

    public boolean isAllElementsHaveSummary() {
        return baseElement.isEachResultHaveElement(SEARCH_RESULTS, SEARCH_RESULT_SUMMARY);
    }

    public boolean isAllElementsHaveThumbnailOrDefaultImage() {
        return baseElement.checkPresenceOneOfTwoElementInsideAnother(SEARCH_RESULTS, NEWS_ARTICLE_THUMBNAIL, DEFAULT_IMAGE);
    }

    public boolean isAllElementsHaveDate() {
        return baseElement.isEachResultHaveElement(SEARCH_RESULTS, SEARCH_RESULT_DATE);
    }

    public boolean isAllElementsHaveEventDate() {
        return baseElement.isEachResultHaveElement(SEARCH_RESULTS, SEARCH_RESULT_EVENT_DATE);
    }

    public String getFirstTitleText() {
        baseElement.waitForTheFirstElementFromArrayIsClickable(SEARCH_RESULT_TITLE);
        return baseElement.getTextFirstElementFromArray(SEARCH_RESULT_TITLE);
    }

    public static String getSearchResultText(){
        return BaseElement.getText(SEARCH_RESULT_MESSAGE);
    }
}