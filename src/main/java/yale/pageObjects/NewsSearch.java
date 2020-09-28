package yale.pageObjects;

import framework.logger.Log;
import org.openqa.selenium.By;

public class NewsSearch extends SearchPage{
    private final By SOURCE_BUTTON = By.xpath("//span[contains(text(), 'Source')]");
    private final By ACTIVE_SOURCE=By.xpath("//div[@id='accordion__panel-articleSource']//button[@tabindex='0']");


    public NewsSearch clickSourceButton() {
        Log.logInfo("Сlick Source Button");
        baseElement.clickElement(SOURCE_BUTTON);
        return this;
    }

    public SearchPage addYsmNewsFilter() {
        Log.logInfo("Add YSM News Filter");
        baseElement.clickElement(ACTIVE_SOURCE);
        return this;
    }

}
