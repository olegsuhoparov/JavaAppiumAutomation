package helpers.ui.ios;

import helpers.ui.MyListsPO;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPO extends MyListsPO {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{NAME}')]";
    }

    public IOSMyListsPO(RemoteWebDriver driver) {
        super(driver);
    }
}
