package tests;

import helpers.CoreTestCase;
import helpers.ui.ArticlePO;
import helpers.ui.SearchPO;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        String searchText = "java";
        String searchSubstring = "Object-oriented programming language";

        SearchPO searchPO = new SearchPO(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField(searchText);
        searchPO.waitForSearchResult(searchSubstring);
    }

    @Test
    public void testCancelSearch() {
        SearchPO searchPO = new SearchPO(driver);
        searchPO.initSearchInput();
        searchPO.waitForCancelButtonToAppear();
        searchPO.clickCancelSearch();
        searchPO.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPO searchPO = new SearchPO(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField("Linking Park discography");
        int amountOfSearchResult = searchPO.getAmountOfFoundArticles();
        assertTrue("No one result", amountOfSearchResult > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPO searchPO = new SearchPO(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField("aweotjrk#KA");
        searchPO.waitForEmptyResultsLabel();
        searchPO.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testCheckTextInSearch() {
        SearchPO searchPO = new SearchPO(driver);
        searchPO.initSearchInput();
        searchPO.assertSearchContainsText("Search");
    }

    @Test
    public void testSaveCheckTitleArticleFast() {
        String searchText = "java";
        String searchSubstring = "Object-oriented programming language";

        SearchPO searchPO = new SearchPO(driver);
        ArticlePO articlePO = new ArticlePO(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField(searchText);
        searchPO.clickByArticleWithSubstring(searchSubstring);
        assertTrue("Title current article not found", articlePO.assertFastCheckArticleTitle());
    }

    @Test
    public void testSearchResultImproved(){
        String searchText = "Java";
        String searchSubstring = "Object-oriented programming language";

        SearchPO searchPO = new SearchPO(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField(searchText);
        searchPO.waitForElementByTitleAndDescription(searchText, searchSubstring);
    }
}
