package steps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.Buying;
import pages.LoginPage;

import java.io.IOException;

public class SecondStep {
    LoginPage loginPage = new LoginPage();
    Buying buyPage = new Buying();

    @Допустим("^открываем страницу https://www.saucedemo.com/$")
    public void mainPage(){
        loginPage.openLoginPage();
    }

    @И("^вводим логин, пароль и авторизуемся$")
    public void loginStep() throws IOException {
        loginPage.login();
    }

    @Когда("^добавляем Backpack и Bike Light в корзину и нажимаем Checkout$")
    public void buyingStep(){
        buyPage.addItemBackpack.click();
        buyPage.addItemBikeLight.click();
        buyPage.cartButton.click();
        buyPage.checkoutButton.click();
    }

    @Тогда("^переходим к заполнению данных заказчика и нажимаем Continue$")
    public void addNameStep(){
        buyPage.setUserName();
    }

    @И("^подтвержаем заказ нажатием на поле Finish$")
    public void finishStep(){
        buyPage.finishButton.click();
        buyPage.completeMessage.shouldBe(Condition.visible);
        Assertions.assertEquals("THANK YOU FOR YOUR ORDER", buyPage.completeMessage.getText());
    }

}
