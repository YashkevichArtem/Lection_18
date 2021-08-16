import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.*;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.elements;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PageTest {

    @BeforeEach
    void setUp() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));
        //Configuration.startMaximized = true;
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("Login test")
    @Feature("Login feature")
    @Issue("12312")
    @Link("http://qwerty.com")
    @Order(1)
    @Test
    @Step("Welcome message")
    public void loginTest() throws IOException{
        LoginPage loginPage = new LoginPage();
        loginPage.openLoginPage();
        loginPage.login();
        loginPage.welcomeMessage.shouldBe(Condition.visible);
        Assertions.assertEquals("PRODUCTS", loginPage.welcomeMessage.getText());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Buying test")
    @Feature("Buy item")
    @Issue("93784")
    @Link("http://asdas.com")
    @Order(2)
    @Test
    public void buyingTest() throws IOException{
        LoginPage loginPage = new LoginPage();
        Buying buyPage = new Buying();
        loginPage.openLoginPage();
        loginPage.login();
        buyPage.addItemBackpack.click();
        buyPage.cartButton.click();
        buyPage.checkoutButton.click();
        buyPage.setUserName();
        buyPage.finishButton.click();
        buyPage.completeMessage.shouldBe(Condition.visible);
        Assertions.assertEquals("THANK YOU FOR YOUR ORDER", buyPage.completeMessage.getText());
    }

    @Severity(SeverityLevel.MINOR)
    @Description("Cancel test")
    @Feature("Cancel buy items")
    @Issue("93337")
    @Link("http://zxcvb.com")
    @Order(3)
    @Test
    public void cancelBuying() throws IOException{
        LoginPage loginPage = new LoginPage();
        Buying buyPage = new Buying();
        loginPage.openLoginPage();
        loginPage.login();
        buyPage.addItemBackpack.click();
        buyPage.cartButton.click();
        buyPage.checkoutButton.click();
        buyPage.setUserName();
        buyPage.cancelButton.click();
        loginPage.welcomeMessage.shouldBe(Condition.visible);
        Assertions.assertEquals("PRODUCTS", loginPage.welcomeMessage.getText());
    }
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Remove test")
    @Feature("Remove all items from cartlist")
    @Issue("343411")
    @Link("http://dependence.com")
    @Order(4)
    @Test
    public void removeItems() throws IOException{
        LoginPage loginPage = new LoginPage();
        Buying buyPage = new Buying();
        loginPage.openLoginPage();
        loginPage.login();
        buyPage.addItemBackpack.click();
        buyPage.addItemBikeLight.click();
        buyPage.cartButton.click();
        buyPage.cartList.shouldBe(Condition.visible);
        ElementsCollection itemsCollection = buyPage.cartList.$$("button");
        int itemsSizeBefore = itemsCollection.size();
        itemsCollection.first().click();
        itemsCollection.first().click();
        int itemsSizeAfter = itemsCollection.size();
        Assertions.assertTrue(itemsSizeBefore>itemsSizeAfter);
        System.out.println("Товаров до их удаления из корзины: " + itemsSizeBefore);
        System.out.println("Товаров после их удаления из корзины: " + itemsSizeAfter);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Total price")
    @Feature("Check total price")
    @Issue("311111")
    @Link("http://wear.com")
    @Order(5)
    @Test
    public void sumPrice() throws IOException{
        LoginPage loginPage = new LoginPage();
        Buying buyPage = new Buying();
        loginPage.openLoginPage();
        loginPage.login();
        buyPage.addItemBackpack.click();
        buyPage.addItemBikeLight.click();
        buyPage.cartButton.click();
        buyPage.checkoutButton.click();
        buyPage.setUserName();
        stringPriceToDouble();
    }

    public void stringPriceToDouble(){
        Buying buyPage = new Buying();
        String stringTotalPrice = buyPage.itemTotalPrice.getText().replace("Item total: $","");
        Double doubleTotalPrice = Double.parseDouble(stringTotalPrice);
        String stringFirstInventoryItemPrice = elements("div.inventory_item_price").first().getText().replace("$","");
        String stringLastInventoryItemPrice = elements("div.inventory_item_price").last().getText().replace("$","");
        Double doubleFirstInventoryItemPrice = Double.parseDouble(stringFirstInventoryItemPrice);
        Double doubleLastInventoryItemPrice = Double.parseDouble(stringLastInventoryItemPrice);
        double doubleFirstAndLastPrice = doubleFirstInventoryItemPrice + doubleLastInventoryItemPrice;
        Assertions.assertEquals(doubleTotalPrice, doubleFirstAndLastPrice);
    }

}




