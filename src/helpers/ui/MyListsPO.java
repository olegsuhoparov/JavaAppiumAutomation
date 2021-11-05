package helpers.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPO extends MainPageObject {

    public static final String
            FOLDER_BY_NAME_TPL = "//*[contains(@text, '{FOLDER_NAME}')]",
            ARTICLE_BY_TITLE_TPL = "//*[contains(@text, '{NAME}')]";

    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }
    private static String getSavedArticle(String title){
        return ARTICLE_BY_TITLE_TPL.replace("{NAME}", title);
    }

    public MyListsPO(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        this.click(By.xpath(getFolderXpathByName(nameOfFolder)), "can't find created folder");
    }

    public void waitArticleToDisappearByTitle(String articleTitle){
        this.waitElementNotPresent(By.xpath(getSavedArticle(articleTitle)), "saved article still present with title " + articleTitle, 15);
    }

    public void waitArticleToAppearByTitle(String articleTitle){
        this.waitElementPresent(By.xpath(getSavedArticle(articleTitle)), "can't find article with title " + articleTitle, 15);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.swipeElementLeft(By.xpath(getSavedArticle(articleTitle)), "can't find article for delete");
        this.waitArticleToDisappearByTitle(articleTitle);
    }
}
