import helpers.CoreTestCase;
import helpers.ui.*;
import org.junit.Test;
import org.openqa.selenium.*;

public class FirstTest extends CoreTestCase {

    private MainPageObject app;

    protected void setUp() throws Exception {
        super.setUp();
        app = new MainPageObject(driver);
    }

    @Test
    public void testClear() {

        app.click(By.id("org.wikipedia:id/search_container"), "Can't find 'search not found");
        app.enterText(By.id("org.wikipedia:id/search_src_text"), "java",
                "search not found");
        app.clear(By.id("org.wikipedia:id/search_src_text"), "can't find search field", 5);
    }




    @Test
    public void testCheckTextInSearch() {
        app.click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search field not found");
        app.assertElementHasText(By.id("org.wikipedia:id/search_src_text"),
                "Search",
                "Search field not found");
    }

    @Test
    public void testCheckSomeArticlesForWord() {
        app.click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search field not found");
        app.enterText(By.xpath("//*[contains(@text, 'Search…')]"), "java",
                "search not found");
        int numberArticles = app.waitElementsPresent(By.id("org.wikipedia:id/page_list_item_title"),
                "No one article were found", 5).size();
        assertTrue(String.format("There were found %s articles", numberArticles),
                numberArticles > 1);
    }

    @Test
    public void javaInEverySearchResult() {
        app.click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search field not found");
        app.enterText(By.xpath("//*[contains(@text, 'Search…')]"), "java",
                "search not found");
        assertTrue("Not all results contains word 'java'",
                app.waitElementsPresent(By.id("org.wikipedia:id/page_list_item_title"),
                        "No one article were found", 5)
                        .stream().allMatch((e) -> e.getAttribute("text").toLowerCase().contains("java")));
        // lowercase использую для приведения строковой выдачи к единому формату в сравнении, на мой взгляд тут это не критично
    }

    @Test
    public void saveTwoArticlesToMyListAndDeleteOneOfThem() {
        String nameFolder = "Programming languages";
        String languageOne = "Java";
        String describeLanguageOne = "Object-oriented programming language";
        String languageTwo = "Python";
        String describeLanguageTwo = "General-purpose programming language";

        app.addInReadingList(languageOne, describeLanguageOne);
        app.click(By.id("onboarding_button"), "button 'Got it' not found");
        app.clear(By.id("text_input"), "field 'Name of reading list' not found", 5);
        app.enterText(By.id("text_input"), nameFolder, "can't put text in articles folder input");
        app.click(By.xpath("//*[@text='OK']"), "can't press 'ok'");
        app.click(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "'X' not found");

        app.addInReadingList(languageTwo, describeLanguageTwo);
        app.click(By.id("org.wikipedia:id/item_image_2"), "readline with slots not found and 'got it' not found");
        app.click(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "'X' not found");
        app.click(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"), "'my lists' not found");
        app.click(By.xpath("//*[contains(@text, '" + nameFolder + "')]"), "can't find created folder");

        app.click(By.xpath("//*[contains(@text, '" + languageTwo + "')]"), "can't find article");
        String titleArticleBeforeDeleteOther = app.waitElementPresent(By.xpath("//*[contains(@text, '" + languageTwo + "')]"),
                "Title about " + languageTwo + " not found").getAttribute("text");
        app.click(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "'X' not found");
        app.swipeElementLeft(By.xpath("//*[contains(@text, '" + languageOne + "')]"), "can't find article");
        app.waitElementNotPresent(By.xpath("//*[contains(@text, '" + languageOne + "')]"), "article with text " + languageOne + " wasn't delete", 5);
        app.click(By.xpath("//*[contains(@text, '" + languageTwo + "')]"), "can't find article with text " + languageTwo);
        String titleArticleAfterDeleteOther = app.waitIgnoreStaleReferent(By.xpath("//*[contains(@text, '" + languageTwo + "')]"),
                "Title about " + languageTwo + " not found").getAttribute("text");
        assertEquals("Title second article before and after delete first article isn't equals",
                titleArticleBeforeDeleteOther, titleArticleAfterDeleteOther);
    }

    @Test
    public void saveCheckTitleArticleFast() {
        String nameFolder = "learning programming";

        app.click(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "search not found");

        app.enterText(By.xpath("//*[contains(@text, 'Search…')]"), "java",
                "search not found");
        app.click(By.xpath("//*[contains(@text, 'Object-oriented programming language')]"), "Title about java not found");
        app.assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "cannot find article");
    }

}
