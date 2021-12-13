package helpers.ui;

import helpers.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPO extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            REMOVE_FROM_SAVED_BUTTON;

    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", nameOfFolder);
    }

    private static String getSavedArticle(String title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", title);
    }

    private static String getRemoveLocatorByTitle(String title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{NAME}", title);
    }


    public MyListsPO(RemoteWebDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        this.click(getFolderXpathByName(nameOfFolder), "can't find created folder");
    }

    public void openArticleByName(String nameOfArticle) {
        this.click(getSavedArticle(nameOfArticle), "can't find created article");
    }

    public void waitArticleToDisappearByTitle(String articleTitle) {
        this.waitElementNotPresent(getSavedArticle(articleTitle), "saved article still present with title " + articleTitle, 15);
    }

    public void waitArticleToAppearByTitle(String articleTitle) {
        this.waitElementPresent(getSavedArticle(articleTitle), "can't find article with title " + articleTitle, 15);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        if(Platform.getInstance().isMw()){
            String removeLocator = getRemoveLocatorByTitle(articleTitle);
            this.click(removeLocator, "Can't find button to remove article");
            driver.navigate().refresh();
            return;
        }
        this.swipeElementLeft(getSavedArticle(articleTitle), "can't find article for delete");
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(articleTitle, "can't find saved article");
        }
        this.waitArticleToDisappearByTitle(articleTitle);
    }
}
