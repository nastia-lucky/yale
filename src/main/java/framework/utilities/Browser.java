package framework.utilities;


import framework.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Browser {

    public static String browserType;
    private static WebDriver driver;
    private static Browser browser;
    private static final int LONG_WAIT = 100;
    Config config = new Config();

    private Browser() {
        System.setProperty("webdriver.chrome.driver", config.getProperty("driverPath"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    public static Browser getInstance() {
        if (browser == null) {
            browser = new Browser();
        }
        return browser;
    }

    public void getUrl(String url) {
        Log.logGetUrl(url);
        driver.get(url);
    }

    public void clickElement(By by) {
        Log.logClick(by);
        driver.findElement(by).click();
    }

    public void typeTo(By by, String value) {
        Log.logTypeTo(by, value);
        driver.findElement(by).sendKeys(value);
    }

    public boolean isElementDisplayed(By by) {
        Log.logTheElementIsDisplayed(by);
        return driver.findElement(by).isDisplayed();
    }

    public String getTextFirstElementFromArray(By by) {
        Log.logTheText(by);
        List<WebElement> elements = driver.findElements(by);
        String text = elements.get(0).getText();
        return text;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void closeBrowser() {
        driver.quit();
        browser = null;
    }

    public static void waitForElementToBeClickable(By by) {
        Log.logWaitForElementToBeClickable(by);
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForTheFirstElementFromArrayIsClickable(By by) {
        Log.logWaitForElementToBeClickable(by);
        List<WebElement> elements = driver.findElements(by);
        WebElement element = elements.get(0);
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBePresent(By by) {
        Log.logWaitForElementToBeDisplayed(by);
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementsFromArrayArePresent(By by) {
        Log.logWaitForElementsArePresent(by);
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), LONG_WAIT);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void switchToNewTab() {
        Log.logInfo("Switching to the new tab");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public void clickFirstElementFromArray(By by) {
        Log.logClick(by);
        List<WebElement> elementsList = driver.findElements(by);
        elementsList.get(0).click();
    }

    public String getAttribute(By by, String name) {
        Log.logGetAttribute(by, name);
        WebElement element = driver.findElement(by);
        return element.getAttribute(name);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void hoverOnElement(By by) {
        Actions action = new Actions(driver);
        List<WebElement> element = driver.findElements(by);
        action.moveToElement(element.get(0)).build().perform();
    }

    public void clickBackButton() {
        Log.logInfo("Click Back Button");
        driver.navigate().back();
    }

    public boolean isElementsDisplayed(By by) {
        return driver.findElements(by).get(0).isDisplayed();
    }

    public boolean checkAllElementsFromArray(By by, By by2) {
        Log.logCheckAllElementContainsOtherElement(by, by2);
        List<WebElement> elements = driver.findElements(by);
        for (WebElement element : elements) {
            if (!element.findElement(by2).isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public String getText(By by) {
        Log.logTheText(by);
        String text = driver.findElement(by).getText();
        return text;
    }

    public boolean isArrayEmpty(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.isEmpty();
    }

    public String getTitle() {
        String title = driver.getTitle();
        return title;
    }

    public boolean compareText(By by, By by2, String value) {
        Log.logCheckComponentInsideOtherComponentContainText(by, by2, value);
        List<WebElement> elements = driver.findElements(by);
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
        List<WebElement> elements = driver.findElements(by);
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
        List<WebElement> elements = driver.findElements(by);
        WebElement element = elements.get(elements.size() - 2);
        String attribute = element.getAttribute(value);
        String[] stringArray = attribute.split(" ");
        String stringNumberPages = stringArray[1];
        int numberPages = Integer.parseInt(stringNumberPages);
        return numberPages;
    }

    public String clickFirstElementFromArrayAndGetTitleText(By by) {
        Log.logClick(by);
        List<WebElement> elementsList = driver.findElements(by);
        WebElement element = elementsList.get(0);
        String textElement = element.getText();
        String substring = textElement.substring(0, textElement.lastIndexOf("(")).trim();
        element.click();
        return substring;
    }

    public boolean isElementContainsText(By by, String text) {
        Log.logCheckElementContainsText(by, text);
        String elementText = driver.findElement(by).getText();
        return elementText.contains(text);
    }

    public boolean isElementsContainText(By by1, By by2, String text) {
        Log.logCheckElementsContainText(by1, by2, text);
        List<WebElement> elements1 = driver.findElements(by1);
        if (!elements1.isEmpty()) {
            String textFromElement = elements1.get(0).getText();
            return textFromElement.contains(text);
        } else {
            String textFromElement2 = driver.findElement(by2).getText();
            return textFromElement2.contains(text);
        }
    }

    public boolean isTheSecondElementContainsOneOfTheTexts(By by, String text, String text2) {
        Log.logCheckTheSecondElementContainsOneOfTheTexts(by, text, text2);
        List<WebElement> elements = driver.findElements(by);
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
        List<WebElement> elements = driver.findElements(by);
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
        List<WebElement> elements = driver.findElements(by);
        WebElement firstElement = elements.get(0);
        WebElement innerElement = firstElement.findElement(by2);
        String innerElementText = innerElement.getText();
        innerElement.click();
        return innerElementText;
    }

    public int clickFirstElementFromArrayAndGetNumberText(By by) {
        Log.logClick(by);
        List<WebElement> elementsList = driver.findElements(by);
        WebElement element = elementsList.get(0);
        String textElement = element.getText();
        String substring = textElement.substring(textElement.lastIndexOf("(") + 1, textElement.lastIndexOf(")")).trim();
        int number = Integer.parseInt(substring);
        elementsList.get(0).click();
        return number;
    }

    public void clickTheSecondElementFromArray(By by) {
        Log.logClick(by);
        List<WebElement> elements = driver.findElements(by);
        elements.get(1).click();
    }

    public boolean isEachResultHaveElement(By by, By by2) {
        Log.logCheckAllElementContainsOtherElement(by, by2);
        List<WebElement> elements = driver.findElements(by);
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
        List<WebElement> elements = driver.findElements(by);
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
        List<WebElement> elements = driver.findElements(by);
        String firstElementText = elements.get(0).getText();
        return firstElementText;
    }

    public boolean isAllElementsContainTexts(By by, By by2, String text, String text2) {
        Log.logCheckAllElementsContainTexts(by, by2, text, text2);
        List<WebElement> elements = driver.findElements(by);
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
        List<WebElement> elements = driver.findElements(by1);
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
        List<WebElement> elements = driver.findElements(by1);
        for (WebElement element : elements) {
            List<WebElement> innerElements = element.findElements(by2);
            if (innerElements.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
