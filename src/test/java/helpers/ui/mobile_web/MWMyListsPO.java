package helpers.ui.mobile_web;

import helpers.ui.MyListsPO;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPO extends MyListsPO {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[contains(@text, '{FOLDER_NAME}')]";
        ARTICLE_BY_TITLE_TPL = "xpath://h1[@id='section_0'][contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://*[contains(@title, '{NAME}')]";
    }

    public MWMyListsPO(RemoteWebDriver driver) {
        super(driver);
    }
}
