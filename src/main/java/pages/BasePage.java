package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    public SelenideElement welcomeMessage = $("span.title");
}
