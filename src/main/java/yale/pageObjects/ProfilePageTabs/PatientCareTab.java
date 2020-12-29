package yale.pageObjects.ProfilePageTabs;

import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yale.pageObjects.ProfilePage;

public class PatientCareTab extends ProfilePage {

    private final By CLINICAL_INTERESTS_TITLE = By.xpath("//h3[contains(text(),'Clinical Interests')]");
    private final By CLINICAL_INTERESTS_SECTION = By.xpath("//p[@class='profile-details-interests-section']");
    private final By PATIENT_CARE_SECTION_TITLE = By.xpath("//h3[contains(text(),'Patient Care')]");
    private final By PATIENT_CARE_SECTION_ITEM = By.xpath("//p[@class='profile-details-patient-care-tab__text']");
    private final By CERTIFICATIONS_SECTION_TITLE = By.xpath("//h3[contains(text(), 'Certifications')]");
    private final By CERTIFICATION_ITEM_DEGREE = By.xpath("//div[@class='profile-education-list-item__degree']");
    private final By CERTIFICATION_ITEM_INSTITUTION = By.xpath("//div[@class='profile-education-list-item__institution']");

    @Step("Verify Clinical Interests Section Title is Displayed")
    public boolean isClinicalInterestsSectionTitleDisplayed() {
        Log.logInfo("Check Clinical Interests Section Title is Displayed");
        return baseElement.isArrayNotEmpty(CLINICAL_INTERESTS_TITLE);
    }

    @Step("Verify Clinical Interests Section is not empty")
    public boolean isClinicalInterestsSectionNotEmpty() {
        Log.logInfo("Check Clinical Interests Section is not empty");
        return baseElement.isAllElementsInArrayContainText(CLINICAL_INTERESTS_SECTION);
    }

    @Step("Verify Patient Care Section Title is Displayed")
    public boolean isPatientCareSectionTitleDisplayed() {
        Log.logInfo("Check Patient Care Section Title is Displayed");
        return baseElement.isArrayNotEmpty(PATIENT_CARE_SECTION_TITLE);
    }

    @Step("Verify Patient Care Section contains 3 items")
    public boolean isPatientCareSectionContain3Items() {
        Log.logInfo("Check Patient Care Section contains 3 items");
        return baseElement.isCountOfElementsCorrect(PATIENT_CARE_SECTION_ITEM, 3);
    }

    @Step("Verify Certifications Section Title is Displayed")
    public boolean isCertificationsSectionTitleDisplayed() {
        Log.logInfo("Check Certifications Section Title is Displayed");
        return baseElement.isArrayNotEmpty(CERTIFICATIONS_SECTION_TITLE);
    }

    @Step("Verify Certifications Section is not Empty")
    public boolean isCertificationsSectionNotEmpty() {
        Log.logInfo("Check Certifications Section is not Empty");
        return baseElement.isAmountOfElementsCoincide(CERTIFICATION_ITEM_DEGREE, CERTIFICATION_ITEM_INSTITUTION);
    }
}
