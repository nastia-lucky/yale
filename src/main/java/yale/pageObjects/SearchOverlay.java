package yale.pageObjects;

import framework.BaseElement;
import framework.Browser;
import framework.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchOverlay extends SearchPage {

    private final By SEARCH_INPUT = By.xpath("//input[@type='search']");
    private final By SEARCH_BUTTON = By.xpath("//button[@aria-label='Go to Search Page']");
    private final By INPUT_SEARCH_PANEL = By.xpath("//input[@aria-label='Search input']");
    private final By FIND_PEOPLE_TAB = By.xpath("//ul[@role='tablist']//li[@aria-label='Find People']");
    private final By POPULAR_LINKS_TAB = By.xpath("//ul[@role='tablist']//li[@aria-label='Popular Links']");
    private final By SEARCH_TAB = By.xpath("//ul[@role='tablist']//li[@aria-label='Search']");
    private final By SEARCH_RESULT_DETAILS = By.xpath("//div[@class='search-result__details']");
    private final By CLEAR_BUTTON = By.xpath("//button[@class='search-input__close-button']");
    private final By SEARCH_RESULT_TITLE = By.xpath("//h4[@class='search-result__title']");
    private final By HIGHLIGHTED_TEXT = By.xpath("//span[@class='search-result__text-highlighted']");
    private final By FIRST_SEARCH_RESULT = By.xpath("//ul[@class='search-results-list__search-results']//a[@class='search-result']");
    private final By PAGE_TITLE = By.xpath("//h1[@class='profile-details-header__name']");
    private final By PEOPLE_NAME_INPUT = By.xpath("//input[@id='peopleName']");
    private final static By SEARCH_COUNT = By.xpath("//h3[@class='find-people-tab__results-count']");
    private final By RESEARCH_AREA_INPUT = By.xpath("//input[@id='researchArea']");
    private final By RESEARCH_AREA_OPTION = By.xpath("//ul[@class='research-area-input__suggestions-container']//li[@role='option']");
    private final By ROLE_DROP_DOWN = By.xpath("//div[@class='find-people-tab__roles-dropdown']");
    private final By ROLE_OPTION = By.xpath("//ul[@role='listbox']//li[@role='option']");

    public SearchPage clickSearchButton() {
        Log.logInfo("Click Search button");
        baseElement.clickElement(SEARCH_BUTTON);
        BaseElement.waitForElementToBeClickable(PEOPLE_FILTER);
        return new SearchPage();
    }

    public int getSearchCount() {
        Log.logInfo("Get search count");
        String searchCount = baseElement.getText(SEARCH_COUNT);
        String[] stringArray = searchCount.split(" ");
        String stringNumber = stringArray[0];
        return Integer.parseInt(stringNumber);
    }

    public boolean isInputSearchPanelDisplayed() {
        Log.logInfo("Check that Input Search Panel is displayed");
        BaseElement.waitForElementToBeClickable(SEARCH_BUTTON);
        return baseElement.isElementDisplayed(INPUT_SEARCH_PANEL);
    }

    public boolean isFindPeopleTabDisplayed() {
        Log.logInfo("Check that Find People Tab is displayed");
        return baseElement.isElementDisplayed(FIND_PEOPLE_TAB);
    }

    public boolean isPopularLinksTabDisplayed() {
        Log.logInfo("Check that Popular Links Tab is displayed");
        return baseElement.isElementDisplayed(POPULAR_LINKS_TAB);
    }

    public boolean isSearchTabDisplayed() {
        Log.logInfo("Check that Search Tab is displayed");
        return baseElement.isElementDisplayed(SEARCH_TAB);
    }

    public boolean isSearchResultDetailsDisplayed() {
        Log.logInfo("Check that Search Result Details is displayed");
        return baseElement.isElementsDisplayed(SEARCH_RESULT_DETAILS);
    }

    public SearchOverlay inputSearchPanelValue(String value) {
        Log.logInfo("Input Search Value" + value);
        baseElement.typeTo(INPUT_SEARCH_PANEL, value);
        return this;
    }

    public SearchOverlay clickClearButton() {
        Log.logInfo("Click Clear Button");
        baseElement.clickElement(CLEAR_BUTTON);
        return this;
    }

    public boolean isResultsDisappears() {
        Log.logInfo("Check that Results disappear");
        return baseElement.isArrayEmpty(SEARCH_RESULT_DETAILS);
    }

    public boolean isSearchResultContainsSearchValue(String searchValue) {
        Log.logInfo("Check that search results contain search value " + searchValue);
        BaseElement.waitForElementToBeClickable(SEARCH_RESULT_TITLE);
        return baseElement.compareText(SEARCH_RESULT_TITLE, HIGHLIGHTED_TEXT, searchValue);
    }

    public ProfilePage openFirstPeopleResult() {
        Log.logInfo("Open First Result");
        baseElement.clickFirstElementFromArray(FIRST_SEARCH_RESULT);
        return new ProfilePage();
    }

    public SearchOverlay clickFindPeopleTab() {
        Log.logInfo("Click Find People Tab");
        baseElement.clickElement(FIND_PEOPLE_TAB);
        return this;
    }

    public SearchOverlay typePeopleName(String value) {
        Log.logInfo("Type people Name " + value);
        String firstText = SearchOverlay.getSearchResultCount();
        baseElement.typeTo(PEOPLE_NAME_INPUT, value);
        BaseElement.waitForInvisibility(SEARCH_COUNT, firstText);
        return this;
    }

    public SearchOverlay typeResearchArea(String researchArea) {
        Log.logInfo("Type research area " + researchArea);
        baseElement.typeTo(RESEARCH_AREA_INPUT, researchArea);
        return this;
    }

    public SearchOverlay clickSuggestionForResearchArea() {
        Log.logInfo("Click suggestion for research area");
        String firstText = SearchOverlay.getSearchResultCount();
        baseElement.clickFirstElementFromArray(RESEARCH_AREA_OPTION);
        BaseElement.waitForInvisibility(SEARCH_COUNT, firstText);
        return this;
    }

    public SearchOverlay openRoleDropDown() {
        Log.logInfo("Open role drop-down");
        baseElement.clickElement(ROLE_DROP_DOWN);
        return this;
    }

    public void addRoleOption() {
        Log.logInfo("Add Role Option");
        String firstText = SearchOverlay.getSearchResultCount();
        baseElement.clickTheSecondElementFromArray(ROLE_OPTION);
        BaseElement.waitForInvisibility(SEARCH_COUNT, firstText);
    }

    public static String getSearchResultCount() {
        Log.logInfo("Get Search Result Count");
        return BaseElement.getText(SEARCH_COUNT);
    }
}
