package yale.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import framework.listener.TestListener;
import yale.pageObjects.MainPage;
import yale.services.CheckPopUp;
import framework.utilities.Browser;

@Listeners({TestListener.class})
public class OpenMainPage {

    MainPage mainPage = new MainPage();

    @BeforeMethod
    public void openBrowser() {
        Browser.getInstance();
    }

    @Test
    public void openMainPage() {
        mainPage.openMainPage();
        CheckPopUp.checkPopUp();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertTrue(new MainPage().searchPanelIsDisplayed(), "Search Panel is not Displayed");
        anAssert.assertTrue(new MainPage().ysmLogoIsDisplayed(), "YSM Logo is not Displayed");
        anAssert.assertAll();
    }

    @AfterMethod
    public void closeBrowser() {
        Browser.closeBrowser();
    }
}
