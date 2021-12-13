package helpers.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPO extends MainPageObject {

    protected static String
            SEARCH_INIT,
            SEARCH_INPUT,
            SEARCH_CANCEL,
            SEARCH_RESULT_SUBSTRING_TPL,
            SEARCH_RESULT_TWO_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_TEXT,
            SEARCH_RESULT_TITLE;

    public SearchPO(RemoteWebDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS */

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementWithTwoSubstrings(String title, String description){
        return SEARCH_RESULT_TWO_SUBSTRING_TPL.replace("{SUBSTRING1}", title).replace("{SUBSTRING2}", description);
    }

    /* TEMPLATE METHODS */

    public void initSearchInput() {
        this.click(SEARCH_INIT, "Search field not found");
    }

    public void typeSearchField(String text) {
        this.enterText(SEARCH_INPUT, text, "Search field for type text not found");
    }

    public void waitForCancelButtonToAppear() {
        this.waitElementsPresent(SEARCH_CANCEL, "button 'cancel search - x - not found", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitElementNotPresent(SEARCH_CANCEL, "button 'cancel search - x - is still present", 5);
    }

    public void clickCancelSearch() {
        this.click(SEARCH_CANCEL, "button cancel search - x - not found");
    }


    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitElementPresent(searchResultXpath, "Can't find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.click(searchResultXpath, "Can't find search result with substring " + substring);
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String element = getResultSearchElementWithTwoSubstrings(title, description);
        waitElementPresent(element,
                "Element with title " + title + " and description " + description + " not found", 5);
    }

    public int getAmountOfFoundArticles() {
        return this.countResults(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitElementsPresent(SEARCH_EMPTY_RESULT_ELEMENT, "can't empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.waitElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any result", 5);
    }

    public void assertSearchContainsText(String text) {
        this.assertElementHasText(SEARCH_TEXT, text, "Search field not found");
    }

    public boolean checkTitleInAllSearchResult(String text) {
        return this.waitElementsPresent(SEARCH_RESULT_TITLE,
                "No one article were found", 5)
                .stream().allMatch((e) -> e.getAttribute("text").toLowerCase().contains(text));
    }

}
