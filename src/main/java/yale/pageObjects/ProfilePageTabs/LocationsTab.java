package yale.pageObjects.ProfilePageTabs;

import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yale.pageObjects.ProfilePage;

public class LocationsTab extends ProfilePage {

    private final By LOCATIONS_SECTIONS_TITLE = By.xpath("//h3[contains(text(),'Locations')]");
    private final By LOCATION_MAP = By.xpath("//div[@class='location-map__map']");

    @Step("Verify Locations Section Title Displayed")
    public boolean isLocationsSectionTitleDisplayed() {
        Log.logInfo("Check Locations Section Title Displayed");
        return baseElement.isArrayNotEmpty(LOCATIONS_SECTIONS_TITLE);
    }

    @Step("Verify Locations Map Displayed")
    public boolean isLocationsMapDisplayed() {
        Log.logInfo("Check Locations Map Displayed");
        return baseElement.isArrayNotEmpty(LOCATION_MAP);
    }
}
