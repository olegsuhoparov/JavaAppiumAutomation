package helpers.ui.factories;

import helpers.Platform;
import helpers.ui.SearchPO;
import helpers.ui.android.AndroidSearchPO;
import helpers.ui.ios.IOSSearchPO;
import helpers.ui.mobile_web.MWSearchPO;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPOFactory {
    
    public static SearchPO get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPO(driver);
        } else {
            return new MWSearchPO(driver);
        }
    }
}
