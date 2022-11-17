package ru.netology.pages;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement debitButton = $("[class='button button_size_m button_theme_alfa-on-white']");
    private final SelenideElement creditButton = $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']");


    public DebitPage chooseDebitPage() {
        debitButton.click();
        return new DebitPage();
    }

    public CreditPage chooseCreditPage() {
        creditButton.click();
        return new CreditPage();
    }

}