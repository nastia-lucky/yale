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

public class BaseElement implements WebElement {

    protected WebElement webElement;
    private By locator;

    public By getLocator() {
        return locator;
    }

    public BaseElement(By by) {
        //this.locator = by;
        this.webElement = getElement(by);
    }

    public BaseElement init() {
        if (webElement == null) {
            this.webElement = getElement(locator);
        }
        return this;
    }

    private static final int LONG_WAIT = 100;

    public void clickElement(By elementLocator) {
        Log.logClick(elementLocator);
        waitForElementToBeClickable(elementLocator);
        Browser.getDriver().findElement(elementLocator).click();

    }

    public static WebElement getElement(By by) {
        Log.logGetElement(by);
        try {
            return new WebDriverWait(Browser.getDriver(), LONG_WAIT)
                    .until(driver -> driver.findElement(by));
        } catch (TimeoutException e) {
            Log.logInfo("I can't find this element " + by);
            throw e;
        }
    }

    public static List<WebElement> getElements(By by) {
        Log.logGetElements(by);
        return new WebDriverWait(Browser.getDriver(), LONG_WAIT)
                .until(driver -> driver.findElements(by));
    }

    public void typeTo(By by, String inputtedValue) {
        Log.logTypeTo(by, inputtedValue);
        getElement(by).sendKeys(inputtedValue);
    }

    public boolean isElementDisplayed(By by) {
        Log.logTheElementIsDisplayed(by);
        return getElement(by).isDisplayed();
    }

    public String getTextFirstElementFromArray(By by) {
        Log.logTheText(by);
        List<WebElement> elements = getElements(by);
        return elements.get(0).getText();
    }

