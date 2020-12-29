package framework;


import framework.logger.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseElement {

    protected WebElement webElement;
    Config config = new Config();

    public BaseElement(By by) {
        this.webElement = getElement(by);
    }

    private static final int LONG_WAIT = 50;

    public void clickElement(By elementLocator) {
        Log.logClick(elementLocator);
        waitElementToBeClickable(elementLocator);
        Browser.getDriver().findElement(elementLocator).click();

    }

    public static WebElement getElement(By elementLocator) {
        try {
            return new WebDriverWait(Browser.getDriver(), LONG_WAIT)
                    .until(driver -> driver.findElement(elementLocator));
        } catch (TimeoutException e) {
            Log.logInfo("I can't find this element " + elementLocator);
            throw e;
        }
    }

    public static List<WebElement> getElements(By elementLocator) {
        return new WebDriverWait(Browser.getDriver(), LONG_WAIT)
                .until(driver -> driver.findElements(elementLocator));
    }

    public void typeTo(By elementLocator, String inputtedValue) {
        Log.logTypeTo(elementLocator, inputtedValue);
        getElement(elementLocator).sendKeys(inputtedValue);
    }

    public boolean isElementDisplayed(By elementLocator) {
        Log.logTheElementIsDisplayed(elementLocator);
        return getElement(elementLocator).isDisplayed();
    }

    public String getTextFirstElementFromArray(By elementLocator) {
        Log.logTheText(elementLocator);
        List<WebElement> elements = getElements(elementLocator);
        return elements.get(0).getText();
    }

    public static void waitElementToBeClickable(By elementLocator) {
        Log.logWaitForElementToBeClickable(elementLocator);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
    }

    public static void waitFirstElementFromArrayIsClickable(By elementLocator) {
        Log.logWaitForElementToBeClickable(elementLocator);
        List<WebElement> elements = getElements(elementLocator);
        WebElement element = elements.get(0);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickFirstElementFromArray(By elementLocator) {
        Log.logClick(elementLocator);
        List<WebElement> elementsList = getElements(elementLocator);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        WebElement element = elementsList.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public String clickFirstElementAndGetFilterType(By elementLocator) {
        Log.logClick(elementLocator);
        List<WebElement> elementsList = getElements(elementLocator);
        String type;
        WebElement element = elementsList.get(0);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        wait.until(ExpectedConditions.elementToBeClickable(element));
        String text = element.getText();
        element.click();
        if (!text.contains("/")) {
            return text;
        }
        {
            type = text.substring(0, text.lastIndexOf("/"));
            return type;
        }
    }

    public String getAttribute(By elementLocator, String tag) {
        Log.logGetAttribute(elementLocator, tag);
        WebElement element = getElement(elementLocator);
        return element.getAttribute(tag);
    }

    public void hoverOnElement(By elementLocator) {
        Actions action = new Actions(Browser.getDriver());
        List<WebElement> element = getElements(elementLocator);
        action.moveToElement(element.get(0)).build().perform();
    }

    public boolean isElementsDisplayed(By elementLocator) {
        return getElements(elementLocator).get(0).isDisplayed();
    }

    public boolean isAllElementsFromArrayDisplayed(By parentLocator, By childLocator) {
        Log.logCheckAllElementContainsOtherElement(parentLocator, childLocator);
        List<WebElement> elements = getElements(parentLocator);
        for (WebElement element : elements) {
            if (!element.findElement(childLocator).isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public static String getText(By elementLocator) {
        Log.logTheText(elementLocator);
        return getElement(elementLocator).getText();

    }

    public boolean isArrayEmpty(By elementLocator) {
        List<WebElement> elements = getElements(elementLocator);
        return elements.isEmpty();
    }

    public String getTitle() {
        return Browser.getDriver().getTitle();
    }

    public boolean compareText(By parentLocator, By childLocator, String inputtedValue) {
        Log.logCheckComponentInsideOtherComponentContainText(parentLocator, childLocator, inputtedValue);
        List<WebElement> elements = getElements(parentLocator);
        WebElement firstElement = elements.get(0);
        List<WebElement> elements2 = firstElement.findElements(childLocator);
        WebElement element2 = elements2.get(0);
        String highlightedText = element2.getText();
        return inputtedValue.contains(highlightedText);
    }

    public boolean isElementsContainsText(By elementLocator, String inputtedValue) {
        Log.logCheckElementContainsText(elementLocator, inputtedValue);
        List<WebElement> elements = getElements(elementLocator);
        for (WebElement element : elements) {
            String gotText = element.getText();
            if (gotText.equals(inputtedValue)) {
                return true;
            }
        }
        return false;
    }

    public int getNumber(By elementLocator, String value) {
        Log.logGetNumberFromElementByAttribute(elementLocator, value);
        List<WebElement> elements = getElements(elementLocator);
        WebElement element = elements.get(elements.size() - 2);
        String attribute = element.getAttribute(value);
        String[] stringArray = attribute.split(" ");
        String stringNumberPages = stringArray[1];
        return Integer.parseInt(stringNumberPages);
    }

    public String clickFirstElementFromArrayAndGetTitleText(By elementLocator) {
        Log.logClick(elementLocator);
        List<WebElement> elementsList = getElements(elementLocator);
        WebElement element = elementsList.get(0);
        String textElement = element.getText();
        String substring = textElement.substring(0, textElement.lastIndexOf("(")).trim();
        element.click();
        return substring;
    }

    public boolean isElementContainsText(By elementLocator, String inputtedValue) {
        Log.logCheckElementContainsText(elementLocator, inputtedValue);
        String elementText = getElement(elementLocator).getText();
        return elementText.contains(inputtedValue);
    }

    public boolean isElementsContainText(By elementLocator1, By elementLocator2, String inputtedValue) {
        Log.logCheckElementsContainText(elementLocator1, elementLocator2, inputtedValue);
        List<WebElement> elements1 = getElements(elementLocator1);
        if (!elements1.isEmpty()) {
            String textFromElement = elements1.get(0).getText();
            return textFromElement.contains(inputtedValue);
        } else {
            String textFromElement2 = getElement(elementLocator2).getText();
            return textFromElement2.contains(inputtedValue);
        }
    }

    public boolean isSecondElementContainsOneOfTheTexts(By elementLocator, String text, String text2) {
        Log.logCheckTheSecondElementContainsOneOfTheTexts(elementLocator, text, text2);
        List<WebElement> elements = getElements(elementLocator);
        String elementText = elements.get(1).getText();
        if (elementText.contains(text)) {
            return true;
        }
        {
            return elementText.equals(text2);
        }
    }

    public boolean isAllElementsInArrayContainsText(By elementLocator, String text) {
        Log.logCheckElementContainsText(elementLocator, text);
        List<WebElement> elements = getElements(elementLocator);
        for (WebElement element : elements) {
            String elementText = element.getText();
            if (!elementText.equals(text)) {
                return false;
            }
        }
        return true;
    }

    public String getTextFromElementInsideOtherElement(By parentLocator, By childLocator) {
        Log.logGetTextFromElementInsideOtherElement(parentLocator, childLocator);
        List<WebElement> elements = getElements(parentLocator);
        WebElement firstElement = elements.get(0);
        WebElement innerElement = firstElement.findElement(childLocator);
        String innerElementText = innerElement.getText();
        innerElement.click();
        return innerElementText;
    }

    public int clickFirstElementFromArrayAndGetNumberText(By elementLocator) {
        Log.logClick(elementLocator);
        List<WebElement> elementsList = getElements(elementLocator);
        WebElement element = elementsList.get(0);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        wait.until(ExpectedConditions.elementToBeClickable(element));
        String textElement = element.getText();
        String substring = textElement.substring(textElement.lastIndexOf("(") + 1, textElement.lastIndexOf(")")).trim();
        int number = Integer.parseInt(substring);
        elementsList.get(0).click();
        return number;
    }

    public void clickTheSecondElementFromArray(By elementLocator) {
        Log.logClick(elementLocator);
        List<WebElement> elements = getElements(elementLocator);
        elements.get(1).click();
    }

    public boolean isEachElementHaveElementWithText(By parentLocator, By childLocator) {
        Log.logCheckAllElementContainsOtherElement(parentLocator, childLocator);
        List<WebElement> elements = getElements(parentLocator);
        List<String> textsList = new ArrayList<>();
        int sizeResults = elements.size();
        for (WebElement element : elements) {
            List<WebElement> innerElements = element.findElements(childLocator);
            if (!innerElements.isEmpty()) {
                String text = innerElements.get(0).getText();
                if (!text.equals("")) {
                    textsList.add(text);
                }
            }
        }
        return sizeResults == textsList.size();
    }

    public boolean isOneOfElementsHaveElementWithText(By parentLocator, By childLocator) {
        List<WebElement> elements = getElements(parentLocator);
        for (WebElement element : elements) {
            List<WebElement> innerElements = element.findElements(childLocator);
            if (!innerElements.isEmpty()) {
                String text = innerElements.get(0).getText();
                if (!text.equals("")) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean isAllElementsContainText(By parentLocator, By childLocator, String text) {
        Log.logCheckElementsContainText(parentLocator, childLocator, text);
        List<WebElement> elements = getElements(parentLocator);
        int sizeResults = elements.size();
        List<String> stringList = new ArrayList<>();
        for (WebElement element : elements) {
            List<WebElement> innerElements = element.findElements(childLocator);
            if (!innerElements.isEmpty()) {
                String elementText = innerElements.get(0).getText();
                if (elementText.equals(text)) {
                    stringList.add(elementText);
                }
            }
        }
        return sizeResults == stringList.size();
    }

    public String getTextFromFirstElement(By elementLocator) {
        Log.logGetTextFromFirstElement(elementLocator);
        List<WebElement> elements = getElements(elementLocator);
        return elements.get(0).getText();
    }

    public boolean isAllElementsContainTexts(By parentLocator, By childLocator, String text, String text2) {
        Log.logCheckAllElementsContainTexts(parentLocator, childLocator, text, text2);
        List<WebElement> elements = getElements(parentLocator);
        int searchNumbers = elements.size();
        List<String> stringList = new ArrayList<>();
        for (WebElement element : elements) {
            List<WebElement> innerElements = element.findElements(childLocator);
            if (!innerElements.isEmpty()) {
                String elementText = innerElements.get(0).getText();
                if (elementText.equals(text)) {
                    stringList.add(elementText);
                }
                if (elementText.equals(text2)) {
                    stringList.add(elementText);
                }
            }
        }
        return searchNumbers == stringList.size();
    }

    public String checkAndOpenElementNotContainAnother(By parentLocator, By childLocator1, By childLocator2) {
        Log.logCheckAndOpenElementNotContainAnother(parentLocator, childLocator1, childLocator2);
        List<WebElement> elements = getElements(parentLocator);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            List<WebElement> innerElements = element.findElements(childLocator1);
            List<WebElement> clickableElements = element.findElements(childLocator2);
            if (innerElements.isEmpty()) {
                WebElement element1 = clickableElements.get(0);
                String text = element1.getText();
                wait.until(ExpectedConditions.elementToBeClickable(element1));
                element1.click();
                return text;
            }

        }
        return "Such element doesn't exist";
    }

    public boolean checkPresenceElementInsideOtherElement(By parentLocator, By childLocator) {
        Log.logCheckPresenceElementInsideOtherElement(parentLocator, childLocator);
        List<WebElement> elements = getElements(parentLocator);
        for (WebElement element : elements) {
            List<WebElement> innerElements = element.findElements(childLocator);
            if (innerElements.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void clickWithJS(By elementLocator) {
        Log.logClick(elementLocator);
        WebElement element = getElement(elementLocator);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        wait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
        executor.executeScript(config.getProperty("argumentForClick"), element);
    }

    public void clickTheFirstElementWithJS(By elementLocator) {
        Log.logClick(elementLocator);
        List<WebElement> elements = getElements(elementLocator);
        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
        executor.executeScript(config.getProperty("argumentForClick"), elements.get(0));
    }

    public boolean isElementContainText(By elementLocator) {
        Log.logCheckTheElementContainsText(elementLocator);
        WebElement element = getElement(elementLocator);
        String text = element.getText();
        return !text.equals("");
    }

    public boolean isEachElementInArrayContainText(By elementLocator) {
        Log.logCheckThatEachElementInArrayContainText(elementLocator);
        List<WebElement> elements = getElements(elementLocator);
        for (WebElement element : elements) {
            String text = element.getText();
            if (text.equals("")) {
                return false;
            }
        }
        return true;
    }

    public boolean isOneOfTwoElementPresentInsideAnother(By parentLocator, By childLocator1, By childLocator2) {
        Log.logСheckPresenceOneOfTwoElementInsideAnother(parentLocator, childLocator1, childLocator2);
        List<WebElement> elements = getElements(parentLocator);
        for (WebElement element : elements) {
            List<WebElement> innerElements1 = element.findElements(childLocator1);
            List<WebElement> innerElements2 = element.findElements(childLocator2);
            if (innerElements1.isEmpty() && innerElements2.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public String chooseValueFromArrayAndGetText(By elementLocator) {
        List<WebElement> elements = getElements(elementLocator);
        int number = elements.size() - 1;
        WebElement element = elements.get(number);
        String text = element.getText();
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return text;
    }

    public boolean isChosenValueCoincidesWithTheFirstLetterOnFocus(String inputtedText) {
        Log.logIsChosenValueCoincidesWithTheFirstLetterOnFocus(inputtedText);
        WebElement element = Browser.getDriver().switchTo().activeElement();
        String text = element.getText();
        String firstValue = text.substring(0, 1);
        return firstValue.equals(inputtedText);
    }

    public static void waitForInvisibility(By elementLocator, String text) {
        Log.logWaitForInvisibilityOfText(elementLocator, text);
        Wait<WebDriver> wait = new FluentWait(Browser.getDriver())
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.invisibilityOfElementWithText(elementLocator, text));
    }

    public static void waitForText(By elementLocator, String text) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), 30);
        wait.until(ExpectedConditions.textToBe(elementLocator, text));
    }

    public boolean isFileDownloaded(String fileName) {
        Path filePath = Browser.getInstance().getDownloadPath().resolve(fileName);
        return Files.exists(filePath);
    }

    public String clickAndGetTextFirstElement(By elementLocator, String text) {
        Log.logClick(elementLocator);
        List<WebElement> elements = getElements(elementLocator);
        WebElement element = elements.get(0);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        wait.until(ExpectedConditions.elementToBeClickable(element));
        String textOfElement = element.getAttribute(text);
        String fileName = textOfElement.substring(textOfElement.lastIndexOf("/") + 1);
        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
        executor.executeScript(config.getProperty("argumentForClick"), element);
        try {
            return URLDecoder.decode(fileName, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return fileName;
        }
    }

    public static void waitForElementDisappear(By elementLocator) {
        Log.logWaitForElementDisappear(elementLocator);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        wait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
    }

    public void scrollToTheBottom() {
        Log.logScrollToTheBottom();
        JavascriptExecutor js = (JavascriptExecutor) Browser.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public Long getYPosition() {
        Log.logGetYPosition();
        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
        return (Long) executor.executeScript("return window.pageYOffset;");
    }

    public boolean isAmountOfElementsCoincideWithExpected(By parentLocator, By childLocator) {
        Log.logIsAmountOfElementsCoincideWithExpected(parentLocator, childLocator);
        List<WebElement> parentElements = getElements(parentLocator);
        int expectedAmount = parentElements.size() + 1;
        List<WebElement> childElements = getElements(childLocator);
        List<String> texts = new ArrayList<>();
        for (WebElement element : childElements) {
            String textElement = element.getText();
            if (!textElement.equals("")) {
                texts.add(textElement);
            }
        }
        int amountOfChildElementsContainingText = texts.size();
        return expectedAmount == amountOfChildElementsContainingText;
    }

    public LocalDate getDate(By elementLocator, String tag) {
        Log.logGetDate(elementLocator);
        List<WebElement> elements = getElements(elementLocator);
        WebElement element = elements.get(0);
        String stringDate = element.getAttribute(tag);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
        LocalDate date = LocalDate.parse(stringDate, dateTimeFormatter);
        element.click();
        return date;
    }

    public LocalDate getSearchResultDate(By parentLocator, By childLocator1, By childLocator2) {
        Log.logGetSearchResultDate(childLocator1, childLocator2);
        List<WebElement> elements = getElements(parentLocator);
        WebElement firstElement = elements.get(0);
        String dateMonth = firstElement.findElement(childLocator1).getText();
        String stringDate = firstElement.findElement(childLocator2).getText();
        int length = stringDate.length();
        if (length == 1) {
            stringDate = "0" + stringDate;
        }
        String fullStringDate = stringDate + " " + dateMonth;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return LocalDate.parse(fullStringDate, dateTimeFormatter);
    }

    public boolean isElementPresentInsideAnotherElement(By parentLocator, By childLocator1, By childLocator2) {
        Log.logCheckPresenceElementInsideAnotherElement(parentLocator, childLocator1, childLocator2);
        List<WebElement> parentElements = getElements(parentLocator);
        for (WebElement element : parentElements) {
            List<WebElement> innerElements = element.findElements(childLocator1);
            if (!innerElements.isEmpty()) {
                List<WebElement> childElements = element.findElements(childLocator2);
                if (childElements.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public LocalDate getDate(By parentLocator, By childLocator, int i) {
        Log.logGetDateFromElementInsideOtherElement(parentLocator, childLocator);
        WebElement parentElement = getElement(parentLocator);
        List<WebElement> childElements = parentElement.findElements(childLocator);
        String dateString = childElements.get(i - 1).getText();
        String[] stringArray = dateString.split(",");
        String firstString = stringArray[0];
        String secondString = stringArray[1];
        String dateUpdated = firstString + secondString;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
        return LocalDate.parse(dateUpdated, dateTimeFormatter);
    }

    public boolean isElementInsideOtherContainText(By parentLocator, By childLocator) {
        Log.logCheckElementInsideOtherContainText(parentLocator, childLocator);
        List<WebElement> parentElements = getElements(parentLocator);
        for (WebElement element : parentElements) {
            List<String> stringsList1 = new ArrayList<>();
            WebElement childElement1 = element.findElement(childLocator);
            String textChildElement1 = childElement1.getText();
            if (!textChildElement1.equals("")) {
                stringsList1.add(textChildElement1);
            }
            if (stringsList1.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean isCountOfElementsCorrect(By elementLocator, int expectedCount) {
        Log.logIsCountOfElementsCorrect(elementLocator, expectedCount);
        List<WebElement> elements = getElements(elementLocator);
        return elements.size() == expectedCount;
    }

    public boolean isOneOfTwoArraysIsNotEmpty(By elementLocator1, By elementLocator2) {
        List<WebElement> elements = getElements(elementLocator1);
        List<WebElement> elements2 = getElements(elementLocator2);
        if (elements.isEmpty() && elements2.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isAllElementsFromOneOfTheArrayContainText(By elementLocator1, By elementLocator2) {
        List<WebElement> elements = getElements(elementLocator1);
        List<WebElement> elements2 = getElements(elementLocator2);
        if (elements.isEmpty() && elements2.isEmpty()) {
            return false;
        }
        for (WebElement element : elements) {
            String text = element.getText();
            if (text.equals("")) {
                return false;
            }
        }
        for (WebElement element : elements2) {
            String text = element.getText();
            if (text.equals("")) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllElementsInArrayContainText(By elementLocator) {
        List<WebElement> elements = getElements(elementLocator);
        if (elements.isEmpty()) {
            return false;
        }
        for (WebElement element : elements) {
            String text = element.getText();
            if (text.equals("")) {
                return false;
            }
        }
        return true;
    }

    public boolean isArrayNotEmpty(By elementLocator) {
        List<WebElement> elements = getElements(elementLocator);
        if (elements.isEmpty()) {
            return false;
        }
        return true;
    }


    public String getCurrentURL() {
        return Browser.getDriver().getCurrentUrl();
    }

    public boolean isStringContainsAnotherString(String string, String string2) {
        Log.logIsStringContainsAnotherString(string, string2);
        return string.contains(string2);
    }

    public boolean isAmountOfElementsCoincide(By elementLocator1, By elementLocator2) {
        List<WebElement> elements1 = getElements(elementLocator1);
        List<WebElement> elements2 = getElements(elementLocator2);
        List<String> stringList1 = new ArrayList<>();
        List<String> stringList2 = new ArrayList<>();
        if (elements1.isEmpty()) {
            return false;
        }
        if (elements2.isEmpty()) {
            return false;
        }
        for (WebElement element : elements1) {
            String text = element.getText();
            if (text.equals("")) {
                return false;
            }
            {
                stringList1.add(text);
            }
        }
        for (WebElement element : elements2) {
            String text = element.getText();
            if (text.equals("")) {
                return false;
            }
            {
                stringList2.add(text);
            }
        }
        return stringList1.size() == stringList2.size();
    }

    public int getNumberText(By elementLocator) {
        List<WebElement> elementsList = getElements(elementLocator);
        int numberResults = 0;
        for (WebElement element : elementsList) {
            String textElement = element.getText();
            String substring = textElement.substring(textElement.lastIndexOf("(") + 1, textElement.lastIndexOf(")")).trim();
            int number = Integer.parseInt(substring);
            numberResults = numberResults + number;
        }
        return numberResults;
    }

    public String typeAndGetValue(By elementLocator, String inputtedValue) {
        Log.logTypeTo(elementLocator, inputtedValue);
        getElement(elementLocator).sendKeys(inputtedValue);
        return inputtedValue;
    }
}
