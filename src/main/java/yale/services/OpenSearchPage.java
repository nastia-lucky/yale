package yale.services;

import yale.pageObjects.MainPage;

public class OpenSearchPage {

    public static void openSearch() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        CheckPopUp.checkPopUp();
        mainPage.clickPerformSearch()
                .clickSearchButton();
    }

}
