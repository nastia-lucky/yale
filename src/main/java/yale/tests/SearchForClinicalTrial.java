package yale.tests;

import framework.listener.TestListener;
import framework.utilities.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.ClinicalTrialPage;
import yale.pageObjects.ClinicalTrialSearch;
import yale.pageObjects.MainPage;
import yale.pageObjects.SearchPage;
import yale.services.CheckPopUp;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForClinicalTrial {
    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();
    private String volunteerNowLink = "https://acceptance.medicine.yale.edu/ycci/clinicaltrials/find/mychart/";
    ClinicalTrialSearch trialSearch = new ClinicalTrialSearch();

    @BeforeMethod
    public void openBrowser() {
        Browser.getInstance();
    }

    @Test
    public void openClinicalTrialPageFromSearch() {
        mainPage.openMainPage();
        CheckPopUp.checkPopUp();
        SearchPage searchPage =
                mainPage.clickPerformSearch()
                        .clickSearchButton()
                        .addClinicalTrialFilter();
        String firstResultTitle = searchPage.getFirstClinicalTrialTitle();
        ClinicalTrialPage trialPage = searchPage.openFirstTrial();
        Assert.assertEquals(trialPage.getTrialPageTitle(), firstResultTitle,
                "Title of first trial on the results page doesn't coincide with title on the clinical trial page");
    }

    @Test
    public void checkClinicalTrialPage() {
        mainPage.openMainPage();
        CheckPopUp.checkPopUp();
        SearchPage searchPage =
                mainPage.clickPerformSearch()
                        .clickSearchButton()
                        .addClinicalTrialFilter();
        ClinicalTrialPage trialPage = searchPage.openFirstTrial();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(trialPage.isClinicalTrialTitleDisplayed(),
                "Clinical Trial Title is not displayed");
        softAssert.assertTrue(trialPage.isClinicalTrialsInvestigatorsDisplayed(),
                "Clinical Trial Investigators are not displayed");
        softAssert.assertTrue(trialPage.isVolunteerSummaryDisplayed(),
                "Clinical Trial Volunteer Summary is not Displayed");
        softAssert.assertTrue(trialPage.isContactInformationDisplayed(),
                "Contact Information is not Displayed");
        softAssert.assertAll();
    }

    @Test
    public void checkVolunteerNowButton() throws InterruptedException {
        mainPage.openMainPage();
        CheckPopUp.checkPopUp();
        SearchPage searchPage =
                mainPage.clickPerformSearch()
                        .clickSearchButton()
                        .addClinicalTrialFilter();
        ClinicalTrialPage trialPage = searchPage.openFirstTrial();
        trialPage.clickVolunteerNowButton();
        Thread.sleep(4000);
        Assert.assertTrue(mainPage.isClinicalTrialHeaderIsDisplayed(),
                "Volunteer Now Link isn't correct");
    }

    @Test
    public void checkGenderFilter() {
        OpenSearchPage.openSearch();
        int numberTrialWithoutFilters = searchPage.addClinicalTrialFilter()
                .getSearchResult();
        String chosenGender = trialSearch.addGenderFilter()
                .addActiveGender();
        int numberTrialsWithGenderFilter = searchPage.getSearchResult();
        ClinicalTrialPage trialPage = searchPage.openFirstTrial();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(numberTrialWithoutFilters > numberTrialsWithGenderFilter,
                "Search Results number haven't changed after applying Gender Filter");
        softAssert.assertTrue(trialPage.isGenderCoincideWithChosenGender(chosenGender),
                "Gender on the clinical trial page doesn't coincide with the chosen gender " + chosenGender);
        softAssert.assertAll();
    }

    @Test
    public void checkAcceptHealthyVolunteersFilter() {
        OpenSearchPage.openSearch();
        int numberTrialWithoutFilters = searchPage.addClinicalTrialFilter()
                .getSearchResult();
        int numberTrialWithHealthyFilter = trialSearch.addAcceptHealthyFilter()
                .addActiveHealthyItem()
                .getSearchResult();
        Assert.assertTrue(numberTrialWithoutFilters > numberTrialWithHealthyFilter,
                "Search Results number haven't changed after applying Accept Healthy Volunteers Filter");
    }

    @Test
    public void checkCategoryFilter() {
        OpenSearchPage.openSearch();
        int numberTrialWithoutFilters = searchPage.addClinicalTrialFilter()
                .getSearchResult();
        String chosenCategory = trialSearch.addCategoryFilter()
                .addActiveCategory();
        int numberTrialWithCategoryFilter = searchPage.getSearchResult();
        ClinicalTrialPage trialPage = searchPage.openFirstTrial();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(numberTrialWithoutFilters > numberTrialWithCategoryFilter,
                "Search Results number haven't changed after applying Category Filter");
        softAssert.assertTrue(trialPage.isConditionsContainsChosenCategory(chosenCategory));
        softAssert.assertAll();
    }

    @Test
    public void checkResultsNumberInBrackets() {
        OpenSearchPage.openSearch();
        int numberGenderBracketsSearchResult = searchPage
                .addClinicalTrialFilter()
                .addGenderFilter()
                .getBracketsGenderResultNumber();
        int numberGenderSearchResults = searchPage.getSearchResult();
        Assert.assertEquals(numberGenderBracketsSearchResult, numberGenderSearchResults,
                "The results numbers in brackets and on the search results page don't coincide for Clinical Trial Gender Filter");
    }

    @Test
    public void checkSearchResultClinicalTrialInfo() {
        OpenSearchPage.openSearch();
        searchPage.addClinicalTrialFilter()
                .getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.isEachResultHaveTitle());
        softAssert.assertTrue(searchPage.isEachSearchResultHaveType("Clinical Trial"));
        softAssert.assertAll();
    }

    @AfterMethod
    public void closeBrowser() {
        Browser.closeBrowser();
    }
}
