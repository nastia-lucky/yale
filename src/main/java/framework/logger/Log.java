package framework.logger;

import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class Log {


    private static Logger logger = Logger.getLogger("My Logger");

    @Attachment
    public static String logInfo(String message) {
        logger.info(message);
        return message;
    }

    @Attachment
    public static String logClick(By by) {
        String click = String.format("Click element located: [%s]", by);
        logger.debug(String.format("Click element located: [%s]", by));
        return click;
    }

    @Attachment
    public static String logGetUrl(String url) {
        String urlString = String.format("Open page by URL : [%s]", url);
        logger.debug(String.format("Open page by URL : [%s]", url));
        return urlString;
    }

    @Attachment
    public static String logTypeTo(By by, String value) {
        String info = String.format("Type [%s] to element located [%s]", value, by);
        logger.debug(String.format("Type [%s] to element located [%s]", value, by));
        return info;
    }

    @Attachment
    public static String logTheElementIsDisplayed(By by) {
        String info = String.format("The next element is displayed [%s]", by);
        logger.debug(String.format("The next element is displayed [%s]", by));
        return info;
    }

    @Attachment
    public static String logTheText(By by) {
        String info = String.format("The text has been got by locator [%s]", by);
        logger.debug(String.format("The text has been got by locator [%s]", by));
        return info;
    }

    @Attachment
    public static String logGetAttribute(By by, String attribute) {
        String info = String.format("The attribute [%s] has been got from locator [%s]", attribute, by);
        logger.debug(String.format("The attribute [%s] has been got from locator [%s]", attribute, by));
        return info;
    }

    @Attachment
    public static String logWaitForElementToBeClickable(By by) {
        String info = String.format("Wait for the element [%s] is clickable", by);
        logger.debug(String.format("Wait for the element [%s] is clickable", by));
        return info;
    }

    @Attachment
    public static String logCheckAllElementContainsOtherElement(By by, By by2) {
        String info = String.format("Check all elements [%s] contain other element [%s]", by, by2);
        logger.debug(String.format("Check all elements [%s] contain other element [%s]", by, by2));
        return info;
    }

    @Attachment
    public static String logCheckElementContainsText(By by, String text) {
        String info = String.format("Check the element [%s] contains text [%s]", by, text);
        logger.debug(String.format("Check the element [%s] contains text [%s]", by, text));
        return info;
    }

    @Attachment
    public static String logCheckComponentInsideOtherComponentContainText(By by, By by2, String value) {
        String info = String.format("Check the element [%s] inside other element [%s] contains text [%s]", by2, by, value);
        logger.debug(String.format("Check the element [%s] inside other element [%s] contains text [%s]", by2, by, value));
        return info;
    }

    @Attachment
    public static String logGetNumberFromElementByAttribute(By by, String text) {
        String info = String.format("Get Number From  element [%s] by attribute [%s]", by, text);
        logger.debug(String.format("Get Number From  element [%s] by attribute [%s]", by, text));
        return info;
    }

    @Attachment
    public static String logCheckElementsContainText(By by1, By by2, String text) {
        String info = String.format("Check one of the elements ([%s] or [%s]) contain text [%s]", by1, by2, text);
        logger.debug(String.format("Check one of the elements ([%s] or [%s]) contain text [%s]", by1, by2, text));
        return info;
    }

    @Attachment
    public static String logCheckTheSecondElementContainsOneOfTheTexts(By by, String text, String text2) {
        String info = String.format("Check the element [%s] contain text one of the texts( [%s] or [%s] )", by, text, text2);
        logger.debug(String.format("Check the element [%s] contain text one of the texts( [%s] or [%s] )", by, text, text2));
        return info;
    }

    @Attachment
    public static String logGetTextFromElementInsideOtherElement(By by, By by2) {
        String info = String.format("Get text from element [%s] inside other element [%s]", by2, by);
        logger.debug(String.format("Get text from element [%s] inside other element [%s]", by2, by));
        return info;
    }

    @Attachment
    public static String logCheckAllElementsContainTexts(By by, By by2, String text, String text2) {
        String info = String.format("Check the element [%s] inside other element [%s] contain one of the texts ([%s] or [%s])", by2, by, text, text2);
        logger.debug(String.format("Check the element [%s] inside other element [%s] contain one of the texts ([%s] or [%s])", by2, by, text, text2));
        return info;
    }

    @Attachment
    public static String logCheckTheElementContainsText(By by) {
        String info = String.format("Check the element [%s] contains text", by);
        logger.debug(String.format("Check the element [%s] contains text", by));
        return info;
    }

    @Attachment
    public static String logCheckPresenceElementInsideOtherElement(By by1, By by2) {
        String info = String.format("Check the element [%s] contains another element [%s]", by1, by2);
        logger.debug(String.format("Check the element [%s] contains another element [%s]", by1, by2));
        return info;
    }

    @Attachment
    public static String logCheckThatEachElementInArrayContainText(By by) {
        String info = String.format("Check each element [%s] in array contains text", by);
        logger.debug(String.format("Check each element [%s] in array contains text", by));
        return info;
    }

    @Attachment
    public static String logСheckPresenceOneOfTwoElementInsideAnother(By by, By by1, By by2) {
        String info = String.format("Check that element [%s] contains one of two elements: [%s] or [%s] ", by, by1, by2);
        logger.debug(String.format("Check that element [%s] contains one of two elements: [%s] or [%s] ", by, by1, by2));
        return info;
    }

    @Attachment
    public static String logCheckAndOpenElementNotContainAnother(By by, By by1, By by2) {
        String info = String.format("Check that element [%s] doesn't contain another [%s] and open inner element [%s]", by, by1, by2);
        logger.debug(String.format("Check that element [%s] doesn't contain another [%s] and open inner element [%s]", by, by1, by2));
        return info;
    }

    @Attachment
    public static String logIsChosenValueCoincidesWithTheFirstLetterOnFocus(String text) {
        String info = String.format("Check that chosen value [%s] coincides with the first Letter on focus ", text);
        logger.debug(String.format("Check that chosen value [%s] coincides with the first Letter on focus ", text));
        return info;
    }

    @Attachment
    public static String logWaitForElementDisappear(By by) {
        String info = String.format("Wait for element [%s] disappear", by);
        logger.debug(String.format("Wait for element [%s] disappear", by));
        return info;
    }

    @Attachment
    public static String logGetDate(By by) {
        String info = String.format("Get date from element [%s]", by);
        logger.debug(String.format("Get date from element [%s]", by));
        return info;
    }

    @Attachment
    public static String logGetSearchResultDate(By by1, By by2) {
        String info = String.format("Get date from elements [%s] and [%s] ", by1, by2);
        logger.debug(String.format("Get date from elements [%s] and [%s] ", by1, by2));
        return info;
    }

    @Attachment
    public static String logCheckPresenceElementInsideAnotherElement(By by1, By by2, By by3) {
        String info = String.format("Check that element [%s] contains another element [%s] if element [%s] is present", by1, by3, by2);
        logger.debug(String.format("Check that element [%s] contains another element [%s] if element [%s] is present", by1, by3, by2));
        return info;
    }

    @Attachment
    public static String logGetDateFromElementInsideOtherElement(By by1, By by2) {
        String info = String.format("Get date from element [%s] inside another element [%s]", by2, by1);
        logger.debug(String.format("Get date from element [%s] inside another element [%s]", by2, by1));
        return info;
    }

    @Attachment
    public static String logCheckElementInsideOtherContainText(By by1, By by2) {
        String info = String.format("Check element [%s] inside another element [%s] contain text", by2, by1);
        logger.debug(String.format("Check element [%s] inside another element [%s] contain text", by2, by1));
        return info;
    }

    @Attachment
    public static String logIsCountOfElementsCorrect(By by, int expectedCount) {
        String info = String.format("Check the counts of elements [%s] coincide with expected [%s]", by, expectedCount);
        logger.debug(String.format("Check the counts of elements [%s] coincide with expected [%s]", by, expectedCount));
        return info;
    }

    @Attachment
    public static String logGetYPosition() {
        String info = "Get Y Position";
        logger.debug("Get Y Position");
        return info;
    }

    @Attachment
    public static String logIsAmountOfElementsCoincideWithExpected(By by, By by2) {
        String info = String.format("Check the amount of elements [%s] coincide with amount of text got from all elements [%s]", by, by2);
        logger.debug(String.format("Check the amount of elements [%s] coincide with amount of text got from all elements [%s]", by, by2));
        return info;
    }

    @Attachment
    public static String logGetElements(By by) {
        String info = String.format("Get Elements [%s]", by);
        logger.debug(String.format("Get Elements [%s]", by));
        return info;
    }

    @Attachment
    public static String logGetElement(By by) {
        String info = String.format("Get Element [%s]", by);
        logger.debug(String.format("Get Element [%s]", by));
        return info;
    }

    @Attachment
    public static String logGetTextFromFirstElement(By by) {
        String info = String.format("Get text from first element [%s]", by);
        logger.debug(String.format("Get text from first element [%s]", by));
        return info;
    }

    @Attachment
    public static String logWaitForInvisibilityOfText(By by, String text) {
        String info = String.format("Wait for invisibility of text [%s] inside of element  [%s]", text, by);
        logger.debug(String.format("Wait for invisibility of text [%s] inside of element  [%s]", text, by));
        return info;
    }

    @Attachment
    public static String logScrollToTheBottom() {
        String info = "Scroll to The Bottom";
        logger.debug("Scroll to The Bottom");
        return info;
    }

    @Attachment
    public static String logIsStringContainsAnotherString(String string, String string2) {
        String info = String.format("Check the String  [%s] contains another String  [%s]", string, string2);
        logger.debug(String.format("Check the String  [%s] contains another String  [%s]", string, string2));
        return info;
    }
}
