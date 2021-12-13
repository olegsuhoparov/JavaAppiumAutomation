package tests;

import helpers.CoreTestCase;
import helpers.Platform;
import helpers.ui.ArticlePO;
import helpers.ui.SearchPO;
import helpers.ui.factories.ArticlePOFactory;
import helpers.ui.factories.SearchPOFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResult() {
        if(Platform.getInstance().isMw()){
            return;
        }
        SearchPO searchPO = SearchPOFactory.get(driver);
        ArticlePO articlePO = ArticlePOFactory.get(driver);;

        searchPO.initSearchInput();
        searchPO.typeSearchField("java");
        searchPO.clickByArticleWithSubstring("bject-oriented programming language");
        this.rotateScreenLandscape();
        String titleBeforeRotation = articlePO.getArticleTitle();
        String titleAfterRotation = articlePO.getArticleTitle();
        assertEquals("Article title have been changed after screen rotation",
                titleBeforeRotation, titleAfterRotation);
        this.rotateScreenPortrait();
        String titleAfterSecondRotation = articlePO.getArticleTitle();
        assertEquals("Article title have been changed after second screen rotation",
                titleBeforeRotation, titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleBackground() {
        if(Platform.getInstance().isMw()){
            return;
        }
        SearchPO searchPO = SearchPOFactory.get(driver);

        searchPO.initSearchInput();
        searchPO.typeSearchField("java");
        searchPO.waitForSearchResult("Object-oriented programming language");
        this.backgroundUp(3);

        searchPO.waitForSearchResult("Object-oriented programming language");
    }
}
