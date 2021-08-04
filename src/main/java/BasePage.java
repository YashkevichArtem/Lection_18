import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    SelenideElement welcomeMessage = $("span.title");
}
