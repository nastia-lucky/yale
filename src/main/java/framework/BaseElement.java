package framework;

import framework.logger.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseElement {

    private static final int LONG_WAIT = 100;

    public void clickElement(By by) {
        Log.logClick(by);
        waitForElementToBeClickable(by);
        Browser.getDriver().findElement(by).click();
    }

    public static WebElement getElement(By by) {
        try {
            WebElement element = new WebDriverWait(Browser.getDriver(), LONG_WAIT)
                    .until(driver -> driver.findElement(by));
            return element;
        } catch (TimeoutException e) {
            throw e;
        }
    }

    public static List<WebElement> getElements(By by) {
        List<WebElement> elements = new WebDriverWait(Browser.getDriver(), LONG_WAIT)
                .until(driver -> driver.findElements(by));
        return elements;
    }

    public void typeTo(By by, String value) {
        Log.logTypeTo(by, value);
        getElement(by).sendKeys(value);
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
        System.out.println(text);
        element.click();
        if (!text.contains("/")) {
            return text;
        }
        {
            type = text.substring(0, text.lastIndexOf("/"));
            System.out.println(type);
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

    public boolean compareText(By by, By by2, String value) {
        Log.logCheckComponentInsideOtherComponentContainText(by, by2, value);
        List<WebElement> elements = getElements(by);
        WebElement firstElement = elements.get(0);
        List<WebElement> elements2 = firstElement.findElements(by2);
        WebElement element2 = elements2.get(0);
        String highlightedText = element2.getText();
        if (value.contains(highlightedText)) {
            return true;
        }
        return false;
    }

    public boolean isElementsContainsText(By by, String text) {
        Log.logCheckElementContainsText(by, text);
        List<WebElement> elements = getElements(by);
        for (WebElement element : elements) {
            String gotText = element.getText();
            if (gotText.equals(text)) {
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

    public boolean isElementContainsText(By by, String text) {
        Log.logCheckElementContainsText(by, text);
        String elementText = getElement(by).getText();
        System.out.println(elementText);
        return elementText.contains(text);
    }

    public boolean isElementsContainText(By by1, By by2, String text) {
        Log.logCheckElementsContainText(by1, by2, text);
        List<WebElement> elements1 = getElements(by1);
        if (!elements1.isEmpty()) {
            String textFromElement = elements1.get(0).getText();
            return textFromElement.contains(text);
        } else {
            String textFromElement2 = getElement(by2).getText();
            return textFromElement2.contains(text);
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
            if (elementText.equals(text2)) {
                return true;
            }
        }
        return false;
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

    public boolean isEachResultHaveElement(By by, By by2) {
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
        return new String("Such element doesn't exist");
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
        if (text.equals("")) {
            return false;
        }
        return true;
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
            if (innerElements1.isEmpty()) {
                if (innerElements2.isEmpty()) {
                    return false;
                }
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
        WebElement element = Browser.getDriver().switchTo().activeElement();
        String text = element.getText();
        String firstValue = text.substring(0, 1);
        return firstValue.equals(inputtedText);
    }

    public static void waitForInvisibility(By by, String text) {
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

    public static boolean waitForJStoLoad() {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), 30);

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) Browser.getDriver()).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) Browser.getDriver()).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public boolean isFileDownloaded(String downloadPath, String file, String text) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        String fileName = file + "." + text;
        for (int i = 0; i < dirContents.length; i++) {
            String downloadedFile = dirContents[i].getName();
            String fileWithoutChar = downloadedFile.replaceAll("_", " ");
            if (fileWithoutChar.equals(fileName)) {
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }

    public String clickAndGetTextFirstElement(By by) {
        List<WebElement> elements = getElements(by);
        WebElement element = elements.get(0);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        wait.until(ExpectedConditions.elementToBeClickable(element));
        String text = element.getText();
        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
        executor.executeScript("arguments[0].click();", element);
        return text;
    }

    public static void waitForElementDisappear(By by) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), '5');
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

}
