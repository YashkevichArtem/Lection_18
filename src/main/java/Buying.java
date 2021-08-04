import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Buying {
    SelenideElement addItemBackpack = $("#add-to-cart-sauce-labs-backpack");
    SelenideElement addItemBikeLight = $("#add-to-cart-sauce-labs-bike-light");
    SelenideElement cartButton = $("a.shopping_cart_link");
    SelenideElement checkoutButton = $("#checkout");
    SelenideElement firstnameField = $("#first-name");
    SelenideElement lastnameField = $("#last-name");
    SelenideElement zipPostField = $("#postal-code");
    SelenideElement continueButton = $("#continue");
    SelenideElement finishButton = $("#finish");
    SelenideElement completeMessage = $("h2.complete-header");
    SelenideElement cancelButton = $("#cancel");
    SelenideElement cartList = $("div.cart_list");
    SelenideElement itemTotalPrice = $("div.summary_subtotal_label");

    public void setUserName(){
        Buying buyPage = new Buying();
        buyPage.firstnameField.sendKeys("First");
        buyPage.lastnameField.sendKeys("Last");
        buyPage.zipPostField.sendKeys("230027");
        buyPage.continueButton.click();
    }


}



