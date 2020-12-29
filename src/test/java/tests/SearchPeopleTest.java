package tests;

import framework.BaseTest;
import framework.Browser;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.*;
import yale.pageObjects.ProfilePageTabs.LocationsTab;
import yale.pageObjects.ProfilePageTabs.NewsTab;
import yale.pageObjects.ProfilePageTabs.PatientCareTab;
import yale.pageObjects.ProfilePageTabs.ResearchTab;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchPeopleTest extends BaseTest {

    @Test(dependsOnMethods = {"checkPeopleFilter"}, description = "Verify user can open profile page from search")
    public void openProfilePageFromSearch() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addPeopleFilter();
        String searchPeopleName = searchPage.getFirstTitleText();
        ProfilePage profilePage = searchPage.clickFirstPeopleName();
        Assert.assertEquals(searchPeopleName, profilePage.getPeopleName(),
                "People Search Name and Name on the Profile Page don't coincide");
    }

    @Test(description = "Verify search results are refined after applying people filter")
    public void checkPeopleFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int numberResultsWithoutPeopleFilter = searchPage.getSearchResult();
        int numberResultsWithPeopleFilter = searchPage.addPeopleFilter().getSearchResult();
        Assert.assertTrue(numberResultsWithoutPeopleFilter > numberResultsWithPeopleFilter,
                "Number of results don't changes after applying People Filter");
    }

    @Test(description = "Verify photo can be downloaded")
    public void checkDownloadPhoto() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addPeopleFilter();
        ProfilePage profilePage = searchPage.clickFirstPeopleName();
        profilePage.clickDownloadPhotoButton()
                .switchToNewTab();
        Assert.assertTrue(profilePage.isDownLoadedImageDisplayed(), "The Image is not Downloaded");
    }

    @Test(dependsOnMethods = {"checkPeopleFilter"}, description = "Verify people search results are refined after applying role filter")
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


    @Test(dependsOnMethods = {"checkPeopleFilter"}, description = "Verify people search results are refined after applying research filter")
    public void checkResearchFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        ProfileSearch profileSearch = new ProfileSearch();
        //ProfileSearch profileSearch=searchPage.addPeopleFilter();
        int numberPeopleWithoutFilter = profileSearch.
                getSearchResult();
        int numberPeopleWithResearchFilter = profileSearch.addPeopleFilter().
                inputResearchArea("Epidemiology")
                .chooseFirstSuggestion()
                .getSearchResult();
        ProfilePage profilePage = searchPage.clickFirstPeopleName()
                .openResearchAndPublicationTab();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(numberPeopleWithoutFilter > numberPeopleWithResearchFilter,
                "Search Results number haven't changed after applying Research Filter");
        softAssert.assertTrue(profilePage.isResearchInterestContainInputResearchArea("Epidemiology"),
                "Research  interests don't contain inputted research area");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkPeopleFilter"}, description = "Verify people search results number in brackets")
    public void checkResultsNumberInBrackets() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int numberRoleBracketsSearchResult = searchPage.addPeopleFilter()
                .getBracketsRoleResultNumber();
        int numberRoleSearchResults = searchPage.getSearchResult();
        Assert.assertEquals(numberRoleBracketsSearchResult, numberRoleSearchResults,
                "The results numbers in brackets and on the search results page don't coincide for People Role Filter");
    }

    @Test(dependsOnMethods = {"checkPeopleFilter"}, description = "Verify people search result info(title, type, image)")
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
    @Test(description = "Verify CV can be downloaded")
    public void checkDownloadCV(String searchValue, String CVDownload) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchOverlay searchOverlay = mainPage.clickPerformSearch()
                .clickFindPeopleTab()
                .typePeopleName(searchValue);
        ProfilePage profilePage = searchOverlay.openFirstPeopleResult()
                .clickDownloadCVButton();
        String url = Browser.getCurrentUrl();
        Assert.assertTrue(profilePage.isURLContainsText(url, CVDownload), "The File is not Downloaded");
    }

    @Parameters({"profileName", "profileKeyword"})
    @Test(description = "Verify people Biography info is displayed correctly on profile page")
    public void checkProfileBiographyContent(String profileName, String profileKeyword) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchPage searchPage = mainPage.clickPerformSearch()
                .inputSearchPanelValue(profileName)
                .clickSearchButton();
        ProfilePage profilePage = searchPage.openFirstPeopleProfile();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(profilePage.isImageDisplayed(),
                "The image is not displayed");
        softAssert.assertTrue(profilePage.isNameIsDisplayed(),
                "The name is not displayed");
        softAssert.assertTrue(profilePage.isSharePanelDisplayed(),
                "Share panel is not displayed");
        softAssert.assertTrue(profilePage.isTitleDisplayed(),
                "Title is not Displayed");
        softAssert.assertTrue(profilePage.isContactInformationTitleDisplayed(),
                "Contact Information Title is not Displayed");
        softAssert.assertTrue(profilePage.isContactInformationSectionNotEmpty(),
                "Contact Information Section is Empty");
        softAssert.assertTrue(profilePage.isGeneralInfoSectionNotEmpty(),
                "General Info Section is Empty");
        softAssert.assertTrue(profilePage.isPatientCareLocationTitleDisplayed(),
                "Patient Care Location Title is not Displayed");
        softAssert.assertTrue(profilePage.isMailingAddressTitleDisplayed(),
                "Mailing Address Title is not Displayed");
        softAssert.assertTrue(profilePage.isMailingAddressSectionNotEmpty(),
                "Mailing Address Section is Empty");
        softAssert.assertTrue(profilePage.isLabWebsiteButtonDisplayed(),
                "Lab Website Button is not Displayed");
        softAssert.assertTrue(profilePage.isDownloadCVButtonDisplayed(),
                "Download CV Button is not Displayed");
        softAssert.assertTrue(profilePage.isRelatedLinksSectionTitleDisplayed(),
                "Related Links Section Title is not Displayed");
        softAssert.assertTrue(profilePage.isRelatedLinksSectionNotEmpty(),
                "Related Links Section is Empty");
        softAssert.assertTrue(profilePage.isYMLInkDisplayed(),
                "YM Link is not Displayed");
        softAssert.assertTrue(profilePage.isBiographySectionTitleDisplayed(),
                "Biography Section Title is not Displayed");
        softAssert.assertTrue(profilePage.isBiographySectionNotEmpty(profileKeyword),
                "Biography Section is Empty");
        softAssert.assertTrue(profilePage.isEducationAndTrainingSectionTitleDisplayed(),
                "Education and Training Section Title is not Displayed");
        softAssert.assertTrue(profilePage.isEducationItemsHaveDegree(),
                "Education Items don't have degree");
        softAssert.assertTrue(profilePage.isEducationItemsHaveInstitution(),
                "Education Items don't have institution");
        softAssert.assertTrue(profilePage.isActivitiesSectionTitleDisplayed(),
                "Activities Section Title is Not Displayed");
        softAssert.assertTrue(profilePage.isActivityItemsHaveTitle(),
                "Activities items don't have title");
        softAssert.assertTrue(profilePage.isActivityItemsHavePlace(),
                "Activities items don't have place");
        softAssert.assertTrue(profilePage.isActivityItemsHaveDescription(),
                "Activities items don't have description");
        softAssert.assertTrue(profilePage.isHonorsRecognitionSectionTitleDisplayed(),
                "Honors And Recognition Section Title is Not Displayed");
        softAssert.assertTrue(profilePage.isDepartmentsOrganizationsSectionTitleDisplayed(),
                "Departments & Organizations Section Title is Not Displayed");
        softAssert.assertTrue(profilePage.isDepartmentsOrganizationsSectionNotEmpty(),
                "Departments And Organizations Section is Empty");
        softAssert.assertTrue(profilePage.isRelatedMediaSectionTitleDisplayed(),
                "Related Media Section Title is Not Displayed");
        softAssert.assertTrue(profilePage.isMediaListDisplayed(),
                "Media List is Not Displayed");
        softAssert.assertTrue(profilePage.isMediaPreviewDisplayed(),
                "Media Preview is Not Displayed");
        softAssert.assertAll();
    }

    @Parameters({"profileName"})
    @Test(description = "Verify Biography info is displayed correctly on profile page")
    public void checkProfileResearchAndPublicationsContent(String profileName) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchPage searchPage = mainPage.clickPerformSearch()
                .inputSearchPanelValue(profileName)
                .clickSearchButton();
        ResearchTab researchPage = searchPage.openFirstPeopleProfile().openResearchAndPublicationTab();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(researchPage.isResearchSummaryTitleDisplayed(),
                "Research Summary Title is Displayed");
        softAssert.assertTrue(researchPage.isResearchSectionNotEmpty(),
                "Research Summary Section is Empty");
        softAssert.assertTrue(researchPage.isResearchDescriptionTitleDisplayed(),
                "Extensive Research Description Title is not Displayed");
        softAssert.assertTrue(researchPage.isExtensiveResearchDescriptionSectionNotEmpty(),
                "Extensive Research Description Section is Empty");
        softAssert.assertTrue(researchPage.isResearchInterestsSectionTitleDisplayed(),
                "Extensive Research Interests Section Title is not Displayed");
        softAssert.assertTrue(researchPage.isResearchInterestsSectionNotEmpty(),
                "Research Interests Section is Empty");
        softAssert.assertTrue(researchPage.isPublicHealthInterestsSectionTitleDisplayed(),
                "Public Health Interests Section Title is not Displayed");
        softAssert.assertTrue(researchPage.isPublicHealthInterestsSectionNotEmpty(),
                "Public Health Interests Section is Empty");
        softAssert.assertTrue(researchPage.isResearchImagesSectionTitleDisplayed(),
                "Research Images Section Title is not Displayed");
        softAssert.assertTrue(researchPage.isResearchImagesListDisplayed(),
                "Research Images List is not Displayed");
        softAssert.assertTrue(researchPage.isResearchImagesPreviewDisplayed(),
                "Research Images Preview is not Displayed");
        softAssert.assertTrue(researchPage.isSelectedPublicationsTitleDisplayed(),
                "Selected Publications Title is not Displayed");
        softAssert.assertTrue(researchPage.isSelectedPublicationsSectionNotEmpty(),
                "Selected Publications Section is empty");
        softAssert.assertAll();
    }

    @Parameters({"profileName"})
    @Test(description = "Verify Patient Care info is displayed correctly on profile page")
    public void checkProfilePatientCareContent(String profileName) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchPage searchPage = mainPage.clickPerformSearch()
                .inputSearchPanelValue(profileName)
                .clickSearchButton();
        PatientCareTab patientCarePage = searchPage.openFirstPeopleProfile().openPatientCareTab();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(patientCarePage.isClinicalInterestsSectionTitleDisplayed(),
                "Clinical Interests Section Title is Not Displayed");
        softAssert.assertTrue(patientCarePage.isClinicalInterestsSectionNotEmpty(),
                "Clinical Interests Section is empty");
        softAssert.assertTrue(patientCarePage.isPatientCareSectionTitleDisplayed(),
                "Patient Care Section Title is Not Displayed");
        softAssert.assertTrue(patientCarePage.isPatientCareSectionContain3Items(),
                "Patient Care Section doesn't contains 3 items");
        softAssert.assertTrue(patientCarePage.isCertificationsSectionTitleDisplayed(),
                "Certifications Section Title is Not Displayed");
        softAssert.assertTrue(patientCarePage.isCertificationsSectionNotEmpty(),
                "Certifications Section is empty");
        softAssert.assertAll();
    }

    @Parameters({"profileName"})
    @Test(description = "Verify Location info is displayed correctly on profile page")
    public void checkProfileLocationsContent(String profileName) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchPage searchPage = mainPage.clickPerformSearch()
                .inputSearchPanelValue(profileName)
                .clickSearchButton();
        LocationsTab locationsPage = searchPage.openFirstPeopleProfile().openLocationsTab();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(locationsPage.isLocationsSectionTitleDisplayed(),
                "Locations Section Title is not Displayed");
        softAssert.assertTrue(locationsPage.isLocationsMapDisplayed(),
                "Locations Map is not Displayed");
        softAssert.assertAll();
    }

    @Parameters({"profileName"})
    @Test(description = "Verify News info is displayed correctly on profile page")
    public void checkProfileNewsContent(String profileName) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchPage searchPage = mainPage.clickPerformSearch()
                .inputSearchPanelValue(profileName)
                .clickSearchButton();
        NewsTab newsTab = searchPage.openFirstPeopleProfile().openNewsTab();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(newsTab.isNewsSectionTitleDisplayed(),
                "News Section Title is Not Displayed");
        softAssert.assertTrue(newsTab.isNewsSectionNotEmpty(),
                "News Section is empty");
        softAssert.assertTrue(newsTab.isNewsItemContainDate(),
                "Not Each News Item Contains Date");
        softAssert.assertTrue(newsTab.isNewsItemContainThumbnail(),
                "Not Each News Item Contains Thumbnail");
        softAssert.assertTrue(newsTab.isNewsItemContainTitle(),
                "Not Each News Item Contains Title");
        softAssert.assertAll();
    }

}
