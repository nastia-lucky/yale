package yale.pageObjects;

import framework.utilities.Browser;

public class BasePage {

    protected Browser browser;

    public BasePage() {
        browser = Browser.getInstance();
    }
}
