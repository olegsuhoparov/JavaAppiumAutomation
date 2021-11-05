package helpers.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class ArticlePO extends MainPageObject {
    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            ADD_TO_MY_LIST = "//*[contains(@text, 'Add to reading list')]",
            ADD_TO_MY_LIST_OVERLAY = "onboarding_button",
            MY_LIST_NAME_INPUT = "text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CURRENT_LIST_ICON = "org.wikipedia:id/item_image_2",
            CLOSE = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePO(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.click(By.id(TITLE), "Can't find article on page!");
    }

    public String getArticleTitle() {
        return waitForTitleElement().getAttribute("text");

    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Can't find the end of article", 20);
    }

    public void addArticleToMyList(String nameOfFolder) {
        this.click(By.xpath(OPTIONS_BUTTON), "button ':' not found");
        this.click(By.xpath(ADD_TO_MY_LIST), "button 'Add to reading list' not found");
        this.click(By.id(ADD_TO_MY_LIST_OVERLAY), "button 'Got it' not found");
        this.clear(By.id(MY_LIST_NAME_INPUT), "input field not found", 5);
        this.enterText(By.id(MY_LIST_NAME_INPUT), nameOfFolder, "can't put text in articles folder input");
        this.click(By.xpath(MY_LIST_OK_BUTTON), "can't press 'ok'");
    }

    public void closeArticle() {
        this.click(By.xpath(CLOSE), "'X' not found");
    }

    public void addArticleInExistList() {
        this.click(By.xpath(OPTIONS_BUTTON), "button ':' not found");
        this.click(By.xpath(ADD_TO_MY_LIST), "button 'Add to reading list' not found");
        this.click(By.id(CURRENT_LIST_ICON), "readline with slots not found");
    }

    public boolean assertFastCheckArticleTitle() {
        return driver.findElements(By.id(TITLE)).size() > 0;
    }

}
