package yale.tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.DocumentSearch;
import yale.pageObjects.SearchPage;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForDocument extends BaseTest {

    @Test
    public void searchForDocument() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        DocumentSearch documentSearch = new DocumentSearch();
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
        SearchPage searchPage = new SearchPage();
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
        SearchPage searchPage = new SearchPage();
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
}
