package helpers;

import helpers.ui.WelcomePO;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase{

    protected RemoteWebDriver driver;

    @Before
    @Step("Start driver and session")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.openWikiWebPageForMobilePlatform();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown(){
        driver.quit();
    }

    @Step("Rotate Screen Portrait")
    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() doing nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Rotate Screen Landscape")
    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape() doing nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Background Up")
    protected void backgroundUp(int sec) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(sec));
        } else {
            System.out.println("Method BackgroundUp() doing nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip Welcome Page For IOS App")
    private void skipWelcomePageForIOSApp() {
        if (Platform.getInstance().isIOS()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePO welcomePO = new WelcomePO(driver);
            welcomePO.clickSkip();
        }
    }

    @Step("Open Wiki Web Page For Mobile Platform")
    protected void openWikiWebPageForMobilePlatform() {
        if (Platform.getInstance().isMw()) {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobilePlatform() doing nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    private void createAllurePropertyFile(){
        String path = System.getProperty("allure.results.directory");
        try{
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Enviroment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://github.com/allure-framework/allure-app/wiki/Environment");
            fos.close();
        }catch (Exception e){
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }

}
