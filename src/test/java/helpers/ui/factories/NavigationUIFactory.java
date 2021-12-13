package helpers.ui.factories;

import helpers.Platform;
import helpers.ui.NavigationUI;
import helpers.ui.android.AndroidNavigationUI;
import helpers.ui.ios.IOSNavigationUI;
import helpers.ui.mobile_web.MWNavigationUI;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory {

    public static NavigationUI get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidNavigationUI(driver);
        }else if(Platform.getInstance().isIOS()){
            return new IOSNavigationUI(driver);
        } else {
            return new MWNavigationUI(driver);
        }
    }
}
