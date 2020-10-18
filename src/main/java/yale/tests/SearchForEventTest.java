package yale.tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.EventPage;
import yale.pageObjects.EventSearch;
import yale.pageObjects.SearchPage;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForEventTest extends BaseTest {

    @Test(dependsOnMethods = {"checkEventFilter"})
    public void openEventDetailsPageFromSearch() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addEventFilter();
        String searchResultTitle = searchPage.getFirstEventTitle();
        EventPage eventPage = searchPage.openFirstEvent();
        Assert.assertEquals(eventPage.getEventTitle(), searchResultTitle,
                "Title of Event From Search result and on the details page don't coincide");
    }

    @Test(dependsOnMethods = {"checkEventFilter"})
    public void checkEventFields() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addEventFilter();
        EventPage eventPage = searchPage.openFirstEvent();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(eventPage.isEventDateIsDisplayed(),
                "The Date is not displayed on the event details page");
        softAssert.assertTrue(eventPage.eventTimeIsDisplayed(),
                "The time is not displayed on the event details page");
        softAssert.assertTrue(eventPage.isEventAdmissionIsDisplayed(),
                "The admission is not displayed on the event details page");
        softAssert.assertTrue(eventPage.isShareViaTwitterLinkDisplayed(),
                "Share Twitter Button is not displayed on the event details page");
        softAssert.assertTrue(eventPage.isEventAudienceDisplayed(),
                "The audience is not displayed on the event details page");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkEventFilter"})
    public void checkAudienceFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        EventSearch eventSearch = new EventSearch();
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

    @Test(dependsOnMethods = {"checkEventFilter"})
    public void checkEventTypeFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        EventSearch eventSearch = new EventSearch();
        int numberEventsWithoutFilter = searchPage
                .addEventFilter()
                .getSearchResult();
        String chosenEventType = eventSearch
                .addEventTypeFilter()
                .addActiveEventType();
        int numberEventsWithEventTypeFilter = searchPage.getSearchResult();
        EventPage eventpage = searchPage.openFirstEvent();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(numberEventsWithoutFilter > numberEventsWithEventTypeFilter,
                "Search Results number haven't changed after applying Event Type Filter");
        softAssert.assertTrue(eventpage.isTagsContainsChosenEventType(chosenEventType),
                "Tags on the event details page don't contain chosen event type " + chosenEventType);
        softAssert.assertAll();
    }

    @Parameters({"keyword2"})
    @Test(dependsOnMethods = {"checkEventFilter"})
    public void checkKeywordFilter(String keyword2) {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int numberEventsWithoutFilter = searchPage
                .addEventFilter()
                .getSearchResult();
        int numberEventsWithKeywordFilter = searchPage.inputKeyword(keyword2)
                .chooseFirstSuggestion()
                .getSearchResult();
        Assert.assertTrue(numberEventsWithoutFilter > numberEventsWithKeywordFilter,
                "Search Results number haven't changed after applying Keyword Filter");
    }

    @Test(dependsOnMethods = {"checkEventFilter"})
    public void checkResultsNumberInBrackets() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int numberAudienceBracketsSearchResult = searchPage
                .addEventFilter()
                .addAudienceFilter()
                .getAudienceBracketsResultNumber();
        int numberAudienceSearchResults = searchPage.getSearchResult();
        Assert.assertEquals(numberAudienceBracketsSearchResult, numberAudienceSearchResults,
                "The results numbers in brackets and on the search results page don't coincide for Event Audience Filter");
    }

    @Test(dependsOnMethods = {"checkEventFilter"})
    public void checkSearchResultEventInfo() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
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

    @Test
    public void checkEventFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int resultsNumberWithoutEventFilter = searchPage.getSearchResult();
        int resultsNumberWithEventFilter = searchPage.addEventFilter().getSearchResult();
        Assert.assertTrue(resultsNumberWithoutEventFilter > resultsNumberWithEventFilter, "" +
                "Results Bumber doesn't change after applying Event Filter ");
    }
}



