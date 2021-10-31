import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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
                "search not found");

        enterText(By.xpath("//*[contains(@text, 'Search…')]"), "java",
                "search not found");

        waitElementPresent(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Can't find 'Object-oriented programming language'",
                15);
//        System.out.println("My first test");
    }

    @Test
    public void testCancelSearch() {

        click(By.id("org.wikipedia:id/search_container"), "Can't find 'search not found");
        click(By.className("android.widget.ImageButton"), "can't find <-");
        notFound(By.className("android.widget.ImageButton"), "can't find <-", 5);
    }

    @Test
    public void testClear() {

        click(By.id("org.wikipedia:id/search_container"), "Can't find 'search not found");
        enterText(By.id("org.wikipedia:id/search_src_text"), "java",
                "search not found");
        clear(By.id("org.wikipedia:id/search_src_text"), "can't find search field", 5);
    }

    @Test
    public void testCompareArticleTitle() {
        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "search not found");

        enterText(By.xpath("//*[contains(@text, 'Search…')]"), "java",
                "search not found");
        click(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"), "Title about java not found");
        WebElement title = waitElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "cannot find article");
        String title_text = title.getAttribute("text");
        Assert.assertEquals("not equals", "Java (programming language)", title_text);
    }

    @Test
    public void testSwipeArticle() {
        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "search not found");

        enterText(By.xpath("//*[contains(@text, 'Search…')]"), "appium",
                "search not found");
        click(By.xpath("//*[@text='Appium']"),
                "Title about appium not found");
        swipeToElement(By.xpath("//*[@text='View page in browser']"),
                "Element in the end of article not found", 15);
    }

    @Test
    public void saveArticleToMyList(){
        String nameFolder = "learning programming";

        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "search not found");

        enterText(By.xpath("//*[contains(@text, 'Search…')]"), "java",
                "search not found");
        click(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"), "Title about java not found");
        waitElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "cannot find article");
        click(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "button ':' not found");
        click(By.xpath("//*[contains(@text, 'Add to reading list')]"), "button 'Add to reading list' not found");
        click(By.id("onboarding_button"), "button 'Got it' not found");
        clear(By.id("text_input"), "button 'Got it' not found", 5);
        enterText(By.id("text_input"), nameFolder, "can't put text in articles folder input");
        click(By.xpath("//*[@text='OK']"), "can't press 'ok'");
        click(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "'X' not found");
        click(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"), "'my lists' not found");
        click(By.xpath("//*[contains(@text, '" + nameFolder + "')]"), "can't find created folder");
        swipeElementLeft(By.xpath("//*[contains(@text, 'Java (programming language)')]"), "can't find article");
        notFound(By.xpath("//*[contains(@text, 'Java (programming language)')]"), "article wasn't delete", 5);
    }

    @Test
    public void testAmountOfNotEmptySearch(){
        String searchLine = "Linking Park discography";
        String searchResult = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "search not found");

        enterText(By.xpath("//*[contains(@text, 'Search…')]"), searchLine,
                "search not found");
        int amountOfSearchResult = countResults(By.xpath(searchResult));

        Assert.assertTrue("No one result", amountOfSearchResult > 0);
    }

    @Test
    public void testAmountOfEmptySearch(){

        String searchLine = "aweotjrk#KA";
        String emptyResultLabel = "//*[@text='No results found']";
        String searchResult = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";

        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "search not found");

        enterText(By.xpath("//*[contains(@text, 'Search…')]"), searchLine,
                "search not found");
        waitElementPresent(By.xpath(emptyResultLabel), "Can't find label 'empty' for request " + searchLine);
        assertElementNotPresent(By.xpath(searchResult), "We've found some results for request" + searchLine);

    }
    @Test
    public void testChangeScreenOrientationOnSearchResult(){
        String searchLine = "Java";

        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "search not found");

        enterText(By.xpath("//*[contains(@text, 'Search…')]"), searchLine,
                "search not found");
        click(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Title about java not found for request " + searchLine);
        String titleBeforeRotation = waitElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "can't find title topic for request " + searchLine).getAttribute("text");
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = waitElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "can't find title topic for request " + searchLine).getAttribute("text");
        Assert.assertEquals("Article title have been changed after screen rotation",
                titleBeforeRotation, titleAfterRotation);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterSecondRotation = waitElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "can't find title topic for request " + searchLine).getAttribute("text");
        Assert.assertEquals("Article title have been changed after second screen rotation",
                titleBeforeRotation, titleAfterRotation);
    }
    @Test
    public void testCheckSearchArticleBackground(){

        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "search not found");

        enterText(By.xpath("//*[contains(@text, 'Search…')]"), "java",
                "search not found");
        waitElementPresent(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Title about java not found");
        driver.runAppInBackground(Duration.ofSeconds(3));
        waitElementPresent(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"),
                "Title about java after returning from background not found");
    }



    @Test
    public void checkTextInSearch(){
        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search field not found");
        assertElementHasText(By.id("org.wikipedia:id/search_src_text"),
                "Search",
                "Search field not found");
    }

    @Test
    public void checkSomeArticlesForWord() {
        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search field not found");
        enterText(By.xpath("//*[contains(@text, 'Search…')]"), "java",
                "search not found");
        int numberArticles = waitElementsPresent(By.id("org.wikipedia:id/page_list_item_title"),
                "No one article were found", 5).size();
        Assert.assertTrue(String.format("There were found %s articles", numberArticles),
                numberArticles > 1);
    }

    @Test
    public void javaInEverySearchResult() {
        click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search field not found");
        enterText(By.xpath("//*[contains(@text, 'Search…')]"), "java",
                "search not found");;
        Assert.assertTrue("Not all results contains word 'java'",
                waitElementsPresent(By.id("org.wikipedia:id/page_list_item_title"),
                "No one article were found", 5)
                .stream().allMatch((e) -> e.getAttribute("text").toLowerCase().contains("java")));
        // lowercase использую для приведения строковой выдачи к единому формату в сравнении, на мой взгляд тут это не критично
    }

    private WebElement waitElementPresent(By locator, String erMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(erMsg);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private List<WebElement> waitElementsPresent(By locator, String erMsg, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(erMsg);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return new ArrayList<WebElement>(driver.findElements(locator));
    }

    private WebElement waitElementPresent(By locator, String erMsg) {
        return waitElementPresent(locator, erMsg, 5);
    }

    private WebElement click(By locator, String msg) {
        WebElement element = waitElementPresent(locator, msg);
        element.click();
        return element;
    }

    private WebElement enterText(By locator, String text, String msg) {
        WebElement element = waitElementPresent(locator, msg);
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

    private void assertElementHasText(By by, String expectedText, String msg){
        WebElement element = waitElementPresent(by, msg);
        Assert.assertTrue(String.format("In element %s not found text %s!", by, expectedText),
                element.getAttribute("text").contains(expectedText));
    }

    protected void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.height/2;
        int startY = (int)(size.height * 0.8);
        int endY = (int)(size.height * 0.2);
        action.press(PointOption.point(x, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, endY))
                .release().perform();
    }

    protected void swipeUpQuick(){
        swipeUp(150);
    }

    protected void swipeToElement(By by, String msg, int maxSwipes){
        int alredySwiped = 0;
        while (driver.findElements(by).size() == 0){
            if(alredySwiped > maxSwipes){
                waitElementPresent(by, "can't find element" + msg, 5);
                return;
            }
            swipeUpQuick();
            ++alredySwiped;
        }
    }

    protected void swipeElementLeft(By by, String msg){
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

    protected int countResults(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }

    protected void assertElementNotPresent(By by, String msg){
        int amountOfElements = countResults(by);
        if (amountOfElements > 0){
            String defaultMessage = "An element '" + by.toString() + "' supposed be not present";
            throw new AssertionError(defaultMessage + " " + msg);
        }
    }

}
