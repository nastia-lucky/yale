package yale.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import framework.logger.Log;
import framework.utilities.Browser;

import java.util.List;

public class SearchOverlay extends MainPage {

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
    private final By SEARCH_COUNT = By.xpath("//h3[@class='find-people-tab__results-count']");
    private final By RESEARCH_AREA_INPUT = By.xpath("//input[@id='researchArea']");
    private final By RESEARCH_AREA_OPTION = By.xpath("//ul[@class='research-area-input__suggestions-container']//li[@role='option']");
    private final By ROLE_DROP_DOWN = By.xpath("//div[@class='find-people-tab__roles-dropdown']");
    private final By ROLE_OPTION = By.xpath("//ul[@role='listbox']//li[@role='option']");

    public SearchPage clickSearchButton() {
        Log.logInfo("Click Search button");
        Browser.waitForElementToBePresent(SEARCH_BUTTON);
        browser.clickElement(SEARCH_BUTTON);
        Browser.waitForElementToBePresent(SEARCH_INPUT);
        return new SearchPage();
    }

    public int getSearchCount() throws InterruptedException {
        Log.logInfo("Get search count");
        Browser.waitForElementToBePresent(SEARCH_COUNT);
        Thread.sleep(3000);
        String searchCount = browser.getText(SEARCH_COUNT);
        String[] stringArray = searchCount.split(" ");
        String stringNumber = stringArray[0];
        int number = Integer.parseInt(stringNumber);
        System.out.println(number);
        return number;
    }

    public MainPage inputSearchValue(String value) {
        Log.logInfo("Input search value" + value);
        Browser.waitForElementToBePresent(SEARCH_INPUT);
        browser.typeTo(SEARCH_INPUT, value);
        return this;
    }

    public boolean isInputSearchPanelDisplayed() {
        Log.logInfo("Check that Input Search Panel is displayed");
        Browser.waitForElementToBePresent(INPUT_SEARCH_PANEL);
        return browser.isElementDisplayed(INPUT_SEARCH_PANEL);
    }

    public boolean isFindPeopleTabDisplayed() {
        Log.logInfo("Check that Find People Tab is displayed");
        Browser.waitForElementToBePresent(FIND_PEOPLE_TAB);
        return browser.isElementDisplayed(FIND_PEOPLE_TAB);
    }

    public boolean isPopularLinksTabDisplayed() {
        Log.logInfo("Check that Popular Links Tab is displayed");
        Browser.waitForElementToBePresent(POPULAR_LINKS_TAB);
        return browser.isElementDisplayed(POPULAR_LINKS_TAB);
    }

    public boolean isSearchTabDisplayed() {
        Log.logInfo("Check that Search Tab is displayed");
        Browser.waitForElementToBePresent(SEARCH_TAB);
        return browser.isElementDisplayed(SEARCH_TAB);
    }

    public boolean isSearchResultDetailsDisplayed() {
        Log.logInfo("Check that search result details is displayed");
        Browser.waitForElementsFromArrayArePresent(SEARCH_RESULT_DETAILS);
        return browser.isElementsDisplayed(SEARCH_RESULT_DETAILS);
    }

    public SearchOverlay inputSearchPanelValue(String value) {
        Log.logInfo("Input Search Value" + value);
        browser.typeTo(INPUT_SEARCH_PANEL, value);
        return this;
    }

    public SearchOverlay clickClearButton() {
        Log.logInfo("Click Clear Button");
        browser.clickElement(CLEAR_BUTTON);
        return this;
    }

    public boolean isResultsDisappears() {
        Log.logInfo("Check that Results disappear");
        return browser.isArrayEmpty(SEARCH_RESULT_DETAILS);
    }

    public boolean isSearchResultContainsSearchValue(String searchValue) {
        Log.logInfo("Check that search results contain search value " + searchValue);
        Browser.waitForElementToBeClickable(SEARCH_RESULT_TITLE);
        return browser.compareText(SEARCH_RESULT_TITLE, HIGHLIGHTED_TEXT, searchValue);
    }

    public SearchOverlay openFirstResult() {
        Log.logInfo("Open First Result");
        List<WebElement> elements = Browser.getDriver().findElements(FIRST_SEARCH_RESULT);
        WebElement element = elements.get(0);
        Browser.waitForTheFirstElementFromArrayIsClickable(FIRST_SEARCH_RESULT);
        browser.clickFirstElementFromArray(FIRST_SEARCH_RESULT);
        return this;
    }

    public boolean isTitleContainsSearchValue(String searchValue) {
        Log.logInfo("Compare title with inputted search value " + searchValue);
        Browser.waitForElementToBePresent(PAGE_TITLE);
        String title = browser.getTitle();
        System.out.println(title);
        if (title.contains(searchValue)) {
            return true;
        }
        return false;
    }

    public SearchOverlay clickFindPeopleTab() {
        Log.logInfo("Click Find People Tab");
        Browser.waitForElementToBePresent(FIND_PEOPLE_TAB);
        browser.clickElement(FIND_PEOPLE_TAB);
        return this;
    }

    public SearchOverlay typePeopleName(String value) {
        Log.logInfo("Type people Name " + value);
        Browser.waitForElementToBePresent(PEOPLE_NAME_INPUT);
        browser.typeTo(PEOPLE_NAME_INPUT, value);
        return this;
    }

    public SearchOverlay typeResearchArea(String researchArea) {
        Log.logInfo("Type research area " + researchArea);
        browser.typeTo(RESEARCH_AREA_INPUT, researchArea);
        return this;
    }

    public SearchOverlay clickSuggestionForResearchArea() {
        Log.logInfo("Click suggestion for research area");
        Browser.waitForTheFirstElementFromArrayIsClickable(RESEARCH_AREA_OPTION);
        browser.clickFirstElementFromArray(RESEARCH_AREA_OPTION);
        return this;
    }

    public SearchOverlay openRoleDropDown() {
        Log.logInfo("Open role drop-down");
        browser.clickElement(ROLE_DROP_DOWN);
        return this;
    }

    public void addRoleOption() {
        Log.logInfo("Add Role Option");
        Browser.waitForTheFirstElementFromArrayIsClickable(ROLE_OPTION);
        browser.clickTheSecondElementFromArray(ROLE_OPTION);
    }
}
