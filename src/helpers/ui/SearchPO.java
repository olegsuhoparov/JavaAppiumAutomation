package helpers.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPO extends MainPageObject {

    private static final String
            SEARCH_INIT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_CANCEL = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '{SUBSTRING}')]",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";

    public SearchPO(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS */

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    /* TEMPLATE METHODS */

    public void initSearchInput() {
        this.click(By.xpath(SEARCH_INIT), "Search field not found");
    }

    public void typeSearchField(String text) {
        this.enterText(By.xpath(SEARCH_INPUT), text, "Search field for type text not found");
    }

    public void waitForCancelButtonToAppear() {
        this.waitElementsPresent(By.id(SEARCH_CANCEL), "button 'cancel search - x - not found", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitElementNotPresent(By.id(SEARCH_CANCEL), "button 'cancel search - x - is still present", 5);
    }

    public void clickCancelSearch() {
        this.click(By.id(SEARCH_CANCEL), "button 'cancel search - x - not found");
    }


    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitElementPresent(By.xpath(searchResultXpath), "Can't find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.click(By.xpath(searchResultXpath), "Can't find search result with substring " + substring);
    }

    public int getAmountOfFoundArticles() {
        return this.countResults(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitElementsPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "can't empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.waitElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any result", 5);
    }

}
