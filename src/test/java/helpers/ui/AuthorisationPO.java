package helpers.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorisationPO extends MainPageObject {
    private static final String
            LOGIN_BUTTON = "xpath://a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button[name='wploginattempt']";

    public AuthorisationPO(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickLoginButton(){
        this.tryClickElementWithFewAttempts(LOGIN_BUTTON, "Can't click log in button", 5);
    }

    public void enterLoginData(String login, String password){
        this.enterText(LOGIN_INPUT, login, "Can't find and put a login to the login input");
        this.enterText(PASSWORD_INPUT, password, "Can't find and put a password to the password input");
    }

    public void submitForm(){
        this.click(SUBMIT_BUTTON, "Can't find for click submit button");
    }
}
