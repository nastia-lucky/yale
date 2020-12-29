package yale.services;

import yale.pageObjects.MainPage;

public class OpenSearchPage {

    private OpenSearchPage(){}

    public static void openSearch() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
        mainPage.clickPerformSearch()
                .clickSearchButton();
    }
}
