package tests.IOS;

import helpers.CoreTestCase;
import helpers.Platform;
import helpers.ui.WelcomePO;
import io.qameta.allure.*;
import org.testng.annotations.Test;


public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Welcome page")})
    @Description("We just walk path for checking that IOS tests works")
    @Step("Starting testPassThroughWelcome")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testPassThroughWelcome(){

        if(Platform.getInstance().isAndroid() || Platform.getInstance().isMw()){
            return;
        }
        WelcomePO welcomePO = new WelcomePO(driver);
        welcomePO.waitForLearnMoreLink();
        welcomePO.clickNextButton();
        welcomePO.waitForNewWayToExploreText();
        welcomePO.clickNextButton();
        welcomePO.waitForAddOrEditNewWayToExploreText();
        welcomePO.clickNextButton();
        welcomePO.waitForLearnMoreAboutDataCollectedText();
        welcomePO.clickGetStartedButton();
    }

}
