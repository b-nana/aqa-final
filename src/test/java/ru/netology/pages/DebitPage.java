package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitPage {

    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField =$("[placeholder='22']");
    private final SelenideElement ownerField = $$("span.input__box>input").get(3);
    private final SelenideElement CVCField = $("[placeholder='999']");

    private final SelenideElement continueButton = $$("[class='button__text']").get(2);

    private final SelenideElement successNotif = $(".notification_status_ok div.notification__content");
    private final SelenideElement failedNotif = $(".notification_status_error > div.notification__content");

    private final SelenideElement incorrectCardField = $("[placeholder='0000 0000 0000 0000']").parent().parent().$(".input__sub");
    private final SelenideElement incorrectMonthField = $("[placeholder='08']").parent().parent().$(".input__sub");
    private final SelenideElement inValidMonthField = $("[placeholder='08']").parent().parent().$(".input__sub");
    private final SelenideElement incorrectYearField = $("[placeholder='22']").parent().parent().$(".input__sub");
    private final SelenideElement incorrectOwnerField = $$("span.input__box>input").get(3).parent().parent().$(".input__sub");
    private final SelenideElement incorrectCVCField = $("[placeholder='999']").parent().parent().$(".input__sub");
    private final SelenideElement сardNotif = $("[placeholder='0000 0000 0000 0000']").parent().parent().$(".input__sub");

    public void fillForm(CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        ownerField.setValue(cardInfo.getCardOwner());
        CVCField.setValue(cardInfo.getCVC());
        continueButton.click();
    }

    public void checkEmptyForm() {
        continueButton.click();
    }

    public void checkSuccessNotif() {
        successNotif.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    public void checkFailedNotif() {
        failedNotif.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    public void checkCardNumberFail() {
        incorrectCardField.shouldBe(Condition.visible);
    }

    public void checkMonthFail() {
        incorrectMonthField.shouldBe(Condition.visible);
    }

    public void checkInValMonthFail() {
        incorrectMonthField.shouldBe(Condition.visible);
    }

    public void checkYearFail() {
        incorrectYearField.shouldBe(Condition.visible);
    }

    public void checkOwnerFail() {
        incorrectOwnerField.shouldBe(Condition.visible);
    }

    public void checkCVCFail() {
        incorrectCVCField.shouldBe(Condition.visible);
    }

}