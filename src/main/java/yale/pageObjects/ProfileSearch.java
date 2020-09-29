package yale.pageObjects;

import framework.BaseElement;
import org.openqa.selenium.By;
import framework.logger.Log;

public class ProfileSearch extends SearchPage {

    private final By ACTIVE_ROLE_FILTER = By.xpath("//div[@id='accordion__panel-Profile']//button[@tabindex='0']");
    private final By RESEARCH_AREA_INPUT = By.xpath("//input[@aria-label='Add research area']");
    private final By RESEARCH_AREA_SUGGESTION = By.xpath("//ul[@role='listbox']//li[@role='option']");

    public String addRoleFilter() {
        Log.logInfo("Add Role Filter");
        return baseElement.clickFirstElementFromArrayAndGetTitleText(ACTIVE_ROLE_FILTER);
    }

    public ProfileSearch inputResearchArea(String researchArea) {
        Log.logInfo("Input research area "+ researchArea );
        baseElement.typeTo(RESEARCH_AREA_INPUT, researchArea);
        return this;
    }

    public ProfileSearch chooseFirstSuggestion() {
        Log.logInfo("Choose Research Area Suggestion");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(RESEARCH_AREA_SUGGESTION);
        baseElement.clickFirstElementFromArray(RESEARCH_AREA_SUGGESTION);
        return this;
    }

    public int getBracketsRoleResultNumber() {
        Log.logInfo("Get Role People type numbers in brackets");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(ACTIVE_ROLE_FILTER);
        return baseElement.clickFirstElementFromArrayAndGetNumberText(ACTIVE_ROLE_FILTER);
    }
}
