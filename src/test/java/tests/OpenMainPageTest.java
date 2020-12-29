package tests;

import framework.BaseTest;
import framework.listener.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import yale.pageObjects.MainPage;

@Listeners({TestListener.class})
public class OpenMainPageTest extends BaseTest {

    @Test(description = "Verify user can ope main page")
    public void openMainPageTest() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        SoftAssert anAssert = new SoftAssert();
        anAssert.assertTrue(new MainPage().searchPanelIsDisplayed(), "Search Panel is not Displayed");
        anAssert.assertTrue(new MainPage().ysmLogoIsDisplayed(), "YSM Logo is not Displayed");
        anAssert.assertAll();
    }
}
