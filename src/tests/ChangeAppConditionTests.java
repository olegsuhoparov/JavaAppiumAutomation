package tests;

import helpers.CoreTestCase;
import helpers.ui.ArticlePO;
import helpers.ui.SearchPO;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResult() {
        SearchPO searchPO = new SearchPO(driver);
        ArticlePO articlePO = new ArticlePO(driver);

        searchPO.initSearchInput();
        searchPO.typeSearchField("java");
        searchPO.clickByArticleWithSubstring("Object-oriented programming language");
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

        SearchPO searchPO = new SearchPO(driver);

        searchPO.initSearchInput();
        searchPO.typeSearchField("java");
        searchPO.waitForSearchResult("Object-oriented programming language");
        this.backgroundUp(3);

        searchPO.waitForSearchResult("Object-oriented programming language");
    }
}
