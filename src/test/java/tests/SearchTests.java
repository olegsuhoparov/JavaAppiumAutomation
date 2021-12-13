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

@Epic("Tests search field and results")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Check that in result search contains description")
    @Description("We enter text 'java' and check that this substring in result")
    @Step("Starting testSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearch() {
        String searchText = "java";
        String searchSubstring = "Object-oriented programming language";

        SearchPO searchPO = SearchPOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField(searchText);
        searchPO.waitForSearchResult(searchSubstring);
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test the cancel button")
    @Description("We enter text in search field and then cancel")
    @Step("Starting testCancelSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCancelSearch() {
        SearchPO searchPO = SearchPOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.waitForCancelButtonToAppear();
        searchPO.clickCancelSearch();
        searchPO.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test amount of not empty search")
    @Description("We enter text in search field and check that at least one more result found")
    @Step("Starting testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch() {
        SearchPO searchPO = SearchPOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField("Linking Park discography");
        int amountOfSearchResult = searchPO.getAmountOfFoundArticles();
        Assert.assertTrue("No one result", amountOfSearchResult > 0);
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test amount of empty search")
    @Description("We enter random symbols in search field and check that no one found")
    @Step("Starting testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch() {
        SearchPO searchPO = SearchPOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField("aweotjrk#KA");
        searchPO.waitForEmptyResultsLabel();
        searchPO.assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test check search field")
    @Description("We are checking the search input placeholder")
    @Step("Starting testCheckTextInSearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckTextInSearch() {
        SearchPO searchPO = SearchPOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.assertSearchContainsText("Search");
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Test fast check article title")
    @Description("We are checking the article in right after loading")
    @Step("Starting testSaveCheckTitleArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testSaveCheckTitleArticle() {
        String searchText = "java";
        String searchSubstring = "bject-oriented programming language";

        SearchPO searchPO = SearchPOFactory.get(driver);
        ArticlePO articlePO = ArticlePOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField(searchText);
        searchPO.clickByArticleWithSubstring(searchSubstring);
        articlePO.assertFastCheckArticleTitle();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test search article with title and description")
    @Description("We are checking that article witch we found contains title and description")
    @Step("Starting testSearchResultImproved")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearchResultImproved() {
        String searchText = "Java";
        String searchSubstring = "bject-oriented programming language";

        SearchPO searchPO = SearchPOFactory.get(driver);
        searchPO.initSearchInput();
        searchPO.typeSearchField(searchText);
        searchPO.waitForElementByTitleAndDescription(searchText, searchSubstring);
    }
}
