package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlUtils;
import ru.netology.pages.DebitPage;
import ru.netology.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitPageTest {


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

    //Успешная оплата картой
    @Test
    void shouldBeSuccessfulWhenUsingApprovedCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getApprovedCardInfo());
        debitPage.checkSuccessNotif();
        assertEquals("APPROVED", SqlUtils.getStatusDebitPurchase());
        assertEquals(1, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидный номер карты
    @Test
    void shouldDeclineWhenUsingDeclinedCard() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getDeclinedCardInfo());
        debitPage.checkFailedNotif();
        assertEquals("DECLINED", SqlUtils.getStatusDebitPurchase());
        assertEquals(1, SqlUtils.getRowsDebitPurchase());
    }

    //Отправка пустой формы
    @Test
    void emptyForm() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getAllEmptyFields());
        debitPage.checkEmptyForm();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Неполный номер карты
    @Test
    void cardNumberTooShort() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getShotCardNumber());
        debitPage.checkCardNumberFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //В номере карты есть латинские буквы
    @Test
    void lettersCardNumber() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEnAlphabCardNumber());
        debitPage.checkCardNumberFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //В номере карты есть кириллица
    @Test
    void ruLettersCardNumber() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getRussAlphabCardNumber());
        debitPage.checkCardNumberFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //В номере карты есть спецсимволы
    @Test
    void symbolCardNumber() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getSymbolCardNumber());
        debitPage.checkCardNumberFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Номер карты не заполнен
    @Test
    void emptyCardNumber() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEmptyCardNumber());
        debitPage.checkCardNumberFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Месяц не заполнен
    @Test
    void emptyMonth() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEmptyMonth());
        debitPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "Месяц"
    @Test
    void invalidMonth() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getInvalidMonth());
        debitPage.checkInValMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "Месяц" (латиница)
    @Test
    void lettersMonth() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEnAlphabMonth());
        debitPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "Месяц" (кириллица)
    @Test
    void ruLettersMonth() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getRusAlphabMonth());
        debitPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "Месяц" (спецсимволы)
    @Test
    void symbolMonth() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getSymbMonth());
        debitPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Поле "Месяц" заполнено не полностью
    @Test
    void monthTooShort() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getOntDigitMonth());
        debitPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Месяц равен нулю
    @Test
    void zeroMonth() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getZeroMonth());
        debitPage.checkMonthFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Год не заполнен
    @Test
    void emptyYear() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEmptyYear());
        debitPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "Год" (прошедший год)
    @Test
    void pastYear() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getPastYear());
        debitPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "Год" (латиница)
    @Test
    void letterYear() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEnAlphabYear());
        debitPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "Год" (кириллица)
    @Test
    void ruLetterYear() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getRusAlphabYear());
        debitPage.checkYearFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Поле "Владелец" не заполнено
    @Test
    void emptyOwner() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEmptyCardOwner());
        debitPage.checkOwnerFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "Владелец" (спецсимволы)
    @Test
    void symbolOwner() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getSymbolsCardOwner());
        debitPage.checkOwnerFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "Владелец" (число)
    @Test
    void numbersOwner() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getNumericCardOwner());
        debitPage.checkOwnerFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "Владелец" (кириллица)
    @Test
    void ruOwner() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getIncorrectCardOwner());
        debitPage.checkOwnerFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Поле "CVC/CVV" не заполнено
    @Test
    void emptyCvc() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEmptyCvc());
        debitPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "CVC/CVV" (неполный код)
    @Test
    void cvcTooShort() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getOneDigitCvc());
        debitPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "CVC/CVV" (латиница)
    @Test
    void letterCvc() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getEnAlphabCvc());
        debitPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "CVC/CVV" (кириллица)
    @Test
    void ruLetterCvc() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getRuAlphabCvc());
        debitPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }

    //Невалидные данные в поле "CVC/CVV" (символы)
    @Test
    void symbolsCvc() {
        MainPage mainPage = new MainPage();
        DebitPage debitPage = mainPage.chooseDebitPage();
        debitPage.fillForm(DataHelper.getSymbolsCvc());
        debitPage.checkCVCFail();
        assertEquals(0, SqlUtils.getRowsDebitPurchase());
    }
}