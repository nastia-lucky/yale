package yale.tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.MainPage;
import yale.pageObjects.ProfilePage;
import yale.pageObjects.SearchOverlay;
import yale.pageObjects.SearchPage;

@Listeners({TestListener.class})
public class SearchOverlayTest extends BaseTest {

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
    @Parameters({"searchValue1"})
    @Test (dependsOnMethods = { "openSearchOverlay" })
    public void checkClearButton(String searchValue1) {
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
    @Parameters({"searchValue1"})
    @Test (dependsOnMethods = { "openSearchOverlay" })
    public void checkSuggestions(String searchValue1) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch()
                .clickFindPeopleTab()
                .typePeopleName(searchValue1);
        Assert.assertTrue(searchOverlay.isSearchResultContainsSearchValue(searchValue1),
                "Search Results don't contain inputted search value");
    }
    @Parameters({"searchValue1"})
    @Test (dependsOnMethods = { "openSearchOverlay" })
    public void checkOpenSearchPageFromSearchOverlay(String searchValue1) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchPage searchPage = mainPage.clickPerformSearch()
                .inputSearchPanelValue(searchValue1)
                .clickSearchButton();
        Assert.assertEquals(searchPage.getExistingSearchValue(), searchValue1,
                "Search value on the search page doesn't coincide with the search value on the search overlay");
    }
    @Parameters({"searchValue1"})
    @Test (dependsOnMethods = { "openSearchOverlay" })
    public void openPageFromSuggestion(String searchValue1) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch()
                .clickFindPeopleTab()
                .typePeopleName(searchValue1);
        ProfilePage profilePage = searchOverlay.openFirstPeopleResult();
        Assert.assertTrue(profilePage.isTitleContainsSearchValue(searchValue1),
                "Page title doesn't contain Search value");
    }
    @Parameters({"searchValue1"})
    @Test (dependsOnMethods = { "openSearchOverlay" })
    public void checkFindPeople(String searchValue1) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch()
                .clickFindPeopleTab()
                .typePeopleName(searchValue1);
        Assert.assertTrue(searchOverlay.isSearchResultContainsSearchValue(searchValue1),
                "Search Results don't contain inputted search value");
    }
    @Parameters({"researchArea", "searchValue1"})
    @Test (dependsOnMethods = { "openSearchOverlay" })
    public void checkSearchCountByResearchArea(String researchArea, String searchValue1) {
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
    @Parameters({"searchValue1"})
    @Test (dependsOnMethods = { "openSearchOverlay" })
    public void checkSearchCountByUserRole(String searchValue1) {
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
