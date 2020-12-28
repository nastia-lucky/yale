package tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import yale.pageObjects.SearchPage;
import yale.services.OpenSearchPage;

@Listeners({TestListener.class})
public class GeneralSearchTests extends BaseTest {

    @Test(description = "Verify that if search results contain items secured by IP special notification is displayed on top of search results")
    public void checkSecureSearchResultsLink() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        Assert.assertTrue(searchPage.isSecureSearchResultLinkIsDisplayed(),
                "Secure Results Link is not Displayed");
    }

    @Test(description = "Verify that all search items are displayed in search results if no query was submitted")
    public void checkGeneralSearchResult() {
        OpenSearchPage.openSearch();
        SearchPage searchPage = new SearchPage();
        Assert.assertEquals(searchPage.getSumFiltersNumberValue(), searchPage.getSearchResult(),
                "Sum of filters values don't coincide with general search result");
    }

}
