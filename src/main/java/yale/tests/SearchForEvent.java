package yale.tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.EventPage;
import yale.pageObjects.EventSearch;
import yale.pageObjects.SearchPage;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForEvent extends BaseTest {

    String keyword = "Cancer";

    @Test
    public void openEventDetailsPageFromSearch() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addEventFilter();
        String searchResultTitle = searchPage.getFirstEventTitle();
        EventPage eventPage = searchPage.openFirstEvent();
        Assert.assertEquals(eventPage.getEventTitle(), searchResultTitle,
                "Title of Event From Search result and on the details page don't coincide");
    }

    @Test
    public void checkEventFields() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addEventFilter();
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

    @Test
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
        SearchPage searchPage = new SearchPage();
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
        SearchPage searchPage = new SearchPage();
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
}