    public static void waitForElementToBeClickable(By by) {
        Log.logWaitForElementToBeClickable(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementToBeClickable2(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForTheFirstElementFromArrayIsClickable(By by) {
        Log.logWaitForElementToBeClickable(by);
        List<WebElement> elements = getElements(by);
        WebElement element = elements.get(0);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickFirstElementFromArray(By by) {
        Log.logClick(by);
        List<WebElement> elementsList = getElements(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        WebElement element = elementsList.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public String clickFirstElementAndGetFilterType(By by) {
        Log.logClick(by);
        List<WebElement> elementsList = getElements(by);
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

    public String getAttribute(By by, String name) {
        Log.logGetAttribute(by, name);
        WebElement element = getElement(by);
        return element.getAttribute(name);
    }

    public void hoverOnElement(By by) {
        Actions action = new Actions(Browser.getDriver());
        List<WebElement> element = getElements(by);
        action.moveToElement(element.get(0)).build().perform();
    }

    public boolean isElementsDisplayed(By by) {
        return getElements(by).get(0).isDisplayed();
    }

    public boolean checkAllElementsFromArray(By by, By by2) {
        Log.logCheckAllElementContainsOtherElement(by, by2);
        List<WebElement> elements = getElements(by);
        for (WebElement element : elements) {
            if (!element.findElement(by2).isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public static String getText(By by) {
        Log.logTheText(by);
        return getElement(by).getText();

    }

    public boolean isArrayEmpty(By by) {
        List<WebElement> elements = getElements(by);
        return elements.isEmpty();
    }

    public String getTitle() {
        return Browser.getDriver().getTitle();
    }

    public boolean compareText(By by, By by2, String inputtedValue) {
        Log.logCheckComponentInsideOtherComponentContainText(by, by2, inputtedValue);
        List<WebElement> elements = getElements(by);
        WebElement firstElement = elements.get(0);
        List<WebElement> elements2 = firstElement.findElements(by2);
        WebElement element2 = elements2.get(0);
        String highlightedText = element2.getText();
        return inputtedValue.contains(highlightedText);
    }

    public boolean isElementsContainsText(By by, String inputtedValue) {
        Log.logCheckElementContainsText(by, inputtedValue);
        List<WebElement> elements = getElements(by);
        for (WebElement element : elements) {
            String gotText = element.getText();
            if (gotText.equals(inputtedValue)) {
                return true;
            }
        }
        return false;
    }

    public int getNumber(By by, String value) {
        Log.logGetNumberFromElementByAttribute(by, value);
        List<WebElement> elements = getElements(by);
        WebElement element = elements.get(elements.size() - 2);
        String attribute = element.getAttribute(value);
        String[] stringArray = attribute.split(" ");
        String stringNumberPages = stringArray[1];
        return Integer.parseInt(stringNumberPages);
    }

    public String clickFirstElementFromArrayAndGetTitleText(By by) {
        Log.logClick(by);
        List<WebElement> elementsList = getElements(by);
        WebElement element = elementsList.get(0);
        String textElement = element.getText();
        String substring = textElement.substring(0, textElement.lastIndexOf("(")).trim();
        element.click();
        return substring;
    }

    public boolean isElementContainsText(By by, String inputtedValue) {
        Log.logCheckElementContainsText(by, inputtedValue);
        String elementText = getElement(by).getText();
        return elementText.contains(inputtedValue);
    }

    public boolean isElementsContainText(By by1, By by2, String inputtedValue) {
        Log.logCheckElementsContainText(by1, by2, inputtedValue);
        List<WebElement> elements1 = getElements(by1);
        if (!elements1.isEmpty()) {
            String textFromElement = elements1.get(0).getText();
            return textFromElement.contains(inputtedValue);
        } else {
            String textFromElement2 = getElement(by2).getText();
            return textFromElement2.contains(inputtedValue);
        }
    }

    public boolean isTheSecondElementContainsOneOfTheTexts(By by, String text, String text2) {
        Log.logCheckTheSecondElementContainsOneOfTheTexts(by, text, text2);
        List<WebElement> elements = getElements(by);
        String elementText = elements.get(1).getText();
        if (elementText.contains(text)) {
            return true;
        }
        {
            return elementText.equals(text2);
        }
    }

    public boolean isAllElementsInArrayContainsText(By by, String text) {
        Log.logCheckElementContainsText(by, text);
        List<WebElement> elements = getElements(by);
        for (WebElement element : elements) {
            String elementText = element.getText();
            if (!elementText.equals(text)) {
                return false;
            }
        }
        return true;
    }

    public String getTextFromElementInsideOtherElement(By by, By by2) {
        Log.logGetTextFromElementInsideOtherElement(by, by2);
        List<WebElement> elements = getElements(by);
        WebElement firstElement = elements.get(0);
        WebElement innerElement = firstElement.findElement(by2);
        String innerElementText = innerElement.getText();
        innerElement.click();
        return innerElementText;
    }

    public int clickFirstElementFromArrayAndGetNumberText(By by) {
        Log.logClick(by);
        List<WebElement> elementsList = getElements(by);
        WebElement element = elementsList.get(0);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        wait.until(ExpectedConditions.elementToBeClickable(element));
        String textElement = element.getText();
        String substring = textElement.substring(textElement.lastIndexOf("(") + 1, textElement.lastIndexOf(")")).trim();
        int number = Integer.parseInt(substring);
        elementsList.get(0).click();
        return number;
    }

    public void clickTheSecondElementFromArray(By by) {
        Log.logClick(by);
        List<WebElement> elements = getElements(by);
        elements.get(1).click();
    }

    public boolean isEachElementHaveElementWithText(By by, By by2) {
        Log.logCheckAllElementContainsOtherElement(by, by2);
        List<WebElement> elements = getElements(by);
        List<String> textsList = new ArrayList<>();
        int sizeResults = elements.size();
        for (WebElement element : elements) {
            List<WebElement> innerElements = element.findElements(by2);
            if (!innerElements.isEmpty()) {
                String text = innerElements.get(0).getText();
                if (!text.equals("")) {
                    textsList.add(text);
                }
            }
        }
        return sizeResults == textsList.size();
    }

    public boolean isAllElementsContainText(By by, By by2, String text) {
        Log.logCheckElementsContainText(by, by2, text);
        List<WebElement> elements = getElements(by);
        int sizeResults = elements.size();
        List<String> stringList = new ArrayList<>();
        for (WebElement element : elements) {
            List<WebElement> innerElements = element.findElements(by2);
            if (!innerElements.isEmpty()) {
                String elementText = innerElements.get(0).getText();
                if (elementText.equals(text)) {
                    stringList.add(elementText);
                }
            }
        }
        return sizeResults == stringList.size();
    }

    public String getTextFromFirstElement(By by) {
        Log.logGetTextFromFirstElement(by);
        List<WebElement> elements = getElements(by);
        return elements.get(0).getText();
    }

    public boolean isAllElementsContainTexts(By by, By by2, String text, String text2) {
        Log.logCheckAllElementsContainTexts(by, by2, text, text2);
        List<WebElement> elements = getElements(by);
        int searchNumbers = elements.size();
        List<String> stringList = new ArrayList<>();
        for (WebElement element : elements) {
            List<WebElement> innerElements = element.findElements(by2);
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

    public String checkAndOpenElementNotContainAnother(By by1, By by2, By by3) {
        Log.logCheckAndOpenElementNotContainAnother(by1, by2, by3);
        List<WebElement> elements = getElements(by1);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            List<WebElement> innerElements = element.findElements(by2);
            List<WebElement> clickableElements = element.findElements(by3);
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

    public boolean checkPresenceElementInsideOtherElement(By by1, By by2) {
        Log.logCheckPresenceElementInsideOtherElement(by1, by2);
        List<WebElement> elements = getElements(by1);
        for (WebElement element : elements) {
            List<WebElement> innerElements = element.findElements(by2);
            if (innerElements.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void clickWithJS(By by) {
        Log.logClick(by);
        WebElement element = getElement(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        wait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickTheFirstElementWithJS(By by) {
        Log.logClick(by);
        List<WebElement> elements = getElements(by);
        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
        executor.executeScript("arguments[0].click();", elements.get(0));
    }

    public boolean checkTheElementContainText(By by) {
        Log.logCheckTheElementContainsText(by);
        WebElement element = getElement(by);
        String text = element.getText();
        return !text.equals("");
    }

    public boolean checkThatEachElementInArrayContainText(By by) {
        Log.logCheckThatEachElementInArrayContainText(by);
        List<WebElement> elements = getElements(by);
        for (WebElement element : elements) {
            String text = element.getText();
            if (text.equals("")) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPresenceOneOfTwoElementInsideAnother(By by, By by2, By by3) {
        Log.logСheckPresenceOneOfTwoElementInsideAnother(by, by2, by3);
        List<WebElement> elements = getElements(by);
        for (WebElement element : elements) {
            List<WebElement> innerElements1 = element.findElements(by2);
            List<WebElement> innerElements2 = element.findElements(by3);
            if (innerElements1.isEmpty() && innerElements2.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public String chooseValueFromArrayAndGetText(By by) {
        List<WebElement> elements = getElements(by);
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

    public static void waitForInvisibility(By by, String text) {
        Log.logWaitForInvisibilityOfText(by, text);
        Wait<WebDriver> wait = new FluentWait(Browser.getDriver())
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.invisibilityOfElementWithText(by, text));
    }

    public static void waitForText(By by, String text) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), 30);
        wait.until(ExpectedConditions.textToBe(by, text));
    }

    public boolean isFileDownloaded(String fileName) {
        Path filePath = Browser.getInstance().getDownloadPath().resolve(fileName);
        return Files.exists(filePath);
    }

    public String clickAndGetTextFirstElement(By by, String text) {
        Log.logClick(by);
        List<WebElement> elements = getElements(by);
        WebElement element = elements.get(0);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        wait.until(ExpectedConditions.elementToBeClickable(element));
        String textOfElement = element.getAttribute(text);
        String fileName = textOfElement.substring(textOfElement.lastIndexOf("/") + 1);
        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
        executor.executeScript("arguments[0].click();", element);
        try {
            return URLDecoder.decode(fileName, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return fileName;
        }
    }

    public static void waitForElementDisappear(By by) {
        Log.logWaitForElementDisappear(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
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

    public boolean isAmountOfElementsCoincideWithExpected(By by, By by2) {
        Log.logIsAmountOfElementsCoincideWithExpected(by, by2);
        List<WebElement> parentElements = getElements(by);
        int expectedAmount = parentElements.size() + 1;
        List<WebElement> childElements = getElements(by2);
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

    public LocalDate getDate(By by, String attribute) {
        Log.logGetDate(by);
        List<WebElement> elements = getElements(by);
        WebElement element = elements.get(0);
        String stringDate = element.getAttribute(attribute);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
        LocalDate date = LocalDate.parse(stringDate, dateTimeFormatter);
        element.click();
        return date;
    }

    public LocalDate getSearchResultDate(By by, By by2, By by3) {
        Log.logGetSearchResultDate(by2, by3);
        List<WebElement> elements = getElements(by);
        WebElement firstElement = elements.get(0);
        String dateMonth = firstElement.findElement(by2).getText();
        String stringDate = firstElement.findElement(by3).getText();
        int length = stringDate.length();
        if (length == 1) {
            stringDate = "0" + stringDate;
        }
        String fullStringDate = stringDate + " " + dateMonth;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return LocalDate.parse(fullStringDate, dateTimeFormatter);
    }

    public boolean checkPresenceElementInsideAnotherElement(By by1, By by2, By by3) {
        Log.logCheckPresenceElementInsideAnotherElement(by1, by2, by3);
        List<WebElement> parentElements = getElements(by1);
        for (WebElement element : parentElements) {
            List<WebElement> innerElements = element.findElements(by2);
            if (!innerElements.isEmpty()) {
                List<WebElement> childElements = element.findElements(by3);
                if (childElements.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public LocalDate getDate(By by1, By by2, int i) {
        Log.logGetDateFromElementInsideOtherElement(by1, by2);
        WebElement parentElement = getElement(by1);
        List<WebElement> childElements = parentElement.findElements(by2);
        String dateString = childElements.get(i - 1).getText();
        String[] stringArray = dateString.split(",");
        String firstString = stringArray[0];
        String secondString = stringArray[1];
        String dateUpdated = firstString + secondString;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
        return LocalDate.parse(dateUpdated, dateTimeFormatter);
    }

    public void clickElementContainAnother(By by1, By by2) {
        List<WebElement> elements = getElements(by1);
        for (WebElement element : elements) {
            List<WebElement> innerElements = element.findElements(by2);
            if (!innerElements.isEmpty()) {
                WebElement element1 = innerElements.get(0);
                //WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
                //wait.until(ExpectedConditions.elementToBeClickable(element1));
                element1.click();
            }
        }
    }

    public boolean checkElementInsideOtherContainText(By by, By by1) {
        Log.logCheckElementInsideOtherContainText(by, by1);
        List<WebElement> parentElements = getElements(by);
        for (WebElement element : parentElements) {
            List<String> stringsList1 = new ArrayList<>();
            WebElement childElement1 = element.findElement(by1);
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

    public boolean isCountOfElementsCorrect(By by, int expectedCount) {
        Log.logIsCountOfElementsCorrect(by, expectedCount);
        List<WebElement> elements = getElements(by);
        return elements.size() == expectedCount;
    }

    public int getHeight(By by) {
        WebElement element = getElement(by);
        return element.getSize().height;
    }

    public boolean checkOneOfTwoArraysIsNotEmpty(By by1, By by2) {
        List<WebElement> elements = getElements(by1);
        List<WebElement> elements2 = getElements(by2);
        if (elements.isEmpty() && elements2.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isAllElementsFromOneOfTheArrayContainText(By by1, By by2) {
        List<WebElement> elements = getElements(by1);
        List<WebElement> elements2 = getElements(by2);
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

    public boolean isAllElementsInArrayContainText(By by) {
        List<WebElement> elements = getElements(by);
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

    public boolean isArrayNotEmpty(By by) {
        List<WebElement> elements = getElements(by);
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

    public boolean isAmountOfElementsCoincide(By by1, By by2) {
        List<WebElement> elements1 = getElements(by1);
        List<WebElement> elements2 = getElements(by2);
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
        if (stringList1.size() != stringList2.size()) {
            return false;
        }
        return true;
    }

    public int getNumberText(By by) {
        List<WebElement> elementsList = getElements(by);
        int numberResults = 0;
        for (WebElement element : elementsList) {
            String textElement = element.getText();
            String substring = textElement.substring(textElement.lastIndexOf("(") + 1, textElement.lastIndexOf(")")).trim();
            int number = Integer.parseInt(substring);
            numberResults = numberResults + number;
        }
        return numberResults;
    }

    @Override
    public void click() {
        waitForElementToBeClickable2(webElement);
        webElement.click();
    }

    @Override
    public void submit() {

    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        webElement.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        webElement.clear();
    }

    @Override
    public String getTagName() {
        return webElement.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return webElement.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return Browser.getDriver().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return Browser.getDriver().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Dimension getSize() {
        return webElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String propertyName) {
        return null;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }
}
