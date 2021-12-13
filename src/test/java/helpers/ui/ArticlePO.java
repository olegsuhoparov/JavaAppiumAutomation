package helpers.ui;

import helpers.Platform;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;


public abstract class ArticlePO extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            ADD_TO_MY_LIST,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            REMOVE_FROM_MY_LIST_BUTTON,
            CURRENT_LIST_ICON,
            CLOSE;

    public ArticlePO(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for title on article page")
    public WebElement waitForTitleElement() {
        return this.click(TITLE, "Can't find article on page!");
    }

    @Step("getting article title")
    public String getArticleTitle() {
        if (Platform.getInstance().isAndroid()) {
            return waitForTitleElement().getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return waitForTitleElement().getAttribute("name");
        } else {
            return waitForTitleElement().getText();
        }

    }

    @Step("Swiping to footer article page")
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Can't find the end of article", 95);
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Can't find the end of article", 50);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Can't find the end of article", 50);
        }
    }

    @Step("Adding article to my list")
    public void addArticleToMyList(String nameOfFolder) {
        this.click(OPTIONS_BUTTON, "button ':' not found");
        this.click(ADD_TO_MY_LIST, "button 'Add to reading list' not found");
        this.click(ADD_TO_MY_LIST_OVERLAY, "button 'Got it' not found");
        this.clear(MY_LIST_NAME_INPUT, "input field not found", 5);
        this.enterText(MY_LIST_NAME_INPUT, nameOfFolder, "can't put text in articles folder input");
        this.click(MY_LIST_OK_BUTTON, "can't press 'ok'");
    }

    @Step("Closing article")
    public void closeArticle() {
        if (Platform.getInstance().isMw()) {
            return;
        }
        this.click(CLOSE, "'X' not found");
    }

    @Step("Adding article in exist list")
    public void addArticleInExistList() {
        this.click(OPTIONS_BUTTON, "button ':' not found");
        this.click(ADD_TO_MY_LIST, "button 'Add to reading list' not found");
        this.click(CURRENT_LIST_ICON, "readline with slots not found");
    }

    @Step("Fast checking article title")
    public void assertFastCheckArticleTitle() {
        this.waitElementPresent(TITLE, "can't find article", 10);
    }

    @Step("Adding article in saved")
    public void addArticleToMySaved() {
        if (Platform.getInstance().isMw()) {
            this.removeArticleFromSavedIfAdded();
        }
        this.click(ADD_TO_MY_LIST, "Can't add article in reading list");
    }

    @Step("Removing article from saved if they was added")
    public void removeArticleFromSavedIfAdded() {
        if (this.isElementPresent(REMOVE_FROM_MY_LIST_BUTTON)) {
            this.click(REMOVE_FROM_MY_LIST_BUTTON, "Can't click button to remove an article from saved");
        }
        this.waitElementPresent(ADD_TO_MY_LIST, "Can't find button to add to my list after removing it from list before");
    }
}
