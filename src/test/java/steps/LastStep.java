package steps;

import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.By;
import pages.LoginPage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LastStep {
    LoginPage loginPage = new LoginPage();

    @Допустим("^пользователь открывает страницу https://www.saucedemo.com/$")
    public void mainPage(){
        loginPage.openLoginPage();
    }

    @И("^вводит логин, пароль и авторизуется$")
    public void loginStep() throws IOException {
        loginPage.login();
    }

    @Когда("^пользователь выполняет нажатие на ссылку \"([^\"]*)\"$")
    public void checkItemName(String link){
        $(By.xpath("//*[contains(text(), \"" + link + "\")]")).click();
    }

    @Тогда("^цена вещи равна \"([^\"]*)\"$")
    public void checkItemPrice(String price){
        String actualPriceInfo = $(By.className("inventory_details_price")).getText();
        assertTrue(actualPriceInfo.contains(price), "Актуальная и ожидаемая цена не совпадают");
    }
}
