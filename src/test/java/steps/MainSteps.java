package steps;

import java.io.IOException;

import com.codeborne.selenide.Condition;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
public class MainSteps{
    LoginPage loginPage = new LoginPage();

    @Допустим("^открыта страница https://www.saucedemo.com/$")
    public void mainPage(){
        loginPage.openLoginPage();
    }

    @И("^вводим данные для авторизации и авторизуемся$")
    public void loginStep() throws IOException {
        loginPage.login();
    }

    @Тогда("^происходит успешная авторизация на сайт https://www.saucedemo.com/ и страница содержит продукты$")
    public void authorizationStep(){
        loginPage.welcomeMessage.shouldBe(Condition.visible);
        Assertions.assertEquals("PRODUCTS", loginPage.welcomeMessage.getText());
    }
}
