package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlUtils;
import ru.netology.pages.CreditPage;
import ru.netology.pages.MainPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreditPageTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        SqlUtils.clearTables();
        open("http://localhost:8080");
    }

    //Успешная оплата кредитной картой
    @Test
    void shouldBeSuccessfulWhenUsingApprovedCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getApprovedCardInfo());
        creditPage.checkSuccessNotif();
        assertEquals("APPROVED", SqlUtils.getStatusCreditPurchase());
        assertEquals(1, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидный номер карты
    @Test
    void shouldDeclineWhenUsingDeclinedCard() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getDeclinedCardInfo());
        creditPage.checkFailNotif();
        assertEquals("DECLINE", SqlUtils.getStatusCreditPurchase());
        assertEquals(1, SqlUtils.getRowsCreditPurchase());
    }

    //Отправка пустой формы
    @Test
    void emptyForm() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getAllEmptyFields());
        creditPage.checkEmptyForm();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Неполный номер карты
    @Test
    void cardNumberTooShort() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getShotCardNumber());
        creditPage.checkCardFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //В номере карты есть латинские буквы
    @Test
    void lettersCardNumber() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getEnAlphabCardNumber());
        creditPage.checkCardFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //В номере карты есть кириллица
    @Test
    void ruLettersCardNumber() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getRussAlphabCardNumber());
        creditPage.checkCardFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //В номере карты есть спецсимволы
    @Test
    void symbolCardNumber() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getSymbolCardNumber());
        creditPage.checkCardFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Номер карты не заполнен
    @Test
    void emptyCardNumber() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getEmptyCardNumber());
        creditPage.checkCardFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Месяц не заполнен
    @Test
    void emptyMonth() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getEmptyMonth());
        creditPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "Месяц"
    @Test
    void invalidMonth() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getInvalidMonth());
        creditPage.checkInValMonthFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "Месяц" (латиница)
    @Test
    void lettersMonth() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getEnAlphabMonth());
        creditPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "Месяц" (кириллица)
    @Test
    void ruLettersMonth() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getRusAlphabMonth());
        creditPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "Месяц" (спецсимволы)
    @Test
    void symbolMonth() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getSymbMonth());
        creditPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Поле "Месяц" заполнено не полностью
    @Test
    void monthTooShort() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getOntDigitMonth());
        creditPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Месяц равен нулю
    @Test
    void zeroMonth() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getZeroMonth());
        creditPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Год не заполнен
    @Test
    void emptyYear() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getEmptyYear());
        creditPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "Год" (прошедший год)
    @Test
    void pastYear() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getPastYear());
        creditPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "Год" (латиница)
    @Test
    void letterYear() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getEnAlphabYear());
        creditPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "Год" (кириллица)
    @Test
    void ruLetterYear() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getRusAlphabYear());
        creditPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Поле "Владелец" не заполнено
    @Test
    void emptyOwner() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getEmptyCardOwner());
        creditPage.checkOwnerFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "Владелец" (спецсимволы)
    @Test
    void symbolOwner() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getSymbolsCardOwner());
        creditPage.checkOwnerFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "Владелец" (число)
    @Test
    void numbersOwner() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getNumericCardOwner());
        creditPage.checkOwnerFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "Владелец" (кириллица)
    @Test
    void ruOwner() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getIncorrectCardOwner());
        creditPage.checkOwnerFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Поле "CVC/CVV" не заполнено
    @Test
    void emptyCvc() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getEmptyCvc());
        creditPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "CVC/CVV" (неполный код)
    @Test
    void cvcTooShort() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getTwoDigitCvc());
        creditPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "CVC/CVV" (латиница)
    @Test
    void letterCvc() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getEnAlphabCvc());
        creditPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "CVC/CVV" (кириллица)
    @Test
    void ruLetterCvc() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getRuAlphabCvc());
        creditPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }

    //Невалидные данные в поле "CVC/CVV" (символы)
    @Test
    void symbolsCvc() {
        MainPage mainPage = new MainPage();
        CreditPage creditPage = mainPage.chooseCreditPage();
        creditPage.fillForm(DataHelper.getSymbolsCvc());
        creditPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsCreditPurchase());
    }
}