package yale.tests;

import framework.listener.TestListener;
import framework.utilities.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.EventPage;
import yale.pageObjects.EventSearch;
import yale.pageObjects.MainPage;
import yale.pageObjects.SearchPage;
import yale.services.CheckPopUp;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForEvent {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();
    String keyword = "Cancer";
    EventSearch eventSearch = new EventSearch();

    @BeforeMethod
    public void openBrowser() {
        Browser.getInstance();
    }

    @Test
    public void openEventDetailsPageFromSearch() {
        mainPage.openMainPage();
        CheckPopUp.checkPopUp();
        SearchPage searchPage =
                mainPage.clickPerformSearch()
                        .clickSearchButton()
                        .addEventFilter();
        String firstResultTitle = searchPage.getFirstEventTitle();
        EventPage eventPage = searchPage.openFirstEvent();
        Assert.assertEquals(eventPage.getEventTitle(), firstResultTitle,
                "Event Details Page Title is not displayed");
    }

    @Test
    public void checkEvent() {
        mainPage.openMainPage();
        CheckPopUp.checkPopUp();
        SearchPage searchPage =
                mainPage.clickPerformSearch()
                        .clickSearchButton()
                        .addEventFilter();
        EventPage eventPage = searchPage.openFirstEvent();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(eventPage.isEventDateIsDisplayed(),
                "The Date is not displayed on the event details page");
        softAssert.assertTrue(eventPage.isEventTimeIsDisplayed(),
                "The time is not displayed on the event details page");
        softAssert.assertTrue(eventPage.isEventAdmissionIsDisplayed(),
                "The admission is not displayed on the event details page");
        softAssert.assertTrue(eventPage.isShareViaTwitterLinkDisplayed(),
                "Share Twitter Button is not displayed on the event details page");
        softAssert.assertTrue(eventPage.isEventAudienceDisplayed(),
                "The audience is not displayed on the event details page");
        softAssert.assertAll();
    }

    @Test
    public void checkAudienceFilter() {
        OpenSearchPage.openSearch();
        int numberEventsWithoutFilter = searchPage
                .addEventFilter()
                .getSearchResult();
        String chosenAudience = eventSearch
                .addAudienceFilter()
                .addActiveAudience();
        int numberEventsWithAudienceFilter = searchPage.getSearchResult();
        EventPage eventPage = searchPage.openFirstEvent();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(numberEventsWithoutFilter > numberEventsWithAudienceFilter,
                "Search Results number haven't changed after applying Audience  Filter");
        softAssert.assertEquals(eventPage.getAudience(), chosenAudience,
                "Audience on the event details page doesn't coincide with the chosen audience " + chosenAudience);
        softAssert.assertAll();
    }

    @Test
    public void checkEventTypeFilter() {
        OpenSearchPage.openSearch();
        int numberEventsWithoutFilter = searchPage
                .addEventFilter()
                .getSearchResult();
        String chosenEventType = eventSearch
                .addEventTypeFilter()
                .addActiveEventType();
        System.out.println(chosenEventType);
        int numberEventsWithEventTypeFilter = searchPage.getSearchResult();
        EventPage eventpage = searchPage.openFirstEvent();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(numberEventsWithoutFilter > numberEventsWithEventTypeFilter,
                "Search Results number haven't changed after applying Event Type Filter");
        softAssert.assertTrue(eventpage.isTagsContainsChosenEventType(chosenEventType),
                "Tags on the event details page don't contain chosen event type " + chosenEventType);
        softAssert.assertAll();
    }

    @Test
    public void checkKeywordFilter() {
        OpenSearchPage.openSearch();
        int numberEventsWithoutFilter = searchPage
                .addEventFilter()
                .getSearchResult();
        int numberEventsWithKeywordFilter = searchPage.inputKeyword(keyword)
                .chooseFirstSuggestion()
                .getSearchResult();
        Assert.assertTrue(numberEventsWithoutFilter > numberEventsWithKeywordFilter,
                "Search Results number haven't changed after applying Keyword Filter");
    }

    @Test
    public void checkResultsNumberInBrackets() {
        OpenSearchPage.openSearch();
        int numberAudienceBracketsSearchResult = searchPage
                .addEventFilter()
                .addAudienceFilter()
                .getAudienceBracketsResultNumber();
        int numberAudienceSearchResults = searchPage.getSearchResult();
        Assert.assertEquals(numberAudienceBracketsSearchResult, numberAudienceSearchResults,
                "The results numbers in brackets and on the search results page don't coincide for Event Audience Filter");
    }

    @Test
    public void checkSearchResultEventInfo() {
        OpenSearchPage.openSearch();
        searchPage.addEventFilter()
                .getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.isEachEventSearchResultHaveTypes("Recurring Event", "Event"),
                "Not each Search Event Result has one of the types");
        softAssert.assertTrue(searchPage.isEachResultHaveTitle(),
                "Not each Search Event Result has Title");
        softAssert.assertTrue(searchPage.isEachEventResultHaveTime(),
                "Not each Search Event Result has Time");
        softAssert.assertTrue(searchPage.isAllElementsHaveEventDate(),
                "Not each Search Event Result has Date");
        softAssert.assertAll();
    }

    @AfterMethod
    public void closeBrowser() {
        Browser.closeBrowser();
    }
}



