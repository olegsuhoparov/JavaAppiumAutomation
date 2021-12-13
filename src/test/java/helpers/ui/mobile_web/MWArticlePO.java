package helpers.ui.mobile_web;

import helpers.ui.ArticlePO;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePO extends ArticlePO {
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        ADD_TO_MY_LIST = "css:#ca-watch:not(.watched)";
        ADD_TO_MY_LIST_OVERLAY = "id:onboarding_button";
        MY_LIST_NAME_INPUT = "id:text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        CURRENT_LIST_ICON = "id:org.wikipedia:id/item_image_2";
        REMOVE_FROM_MY_LIST_BUTTON = "css:#ca-watch.watched";
        CLOSE = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public MWArticlePO(RemoteWebDriver driver) {
        super(driver);
    }
}
