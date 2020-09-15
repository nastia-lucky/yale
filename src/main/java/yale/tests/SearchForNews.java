package yale.tests;

import framework.listener.TestListener;
import framework.utilities.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.NewsPage;
import yale.pageObjects.SearchPage;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForNews {

    SearchPage searchPage = new SearchPage();
    NewsPage newsPage = new NewsPage();
    String keyword = "Epidemiology and Public Health";
    String value = "aria-label";

    @BeforeMethod
    public void openBrowser() {
        Browser.getInstance();
    }

    @Test
    public void openNewsPage() {
        OpenSearchPage.openSearch();
        searchPage.addNewsFilter()
                .getSearchResult();
        String newsSearchTitle= searchPage.openNotExternalNews();
        Assert.assertEquals(newsSearchTitle, newsPage.getNewsTitle(),
                "News title in search results and on the details page don't coincide");
    }

    @Test
    public void checkNewsFields() {
        OpenSearchPage.openSearch();
        newsPage = searchPage.addNewsFilter()
                .openFirstNews();
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
        int numberNewsWithoutFilter = searchPage.addNewsFilter()
                .getSearchResult();
        int numberNewsWithKeywordFilter = searchPage.inputKeyword(keyword)
                .chooseFirstSuggestion()
                .getSearchResult();
        searchPage.openFirstNews();
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
        int numberNewsWithoutFilter = searchPage.addNewsFilter()
                .getSearchResult();
        int numberNewsWithSourceFilter = searchPage.clickSourceButton()
                .addYsmNewsFilter()
                .getSearchResult();
        Assert.assertTrue(numberNewsWithoutFilter > numberNewsWithSourceFilter,
                "Search Results number haven't changed after applying Source Filter");
    }

    @Test
    public void checkPagination() throws InterruptedException {
        OpenSearchPage.openSearch();
        int numberPagesSearchResults = searchPage.addNewsFilter()
                .inputKeyword(keyword)
                .chooseFirstSuggestion()
                .clickSourceButton()
                .getPageNumbers(value);
        int expectedPageNumber = searchPage.getExpectedPageNumber(searchPage.getSearchResult());
        Assert.assertTrue(numberPagesSearchResults == expectedPageNumber,
                "Expected page number doesn't coincide with the actual one");
    }

    @Test
    public void checkSearchResultNewsInfo() {
        OpenSearchPage.openSearch();
        searchPage.addNewsFilter()
                .getSearchResult();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.isEachSearchResultHaveType("News"),
                "Not each Search News Result has News Type");
        softAssert.assertTrue(searchPage.isEachResultHaveTitle(),
                "Not each Search News Result has Title");
        softAssert.assertTrue(searchPage.isAllElementsHaveThumbnail(),
                "Not each Search News Result has Thumbnail");
        softAssert.assertTrue(searchPage.isAllElementsHaveSummary(),
                "Not each Search News Result has Summary");
        softAssert.assertTrue(searchPage.isAllElementsHaveDate(),
                "Not each Search News Result has Date");
        softAssert.assertAll();
    }

    @AfterMethod
    public void closeBrowser() {
        Browser.closeBrowser();
    }
}
