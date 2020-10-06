package yale.pageObjects;

import framework.logger.Log;
import framework.BaseElement;
import org.openqa.selenium.By;

public class NewsPage extends BasePage {

    private static By NEWS_TITLE = By.xpath("//h1[@class='article-header__title']");
    private final By NEWS_PUBLISH_DATE = By.xpath("//div[@class='news-details__date']");
    private final By NEWS_CONTENT = By.xpath("//div[@class='news-content__text']");
    private final By NEWS_SUBMITTER = By.xpath("//div[@class='news-submitter']");
    private final By TAG_ITEM = By.xpath("//ul[@class='article-tags__list']/li");

    public NewsPage(){
        super(NEWS_TITLE);
    }
    public String getNewsTitle() {
        Log.logInfo("Get News Title");
        String title = baseElement.getTitle();
        String[] parts = title.split(" <");
        return parts[0];
    }

    public boolean isNewsDateDisplayed() {
        Log.logInfo("Check that News Date is displayed");
        return baseElement.isElementDisplayed(NEWS_PUBLISH_DATE);
    }

    public boolean isNewsContentDisplayed() {
        Log.logInfo("Check that News Content is displayed");
        return baseElement.isElementDisplayed(NEWS_CONTENT);
    }

    public boolean isNewsSubmitterDisplayed() {
        Log.logInfo("Check that News Submitter is displayed");
        return baseElement.isElementDisplayed(NEWS_SUBMITTER);
    }

    public boolean isTagsListContainsInputtedKeyword(String keyword) {
        Log.logInfo("Check that tags on the news details page contain chosen keyword");
        BaseElement.waitForTheFirstElementFromArrayIsClickable(TAG_ITEM);
        return baseElement.isElementsContainsText(TAG_ITEM, keyword);
    }
}
