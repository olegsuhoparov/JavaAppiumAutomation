package helpers.ui.mobile_web;

import helpers.ui.SearchPO;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPO extends SearchPO {
    static {
        SEARCH_INIT = "css:button[id='searchIcon']";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL = "css:div>button.cancel";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://div[contains(@class, 'a-description')][contains(text(), '{SUBSTRING}')]";
        SEARCH_RESULT_TWO_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '{SUBSTRING1}')]/..//*[contains(@text, '{SUBSTRING2}')]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        SEARCH_TEXT = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_TITLE = "id:org.wikipedia:id/page_list_item_title";
    }

    public MWSearchPO(RemoteWebDriver driver) {
        super(driver);
    }
}
