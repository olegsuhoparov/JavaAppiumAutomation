package helpers.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePO extends MainPageObject {
    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
            NEXT_LINK = "id:Next",
            GET_STARTED_BUTTON = "id:Get started",
    SKIP = "id:Skip";

    public WelcomePO(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for link 'learn more'")
    public void waitForLearnMoreLink() {
        this.waitElementPresent(STEP_LEARN_MORE_LINK, "Can't find 'learn more about wikipedia' link");
    }

    @Step("Waiting for 'New way to explore' text")
    public void waitForNewWayToExploreText() {
        this.waitElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Can't find 'New ways to explore' link");
    }

    @Step("Waiting for 'Add or edit new way to explore' text")
    public void waitForAddOrEditNewWayToExploreText() {
        this.waitElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "Can't find 'Add or edit preferred languages' link");
    }

    @Step("Waiting for 'Learn more about data collected' text")
    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Can't find 'Learn more about data collected' link");
    }

    @Step("Click button 'Next'")
    public void clickNextButton() {
        this.click(NEXT_LINK, "'Next' link not found for click");
    }

    @Step("Click button 'Get started'")
    public void clickGetStartedButton() {
        this.click(GET_STARTED_BUTTON, "'Get started' link not found");
    }

    @Step("Click button 'Skip'")
    public void clickSkip(){
        this.click(SKIP, "button 'Skip' not found for click");
    }

}
