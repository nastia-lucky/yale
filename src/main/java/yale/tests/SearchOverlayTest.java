package yale.tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.MainPage;
import yale.pageObjects.SearchOverlay;
import yale.pageObjects.SearchPage;

@Listeners({TestListener.class})
public class SearchOverlayTest extends BaseTest {

    private String searchValue1 = "Peggy";
    private String researchArea = "Epidemiology";

    @Test
    public void openSearchOverlay() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchOverlay.isInputSearchPanelDisplayed(),
                "Input Search Panel is not displayed");
        softAssert.assertTrue(searchOverlay.isFindPeopleTabDisplayed(),
                "Find People Tab is not displayed");
        softAssert.assertTrue(searchOverlay.isPopularLinksTabDisplayed(),
                "Popular Links Tab is not displayed");
        softAssert.assertTrue(searchOverlay.isSearchTabDisplayed(),
                "Search Tab is not displayed");
        softAssert.assertAll();
    }

    @Test
    public void checkClearButton() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch().inputSearchPanelValue(searchValue1);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchOverlay.isSearchResultDetailsDisplayed(),
                "Search Result Details is not displayed");
        searchOverlay.clickClearButton();
        softAssert.assertTrue(searchOverlay.isResultsDisappears(),
                "Results doesn't disappeared");
        softAssert.assertAll();
    }

    @Test
    public void checkSuggestions() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch().inputSearchPanelValue(searchValue1);
        Assert.assertTrue(searchOverlay.isSearchResultContainsSearchValue(searchValue1),
                "Search Results don't contain inputted search value");
    }

    @Test
    public void checkOpenSearchPageFromSearchOverlay() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchPage searchPage = mainPage.clickPerformSearch()
                .inputSearchPanelValue(searchValue1)
                .clickSearchButton();
        Assert.assertEquals(searchPage.getExistingSearchValue(), searchValue1,
                "Search value on the search page doesn't coincide with the search value on the search overlay");
    }

    @Test
    public void openPageFromSuggestion() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch()
                .inputSearchPanelValue(searchValue1);
        Thread.sleep(3000);
        searchOverlay.openFirstResult();
        Assert.assertTrue(searchOverlay.isTitleContainsSearchValue(searchValue1),
                "Page title doesn't contain Search value");
    }

    @Test
    public void checkFindPeople() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch()
                .clickFindPeopleTab()
                .typePeopleName(searchValue1);
        Thread.sleep(2000);
        Assert.assertTrue(searchOverlay.isSearchResultContainsSearchValue(searchValue1),
                "Search Results don't contain inputted search value");
    }

    @Test
    public void checkSearchCountByResearchArea() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch()
                .clickFindPeopleTab()
                .typePeopleName(searchValue1);
        int searchCountByPeopleName = searchOverlay.getSearchCount();
        searchOverlay.typeResearchArea(researchArea)
                .clickSuggestionForResearchArea();
        int searchCountByResearchAreaAndPeopleName = searchOverlay.getSearchCount();
        Assert.assertTrue(searchCountByPeopleName > searchCountByResearchAreaAndPeopleName,
                "Search Results number haven't changed after applying Research Area Filter");
    }

    @Test
    public void checkSearchCountByUserRole() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch()
                .clickFindPeopleTab()
                .typePeopleName(searchValue1);
        int searchCountByPeopleName = searchOverlay.getSearchCount();
        searchOverlay.openRoleDropDown()
                .addRoleOption();
        int searchCountByUserRoleAndPeopleName = searchOverlay.getSearchCount();
        Assert.assertTrue(searchCountByPeopleName > searchCountByUserRoleAndPeopleName,
                "Search Results number haven't changed after applying User Role Filter");
    }
}
