package yale.pageObjects;

import framework.BaseElement;
import framework.logger.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NewsPage extends BasePage {

    private static By NEWS_TITLE = By.xpath("//h1[@class='article-header__title']");
    private final By NEWS_PUBLISH_DATE = By.xpath("//div[@class='news-details__date']");
    private final By NEWS_CONTENT = By.xpath("//div[@class='news-content__text']");
    private final By NEWS_SUBMITTER = By.xpath("//div[@class='news-submitter']");
    private final By TAG_ITEM = By.xpath("//ul[@class='article-tags__list']/li");
    private final By RELATED_LINKS_TITLE = By.xpath("//h3[contains(text(), 'Related Links')]");
    private final By SIDEBAR_LINK_ITEM = By.xpath("//li[@class='news-details-sidebar-link-list__item']");
    private final By SHARE_VIA_TWITTER_LINK = By.xpath("//button[@aria-label='Share via Twitter']");
    private final By MEDIA_GALLERY = By.xpath("//div[@class='media-gallery']");
    private final By QUOTE_TEXT = By.xpath("//p[@class='quote__text']");
    private final By QUOTE_AUTHOR = By.xpath("//footer[@class='quote__author']");
    private final By TAGS_TITLE = By.xpath("//h2[contains(text(), 'Tags')]");
    private final By FEATURED_PEOPLE = By.xpath("//li[@class='profile-list__item profile-list__item--type--profile-list profile-list__item--size-mode--large profile-list__item--with-first-item-top-border']");

    public NewsPage() {
        super(NEWS_TITLE);
    }

    @Step("Get News Title")
    public String getNewsTitle() {
        Log.logInfo("Get News Title");
        String title = baseElement.getTitle();
        String[] parts = title.split(" <");
        return parts[0];
    }

    @Step("Verify that News Date is displayed")
    public boolean isNewsDateDisplayed() {
        Log.logInfo("Check that News Date is displayed");
        return baseElement.isElementDisplayed(NEWS_PUBLISH_DATE);
    }

    @Step("Verify that News Content is displayed")
    public boolean isNewsContentDisplayed() {
        Log.logInfo("Check that News Content is displayed");
        return baseElement.isElementDisplayed(NEWS_CONTENT);
    }

    @Step("Verify that News Submitter is displayed")
    public boolean isNewsSubmitterDisplayed() {
        Log.logInfo("Check that News Submitter is displayed");
        return baseElement.isElementDisplayed(NEWS_SUBMITTER);
    }

    @Step("Verify that tags on the news details page contain chosen keyword")
    public boolean isTagsListContainsInputtedKeyword(String keyword) {
        Log.logInfo("Check that tags on the news details page contain chosen keyword");
        BaseElement.waitFirstElementFromArrayIsClickable(TAG_ITEM);
        return baseElement.isElementsContainsText(TAG_ITEM, keyword);
    }

    @Step("Verify that Related Links Section Title is displayed")
    public boolean isRelatedLinksTitleIsDisplayed() {
        Log.logInfo("Check that Related Links Section Title is displayed");
        BaseElement.waitElementToBeClickable(SHARE_VIA_TWITTER_LINK);
        return baseElement.isElementDisplayed(RELATED_LINKS_TITLE);
    }

    @Step("Verify that Related Links Section is not empty")
    public boolean isSideBarRelatedLinkItemDisplayed() {
        Log.logInfo("Check that Related Links Section is not empty");
        return baseElement.isArrayNotEmpty(SIDEBAR_LINK_ITEM);
    }

    @Step("Verify that Media Gallery is displayed")
    public boolean isMediaGalleryDisplayed() {
        Log.logInfo("Check that Media Gallery is displayed");
        return baseElement.isArrayNotEmpty(MEDIA_GALLERY);
    }

    @Step("Verify that Quote Text is displayed")
    public boolean isQuoteTextDisplayed() {
        Log.logInfo("Check that Quote Text is displayed");
        return baseElement.isElementDisplayed(QUOTE_TEXT);
    }

    @Step("Verify that Quote Author is displayed")
    public boolean isQuoteAuthorDisplayed() {
        Log.logInfo("Check that Quote Author is displayed");
        return baseElement.isElementDisplayed(QUOTE_AUTHOR);
    }

    @Step("Verify that Tags Title is displayed")
    public boolean isTagsTitleDisplayed() {
        Log.logInfo("Check that Tags Title is displayed");
        return baseElement.isElementDisplayed(TAGS_TITLE);
    }

    @Step("Verify that Tags Section is not empty")
    public boolean isTagsSectionNotEmpty() {
        Log.logInfo("Check that Tags Section is not empty");
        return baseElement.isArrayNotEmpty(TAG_ITEM);
    }

    @Step("Verify that Featured people  Section is not empty")
    public boolean isFeaturedPeopleSectionNotEmpty() {
        Log.logInfo("Check that Featured People Section is not empty");
        return baseElement.isArrayNotEmpty(FEATURED_PEOPLE);
    }
}
