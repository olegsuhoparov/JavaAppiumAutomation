package helpers.ui.factories;

import helpers.Platform;
import helpers.ui.MyListsPO;
import helpers.ui.android.AndroidMyListPO;
import helpers.ui.ios.IOSMyListsPO;
import helpers.ui.mobile_web.MWMyListsPO;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPOFactory {
    public static MyListsPO get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidMyListPO(driver);
        } else if(Platform.getInstance().isIOS()){
            return new IOSMyListsPO(driver);
        } else {
            return new MWMyListsPO(driver);
        }
    }
}
