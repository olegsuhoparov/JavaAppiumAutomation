package helpers.ui;

import helpers.Platform;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Open navigation")
    public void openNavigation() {
        if(Platform.getInstance().isMw()){
            this.click(OPEN_NAVIGATION, "Can't find and click open navigation button");
        } else {
            System.out.println("Method openNavigation() doing nothing for platform");
        }

    }

    @Step("Click 'my lists' button")
    public void clickMyLists() {
        if(Platform.getInstance().isMw()){
            this.tryClickElementWithFewAttempts(MY_LISTS_LINK, "'My lists' not found", 9);
            return;
        }
        this.click(MY_LISTS_LINK, "'My lists' not found");
    }
}
