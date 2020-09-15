package yale.tests;

import framework.listener.TestListener;
import framework.utilities.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.*;
import yale.services.CheckPopUp;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchPeople {
    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();
    String researchArea = "Epidemiology";
    ProfileSearch profileSearch = new ProfileSearch();

    @BeforeMethod
    public void openBrowser() {
        Browser.getInstance();
    }

    @Test
    public void searchUser() {
        mainPage.openMainPage();
        CheckPopUp.checkPopUp();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch();
        SearchPage searchPage = searchOverlay.clickSearchButton()
                .addPeopleFilter();
        //SoftAssert softAssert=new SoftAssert();
        //softAssert.assertTrue(searchPage.isSearchResultPeopleTypeIsDisplayed());
        Assert.assertTrue(searchPage.isSearchResultPeopleImageIsDisplayed());
        //softAssert.assertAll();
    }

    @Test
    public void openProfilePageFromSearch() {
        mainPage.openMainPage();
        CheckPopUp.checkPopUp();
        SearchPage searchPage =
                mainPage.clickPerformSearch()
                        .clickSearchButton()
                        .addPeopleFilter();
        ProfilePage profilePage = searchPage.clickFirstPeopleName();
        Assert.assertTrue(profilePage.isNameIsDisplayed(),
                "Profile name is not displayed on the profile page");
    }

    @Test
    public void checkProfilePage() {
        mainPage.openMainPage();
        CheckPopUp.checkPopUp();
        SearchPage searchPage =
                mainPage.clickPerformSearch()
                        .clickSearchButton()
                        .addPeopleFilter();
        ProfilePage profilePage = searchPage.clickFirstPeopleName();
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
        mainPage.openMainPage();
        CheckPopUp.checkPopUp();
        SearchPage searchPage =
                mainPage.clickPerformSearch()
                        .clickSearchButton()
                        .addPeopleFilter();
        ProfilePage profilePage = searchPage.clickFirstPeopleName();
        profilePage.clickDownloadPhotoButton()
                .switchToNewTab();
        Assert.assertTrue(profilePage.isDownLoadedImageDisplayed(), "The Image is not Downloaded");
    }

    @Test
    public void checkPeopleSearchByRole() {
        OpenSearchPage.openSearch();
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

    @Test
    public void checkResearchFilter() {
        OpenSearchPage.openSearch();
        int numberPeopleWithoutFilter = searchPage.addPeopleFilter().
                getSearchResult();
        int numberPeopleWithResearchFilter = profileSearch.
                inputResearchArea(researchArea)
                .chooseFirstSuggestion()
                .getSearchResult();
        searchPage.clickFirstPeopleName();
        Assert.assertTrue(numberPeopleWithoutFilter > numberPeopleWithResearchFilter,
                "Search Results number haven't changed after applying Research Filter");
    }

    @Test
    public void checkResultsNumberInBrackets() {
        OpenSearchPage.openSearch();
        int numberRoleBracketsSearchResult = searchPage.addPeopleFilter()
                .getBracketsRoleResultNumber();
        int numberRoleSearchResults = searchPage.getSearchResult();
        Assert.assertEquals(numberRoleBracketsSearchResult, numberRoleSearchResults,
                "The results numbers in brackets and on the search results page don't coincide for People Role Filter");
    }

    @Test
    public void checkSearchResultPeopleInfo() {
        OpenSearchPage.openSearch();
        searchPage.addPeopleFilter();
        searchPage.getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.isAllElementsHaveImage(),
                "Not All elements have image");
        softAssert.assertTrue(searchPage.isAllElementsHaveType(),
                "Not All elements have types");
        softAssert.assertTrue(searchPage.isEachResultHaveTitle(),
                "Not All elements have titles");
        softAssert.assertAll();
    }

    @AfterMethod
    public void closeBrowser() {
        Browser.closeBrowser();
    }
}
