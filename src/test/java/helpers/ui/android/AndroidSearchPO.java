package helpers.ui.android;

import helpers.ui.SearchPO;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPO extends SearchPO {
    static {
        SEARCH_INIT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_CANCEL = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '{SUBSTRING}')]";
        SEARCH_RESULT_TWO_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '{SUBSTRING1}')]/..//*[contains(@text, '{SUBSTRING2}')]";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
        SEARCH_TEXT = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_TITLE = "id:org.wikipedia:id/page_list_item_title";
    }

    public AndroidSearchPO(RemoteWebDriver driver) {
        super(driver);
    }


}
