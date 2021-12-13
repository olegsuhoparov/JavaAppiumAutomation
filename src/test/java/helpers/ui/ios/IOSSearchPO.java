package helpers.ui.ios;

import helpers.ui.SearchPO;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPO extends SearchPO {
    static {
        SEARCH_INIT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL = "id:Close";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@text, '{SUBSTRING}')]";
        SEARCH_RESULT_TWO_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '{SUBSTRING1}')]/..//*[contains(@text, '{SUBSTRING2}')]"; //old
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_TEXT = "id:org.wikipedia:id/search_src_text"; //old
        SEARCH_RESULT_TITLE = "id:org.wikipedia:id/page_list_item_title"; //old
    }

    public IOSSearchPO(RemoteWebDriver driver) {
        super(driver);
    }
}
