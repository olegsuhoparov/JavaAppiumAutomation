package tests;

import helpers.CoreTestCase;
import helpers.ui.ArticlePO;
import helpers.ui.SearchPO;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle() {
        SearchPO searchPO = new SearchPO(driver);
        ArticlePO articlePO = new ArticlePO(driver);

        searchPO.initSearchInput();
        searchPO.typeSearchField("java");
        searchPO.clickByArticleWithSubstring("Object-oriented programming language");
        String title = articlePO.getArticleTitle();

        assertEquals("not equals", "Java (programming language)", title);
    }

    @Test
    public void testSwipeArticle() {
        SearchPO searchPO = new SearchPO(driver);
        ArticlePO articlePO = new ArticlePO(driver);

        searchPO.initSearchInput();
        searchPO.typeSearchField("Appium");
        searchPO.clickByArticleWithSubstring("Appium");
        articlePO.waitForTitleElement();
        articlePO.swipeToFooter();
    }

    @Test
    public void testCheckSomeArticlesForWord() {
        SearchPO searchPO = new SearchPO(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField("java");

        int numberArticles = searchPO.getAmountOfFoundArticles();
        assertTrue(String.format("There were found %s articles", numberArticles),
                numberArticles > 1);
    }

    @Test
    public void testStringInEverySearchResult() {
        String text = "java";
        SearchPO searchPO = new SearchPO(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField(text);

        assertTrue("Not all results contains word 'java'",
                searchPO.checkTitleInAllSearchResult(text));
    }
}
