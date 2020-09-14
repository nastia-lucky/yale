package yale.services;

import yale.pageObjects.MainPage;

public class CheckPopUp {

    public static void checkPopUp() {
        MainPage mainPage = new MainPage();
        if (mainPage.anouncementDisplayed()) {
            mainPage.closePopUp();
        }
    }
}
