package yale.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import framework.listener.TestListener;
import yale.pageObjects.MediaPage;
import yale.pageObjects.MediaSearch;
import yale.pageObjects.SearchPage;
import yale.services.OpenSearchPage;
import framework.utilities.Browser;

@Listeners({TestListener.class})
public class SearchForMedia {

    SearchPage searchPage = new SearchPage();
    MediaPage mediaPage = new MediaPage();
    MediaSearch mediaSearchPage = new MediaSearch();

    @BeforeMethod
    public void openBrowser() {
        Browser.getInstance();
    }

    @Test
    public void checkMediaFilter() {
        OpenSearchPage.openSearch();
        int numberMediaWithoutFilters = searchPage
                .addMediaFilter()
                .getSearchResult();
        String chosenMediaType = mediaSearchPage.addActiveMediaFilter();
        int numberMediaWithMediaTypeFilter = searchPage.getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(numberMediaWithoutFilters > numberMediaWithMediaTypeFilter,
                "Search Results number haven't changed after applying Media Type Filter");
        softAssert.assertTrue(searchPage.isSearchResultMediaTypeCoincideWithChosen(chosenMediaType),
                "Media type on search results doesn't coincide with the chosen media type " + chosenMediaType);
        softAssert.assertAll();
    }

    @Test
    public void checkAudioModal() {
        OpenSearchPage.openSearch();
        mediaPage = searchPage.addMediaFilter()
                .openMediaPopUp()
                .clickPlayButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(mediaPage.isPauseButtonIsDisplayed());
        mediaPage.clickTranscriptButton();
        softAssert.assertTrue(mediaPage.idMediaTitleDisplayed(),
                "Media Title is not displayed");
        softAssert.assertTrue(mediaPage.isInformationTabDisplayed(),
                "Information Tab is not displayed");
        softAssert.assertAll();
    }

    @Test
    public void checkSearchResultMediaInfo() {
        OpenSearchPage.openSearch();
        String mediaFilter = searchPage
                .addMediaFilter()
                .addActiveMediaFilter();
        searchPage.getSearchResult();
        SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(searchPage.isEachSearchResultHaveType(mediaFilter),
                    "Not each Search Media Result has Type");
            softAssert.assertTrue(searchPage.isEachResultHaveTitle(),
                    "Not each Search Media Result has Title");
            softAssert.assertTrue(searchPage.isEachMediaHaveDuration(),
                    "Not each Search Media Result has Duration");
            softAssert.assertTrue(searchPage.isAllElementsHaveSummary(),
                    "Not each Search Media Result has Summary");
            softAssert.assertAll();
    }

    @Test
    public void checkMediaResultsNumberInBrackets() {
        OpenSearchPage.openSearch();
        int numberMediaTypeBracketsSearchResult = searchPage.addMediaFilter()
                .getBracketsMediaTypeResultNumber();
        int numberRoleSearchResults = searchPage.getSearchResult();
        Assert.assertEquals(numberMediaTypeBracketsSearchResult, numberRoleSearchResults,
                "The results numbers in brackets and on the search results page don't coincide for Media Type Filter");
    }

    @AfterMethod
    public void closeBrowser() {
        Browser.closeBrowser();
    }
}
