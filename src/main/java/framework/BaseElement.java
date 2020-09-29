package framework;

import framework.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

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
            //WebElement element = Browser.getDriver().findElement(by);
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
        String text = elements.get(0).getText();
        return text;
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

    public static void waitForElementToBePresent(By by) {
        Log.logWaitForElementToBeDisplayed(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementsFromArrayArePresent(By by) {
        Log.logWaitForElementsArePresent(by);
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void clickFirstElementFromArray(By by) {
        Log.logClick(by);
        List<WebElement> elementsList = getElements(by);
        elementsList.get(0).click();
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

    public String getText(By by) {
        Log.logTheText(by);
        String text = getElement(by).getText();
        return text;
    }

    public boolean isArrayEmpty(By by) {
        List<WebElement> elements = getElements(by);
        return elements.isEmpty();
    }

    public String getTitle() {
        String title = Browser.getDriver().getTitle();
        return title;
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
        int numberPages = Integer.parseInt(stringNumberPages);
        return numberPages;
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
        String firstElementText = elements.get(0).getText();
        System.out.println(firstElementText);
        return firstElementText;
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
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            List<WebElement> innerElements = element.findElements(by2);
            List<WebElement> clickableElements = element.findElements(by3);
            if (innerElements.isEmpty()) {
                WebElement element1 = clickableElements.get(0);
                String text = element1.getText();
                element1.click();
                return text;
            }

        }
        return new String("Such element doesn't exist");
    }

    public boolean checkPresenceElementInsideOtherElement(By by1, By by2) {
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
        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickTheFirstElementWithJE(By by) {
        List<WebElement> elements = getElements(by);
        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
        executor.executeScript("arguments[0].click();", elements.get(0));
    }

    public boolean checkTheElementContainText(By by) {
        WebElement element = getElement(by);
        String text = element.getText();
        if (text.equals("")) {
            return false;
        }
        return true;
    }

    public boolean checkThatEachElementInArrayContainText(By by) {
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
        element.click();
        return text;
    }

    public boolean isChosenValueCoincidesWithTheFirstLetterOnFocus(String inputtedText) {
        WebElement element = Browser.getDriver().switchTo().activeElement();
        String text = element.getText();
        String firstValue = text.substring(0, 1);
        return firstValue.equals(inputtedText);
    }
}
