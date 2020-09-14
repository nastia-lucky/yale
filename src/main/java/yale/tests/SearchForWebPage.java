package yale.tests;

import framework.listener.TestListener;
import framework.utilities.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.MainPage;
import yale.pageObjects.SearchPage;
import yale.pageObjects.WebSiteSearch;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForWebPage {
    SearchPage searchPage = new SearchPage();
    WebSiteSearch webPageSearch = new WebSiteSearch();
    MainPage mainPage = new MainPage();

    @BeforeMethod
    public void openBrowser() {
        Browser.getInstance();
    }

    @Test
    public void checkWebPageFilter() {
        OpenSearchPage.openSearch();
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

    @Test
    public void checkResultsNumberInBracketsForWebPages() {
        OpenSearchPage.openSearch();
        int numberWebSiteBracketsSearchResult = searchPage.addWebPageFilter()
                .getBracketsWebSiteResultNumber();
        int numberWebSiteSearchResults = searchPage.getSearchResult();
        Assert.assertEquals(numberWebSiteBracketsSearchResult, numberWebSiteSearchResults,
                "The results numbers in brackets and on the search results page don't coincide for Web Site Filter");
    }

    @Test
    public void checkSearchResultWebPageInfo() {
        OpenSearchPage.openSearch();
        searchPage.addWebPageFilter()
                .addActiveWebPageFilter()
                .getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.isEachWebPageResultHaveBreadcrumb(),
                "Not each web page result contains breadcrumb");
        softAssert.assertTrue(searchPage.isEachWebPageResultHaveType(),
                "Not each web page result contains type");
        softAssert.assertTrue(searchPage.isEachResultHaveTitle(),
                "Not each web page result contains title");
        softAssert.assertAll();
    }

    @AfterMethod
    public void closeBrowser() {
        Browser.closeBrowser();
    }
}
