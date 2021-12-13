package helpers.ui.ios;

import helpers.ui.ArticlePO;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePO extends ArticlePO {

    public IOSArticlePO(RemoteWebDriver driver) {
        super(driver);
    }

    static {
        TITLE = "id:Java (programing language)";
        FOOTER_ELEMENT = "id:View article in browser";
        ADD_TO_MY_LIST = "id:Save for later";
        CURRENT_LIST_ICON = "id:org.wikipedia:id/item_image_2";
        CLOSE = "id:Back";
    }

}
