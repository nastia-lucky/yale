package yale.pageObjects;

import framework.logger.Log;
import framework.BaseElement;
import org.openqa.selenium.By;

public class ClinicalTrialPage extends BasePage {


    private final By CLINICAL_TRIAL_TITLE = By.xpath("//h1[@class='clinical-trial-details__title']");
    private final By CLINICAL_TRIAL_INVESTIGATORS = By.xpath("//section[@aria-label='Clinical trial investigators']");
    private final By VOLUNTEER_SUMMARY = By.xpath("//div[@class='clinical-trial-volunteer-tab__summary']");
    private final By HEALTH_PROFESSIONALS_TAB = By.xpath("//h2[contains(text(), 'Health Professionals')]");
    private static final By VOLUNTEER_NOW_BUTTON = By.xpath("//section[@class='clinical-trial-volunteer-contact-block']//a[@class='button button--large button--secondary button--link button-with-icon']");
    private final By SIDEBAR_CONTACT_INFORMATION = By.xpath("//section[@aria-label='Contact Information']");
    private final By DEMOGRAPHIC = By.xpath("//div[@class='clinical-trial-demographic-settings__item-value']");
    private final By CONDITIONS = By.xpath("//section[@aria-label='Conditions']");
    private final By CLINICAL_TRIAL_PURPOSE_TITLE =By.xpath("//h3[contains(text(), 'Trial Purpose and Description')]");
    private final By CLINICAL_TRIAL_DESCRIPTION=By.xpath("//div[@class='clinical-trial-health-pro-tab__summary']");

    public ClinicalTrialPage() {
        super(VOLUNTEER_NOW_BUTTON);
    }

    public boolean isClinicalTrialTitleDisplayed() {
        Log.logInfo("Check that Clinical Trial Title is displayed");
        baseElement.waitForElementToBePresent(CLINICAL_TRIAL_TITLE);
        return baseElement.isElementDisplayed(CLINICAL_TRIAL_TITLE);
    }

    public boolean isClinicalTrialsInvestigatorsDisplayed() {
        Log.logInfo("Check that Clinical Trial Investigators Section is displayed");
        BaseElement.waitForElementToBePresent(CLINICAL_TRIAL_INVESTIGATORS);
        return baseElement.isElementDisplayed(CLINICAL_TRIAL_INVESTIGATORS);
    }

    public boolean isVolunteerSummaryDisplayed() {
        Log.logInfo("Check that Clinical Trial Volunteer Summary is displayed");
        BaseElement.waitForElementToBePresent(VOLUNTEER_SUMMARY);
        return baseElement.isElementDisplayed(VOLUNTEER_SUMMARY);
    }

    public void clickVolunteerNowButton() {
        Log.logInfo("Click Volunteer Now button");
        BaseElement.waitForElementToBePresent(VOLUNTEER_NOW_BUTTON);
        baseElement.clickElement(VOLUNTEER_NOW_BUTTON);
    }

    public boolean isContactInformationDisplayed() {
        Log.logInfo("Check that Clinical Trial Contact Information is displayed");
        BaseElement.waitForElementToBePresent(SIDEBAR_CONTACT_INFORMATION);
        return baseElement.isElementDisplayed(SIDEBAR_CONTACT_INFORMATION);
    }

    public boolean isGenderCoincideWithChosenGender(String text) {
        Log.logInfo("Check that Clinical Trial Gender coincides with chosen gender " + text);
        BaseElement.waitForElementToBeClickable(VOLUNTEER_NOW_BUTTON);
        return baseElement.isTheSecondElementContainsOneOfTheTexts(DEMOGRAPHIC, text, "Both");
    }

    public boolean isConditionsContainsChosenCategory(String chosenCategory) {
        Log.logInfo("Check that Clinical Trial Conditions contain chosen category "+ chosenCategory);
        BaseElement.waitForElementToBeClickable(VOLUNTEER_NOW_BUTTON);
        return baseElement.isElementContainsText(CONDITIONS, chosenCategory);
    }

    public String getTrialPageTitle() {
        Log.logInfo("Get Clinical Trial Page Title");
        BaseElement.waitForElementToBeClickable(HEALTH_PROFESSIONALS_TAB);
        String trialTitle = baseElement.getText(CLINICAL_TRIAL_TITLE);
        return trialTitle;
    }

    public ClinicalTrialPage openHealthProfessionalTab(){
        Log.logInfo("Open Health Professional Tab");
        baseElement.clickWithJS(HEALTH_PROFESSIONALS_TAB);
        return this;
    }

    public boolean isTrialPurposeTitleIsDisplayed(){
        Log.logInfo("Check that Trial Purpose Title is Displayed");
        return  baseElement.checkTheElementContainText(CLINICAL_TRIAL_PURPOSE_TITLE);
    }

    public boolean isTrialDescriptionDisplayed(){
        Log.logInfo("Check that Trial Description is Displayed");
        return baseElement.checkThatEachElementInArrayContainText(CLINICAL_TRIAL_DESCRIPTION);
    }
}
