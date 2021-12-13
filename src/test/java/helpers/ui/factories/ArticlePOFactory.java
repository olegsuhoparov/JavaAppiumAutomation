package helpers.ui.factories;

import helpers.Platform;
import helpers.ui.ArticlePO;
import helpers.ui.android.AndroidArticlePO;
import helpers.ui.ios.IOSArticlePO;
import helpers.ui.mobile_web.MWArticlePO;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePOFactory {

    public static ArticlePO get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePO(driver);
        } else if (Platform.getInstance().isIOS()){
            return new IOSArticlePO(driver);
        } else {
            return new MWArticlePO(driver);
        }
    }
}
