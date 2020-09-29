package yale.pageObjects;

import framework.logger.Log;
import framework.BaseElement;
import org.openqa.selenium.By;

public class ClinicalTrialSearch extends SearchPage {

    private final By GENDER_FILTER = By.xpath("//div[@id='accordion__heading-gender']");
    private final By ACTIVE_GENDER = By.xpath("//div[@id='accordion__panel-gender']//button[@tabindex='0']");
    private final By ACCEPT_HEALTHY_FILTER = By.xpath("//div[@id='accordion__heading-acceptHealthy']");
    private final By ACTIVE_HEALTHY_FILTER = By.xpath("//div[@id='accordion__panel-acceptHealthy']//button[@tabindex='0']");
    private final By CATEGORY_FILTER = By.xpath("//div[@id='accordion__heading-categoryId']");
    private final By ACTIVE_CATEGORY = By.xpath("//div[@id='accordion__panel-categoryId']//button[@tabindex='0']");

    public ClinicalTrialSearch addGenderFilter() {
        Log.logInfo("Add Gender Filter");
        baseElement.clickElement(GENDER_FILTER);
        return this;
    }

    public String addActiveGender() {
        Log.logInfo("Add active Gender");
        return baseElement.clickFirstElementFromArrayAndGetTitleText(ACTIVE_GENDER);
    }

    public ClinicalTrialSearch addAcceptHealthyFilter() {
        Log.logInfo("Add Accept Healthy Volunteers Filter");
        baseElement.clickElement(ACCEPT_HEALTHY_FILTER);
        return this;
    }

    public ClinicalTrialSearch addActiveHealthyItem() {
        Log.logInfo("Add active healthy item");
        baseElement.clickFirstElementFromArray(ACTIVE_HEALTHY_FILTER);
        return this;
    }

    public ClinicalTrialSearch addCategoryFilter() {
        Log.logInfo("Add Category Filter");
        baseElement.clickElement(CATEGORY_FILTER);
        return this;
    }

    public String addActiveCategory() {
        Log.logInfo("Add active category");
        return baseElement.clickFirstElementFromArrayAndGetTitleText(ACTIVE_CATEGORY);
    }

    public int getBracketsGenderResultNumber() {
        Log.logInfo("Get Clinical Trials number in brackets");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(ACTIVE_GENDER);
        return baseElement.clickFirstElementFromArrayAndGetNumberText(ACTIVE_GENDER);
    }
}
