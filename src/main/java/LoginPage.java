import com.codeborne.selenide.SelenideElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage{
    SelenideElement usernameField = $("#user-name");
    SelenideElement passwordField = $("#password");
    SelenideElement loginButton = $("#login-button");

    void login() throws IOException {
        Properties log = new Properties();
        log.load(new FileInputStream("src/main/resources/log.inf"));
        usernameField.sendKeys(log.getProperty("us.username"));
        passwordField.sendKeys(log.getProperty("us.password"));
        loginButton.click();

    }

    public void openLoginPage(){
        open("https://www.saucedemo.com/");
    }
}
