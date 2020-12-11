package yale.pageObjects.ProfilePageTabs;

import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import yale.pageObjects.ProfilePage;

public class NewsTab extends ProfilePage {

    private final By NEWS_SECTION_TITLE = By.xpath("//h3[contains(text(), 'News')]");
    private final By NEWS_ITEM = By.xpath("//li[@class='article-list__post article-list__post--large']");
    private final By NEWS_DATE = By.xpath(".//span[@class='article-post-date']");
    private final By NEWS_THUMBNAIL = By.xpath("//img[@class='article-post-thumbnail article-post__thumbnail']");
    private final By NEWS_TITLE = By.xpath(".//h4[@class='article-post-title article-post-title--size-mode--large']");


    @Step("Verify News Section Title is Displayed")
    public boolean isNewsSectionTitleDisplayed() {
        Log.logInfo("Check News Section Title is Displayed");
        return baseElement.isArrayNotEmpty(NEWS_SECTION_TITLE);
    }

    @Step("Verify News Section is not empty")
    public boolean isNewsSectionNotEmpty() {
        Log.logInfo("Check News Section is not empty");
        return baseElement.isArrayNotEmpty(NEWS_ITEM);
    }

    @Step("Verify Each News Item contains date")
    public boolean isNewsItemContainDate() {
        Log.logInfo("Check Each News Item contains date");
        return baseElement.isEachElementHaveElementWithText(NEWS_ITEM, NEWS_DATE);
    }

    @Step("Verify Each News Item contains thumbnail")
    public boolean isNewsItemContainThumbnail() {
        Log.logInfo("Check Each News Item contains thumbnail");
        return baseElement.checkPresenceElementInsideOtherElement(NEWS_ITEM, NEWS_THUMBNAIL);
    }

    @Step("Verify Each News Item contains title")
    public boolean isNewsItemContainTitle() {
        Log.logInfo("Check Each News Item contains title");
        return baseElement.isEachElementHaveElementWithText(NEWS_ITEM, NEWS_TITLE);
    }
}


