package web.components;

import com.codeborne.selenide.SelenideElement;
import web.pages.ShoppingCart;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Menu {

    public SelenideElement getMenu() {
        return $("[role='dialog']");
    }

    public SelenideElement getCockpit() {
        return getMenu().$(byText("Cockpit"));
    }

    public SelenideElement getCatalog() {
        return getMenu().$(byText("Warenkatalog"));
    }

    public SelenideElement getOrders() {
        return getMenu().$(byText("Bestellungen"));
    }

    public SelenideElement getOrdersInCreation() {
        return getMenu().$(byText("In Erstellung"));
    }

    public SelenideElement getOrdersWFD() {
        return getMenu().$(byText("Warte auf Anlieferung"));
    }

    public SelenideElement getOrdersWFC() {
        return getMenu().$(byText("Warte auf Kunden"));
    }

    public SelenideElement getOrdersArchive() {
        return getMenu().$(byText("Abschluss"));
    }

    public SelenideElement getImprint() {
        return getMenu().$(byText("Impressum"));
    }

    public SelenideElement getDataProtection() {
        return getMenu().$(byText("Datenschutzerklärung"));
    }

    public LanguageSelection getLanguageSelectionElement() {
        return new LanguageSelection();
    }

    public SelenideElement getMerchantText() {
        return $(byText("Mitarbeiter"));
    }

    public SelenideElement getMerchantName() {
        return $(byText("MASTERMATE MASTERMATE"));
    }

    public SelenideElement getLogOutButton() {
        return $("[data-testid='ExitToAppOutlinedIcon']");
    }

    public SelenideElement getChangeStoreButton() {
        return $(byText("Store Wechseln"));
    }

 /*   public ShoppingCart clickToShoppingCart() {
        $("[xmlns='http://www.w3.org/2000/svg']").parent().click();
        return new ShoppingCart();
    }*/


}
