package yale.tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.NewsPage;
import yale.pageObjects.NewsSearch;
import yale.pageObjects.SearchPage;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForNewsTest extends BaseTest {

    @Test(dependsOnMethods = {"checkNewsFilter"})
    public void openNewsPage() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addNewsFilter()
                .getSearchResult();
        String newsSearchTitle = searchPage.openNotExternalNews();
        NewsPage newsPage = new NewsPage();
        Assert.assertEquals(newsSearchTitle, newsPage.getNewsTitle(),
                "News title in search results and on the details page don't coincide");
    }

    @Test(dependsOnMethods = {"checkNewsFilter"})
    public void checkNewsFields() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addNewsFilter()
                .getSearchResult();
        searchPage.openNotExternalNews();
        NewsPage newsPage = new NewsPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(newsPage.isNewsDateDisplayed(),
                "News Date is not displayed");
        softAssert.assertTrue(newsPage.isNewsContentDisplayed(),
                "News Content is not Displayed");
        softAssert.assertTrue(newsPage.isNewsSubmitterDisplayed(),
                "News Submitter is not displayed");
        softAssert.assertAll();
    }

    @Parameters({"keyword"})
    @Test(dependsOnMethods = {"checkNewsFilter"})
    public void checkKeywordFilter(String keyword) {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int numberNewsWithoutFilter = searchPage.addNewsFilter()
                .getSearchResult();
        int numberNewsWithKeywordFilter = searchPage.inputKeyword(keyword)
                .chooseFirstSuggestion()
                .getSearchResult();
        NewsPage newsPage = searchPage.openFirstNews();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(newsPage.isTagsListContainsInputtedKeyword(keyword),
                "Tags list on the news details page doesn't contain chosen keyword " + keyword);
        softAssert.assertTrue(numberNewsWithoutFilter > numberNewsWithKeywordFilter,
                "Search Results number haven't changed after applying Keyword Filter");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkNewsFilter"})
    public void checkSourceFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        NewsSearch newsSearch = new NewsSearch();
        int numberNewsWithoutFilter = searchPage.addNewsFilter()
                .getSearchResult();
        int numberNewsWithSourceFilter = newsSearch.clickSourceButton()
                .addSourceNewsFilter()
                .getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(numberNewsWithoutFilter > numberNewsWithSourceFilter,
                "Search Results number haven't changed after applying Source Filter");
        softAssert.assertAll();
    }

    @Parameters({"keyword", "value"})
    @Test(dependsOnMethods = {"checkNewsFilter"})
    public void checkPagination(String keyword, String value) {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        NewsSearch newsSearch = new NewsSearch();
        searchPage.addNewsFilter()
                .inputKeyword(keyword)
                .chooseFirstSuggestion();
        int numberPagesSearchResults = newsSearch.clickSourceButton().addSourceNewsFilter()
                .getPageNumbers(value);
        int expectedPageNumber = searchPage.getExpectedPageNumber(searchPage.getSearchResult());
        Assert.assertTrue(numberPagesSearchResults == expectedPageNumber,
                "Expected page number doesn't coincide with the actual one");
    }

    @Test(dependsOnMethods = {"checkNewsFilter"})
    public void checkSearchResultNewsInfo() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        searchPage.addNewsFilter()
                .getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.isEachSearchResultHaveType("News"),
                "Not each Search News Result has News Type");
        softAssert.assertTrue(searchPage.isEachResultHaveTitle(),
                "Not each Search News Result has Title");
        softAssert.assertTrue(searchPage.isAllElementsHaveThumbnailOrDefaultImage(),
                "Not each Search News Result has Thumbnail");
        softAssert.assertTrue(searchPage.isAllElementsHaveSummary(),
                "Not each Search News Result has Summary");
        softAssert.assertTrue(searchPage.isAllElementsHaveDate(),
                "Not each Search News Result has Date");
        softAssert.assertAll();
    }

    @Test
    public void checkNewsFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        int resultsNumberWithoutNewsFilter = searchPage.getSearchResult();
        int resultsNumberWithNewsFilter = searchPage.addNewsFilter().getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultsNumberWithoutNewsFilter > resultsNumberWithNewsFilter,
                "Search Result Number doesn't change after applying News Filter");
        softAssert.assertTrue(searchPage.isSearchResultTypeCoincidesWithTheChosen("News"),
                "The search result type doesn't coincide with the chosen");
        softAssert.assertAll();
    }
}
