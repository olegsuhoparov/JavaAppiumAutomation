package tests;

import helpers.CoreTestCase;
import helpers.ui.ArticlePO;
import helpers.ui.MyListsPO;
import helpers.ui.NavigationUI;
import helpers.ui.SearchPO;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveArticleToMyList() {
        String nameFolder = "learning programming";
        SearchPO searchPO = new SearchPO(driver);
        ArticlePO articlePO = new ArticlePO(driver);
        NavigationUI navigationUI = new NavigationUI(driver);
        MyListsPO myListsPO = new MyListsPO(driver);

        searchPO.initSearchInput();
        searchPO.typeSearchField("java");
        searchPO.clickByArticleWithSubstring("Object-oriented programming language");
        articlePO.waitForTitleElement();
        String articleTitle = articlePO.getArticleTitle();
        articlePO.addArticleToMyList(nameFolder);
        articlePO.closeArticle();
        navigationUI.clickMyLists();
        myListsPO.openFolderByName(nameFolder);
        myListsPO.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticlesToMyListAndDeleteOneOfThem() {
        String nameFolder = "Programming languages";
        String languageOne = "Java";
        String describeLanguageOne = "Object-oriented programming language";
        String languageTwo = "Python";
        String describeLanguageTwo = "General-purpose programming language";

        SearchPO searchPO = new SearchPO(driver);
        ArticlePO articlePO = new ArticlePO(driver);
        NavigationUI navigationUI = new NavigationUI(driver);
        MyListsPO myListsPO = new MyListsPO(driver);

        searchPO.initSearchInput();
        searchPO.typeSearchField(languageOne);
        searchPO.clickByArticleWithSubstring(describeLanguageOne);
        articlePO.waitForTitleElement();
        articlePO.addArticleToMyList(nameFolder);
        articlePO.closeArticle();
        searchPO.initSearchInput();
        searchPO.typeSearchField(languageTwo);
        searchPO.clickByArticleWithSubstring(describeLanguageTwo);
        String articleTitle = articlePO.getArticleTitle();
        articlePO.addArticleInExistList();
        articlePO.closeArticle();
        navigationUI.clickMyLists();
        myListsPO.openFolderByName(nameFolder);
        myListsPO.swipeByArticleToDelete(languageOne);
        myListsPO.openArticleByName(languageTwo);
        String articleTitleAfterDeleteOther = articlePO.getArticleTitle();

        assertEquals("Title second article before and after delete first article isn't equals",
                articleTitle, articleTitleAfterDeleteOther);
    }

}
