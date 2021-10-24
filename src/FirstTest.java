import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\javaprojects\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {

        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "search not found",
                5);

        enterText(By.xpath("//*[contains(@text, 'Search…')]"), "java",
                "search not found",
                5);

        waitElementPresent(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Can't find 'Object-oriented programming language'",
                15);
//        System.out.println("My first test");
    }

    @Test
    public void testCancelSearch() {

        click(By.id("org.wikipedia:id/search_container"), "Can't find 'search not found", 5);
        click(By.className("android.widget.ImageButton"), "can't find <-", 5);
        notFound(By.className("android.widget.ImageButton"), "can't find <-", 5);
    }

    @Test
    public void testClear() {

        click(By.id("org.wikipedia:id/search_container"), "Can't find 'search not found", 5);
        enterText(By.id("org.wikipedia:id/search_src_text"), "java",
                "search not found",
                5);
        clear(By.id("org.wikipedia:id/search_src_text"), "can't find search field", 5);
    }

    @Test
    public void testCompareArticleTitle() {
        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "search not found",
                5);

        enterText(By.xpath("//*[contains(@text, 'Search…')]"), "java",
                "search not found",
                5);
        click(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"), "Title about java not found", 5);
        WebElement title = waitElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "cannot find article", 5);
        String title_text = title.getAttribute("text");
        Assert.assertEquals("not equals", "Java (programming language)", title_text);
    }

    private WebElement waitElementPresent(By locator, String erMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(erMsg);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private WebElement waitElementPresent(By locator, String erMsg) {
        return waitElementPresent(locator, erMsg, 5);
    }

    private WebElement click(By locator, String msg, long timeout) {
        WebElement element = waitElementPresent(locator, msg, timeout);
        element.click();
        return element;
    }

    private WebElement enterText(By locator, String text, String msg, long timeout) {
        WebElement element = waitElementPresent(locator, msg, timeout);
        element.sendKeys(text);
        return element;
    }
    private boolean notFound(By locator, String msg, long timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(msg);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private WebElement clear(By by, String msg, long timeout){
        WebElement element = waitElementPresent(by, msg, timeout);
        element.clear();
        return element;
    }


}
