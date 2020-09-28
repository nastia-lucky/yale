package yale.tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.NewsPage;
import yale.pageObjects.NewsSearch;
import yale.pageObjects.SearchPage;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForNews extends BaseTest {

    String keyword = "Epidemiology and Public Health";
    String value = "aria-label";

    @Test
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

    @Test
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

    @Test
    public void checkKeywordFilter() {
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

    @Test
    public void checkSourceFilter() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        NewsSearch newsSearch = new NewsSearch();
        int numberNewsWithoutFilter = searchPage.addNewsFilter()
                .getSearchResult();
        int numberNewsWithSourceFilter = newsSearch.clickSourceButton()
                .addYsmNewsFilter()
                .getSearchResult();
        Assert.assertTrue(numberNewsWithoutFilter > numberNewsWithSourceFilter,
                "Search Results number haven't changed after applying Source Filter");
    }

    @Test
    public void checkPagination() throws InterruptedException {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        NewsSearch newsSearch = new NewsSearch();
        searchPage.addNewsFilter()
                .inputKeyword(keyword)
                .chooseFirstSuggestion();
        int numberPagesSearchResults = newsSearch.clickSourceButton()
                .getPageNumbers(value);
        int expectedPageNumber = searchPage.getExpectedPageNumber(searchPage.getSearchResult());
        Assert.assertTrue(numberPagesSearchResults == expectedPageNumber,
                "Expected page number doesn't coincide with the actual one");
    }

    @Test
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
}
