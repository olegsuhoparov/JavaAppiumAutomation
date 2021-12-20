package tests;

import helpers.CoreTestCase;
import helpers.ui.ArticlePO;
import helpers.ui.SearchPO;
import helpers.ui.factories.ArticlePOFactory;
import helpers.ui.factories.SearchPOFactory;
import io.qameta.allure.*;

import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Description("We open 'Java programing language' article and make sure that title is expected")
    @Step("Starting testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {
        SearchPO searchPO = SearchPOFactory.get(driver);
        ArticlePO articlePO = ArticlePOFactory.get(driver);

        searchPO.initSearchInput();
        searchPO.typeSearchField("java");
        searchPO.clickByArticleWithSubstring("bject-oriented programming language");
        String title = articlePO.getArticleTitle();

        Assert.assertEquals( "Java (programming language2)", title, "not equals");
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Description("We open an article and swipe to footer")
    @Step("Starting testSwipeArticle")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSwipeArticle() {
        SearchPO searchPO = SearchPOFactory.get(driver);
        ArticlePO articlePO = ArticlePOFactory.get(driver);

        searchPO.initSearchInput();
        searchPO.typeSearchField("Java");
        searchPO.clickByArticleWithSubstring("bject-oriented programming language");
        articlePO.waitForTitleElement();
        articlePO.swipeToFooter();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Description("We enter text 'java' and check that this word in every result")
    @Step("Starting testCheckSomeArticlesForWord")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSomeArticlesForWord() {
        SearchPO searchPO = SearchPOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField("java");

        int numberArticles = searchPO.getAmountOfFoundArticles();
        Assert.assertTrue(numberArticles > 1, String.format("There were found %s articles", numberArticles));
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Description("We enter text 'java' and check that this word in every result")
    @Step("Starting testStringInEverySearchResult")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testStringInEverySearchResult() {
        String text = "java";
        SearchPO searchPO = SearchPOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField(text);

        Assert.assertTrue(searchPO.checkTitleInAllSearchResult(text), "Not all results contains word 'java'");
    }
}
