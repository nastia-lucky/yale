package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ClinicalTrialPage extends BasePage {

    private final By TTL_CLINICAL_TRIAL = By.xpath("//h1[@class='clinical-trial-details__title']");
    private final By SCT_CLINICAL_TRIAL_INVESTIGATORS = By.xpath("//section[@aria-label='Clinical trial investigators']");
    private final By SCT_VOLUNTEER_SUMMARY = By.xpath("//div[@class='clinical-trial-volunteer-tab__summary']");
    private final By TAB_HEALTH_PROFESSIONALS = By.xpath("//h2[contains(text(), 'Health Professionals')]");
    private static final By BTN_VOLUNTEER_NOW = By.xpath("//section[@class='clinical-trial-volunteer-contact-block']//a[@class='button button--large button--secondary button--link button-with-icon']");
    private final By SCT_SIDEBAR_CONTACT_INFORMATION = By.xpath("//section[@aria-label='Contact Information']");
    private final By ITM_DEMOGRAPHIC = By.xpath("//div[@class='clinical-trial-demographic-settings__item-value']");
    private final By SCT_CONDITIONS = By.xpath("//section[@aria-label='Conditions']");
    private final By TTL_CLINICAL_TRIAL_PURPOSE = By.xpath("//h3[contains(text(), 'Trial Purpose and Description')]");
    private final By SCT_CLINICAL_TRIAL_DESCRIPTION = By.xpath("//div[@class='clinical-trial-health-pro-tab__summary']");

    public ClinicalTrialPage() {
        super(BTN_VOLUNTEER_NOW);
    }

    @Step("Verify that Clinical Trial Title is displayed")
    public boolean isClinicalTrialTitleDisplayed() {
        Log.logInfo("Check that Clinical Trial Title is displayed");
        return baseElement.isElementDisplayed(TTL_CLINICAL_TRIAL);
    }

    @Step("Verify that Clinical Trial Investigators Section is displayed")
    public boolean isClinicalTrialsInvestigatorsDisplayed() {
        Log.logInfo("Check that Clinical Trial Investigators Section is displayed");
        return baseElement.isElementDisplayed(SCT_CLINICAL_TRIAL_INVESTIGATORS);
    }

    @Step("Verify that Clinical Trial Volunteer Summary is displayed")
    public boolean isVolunteerSummaryDisplayed() {
        Log.logInfo("Check that Clinical Trial Volunteer Summary is displayed");
        return baseElement.isElementDisplayed(SCT_VOLUNTEER_SUMMARY);
    }

    @Step("Click Volunteer Now button")
    public void clickVolunteerNowButton() {
        Log.logInfo("Click Volunteer Now button");
        baseElement.clickElement(BTN_VOLUNTEER_NOW);
    }

    @Step("Verify that Clinical Trial Contact Information is displayed")
    public boolean isContactInformationDisplayed() {
        Log.logInfo("Check that Clinical Trial Contact Information is displayed");
        return baseElement.isElementDisplayed(SCT_SIDEBAR_CONTACT_INFORMATION);
    }

    @Step("Verify that Clinical Trial Gender coincides with chosen gender")
    public boolean isGenderCoincideWithChosenGender(String text) {
        Log.logInfo("Check that Clinical Trial Gender coincides with chosen gender " + text);
        BaseElement.waitElementToBeClickable(BTN_VOLUNTEER_NOW);
        return baseElement.isSecondElementContainsOneOfTheTexts(ITM_DEMOGRAPHIC, text, "Both");
    }

    @Step("Verify that Clinical Trial Conditions contain chosen category")
    public boolean isConditionsContainsChosenCategory(String chosenCategory) {
        Log.logInfo("Check that Clinical Trial Conditions contain chosen category" + chosenCategory);
        BaseElement.waitElementToBeClickable(BTN_VOLUNTEER_NOW);
        return baseElement.isElementContainsText(SCT_CONDITIONS, chosenCategory);
    }

    @Step("Get Clinical Trial Page Title")
    public String getTrialPageTitle() {
        Log.logInfo("Get Clinical Trial Page Title");
        BaseElement.waitElementToBeClickable(TAB_HEALTH_PROFESSIONALS);
        return BaseElement.getText(TTL_CLINICAL_TRIAL);

    }

    @Step("Open Health Professional Tab")
    public ClinicalTrialPage openHealthProfessionalTab() {
        Log.logInfo("Open Health Professional Tab");
        baseElement.clickWithJS(TAB_HEALTH_PROFESSIONALS);
        return this;
    }

    @Step("Verify that Trial Purpose Title is Displayed")
    public boolean isTrialPurposeTitleIsDisplayed() {
        Log.logInfo("Check that Trial Purpose Title is Displayed");
        return baseElement.isElementContainText(TTL_CLINICAL_TRIAL_PURPOSE);
    }

    @Step("Verify that Trial Description is Displayed")
    public boolean isTrialDescriptionDisplayed() {
        Log.logInfo("Check that Trial Description is Displayed");
        return baseElement.isEachElementInArrayContainText(SCT_CLINICAL_TRIAL_DESCRIPTION);
    }
}
