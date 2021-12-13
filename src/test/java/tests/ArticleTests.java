package tests;

import helpers.CoreTestCase;
import helpers.ui.ArticlePO;
import helpers.ui.SearchPO;
import helpers.ui.factories.ArticlePOFactory;
import helpers.ui.factories.SearchPOFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle() {
        SearchPO searchPO = SearchPOFactory.get(driver);
        ArticlePO articlePO = ArticlePOFactory.get(driver);

        searchPO.initSearchInput();
        searchPO.typeSearchField("java");
        searchPO.clickByArticleWithSubstring("bject-oriented programming language");
        String title = articlePO.getArticleTitle();

        assertEquals("not equals", "Java (programming language)", title);
    }

    @Test
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
    public void testCheckSomeArticlesForWord() {
        SearchPO searchPO = SearchPOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField("java");

        int numberArticles = searchPO.getAmountOfFoundArticles();
        assertTrue(String.format("There were found %s articles", numberArticles),
                numberArticles > 1);
    }

    @Test
    public void testStringInEverySearchResult() {
        String text = "java";
        SearchPO searchPO = SearchPOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField(text);

        assertTrue("Not all results contains word 'java'",
                searchPO.checkTitleInAllSearchResult(text));
    }
}
