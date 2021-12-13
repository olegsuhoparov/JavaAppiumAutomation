package helpers.ui.android;

import helpers.ui.ArticlePO;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePO extends ArticlePO {

    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        ADD_TO_MY_LIST = "xpath://*[contains(@text, 'Add to reading list')]";
        ADD_TO_MY_LIST_OVERLAY = "id:onboarding_button";
        MY_LIST_NAME_INPUT = "id:text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        CURRENT_LIST_ICON = "id:org.wikipedia:id/item_image_2";
        CLOSE = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidArticlePO(RemoteWebDriver driver) {
        super(driver);
    }

}
