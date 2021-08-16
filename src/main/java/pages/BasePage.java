package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;


public class BasePage {
    public SelenideElement welcomeMessage = $("span.title");
}
