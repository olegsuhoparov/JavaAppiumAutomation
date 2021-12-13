package tests;

import helpers.CoreTestCase;
import helpers.ui.ArticlePO;
import helpers.ui.SearchPO;
import helpers.ui.factories.ArticlePOFactory;
import helpers.ui.factories.SearchPOFactory;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Compare article title with displayed one")
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

        Assert.assertEquals("not equals", "Java (programming language)", title);
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Swipe article to the footer")
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
    @DisplayName("Check result list articles for word")
    @Description("We enter text 'java' and check that this word in every result")
    @Step("Starting testCheckSomeArticlesForWord")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSomeArticlesForWord() {
        SearchPO searchPO = SearchPOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField("java");

        int numberArticles = searchPO.getAmountOfFoundArticles();
        Assert.assertTrue(String.format("There were found %s articles", numberArticles),
                numberArticles > 1);
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Check result list articles for word")
    @Description("We enter text 'java' and check that this word in every result")
    @Step("Starting testStringInEverySearchResult")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testStringInEverySearchResult() {
        String text = "java";
        SearchPO searchPO = SearchPOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField(text);

        Assert.assertTrue("Not all results contains word 'java'",
                searchPO.checkTitleInAllSearchResult(text));
    }
}
