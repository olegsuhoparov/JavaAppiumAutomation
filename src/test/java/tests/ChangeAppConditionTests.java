package tests;

import helpers.CoreTestCase;
import helpers.Platform;
import helpers.ui.ArticlePO;
import helpers.ui.SearchPO;
import helpers.ui.factories.ArticlePOFactory;
import helpers.ui.factories.SearchPOFactory;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;


@Epic("Tests for change conditions app")
public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Articles"), @Feature(value = "Conditions")})
    @Description("We enter text 'java' and check that this word in every result")
    @Step("Starting testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testChangeScreenOrientationOnSearchResult() {
        if (Platform.getInstance().isMw()) {
            return;
        }
        SearchPO searchPO = SearchPOFactory.get(driver);
        ArticlePO articlePO = ArticlePOFactory.get(driver);
        ;

        searchPO.initSearchInput();
        searchPO.typeSearchField("java");
        searchPO.clickByArticleWithSubstring("bject-oriented programming language");
        this.rotateScreenLandscape();
        String titleBeforeRotation = articlePO.getArticleTitle();
        String titleAfterRotation = articlePO.getArticleTitle();
        Assert.assertEquals(titleBeforeRotation, titleAfterRotation,
                "Article title have been changed after screen rotation");
        this.rotateScreenPortrait();
        String titleAfterSecondRotation = articlePO.getArticleTitle();
        Assert.assertEquals(titleBeforeRotation, titleAfterSecondRotation,
                "Article title have been changed after second screen rotation");
    }

    @Test
    public void testCheckSearchArticleBackground() {
        if (Platform.getInstance().isMw()) {
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
