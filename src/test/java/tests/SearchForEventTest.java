package tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.EventPage;
import yale.pageObjects.EventSearch;
import yale.pageObjects.MainPage;
import yale.pageObjects.SearchPage;
import yale.services.OpenSearchPage;

import java.time.LocalDate;

@Listeners({TestListener.class})
public class SearchForEventTest extends BaseTest {

    @Test(dependsOnMethods = {"checkEventFilter"}, description = "Verify user can open event page from search")
    public void openEventDetailsPageFromSearch() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addEventFilter();
        String searchResultTitle = searchPage.getFirstEventTitle();
        EventPage eventPage = searchPage.openFirstEvent();
        Assert.assertEquals(eventPage.getEventTitle(), searchResultTitle,
                "Title of Event From Search result and on the details page don't coincide");
    }

    @Test(dependsOnMethods = {"checkEventFilter"}, description = "Verify that event info (date, time, admission, share twitter button, audience) is displayed correctly")
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

    @Test(dependsOnMethods = {"checkEventFilter"}, description = "Verify events search results are refined after applying audience filter")
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

    @Test(dependsOnMethods = {"checkEventFilter"}, description = "Verify events search results are refined after applying event type filter")
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
    @Test(dependsOnMethods = {"checkEventFilter"}, description = "Verify events search results are refined after applying keyword filter")
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

    @Test(dependsOnMethods = {"checkEventFilter"}, description = "Verify events search results number in brackets")
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

    @Test(dependsOnMethods = {"checkEventFilter"}, description = "Verify events search result info(title, type, time, date)")
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

    @Test(description = "Verify search results are refined after applying events filter")
    public void checkEventFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int resultsNumberWithoutEventFilter = searchPage.getSearchResult();
        int resultsNumberWithEventFilter = searchPage.addEventFilter().getSearchResult();
        Assert.assertTrue(resultsNumberWithoutEventFilter > resultsNumberWithEventFilter, "" +
                "Results Number doesn't change after applying Event Filter ");
    }

    @Test(description = "Verify events search results are refined after applying date filter")
    public void checkEventDateFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        LocalDate chosenDate = searchPage.addEventFilter()
                .clickDatePicker()
                .clickAndGetFirstDate();
        LocalDate dateFirstEvent = searchPage.getSearchResultDate();
        Assert.assertTrue(dateFirstEvent.compareTo(chosenDate) >= 0,
                "Date of first event less than chosen date");
    }

    @Test(description = "Verify recurring events have circle")
    public void checkRecurringEventsHaveCircle() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addEventFilter();
        Assert.assertTrue(searchPage.isTheRecurringEventsHaveCircle(), "Not Each Recurring Events has circle ");
    }

    @Parameters({"eventTitle"})
    @Test(description = "Verify that event page contain all possible content")
    public void checkEventContent(String eventTitle) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchPage searchPage = mainPage.clickPerformSearch()
                .inputSearchPanelValue(eventTitle)
                .clickSearchButton();
        EventPage eventPage = searchPage.openFirstEvent();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(eventPage.isEventAdmissionIsDisplayed(),
                "Event Admission is not Displayed");
        softAssert.assertTrue(eventPage.isEventAudienceDisplayed(),
                "Event Audience is not Displayed");
        softAssert.assertTrue(eventPage.isEventDateIsDisplayed(),
                "Event Date is not Displayed");
        softAssert.assertTrue(eventPage.eventTimeIsDisplayed(),
                "Event Time is not Displayed");
        softAssert.assertTrue(eventPage.idSpeakerSectionTitleDisplayed(),
                "Speaker Section Title is not displayed");
        softAssert.assertTrue(eventPage.isSpeakerSectionNotEmpty(),
                "Speaker Section is empty");
        softAssert.assertTrue(eventPage.isContactSectionTitleDisplayed(),
                "Contact Section Title is not displayed");
        softAssert.assertTrue(eventPage.isContactSectionNotEmpty(),
                "Contact Section is empty");
        softAssert.assertTrue(eventPage.isHostSectionTitleDisplayed(),
                "Host Section Title is not displayed");
        softAssert.assertTrue(eventPage.isHostSectionNotEmpty(),
                "Host Section is empty");
        softAssert.assertTrue(eventPage.isHostOrganizationSectionTitleDisplayed(),
                "Host Organization Section Title is not displayed");
        softAssert.assertTrue(eventPage.isHostOrganizationSectionNotEmpty(),
                "Host Organization Section Title is empty");
        softAssert.assertTrue(eventPage.isTagsSectionTitleIsDisplayed(),
                "Host Organization Section Title is empty");
        softAssert.assertTrue(eventPage.isTagsSectionNotEmpty(),
                "Tags Section is empty");
        softAssert.assertTrue(eventPage.isFoodSectionTitleDisplayed(),
                "Food Section Title is not displayed");
        softAssert.assertTrue(eventPage.isFoodSectionNotEmpty(),
                "Food Section is empty");
        softAssert.assertTrue(eventPage.isEventRSVPSectionTitleDisplayed(),
                "Event RSVP section title is not displayed");
        softAssert.assertTrue(eventPage.isEventRSVPSectionNotEmpty(),
                "Event RSVP Section is empty");
        softAssert.assertTrue(eventPage.isEventRSVPButtonDisplayed(),
                "Event RSVP Button is not displayed");
        softAssert.assertTrue(eventPage.isRelatedLinksSectionTitleIsDisplayed(),
                "Related Links Section Title is not displayed");
        softAssert.assertTrue(eventPage.isRelatedLinksSectionNotEmpty(),
                "Related Links Section Section is empty");
        softAssert.assertTrue(eventPage.isDownloadFlyerButtonDisplayed(),
                "Download Flyer Button is not displayed");
        softAssert.assertTrue(eventPage.isMapDisplayed(),
                "Map is not displayed");
        softAssert.assertTrue(eventPage.isAddSingleEventLinkDisplayed(),
                "Add Single Event Link is not displayed");
        softAssert.assertAll();
    }

    @Parameters({"eventTitle"})
    @Test(description = "Verify Google Map for Event opens")
    public void checkGoogleMapOpen(String eventTitle) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchPage searchPage = mainPage.clickPerformSearch()
                .inputSearchPanelValue(eventTitle)
                .clickSearchButton();
        EventPage eventPage = searchPage.openFirstEvent().clickFirstEventHeaderLink();
        String currentURL = eventPage.getCurrentURL();
        Assert.assertTrue(eventPage.isURLIncludeGoogleMapLink(currentURL),
                "Current URL doesn't include Google Map link");
    }

    @Test(description = "Verify Next Occurrences Section")
    public void checkGoogleMapOpen() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchPage searchPage = mainPage.clickPerformSearch()
                .inputSearchPanelValue("recurring bonaire")
                .clickSearchButton();
        EventPage eventPage = searchPage.openFirstEvent();}

}



