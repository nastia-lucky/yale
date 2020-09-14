package yale.tests;

import framework.listener.TestListener;
import framework.utilities.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.DocumentSearch;
import yale.pageObjects.SearchPage;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForDocument {

    SearchPage searchPage = new SearchPage();
    DocumentSearch documentSearch = new DocumentSearch();

    @BeforeMethod
    public void openBrowser() {
        Browser.getInstance();
    }

    @Test
    public void searchForDocument() {
        OpenSearchPage.openSearch();
        int numberDocumentsWithoutFilter = searchPage
                .addDocumentFilter()
                .getSearchResult();
        int numberDocumentsWithActiveFilter = documentSearch
                .addActiveDocumentFilter()
                .getSearchResult();
        Assert.assertTrue(numberDocumentsWithoutFilter > numberDocumentsWithActiveFilter,
                "Search Results number haven't changed after applying Document Type filter  Filter");
    }

    @Test
    public void checkResultsNumberInBrackets() {
        OpenSearchPage.openSearch();
        int numberDocumentTypeBracketsSearchResult = searchPage
                .addDocumentFilter()
                .getBracketsDocumentTypeResultNumber();
        int numberDocumentTypeSearchResults = searchPage.getSearchResult();
        Assert.assertEquals(numberDocumentTypeBracketsSearchResult, numberDocumentTypeSearchResults,
                "The results numbers in brackets and on the search results page don't coincide for Document Type Filter");
    }

    @Test
    public void checkSearchResultDocumentInfo() {
        OpenSearchPage.openSearch();
        searchPage.addDocumentFilter()
                .addActiveDocumentFilter()
                .getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.isEachResultHaveTitle(),
                "Not each Search Document Result has Title");
        softAssert.assertTrue(searchPage.isEachSearchResultHaveType("Document"),
                "Not each Search Document Result has type Document");
        softAssert.assertTrue(searchPage.isEachDocumentResultHaveBreadcrumb(),
                "Not each Search Document Result has BreadCrumb");
        softAssert.assertAll();
    }

    @AfterMethod
    public void closeBrowser() {
        Browser.closeBrowser();
    }
}