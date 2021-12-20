package helpers.ui;

import helpers.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitElementPresent(String locator, String erMsg, long timeoutSec) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(erMsg);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> waitElementsPresent(String locator, String erMsg, long timeoutSec) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(erMsg);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return new ArrayList<WebElement>(driver.findElements(by));
    }

    public WebElement waitElementPresent(String locator, String msg) {
        return waitElementPresent(locator, msg, 6);
    }

    public WebElement waitIgnoreStaleReferent(String locator, String msg) {
        int i = 0;
        while (i < 5)
            try {
                Thread.sleep(500);
                return waitElementPresent(locator, msg);
            } catch (StaleElementReferenceException | InterruptedException e) {
                i++;
            }
        return waitElementPresent(locator, msg);
    }

    public WebElement click(String locator, String msg) {
        WebElement element = waitElementPresent(locator, msg);
        element.click();
        return element;
    }

    public WebElement enterText(String locator, String text, String msg) {
        WebElement element = waitElementPresent(locator, msg);
        element.sendKeys(text);
        return element;
    }

    public boolean waitElementNotPresent(String locator, String msg, long timeout) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(msg);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement clear(String by, String msg, long timeout) {
        WebElement element = waitElementPresent(by, msg, timeout);
        element.clear();
        return element;
    }

    public void assertElementHasText(String locator, String expectedText, String msg) {
        WebElement element = waitElementPresent(locator, msg);
        Assert.assertTrue(element.getAttribute("text").contains(expectedText),
                String.format("In element %s not found text %s!", locator, expectedText));
    }

    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.height / 2;
            int startY = (int) (size.height * 0.8);
            int endY = (int) (size.height * 0.2);
            action.press(PointOption.point(x, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, endY))
                    .release().perform();
        } else {
            System.out.println("Method swipeUp() doing nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpQuick() {
        swipeUp(225);
    }

    public void swipeUpToFindElement(String locator, String msg, int maxSwipes) {
        By by = this.getLocatorByString(locator);
        int alredySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alredySwiped > maxSwipes) {
                waitElementPresent(locator, "Can't find element" + msg, 5);
                return;
            }
            swipeUpQuick();
            ++alredySwiped;
        }
    }

    public void swipeElementLeft(String locator, String msg) {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitElementPresent(locator, msg);
            int leftX = element.getLocation().getX();
            int rightX = leftX + element.getSize().getWidth();
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();
            int middleY = (upperY + lowerY) / 2;
            TouchAction action = new TouchAction((AppiumDriver) driver);

            action.press(PointOption.point(rightX, middleY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));

            if (Platform.getInstance().isAndroid()) {
                action.moveTo(PointOption.point(leftX, middleY));
            } else {
                int offsetX = (-1 * element.getSize().getWidth());
                action.moveTo(PointOption.point(offsetX, 0));
            }
            action.release().perform();
        } else {
            System.out.println("Method swipeUp() doing nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public int countResults(String locator) {
        By by = this.getLocatorByString(locator);
        try { // добавил для ожидания первичного рендера элементов, практически никак не влияет на скоросто т.к. expected_condition так же под капотом ждут 0,5 сек
            Thread.sleep(900);
        } catch (Exception e) {
            System.out.println("DANGER! WAIT BROKEN SYSTEM!!");
        }
        List elements = driver.findElements(by);
        return elements.size();
    }

    public boolean isElementPresent(String locator) {
        return countResults(locator) > 0;
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int elementLocationByY = this.waitElementPresent(locator, "Can't find element by locator").getLocation().getY();
        if (Platform.getInstance().isMw()) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            Object jsResult = jsExecutor.executeScript("return window.pageYOffset");
            elementLocationByY -= Integer.parseInt(jsResult.toString());
        }
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }

    public void swipeUpTillElementAppear(String locator, String msg, int maxSwipes) {
        int alreadySwiped = 0;
        while (this.isElementLocatedOnTheScreen(locator)) {
            if (alreadySwiped > maxSwipes) {
                Assert.assertTrue(this.isElementLocatedOnTheScreen(locator), msg);
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    private By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        if (byType.equals("xpath")) {
            return By.xpath(locator);
        } else if (byType.equals("id")) {
            return By.id(locator);
        } else if (byType.equals("css")) {
            return By.cssSelector(locator);
        } else throw new IllegalArgumentException("Can't get type of locator - " + locatorWithType);
    }

    public void clickElementToTheRightUpperCorner(String locator, String msg) {
        if (driver instanceof AppiumDriver) {
            WebElement element = this.waitElementPresent(locator + "/..", msg);
            int rightX = element.getLocation().getX();
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();
            int width = element.getSize().getWidth();
            int middleY = (upperY + lowerY) / 2;

            int pointToClickX = (rightX + width) - 3;
            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.tap(PointOption.point(pointToClickX, middleY)).perform();
        } else {
            System.out.println("Method swipeUp() doing nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageApp() {
        if (Platform.getInstance().isMw()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageApp() doing nothing for platform" + Platform.getInstance().getPlatformVar());

        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String msg, int maxSwipes) {
        int alreadySwiped = 0;
        WebElement element = this.waitElementPresent(locator, msg);
        while (this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageApp();
            if (alreadySwiped < maxSwipes) {
                Assert.assertTrue(element.isDisplayed(), msg);
            }
        }
    }

    public void tryClickElementWithFewAttempts(String locator, String msg, int attempts) {
        int currentAttempts = 0;
        boolean needMoreAttempts = true;
        while (needMoreAttempts) {
            try {
                this.waitElementPresent(locator, msg, 1).click();
                needMoreAttempts = false;
            } catch (Exception e) {
                if (currentAttempts > attempts) {
                    this.click(locator, msg);
                }
            }
            ++currentAttempts;
        }
    }

    public String takeScreenshot() {
        TakesScreenshot ts = (TakesScreenshot) this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + "name" + "_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken: " + path);
        } catch (Exception e) {
            System.out.println("Can't take screenshot. Error: " + e.getMessage());
        }
        return path;
    }

    @Attachment
    public static byte[] screenshot(String path) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (Exception e) {
            System.out.println("Can't get bytes from screenshot. Error " + e.getMessage());
        }
        return bytes;
    }
}
