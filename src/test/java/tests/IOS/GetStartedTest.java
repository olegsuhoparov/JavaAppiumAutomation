package tests.IOS;

import helpers.CoreTestCase;
import helpers.Platform;
import helpers.ui.WelcomePO;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
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
