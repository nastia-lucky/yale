package yale.pageObjects;

import framework.logger.Log;
import framework.utilities.Browser;
import org.openqa.selenium.By;

public class ClinicalTrialPage extends BasePage {

    private final By CLINICAL_TRIAL_TITLE = By.xpath("//h1[@class='clinical-trial-details__title']");
    private final By CLINICAL_TRIAL_INVESTIGATORS = By.xpath("//section[@aria-label='Clinical trial investigators']");
    private final By VOLUNTEER_SUMMARY = By.xpath("//div[@class='clinical-trial-volunteer-tab__summary']");
    private final By HEALTH_PROFESSIONALS_TAB = By.xpath("//h2[contains(text(), 'Health Professionals')]");
    private final By VOLUNTEER_NOW_BUTTON = By.xpath("//section[@class='clinical-trial-volunteer-contact-block']//a[@class='button button--large button--secondary button--link button-with-icon']");
    private final By SIDEBAR_CONTACT_INFORMATION = By.xpath("//section[@aria-label='Contact Information']");
    private final By DEMOGRAPHIC = By.xpath("//div[@class='clinical-trial-demographic-settings__item-value']");
    private final By CONDITIONS = By.xpath("//section[@aria-label='Conditions']");

    public boolean isClinicalTrialTitleDisplayed() {
        Log.logInfo("Check that Clinical Trial Title is displayed");
        Browser.waitForElementToBePresent(CLINICAL_TRIAL_TITLE);
        return browser.isElementDisplayed(CLINICAL_TRIAL_TITLE);
    }

    public boolean isClinicalTrialsInvestigatorsDisplayed() {
        Log.logInfo("Check that Clinical Trial Investigators Section is displayed");
        Browser.waitForElementToBePresent(CLINICAL_TRIAL_INVESTIGATORS);
        return browser.isElementDisplayed(CLINICAL_TRIAL_INVESTIGATORS);
    }

    public boolean isVolunteerSummaryDisplayed() {
        Log.logInfo("Check that Clinical Trial Volunteer Summary is displayed");
        Browser.waitForElementToBePresent(VOLUNTEER_SUMMARY);
        return browser.isElementDisplayed(VOLUNTEER_SUMMARY);
    }

    public void clickVolunteerNowButton() {
        Log.logInfo("Click Volunteer Now button");
        Browser.waitForElementToBePresent(VOLUNTEER_NOW_BUTTON);
        browser.clickElement(VOLUNTEER_NOW_BUTTON);
    }

    public String getCurrentUrl() {
        Log.logInfo("Get Current URL");
        return browser.getCurrentUrl();
    }

    public boolean isContactInformationDisplayed() {
        Log.logInfo("Check that Clinical Trial Contact Information is displayed");
        Browser.waitForElementToBePresent(SIDEBAR_CONTACT_INFORMATION);
        return browser.isElementDisplayed(SIDEBAR_CONTACT_INFORMATION);
    }

    public boolean isGenderCoincideWithChosenGender(String text) {
        Log.logInfo("Check that Clinical Trial Gender coincides with chosen gender " + text);
        Browser.waitForElementToBeClickable(VOLUNTEER_NOW_BUTTON);
        return browser.isTheSecondElementContainsOneOfTheTexts(DEMOGRAPHIC, text, "Both");
    }

    public boolean isConditionsContainsChosenCategory(String chosenCategory) {
        Log.logInfo("Check that Clinical Trial Conditions contain chosen category "+ chosenCategory);
        Browser.waitForElementToBeClickable(VOLUNTEER_NOW_BUTTON);
        return browser.isElementContainsText(CONDITIONS, chosenCategory);
    }

    public String getTrialPageTitle() {
        Log.logInfo("Get Clinical Trial Page Title");
        Browser.waitForElementToBePresent(CLINICAL_TRIAL_TITLE);
        String trialTitle = browser.getText(CLINICAL_TRIAL_TITLE);
        return trialTitle;
    }
}
