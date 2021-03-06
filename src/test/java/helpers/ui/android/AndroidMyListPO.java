package helpers.ui.android;

import helpers.ui.MyListsPO;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPO extends MyListsPO {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[contains(@text, '{FOLDER_NAME}')]";
        ARTICLE_BY_TITLE_TPL = "xpath://*[contains(@text, '{TITLE}')]";
        ARTICLE_CONTAINERS = "id:org.wikipedia:id/page_list_item_container";
    }

    public AndroidMyListPO(RemoteWebDriver driver) {
        super(driver);
    }
}
