package tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.MainPage;
import yale.pageObjects.SearchPage;
import yale.pageObjects.WebSiteSearch;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForWebPageTest extends BaseTest {

    @Test(description = "Verify search results are refined after applying web page filter")
    public void checkWebPageFilter() {
        MainPage mainPage = new MainPage();
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        WebSiteSearch webPageSearch = new WebSiteSearch();
        int numberWebPagesWithoutFilter = searchPage
                .addWebPageFilter()
                .getSearchResult();
        int numberWebPagesWithWebSiteFilter = webPageSearch
                .addActiveWebPageFilter()
                .getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        String breadCrumbText = searchPage.openAndGetTextFromFirstWebSiteSearchResult();
        softAssert.assertTrue(numberWebPagesWithoutFilter > numberWebPagesWithWebSiteFilter,
                "Search Results number haven't changed after applying Web Site Filter");
        softAssert.assertEquals(breadCrumbText, mainPage.getTitleWebsitePage(),
                "Search Result breadcrumb doesn't coincide with title on the website page");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkWebPageFilter"}, description = "Verify web page search results number in brackets")
    public void checkResultsNumberInBracketsForWebPages() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int numberWebSiteBracketsSearchResult = searchPage.addWebPageFilter()
                .getBracketsWebSiteResultNumber();
        int numberWebSiteSearchResults = searchPage.getSearchResult();
        Assert.assertEquals(numberWebSiteBracketsSearchResult, numberWebSiteSearchResults,
                "The results numbers in brackets and on the search results page don't coincide for Web Site Filter");
    }

    @Test(dependsOnMethods = {"checkWebPageFilter"}, description = "Verify web page search result info(title, type, breadcrumb)")
    public void checkSearchResultWebPageInfo() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addWebPageFilter()
                .addActiveWebPageFilter()
                .getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.isOneWebPageResultHaveBreadcrumb(),
                "Not one web page result contains breadcrumb");
        softAssert.assertTrue(searchPage.isEachWebPageResultHaveType(),
                "Not each web page result contains type");
        softAssert.assertTrue(searchPage.isEachResultHaveTitle(),
                "Not each web page result contains title");
        softAssert.assertAll();
    }
}
