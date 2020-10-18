package yale.tests;

import framework.BaseTest;
import framework.Browser;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.*;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchPeopleTest extends BaseTest {

    @Test(dependsOnMethods = {"checkPeopleFilter"})
    public void openProfilePageFromSearch() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addPeopleFilter();
        String searchPeopleName = searchPage.getFirstTitleText();
        ProfilePage profilePage = searchPage.clickFirstPeopleName();
        Assert.assertEquals(searchPeopleName, profilePage.getPeopleName(),
                "People Search Name and Name on the Profile Page don't coincide");
    }

    @Test
    public void checkPeopleFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int numberResultsWithoutPeopleFilter = searchPage.getSearchResult();
        int numberResultsWithPeopleFilter = searchPage.addPeopleFilter().getSearchResult();
        Assert.assertTrue(numberResultsWithoutPeopleFilter > numberResultsWithPeopleFilter,
                "Number of results don't changes after applying People Filter");
    }

    @Test
    public void checkProfilePage() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        ProfilePage profilePage = searchPage.addPeopleFilter()
                .clickFirstPeopleName();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(profilePage.isImageDisplayed(),
                "The image is not displayed");
        softAssert.assertTrue(profilePage.isNameIsDisplayed(),
                "The name is not displayed");
        softAssert.assertTrue(profilePage.isEditProfileLinkDisplayed(),
                "Edit Profile Button is not displayed");
        softAssert.assertTrue(profilePage.isSharePanelDisplayed(),
                "Share panel is not displayed");
        softAssert.assertFalse(profilePage.isTabNotExist(),
                "Tabs are not displayed");
        softAssert.assertTrue(profilePage.isTitleDisplayed(),
                "Title is not Displayed");
        softAssert.assertTrue(profilePage.isContactInformationDisplayed(),
                "Contact Information Section is not Displayed");
        softAssert.assertAll();
    }

    @Test
    public void checkDownloadPhoto() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addPeopleFilter();
        ProfilePage profilePage = searchPage.clickFirstPeopleName();
        profilePage.clickDownloadPhotoButton()
                .switchToNewTab();
        Assert.assertTrue(profilePage.isDownLoadedImageDisplayed(), "The Image is not Downloaded");
    }

    @Test(dependsOnMethods = {"checkPeopleFilter"})
    public void checkPeopleSearchByRole() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        ProfileSearch profileSearch = new ProfileSearch();
        int numberPeopleWithoutFilter = searchPage.addPeopleFilter().
                getSearchResult();
        String chosenRole = profileSearch.addRoleFilter();
        int numberPeopleWithFacultyFilter = searchPage.getSearchResult();
        String roleFirstResult = searchPage.getFirstSearchResultType();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(numberPeopleWithoutFilter > numberPeopleWithFacultyFilter,
                "Number of people after applying Role Filter more then before applying the filter");
        softAssert.assertEquals(roleFirstResult, chosenRole);
        softAssert.assertAll();
    }

    @Parameters({"researchArea"})
    @Test(dependsOnMethods = {"checkPeopleFilter"})
    public void checkResearchFilter(String researchArea) {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        ProfileSearch profileSearch = new ProfileSearch();
        int numberPeopleWithoutFilter = searchPage.addPeopleFilter().
                getSearchResult();
        int numberPeopleWithResearchFilter = profileSearch.
                inputResearchArea(researchArea)
                .chooseFirstSuggestion()
                .getSearchResult();
        ProfilePage profilePage = searchPage.clickFirstPeopleName()
                .openResearchAndPublicationTab();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(numberPeopleWithoutFilter > numberPeopleWithResearchFilter,
                "Search Results number haven't changed after applying Research Filter");
        softAssert.assertTrue(profilePage.isResearchInterestContainInputResearchArea(researchArea),
                "Research  interests don't contain inputted research area");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkPeopleFilter"})
    public void checkResultsNumberInBrackets() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int numberRoleBracketsSearchResult = searchPage.addPeopleFilter()
                .getBracketsRoleResultNumber();
        int numberRoleSearchResults = searchPage.getSearchResult();
        Assert.assertEquals(numberRoleBracketsSearchResult, numberRoleSearchResults,
                "The results numbers in brackets and on the search results page don't coincide for People Role Filter");
    }

    @Test(dependsOnMethods = {"checkPeopleFilter"})
    public void checkSearchResultPeopleInfo() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addPeopleFilter();
        searchPage.getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.isAllElementsHavePhotoOrDefaultImage(),
                "Not All elements have image");
        softAssert.assertTrue(searchPage.isAllElementsHaveType(),
                "Not All elements have types");
        softAssert.assertTrue(searchPage.isEachResultHaveTitle(),
                "Not All elements have titles");
        softAssert.assertAll();
    }

    @Parameters({"searchValue", "CVDownload"})
    @Test
    public void checkDownloadCV(String searchValue, String CVDownload) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch()
                .clickFindPeopleTab()
                .typePeopleName(searchValue);
        ProfilePage profilePage = searchOverlay.openFirstPeopleResult()
                .clickDownloadCVButton();
        String url = Browser.getCurrentUrl();
        Assert.assertTrue(profilePage.isURLContainsText(url, CVDownload));
    }
}
