package tests;

import helpers.CoreTestCase;
import helpers.Platform;
import helpers.ui.*;
import helpers.ui.factories.ArticlePOFactory;
import helpers.ui.factories.MyListsPOFactory;
import helpers.ui.factories.NavigationUIFactory;
import helpers.ui.factories.SearchPOFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    String login = "Olegst4";
    String password = "gdepe4ka";

    @Test
    public void testSaveArticleToMyList() {

        String nameFolder = "learning programming";
        SearchPO searchPO = SearchPOFactory.get(driver);
        ArticlePO articlePO = ArticlePOFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListsPO myListsPO = MyListsPOFactory.get(driver);

        searchPO.initSearchInput();
        searchPO.typeSearchField("java");
        searchPO.clickByArticleWithSubstring("bject-oriented programming language");
        articlePO.waitForTitleElement();
        String articleTitle = articlePO.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            articlePO.addArticleToMyList(nameFolder);
        } else {
            articlePO.addArticleToMySaved();
        }
        if (Platform.getInstance().isMw()) {
            AuthorisationPO authorisationPO = new AuthorisationPO(driver);
            authorisationPO.clickLoginButton();
            authorisationPO.enterLoginData(login, password);
            authorisationPO.submitForm();
            articlePO.waitForTitleElement();
            assertEquals("We aren't the same pag after login", articleTitle, articlePO.getArticleTitle());
            articlePO.addArticleToMySaved();
        }
        articlePO.closeArticle();
        navigationUI.openNavigation();
        navigationUI.clickMyLists();
        if (Platform.getInstance().isAndroid()) {
            myListsPO.openFolderByName(nameFolder);
        }
        myListsPO.swipeByArticleToDelete(articleTitle);
    }

    @Test
    public void testSaveTwoArticlesToMyListAndDeleteOneOfThem() {
        String nameFolder = "Programming languages";
        String languageOne = "Java";
        String describeLanguageOne = "bject-oriented programming language";
        String languageTwo = "Python";
        String describeLanguageTwo = "eneral-purpose programming language";

        SearchPO searchPO = SearchPOFactory.get(driver);
        ArticlePO articlePO = ArticlePOFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListsPO myListsPO = MyListsPOFactory.get(driver);

        searchPO.initSearchInput();
        searchPO.typeSearchField(languageOne);
        searchPO.clickByArticleWithSubstring(describeLanguageOne);
        articlePO.waitForTitleElement();
        String articleTitle = articlePO.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            articlePO.addArticleToMyList(nameFolder);
        } else {
            articlePO.addArticleToMySaved();
        }
        if (Platform.getInstance().isMw()) {
            AuthorisationPO authorisationPO = new AuthorisationPO(driver);
            authorisationPO.clickLoginButton();
            authorisationPO.enterLoginData(login, password);
            authorisationPO.submitForm();
            articlePO.waitForTitleElement();
            assertEquals("We aren't the same pag after login", articleTitle, articlePO.getArticleTitle());
            articlePO.addArticleToMySaved();
        }
        articlePO.closeArticle();
        searchPO.initSearchInput();
        searchPO.typeSearchField(languageTwo);
        searchPO.clickByArticleWithSubstring(describeLanguageTwo);
        if (Platform.getInstance().isAndroid()) {
            articlePO.addArticleInExistList();
            articlePO.closeArticle();
        } else if(Platform.getInstance().isMw()) {
            articlePO.addArticleToMySaved();
        }
        navigationUI.openNavigation();

        navigationUI.clickMyLists();
        if (Platform.getInstance().isAndroid()) {
            myListsPO.openFolderByName(nameFolder);
        }
        int articlesBeforeDelete = myListsPO.countArticles();
        myListsPO.swipeByArticleToDelete(languageTwo);
        int articlesAfterDelete = myListsPO.countArticles();

        assertEquals("Title second article before and after delete first article isn't equals",
                articlesBeforeDelete - 1, articlesAfterDelete);

        myListsPO.openArticleByName(languageOne);

        String articleTitleAfterDeleteOther = articlePO.getArticleTitle();
        assertEquals("Title second article before and after delete first article isn't equals",
                articleTitle, articleTitleAfterDeleteOther);
    }

}
