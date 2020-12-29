package tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.MainPage;
import yale.pageObjects.NewsPage;
import yale.pageObjects.NewsSearch;
import yale.pageObjects.SearchPage;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class SearchForNewsTest extends BaseTest {

    @Test(dependsOnMethods = {"checkNewsFilter"}, description = "Verify user can open news page from search results")
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

    @Test(dependsOnMethods = {"checkNewsFilter"}, description = "Verify that event info (date, content, news submitter) is displayed correctly")
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
    @Test(dependsOnMethods = {"checkNewsFilter"}, description = "Verify news search results are refined after applying keyword filter")
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

    @Test(dependsOnMethods = {"checkNewsFilter"}, description = "Verify news search results are refined after applying source filter")
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
    @Test(dependsOnMethods = {"checkNewsFilter"}, description = "Verify pagination for news search results")
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

    @Test(dependsOnMethods = {"checkNewsFilter"}, description = "Verify news search result info(title, type, thumbnail, summary, date)")
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
        softAssert.assertTrue(searchPage.isOneOfElementsHaveSummary(),
                "Not each Search News Result has Summary");
        softAssert.assertTrue(searchPage.isAllElementsHaveDate(),
                "Not each Search News Result has Date");
        softAssert.assertAll();
    }

    @Test(description = "Verify search results are refined after applying news filter")
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

    @Parameters({"newsTitle"})
    @Test(description = "Verify that news page contain all possible content")
    public void checkNewsContent(String newsTitle) {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SearchPage searchPage = mainPage.clickPerformSearch()
                .inputSearchPanelValue(newsTitle)
                .clickSearchButton();
        NewsPage newsPage = searchPage.openFirstNews();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(newsPage.isRelatedLinksTitleIsDisplayed(),
                "Related Links are not displayed");
        softAssert.assertTrue(newsPage.isSideBarRelatedLinkItemDisplayed(),
                "Sidebar Link List is not displayed");
        softAssert.assertTrue(newsPage.isMediaGalleryDisplayed(),
                "Media Gallery is not displayed");
        softAssert.assertTrue(newsPage.isQuoteTextDisplayed(),
                "Quote Text is not displayed");
        softAssert.assertTrue(newsPage.isQuoteAuthorDisplayed(),
                "Quote Author is not displayed");
        softAssert.assertTrue(newsPage.isTagsTitleDisplayed(),
                "Tags Title is not displayed");
        softAssert.assertTrue(newsPage.isTagsSectionNotEmpty(),
                "Tags Section doesn't contain any tags");
        softAssert.assertTrue(newsPage.isFeaturedPeopleSectionNotEmpty(),
                "Featured People Section doesn't contain any people");
        softAssert.assertAll();
    }
}
