package tests;

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

import java.time.LocalDate;

@Listeners({TestListener.class})
public class MainPageTests extends BaseTest {

    @Parameters({"patientCareLink"})
    @Test(description = "Verify \"Patient Care\" redirects to yalemedicine.org site")
    public void openPatientCareTest(String patientCareLink) {
        MainPage mainPage = new MainPage();
        mainPage.clickPatientCareLink();
        Assert.assertEquals(mainPage.getCurrentURL(), patientCareLink, "Links don't coincide");
    }

    @Parameters({"YSMuRL"})
    @Test(description = "Verify that clicking site icon in header redirects user to Home page")
    public void redirectSiteIconTest(String YSMuRL) {
        MainPage mainPage = new MainPage();
        mainPage.clickSiteIcon();
        Assert.assertEquals(mainPage.getCurrentURL(), YSMuRL, "Logo leads to incorrect URL");
    }

    @Parameters({"mapsURL", "supportUsURL", "calendarLink", "contactUsLink"})
    @Test(description = "Verify that all links in footer are clickable and lead to appropriate sites")
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

    @Test(description = "Verify news article is opened in new tab when clicking news title")
    public void openNewsPageTest() {
        MainPage mainPage = new MainPage();
        String newsTitleSearchPage = mainPage.getNewsTitle();
        NewsPage newsPage = mainPage.clickNewsTitle();
        String newsTitle = newsPage.getNewsTitle();
        Assert.assertEquals(newsTitleSearchPage, newsTitle,
                "News Title in listing doesn't coincide with  title on news details page ");
    }

    @Test(description = "Verify search results for news is opened when clicking \"More News\" button")
    public void moreTopStoriesButtonTest() {
        MainPage mainPage = new MainPage();
        SearchPage searchPage = mainPage.clickMoreTopStoriesButton();
        Assert.assertTrue(searchPage.isRemoveTopStoriesFilterDisplayed(),
                "Remove Top Stories Filters is not displayed");
    }

    @Test(description = "Verify that clicking \"See All Events\" button leads to search results for events")
    public void moreEventsButtonTest() {
        MainPage mainPage = new MainPage();
        SearchPage searchPage = mainPage.clickMoreEventsButton();
        Assert.assertTrue(searchPage.isRemoveEventsFilterIsDisplayed(),
                "Remove events filter is not displayed");
    }

    @Test(description = "Event details modal should be opened when clicking event title")
    public void openEventFromMainPageTest() {
        MainPage mainPage = new MainPage();
        String searchTitle = mainPage.getFirstEventTitle();
        EventPage eventPage = mainPage.openFirstEvent();
        Assert.assertEquals(searchTitle, eventPage.getEventModalTitle(),
                "Search event title doesn't coincide with title on the details page");
    }

    @Parameters({"twitterLink"})
    @Test(description = "Verify that clicking \"Follow YaleMed\" button redirects user to YaleMed twitter account")
    public void openTwitterTest(String twitterLink) {
        MainPage mainPage = new MainPage();
        mainPage.goToTwitter();
        Assert.assertEquals(mainPage.getCurrentURL(), twitterLink,
                "Link to twitter feed doesn't coincide with expected " + twitterLink);
    }

    @Test(description = "Verify that A-Z indexes work correctly")
    public void checkAZListTest() {
        MainPage mainPage = new MainPage();
        String anchorLetter = mainPage.goAToAboutYSMPage()
                .goToFindPeoplePage()
                .goToA_ZFacultyPage()
                .clickAnyAnchorLink();
        Assert.assertTrue(mainPage.isTheFirstLetterCoincidesWithTheChosenAnchorLink(anchorLetter),
                "The First letter of focused element doesn't coincide with chosen anchor link");
    }

    @Test(description = "Verify that \"Back to top\" button scrolls the page up to the very top")
    public void checkBackToTopButton() throws InterruptedException {
        MainPage mainPage = new MainPage();
        Long firstPosition = mainPage.scrollPageToTheBottomAndGetYPosition();
        Long secondPosition = mainPage.clickBackToTopButton();
        Assert.assertEquals(firstPosition, secondPosition,
                "The position at the beginning and after clicking Back to Top button don't coincide");
    }

    @Test(description = "Verify that event info contain audience, title, date")
    public void checkEventInfo() {
        MainPage mainPage = new MainPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(mainPage.isAllEventsContainAudience(),
                "Not All Events contain Audience");
        softAssert.assertTrue(mainPage.isAllEventsContainTitle(),
                "Not All Events contain Title");
        softAssert.assertTrue(mainPage.isAllEventsContainDate(),
                "Not All Events contain Date");
        softAssert.assertAll();
    }

    @Test(description = "Verify the latest news are presented in this section in DESC order")
    public void checkDESCOrderNews() {
        MainPage mainPage = new MainPage();
        LocalDate firstDate = mainPage.getNewsDate(1);
        LocalDate secondDate = mainPage.getNewsDate(2);
        LocalDate thirdDate = mainPage.getNewsDate(3);
        LocalDate fourthDate = mainPage.getNewsDate(4);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(firstDate.compareTo(secondDate) >= 0,
                "The first News Date is Less than the Second");
        softAssert.assertTrue(secondDate.compareTo(thirdDate) >= 0,
                "The Second News Date is Less than the Third");
        softAssert.assertTrue(thirdDate.compareTo(fourthDate) >= 0,
                "The Third News Date is Less than The Fourth");
        softAssert.assertAll();
    }

    @Test(description = "Verify that three latest twitter posts are displayed in Twitter Feed section")
    public void checkTwitterFeed() {
        MainPage mainPage = new MainPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(mainPage.isTweetContainAuthor(),
                "Not all Tweets contain Author");
        softAssert.assertTrue(mainPage.isTweetContainTime(),
                "Not all Tweets contain Time");
        softAssert.assertTrue(mainPage.isTweetContainText(),
                "Not all Tweets contain Text");
        softAssert.assertTrue(mainPage.isThreeTwitterFeeds(),
                "Not three Tweets displayed on the page");
        softAssert.assertAll();
    }

    @Test(description = "Verify that there are 4 events on the main page")
    public void checkEventCount() {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isEventItemsCountCorrect(),
                "Not 4 events on the main page");
    }

}
