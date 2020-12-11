package yale.pageObjects.ProfilePageTabs;

import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yale.pageObjects.ProfilePage;

public class ResearchTab extends ProfilePage {

    private final By RESEARCH_SUMMARY_TITLE = By.xpath("//h3[contains(text(), 'Research Summary')]");
    private final By RESEARCH_SECTION = By.xpath("//section[@aria-label='Research Summary']//div[@class='profile-details-html-text-section']");
    private final By EXTENSIVE_RESEARCH_DESCRIPTION_TITLE = By.xpath("//h3[contains(text(),'Extensive Research Description')]");
    private final By EXTENSIVE_RESEARCH_DESCRIPTION_SECTION = By.xpath("//section[@aria-label='Extensive Research Description']//div[@class='profile-details-html-text-section']");
    private final By RESEARCH_INTERESTS_SECTION_TITLE = By.xpath("//h3[contains(text(), 'Research Interests')]");
    private final By RESEARCH_INTERESTS_SECTION = By.xpath("//section[@aria-label='Research Interests']//p[@class='profile-details-interests-section']");
    private final By PUBLIC_HEALTH_INTERESTS_TITLE = By.xpath("//h3[contains(text(),'Public Health Interests')]");
    private final By PUBLIC_HEALTH_INTERESTS_SECTION = By.xpath("//section[@aria-label='Public Health Interests']//p[@class='profile-details-interests-section']");
    private final By RESEARCH_IMAGES_TITLE = By.xpath("//h3[contains(text(), 'Research Images')]");
    private final By RESEARCH_IMAGES_LIST = By.xpath("//div[@class='slick-list']");
    private final By RESEARCH_IMAGES_PREVIEW = By.xpath("//ul[@class='media-gallery__preview-gallery']");
    private final By SELECTED_PUBLICATIONS_TITLE = By.xpath("//h3[contains(text(),'Selected Publications')]");
    private final By RESEARCH_PUBLICATION_ITEM = By.xpath("//div[@class='research-list-item']");

    @Step("Verify Research Summary Section Title Displayed")
    public boolean isResearchSummaryTitleDisplayed() {
        Log.logInfo("Check Research Summary Section Title Displayed");
        return baseElement.isArrayNotEmpty(RESEARCH_SUMMARY_TITLE);
    }

    @Step("Verify Research Summary Section is not Empty")
    public boolean isResearchSectionNotEmpty() {
        Log.logInfo("Check Research Summary Section is not Empty");
        return baseElement.isAllElementsInArrayContainText(RESEARCH_SECTION);
    }

    @Step("Verify Extensive Research Description Section Title Displayed")
    public boolean isResearchDescriptionTitleDisplayed() {
        Log.logInfo("Check Extensive Research Description Section Title Displayed");
        return baseElement.isArrayNotEmpty(EXTENSIVE_RESEARCH_DESCRIPTION_TITLE);
    }

    @Step("Verify Extensive Research Description Section is not Empty")
    public boolean isExtensiveResearchDescriptionSectionNotEmpty() {
        Log.logInfo("Check Extensive Research Description Section is not Empty");
        return baseElement.isAllElementsInArrayContainText(EXTENSIVE_RESEARCH_DESCRIPTION_SECTION);
    }

    @Step("Verify Research Interests Section Title Displayed")
    public boolean isResearchInterestsSectionTitleDisplayed() {
        Log.logInfo("Check Research Interests Section Title Displayed");
        return baseElement.isArrayNotEmpty(RESEARCH_INTERESTS_SECTION_TITLE);
    }

    @Step("Verify Research Interests Section is not Empty")
    public boolean isResearchInterestsSectionNotEmpty() {
        Log.logInfo("Check Research Interests Section is not Empty");
        return baseElement.isAllElementsInArrayContainText(RESEARCH_INTERESTS_SECTION);
    }

    @Step("Verify Public Health Interests Section Title Displayed")
    public boolean isPublicHealthInterestsSectionTitleDisplayed() {
        Log.logInfo("Check Research Interests Section Title Displayed");
        return baseElement.isArrayNotEmpty(PUBLIC_HEALTH_INTERESTS_TITLE);
    }

    @Step("Verify Public Health Interests Section is not Empty")
    public boolean isPublicHealthInterestsSectionNotEmpty() {
        Log.logInfo("Check Public Health Interests Section is not Empty");
        return baseElement.isAllElementsInArrayContainText(PUBLIC_HEALTH_INTERESTS_SECTION);
    }

    @Step("Verify Research Images Section Title Displayed")
    public boolean isResearchImagesSectionTitleDisplayed() {
        Log.logInfo("Check Research Images Section Title Displayed");
        return baseElement.isArrayNotEmpty(RESEARCH_IMAGES_TITLE);
    }

    @Step("Verify Research Images List Displayed")
    public boolean isResearchImagesListDisplayed() {
        Log.logInfo("Check Research Images List Displayed");
        return baseElement.isArrayNotEmpty(RESEARCH_IMAGES_LIST);
    }

    @Step("Verify Research Images Preview Displayed")
    public boolean isResearchImagesPreviewDisplayed() {
        Log.logInfo("Check Research Images Preview Displayed");
        return baseElement.isArrayNotEmpty(RESEARCH_IMAGES_PREVIEW);
    }

    @Step("Verify Selected Publications Title Displayed")
    public boolean isSelectedPublicationsTitleDisplayed() {
        Log.logInfo("Check Selected Publications Title Displayed");
        return baseElement.isArrayNotEmpty(SELECTED_PUBLICATIONS_TITLE);
    }

    @Step("Verify Selected Publications Section is Not Empty")
    public boolean isSelectedPublicationsSectionNotEmpty() {
        Log.logInfo("Check Selected Publications Section is Not Empty");
        return baseElement.isAllElementsInArrayContainText(RESEARCH_PUBLICATION_ITEM);
    }
}
