package yale.tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.EventPage;
import yale.pageObjects.MainPage;
import yale.pageObjects.NewsPage;
import yale.pageObjects.SearchPage;

@Listeners({TestListener.class})
public class MainPageTests extends BaseTest {

    @Parameters({"patientCareLink"})
    @Test
    public void openPatientCareTest(String patientCareLink) {
        MainPage mainPage = new MainPage();
        mainPage.clickPatientCareLink();
        Assert.assertEquals(mainPage.getCurrentURL(), patientCareLink, "Links don't coincide");
    }

    @Parameters({"YSMuRL"})
    @Test
    public void redirectSiteIconTest(String YSMuRL) {
        MainPage mainPage = new MainPage();
        mainPage.clickSiteIcon();
        Assert.assertEquals(mainPage.getCurrentURL(), YSMuRL, "Logo leads to incorrect URL");
    }

    @Parameters({"mapsURL", "supportUsURL", "calendarLink"})
    @Test
    public void checkFooterLinksTest(String mapsURL, String supportUsURL, String calendarLink, String contactUsLink) {
        MainPage mainPage = new MainPage();
        SoftAssert anAssert = new SoftAssert();
        mainPage.clickMapsFooterLink();
        anAssert.assertEquals(mainPage.getCurrentURL(), mapsURL,
                "Maps' URL is not correct");
        mainPage.clickLogo()
                .clickSupportUsLink();
        anAssert.assertEquals(mainPage.getCurrentURL(), supportUsURL,
                "Support Us URL is not correct");
        mainPage.clickLogo()
                .clickCalendarLink();
        anAssert.assertEquals(mainPage.getCurrentURL(), calendarLink,
                "Calendar Link is not correct");
        mainPage.clickLogo()
                .clickContactUsLink();
        anAssert.assertEquals(mainPage.getCurrentURL(), contactUsLink,
                "Contact us Link is not correct");
        anAssert.assertAll();
    }

    @Test
    public void openNewsPageTest() {
        MainPage mainPage = new MainPage();
        String newsTitleSearchPage = mainPage.getNewsTitle();
        NewsPage newsPage = mainPage.clickNewsTitle();
        String newsTitle = newsPage.getNewsTitle();
        Assert.assertEquals(newsTitleSearchPage, newsTitle,
                "News Title in listing doesn't coincide with  title on news details page ");
    }

    @Test
    public void moreTopStoriesButtonTest() {
        MainPage mainPage = new MainPage();
        SearchPage searchPage = mainPage.clickMoreTopStoriesButton();
        Assert.assertTrue(searchPage.isRemoveTopStoriesFilterDisplayed(),
                "Remove Top Stories Filters is not displayed");
    }

    @Test
    public void moreEventsButtonTest() {
        MainPage mainPage = new MainPage();
        SearchPage searchPage = mainPage.clickMoreEventsButton();
        Assert.assertTrue(searchPage.isRemoveEventsFilterIsDisplayed(),
                "Remove events filter is not displayed");
    }

    @Test
    public void openEventFromMainPageTest() {
        MainPage mainPage = new MainPage();
        String searchTitle = mainPage.getFirstEventTitle();
        EventPage eventPage = mainPage.openFirstEvent();
        Assert.assertEquals(searchTitle, eventPage.getEventModalTitle(),
                "Search event title doesn't coincide with title on the details page");
    }

    @Parameters({"twitterLink"})
    @Test
    public void openTwitterTest(String twitterLink) {
        MainPage mainPage = new MainPage();
        mainPage.goToTwitter();
        Assert.assertEquals(mainPage.getCurrentURL(), twitterLink,
                "Link to twitter feed doesn't coincide with expected " + twitterLink);
    }

    @Test
    public void checkAZListTest() {
        MainPage mainPage = new MainPage();
        String anchorLetter = mainPage.goAToAboutYSMPage()
                .goToFindPeoplePage()
                .goToA_ZFacultyPage()
                .clickAnyAnchorLink();
        Assert.assertTrue(mainPage.isTheFirstLetterCoincidesWithTheChosenAnchorLink(anchorLetter),
                "The First letter of focused element doesn't coincide with chosen anchor link");
    }
}
