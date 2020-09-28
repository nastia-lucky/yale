package framework.logger;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class Log {

    private static Logger logger = Logger.getLogger("My Logger");

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logClick(By by) {
        logger.debug(String.format("Click element located: [%s]", by));
    }

    public static void logGetUrl(String url) {
        logger.debug(String.format("Open page by URL : [%s]", url));
    }

    public static void logTypeTo(By by, String value) {
        logger.debug(String.format("Type [%s] to element located [%s]", value, by));
    }

    public static void logTheElementIsDisplayed(By by) {
        logger.debug(String.format("The next element is displayed [%s]", by));
    }

    public static void logTheText(By by) {
        logger.debug(String.format("The text has been got by locator [%s]", by));
    }

    public static void logGetAttribute(By by, String attribute) {
        logger.debug(String.format("The attribute [%s] has been got from locator [%s]", attribute, by));
    }

    public static void logWaitForElementToBeClickable(By by) {
        logger.debug(String.format("Wait for the element [%s] is clickable", by));
    }

    public static void logWaitForElementsArePresent(By by) {
        logger.debug(String.format("Wait for the elements [%s] are present", by));
    }

    public static void logWaitForElementToBeDisplayed(By by) {
        logger.debug(String.format("Wait for the element [%s] is displayed", by));
    }

    public static void logCheckAllElementContainsOtherElement(By by, By by2) {
        logger.debug(String.format("Check all elements [%s] contain other element [%s]", by, by2));
    }

    public static void logCheckElementContainsText(By by, String text) {
        logger.debug(String.format("Check the element [%s] contains text [%s]", by, text));
    }

    public static void logCheckComponentInsideOtherComponentContainText(By by, By by2, String value) {
        logger.debug(String.format("Check the element [%s] inside other element [%s] contains text [%s]", by2, by, value));
    }

    public static void logGetNumberFromElementByAttribute(By by, String text) {
        logger.debug(String.format("Get Number From  element [%s] by attribute [%s]", by, text));
    }

    public static void logCheckElementsContainText(By by1, By by2, String text) {
        logger.debug(String.format("Check one of the elements ([%s] or [%s]) contain text [%s]", by1, by2, text));
    }

    public static void logCheckTheSecondElementContainsOneOfTheTexts(By by, String text, String text2) {
        logger.debug(String.format("Check the element [%s] contain text one of the texts( [%s] or [%s] )", by, text, text2));
    }

    public static void logGetTextFromElementInsideOtherElement(By by, By by2) {
        logger.debug(String.format("Get text from element [%s] inside other element [%s]", by2, by));
    }

    public static void logCheckAllElementsContainTexts(By by, By by2, String text, String text2) {
        logger.debug(String.format("Check the element [%s] inside other element [%s] contain one of the texts ([%s] or [%s])", by2, by, text, text2));
    }

}
