package tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.ClinicalTrialPage;
import yale.pageObjects.ClinicalTrialSearch;
import yale.pageObjects.MainPage;
import yale.pageObjects.SearchPage;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForClinicalTrialTest extends BaseTest {

    @Test(dependsOnMethods = {"checkClinicalTrialFilter"}, description = "Verify user can open clinical trial from search")
    public void openClinicalTrialPageFromSearchTest() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addClinicalTrialFilter();
        String firstResultTitle = searchPage.getFirstClinicalTrialTitle();
        ClinicalTrialPage trialPage = searchPage.openFirstTrial();
        Assert.assertEquals(trialPage.getTrialPageTitle(), firstResultTitle,
                "Title of first trial on the results page doesn't coincide with title on the clinical trial page");
    }

    @Test(dependsOnMethods = {"checkClinicalTrialFilter"}, description = "Verify that clinical trial info (title, volunteer and physician summary, criteria text and investigators) is displayed correctly")
    public void checkClinicalTrialPageTest() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addClinicalTrialFilter();
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

    @Test(dependsOnMethods = {"checkClinicalTrialFilter"}, description = "Verify after cicking Volunteer Now button user is redirected to Volunteer Online with MyChart web-page")
    public void checkVolunteerNowButton() {
        MainPage mainPage = new MainPage();
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addClinicalTrialFilter();
        ClinicalTrialPage trialPage = searchPage.openFirstTrial();
        trialPage.clickVolunteerNowButton();
        Assert.assertTrue(mainPage.isClinicalTrialHeaderIsDisplayed(),
                "Volunteer Now Link isn't correct");
    }

    @Test(dependsOnMethods = {"checkClinicalTrialFilter"}, description = "Verify that clinical trials search results are refined after applying Gender filter")
    public void checkGenderFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        ClinicalTrialSearch trialSearch = new ClinicalTrialSearch();
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

    @Test(dependsOnMethods = {"checkClinicalTrialFilter"}, description = "Verify  clinical trials search results are refined after applying Accept HealthyVolunteers Filter")
    public void checkAcceptHealthyVolunteersFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        ClinicalTrialSearch trialSearch = new ClinicalTrialSearch();
        int numberTrialWithoutFilters = searchPage.addClinicalTrialFilter()
                .getSearchResult();
        int numberTrialWithHealthyFilter = trialSearch.addAcceptHealthyFilter()
                .addActiveHealthyItem()
                .getSearchResult();
        Assert.assertTrue(numberTrialWithoutFilters > numberTrialWithHealthyFilter,
                "Search Results number haven't changed after applying Accept Healthy Volunteers Filter");
    }

    @Test(dependsOnMethods = {"checkClinicalTrialFilter"}, description = "Verify  clinical trials search results are refined after applying Category filter")
    public void checkCategoryFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        ClinicalTrialSearch trialSearch = new ClinicalTrialSearch();
        int numberTrialWithoutFilters = searchPage.addClinicalTrialFilter()
                .getSearchResult();
        String chosenCategory = trialSearch.addCategoryFilter()
                .addActiveCategory();
        int numberTrialWithCategoryFilter = searchPage.getSearchResult();
        ClinicalTrialPage trialPage = searchPage.openFirstTrial();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(numberTrialWithoutFilters > numberTrialWithCategoryFilter,
                "Search Results number haven't changed after applying Category Filter");
        softAssert.assertTrue(trialPage.isConditionsContainsChosenCategory(chosenCategory),
                "Clinical Trials Conditions don't contain chosen category");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkClinicalTrialFilter"}, description = "Verify clinical trials search results number in brackets")
    public void checkResultsNumberInBrackets() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int numberGenderBracketsSearchResult = searchPage
                .addClinicalTrialFilter()
                .addGenderFilter()
                .getBracketsGenderResultNumber();
        int numberGenderSearchResults = searchPage.getSearchResult();
        Assert.assertEquals(numberGenderBracketsSearchResult, numberGenderSearchResults,
                "The results numbers in brackets and on the search results page don't coincide for Clinical Trial Gender Filter");
    }

    @Test(dependsOnMethods = {"checkClinicalTrialFilter"}, description = "Verify clinical trial search result info")
    public void checkSearchResultClinicalTrialInfo() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addClinicalTrialFilter()
                .getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.isEachResultHaveTitle(), "" +
                "Not each Search Result has Title");
        softAssert.assertTrue(searchPage.isEachSearchResultHaveType("Clinical Trial"),
                "Not each Search Result has Type");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkClinicalTrialFilter"}, description = "Verify that health professional tab contains trial purpose and desription")
    public void checkHealthProfessionalTab() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addClinicalTrialFilter();
        ClinicalTrialPage trialPage = searchPage.openFirstTrial()
                .openHealthProfessionalTab();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(trialPage.isTrialPurposeTitleIsDisplayed(),
                "Trial Purpose is not displayed in Health Professional Tab");
        softAssert.assertTrue(trialPage.isTrialDescriptionDisplayed(),
                "Trial Description is not displayed in Health Professional Tab");
        softAssert.assertAll();
    }

    @Test(description = "Verify search results are refined after applying clinical trial filter")
    public void checkClinicalTrialFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int resultsNumberWithoutEventFilter = searchPage.getSearchResult();
        int resultsNumberWithClinicalTrialFilter = searchPage.addClinicalTrialFilter().getSearchResult();
        Assert.assertTrue(resultsNumberWithoutEventFilter > resultsNumberWithClinicalTrialFilter,
                "Search Results Number don't changes after applying Clinical Trial Filter");
    }
}
