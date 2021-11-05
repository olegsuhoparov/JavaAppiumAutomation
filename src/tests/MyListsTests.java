package tests;

import helpers.CoreTestCase;
import helpers.ui.ArticlePO;
import helpers.ui.MyListsPO;
import helpers.ui.NavigationUI;
import helpers.ui.SearchPO;
import org.junit.Test;

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
}
