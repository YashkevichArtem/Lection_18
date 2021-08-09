package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Buying {
    public SelenideElement addItemBackpack = $("#add-to-cart-sauce-labs-backpack");
    public SelenideElement addItemBikeLight = $("#add-to-cart-sauce-labs-bike-light");
    public SelenideElement cartButton = $("a.shopping_cart_link");
    public SelenideElement checkoutButton = $("#checkout");
    SelenideElement firstnameField = $("#first-name");
    SelenideElement lastnameField = $("#last-name");
    SelenideElement zipPostField = $("#postal-code");
    SelenideElement continueButton = $("#continue");
    public SelenideElement finishButton = $("#finish");
    public SelenideElement completeMessage = $("h2.complete-header");
    public SelenideElement cancelButton = $("#cancel");
    public SelenideElement cartList = $("div.cart_list");
    public SelenideElement itemTotalPrice = $("div.summary_subtotal_label");

    public void setUserName(){
        Buying buyPage = new Buying();
        buyPage.firstnameField.sendKeys("First");
        buyPage.lastnameField.sendKeys("Last");
        buyPage.zipPostField.sendKeys("230027");
        buyPage.continueButton.click();
    }


}



