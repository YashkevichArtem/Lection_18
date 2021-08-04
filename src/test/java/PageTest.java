import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.elements;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PageTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Order(1)
    @Test
    public void loginTest() throws IOException{
        LoginPage loginPage = new LoginPage();
        loginPage.openLoginPage();
        loginPage.login();
        loginPage.welcomeMessage.shouldBe(Condition.visible);
        Assertions.assertEquals("PRODUCTS", loginPage.welcomeMessage.getText());
    }

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
        //Тут я рад бы был критике по поводу двух следующих строк кода ниже, так как мне кажется,
        //что это не совсем разумно использовать first() или last().
        //Буду рад подсказке :)
        itemsCollection.first().click();
        itemsCollection.first().click();
        int itemsSizeAfter = itemsCollection.size();
        Assertions.assertTrue(itemsSizeBefore>itemsSizeAfter);
        System.out.println("Товаров до их удаления из корзины: " + itemsSizeBefore);
        System.out.println("Товаров после их удаления из корзины: " + itemsSizeAfter);
    }

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
        //Тут так же у меня проблема, я добавил два товара и использую .first() и .last(), но если товаров
        //будет три или более я не выполню тест. Я не смогу выбрать допустим второй товар из трёх.
        //Очень нуждаюсь в подсказке, искал ответ на свой вопрос, гуглил, смотрел ютуб, но к сожалению ничего...
        String stringFirstInventoryItemPrice = elements("div.inventory_item_price").first().getText().replace("$","");
        String stringLastInventoryItemPrice = elements("div.inventory_item_price").last().getText().replace("$","");
        Double doubleFirstInventoryItemPrice = Double.parseDouble(stringFirstInventoryItemPrice);
        Double doubleLastInventoryItemPrice = Double.parseDouble(stringLastInventoryItemPrice);
        double doubleFirstAndLastPrice = doubleFirstInventoryItemPrice + doubleLastInventoryItemPrice;
        Assertions.assertEquals(doubleTotalPrice, doubleFirstAndLastPrice);
    }
}