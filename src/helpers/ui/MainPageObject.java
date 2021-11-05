package helpers.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitElementPresent(By locator, String erMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(erMsg);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> waitElementsPresent(By locator, String erMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(erMsg);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return new ArrayList<WebElement>(driver.findElements(locator));
    }

    public WebElement waitElementPresent(By locator, String msg) {
        return waitElementPresent(locator, msg, 5);
    }

    public WebElement waitIgnoreStaleReferent(By by, String msg) {
        int i = 0;
        while (i < 5)
            try {
                Thread.sleep(500);
                return waitElementPresent(by, msg);
            } catch (StaleElementReferenceException | InterruptedException e) {
                i++;
            }
        return waitElementPresent(by, msg);
    }

    public WebElement click(By locator, String msg) {
        WebElement element = waitElementPresent(locator, msg);
        element.click();
        return element;
    }

    public WebElement enterText(By locator, String text, String msg) {
        WebElement element = waitElementPresent(locator, msg);
        element.sendKeys(text);
        return element;
    }

    public boolean waitElementNotPresent(By locator, String msg, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(msg);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement clear(By by, String msg, long timeout) {
        WebElement element = waitElementPresent(by, msg, timeout);
        element.clear();
        return element;
    }

    public void assertElementHasText(By by, String expectedText, String msg) {
        WebElement element = waitElementPresent(by, msg);
        Assert.assertTrue(String.format("In element %s not found text %s!", by, expectedText),
                element.getAttribute("text").contains(expectedText));
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.height / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action.press(PointOption.point(x, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, endY))
                .release().perform();
    }

    public void swipeUpQuick() {
        swipeUp(150);
    }

    public void swipeUpToFindElement(By by, String msg, int maxSwipes) {
        int alredySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alredySwiped > maxSwipes) {
                waitElementPresent(by, "can't find element" + msg, 5);
                return;
            }
            swipeUpQuick();
            ++alredySwiped;
        }
    }

    public void swipeElementLeft(By by, String msg) {
        WebElement element = waitElementPresent(by, msg);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(rightX, middleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .moveTo(PointOption.point(leftX, middleY))
                .release().perform();
    }

    public int countResults(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String msg) {
        int amountOfElements = countResults(by);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + by.toString() + "' supposed be not present";
            throw new AssertionError(defaultMessage + " " + msg);
        }
    }

    public void addInReadingList(String textSearch, String describe) {
        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "search not found");
        enterText(By.xpath("//*[contains(@text, 'Search…')]"), textSearch,
                "search not found");
        click(By.xpath("//*[contains(@text, '" + describe + "')]"), "Title about " + textSearch + " not found");
        waitElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "cannot find article");
        click(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "button ':' not found");
        click(By.xpath("//*[contains(@text, 'Add to reading list')]"), "button 'Add to reading list' not found");

    }

    public void assertElementPresent(By by, String msg) {
        List<WebElement> elements = driver.findElements(by);
        Assert.assertTrue(msg, elements.size() > 0);
    }

}
