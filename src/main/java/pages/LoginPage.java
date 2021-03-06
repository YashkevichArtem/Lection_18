package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class LoginPage extends BasePage {
    SelenideElement usernameField = $("#user-name");
    SelenideElement passwordField = $("#password");
    SelenideElement loginButton = $("#login-button");

    @Step("Login fields")
    public void login() throws IOException {
        Properties log = new Properties();
        log.load(new FileInputStream("src/main/resources/log.inf"));
        usernameField.sendKeys(log.getProperty("us.username"));
        passwordField.sendKeys(log.getProperty("us.password"));
        loginButton.click();

    }

    @Step("Open website")
    public void openLoginPage(){
        open("https://www.saucedemo.com/"); }
}
