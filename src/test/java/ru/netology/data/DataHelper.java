package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

    public class DataHelper {

        private DataHelper() {
        }


        public static String generateDate(int month, String pattern) {
            return LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern(pattern));
        }

        public static String generatePastDate(int month, String pattern) {
            return LocalDate.now().minusMonths(month).format(DateTimeFormatter.ofPattern(pattern));
        }

        public static String generatePastYear() {
            return generatePastDate(12, "YY");
        }

        public static String generateValidYear() {
            return generateDate(15, "YY");
        }

        public static String generateValidMonth() {
            return generateDate(6, "MM");
        }


        public static String generateValidCardOwner() {
            Faker faker = new Faker(new Locale("en"));
            return faker.name().firstName() + " " + faker.name().lastName();
        }

        public static String generateInValidCardOwner() {
            Faker faker = new Faker(new Locale("ru"));
            return faker.name().fullName();
        }

        public static String generateCVC() {
            Random rnd = new Random();
            int cvc = rnd.nextInt(900) + 100;
            return String.valueOf(cvc);
        }

        public static String generateOneDigitMonth() {
            Random rnd = new Random();
            int mon = rnd.nextInt(9) + 1;
            return String.valueOf(mon);
        }

        public static CardInfo getApprovedCardInfo() {
            return new CardInfo("4444444444444441", generateValidMonth(), generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getDeclinedCardInfo() {
            return new CardInfo("4444444444444442", generateValidMonth(), generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getAllEmptyFields() {
            return new CardInfo("", "", "", "", "");
        }

        public static CardInfo getEmptyCardNumber() {
            return new CardInfo("", generateValidMonth(), generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getShotCardNumber() {
            return new CardInfo("444444444444441", generateValidMonth(), generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getEnAlphabCardNumber() {
            return new CardInfo("lkdfbhytasvbnjdf", generateValidMonth(), generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getRussAlphabCardNumber() {
            return new CardInfo("пвпикелтьясмцува", generateValidMonth(), generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getSymbolCardNumber() {
            return new CardInfo("@#$%^&*(><.,?/!+", generateValidMonth(), generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getInvalidMonth() {
            return new CardInfo("4444444444444441", "13", generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getEnAlphabMonth() {
            return new CardInfo("4444444444444441", "en", generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getRusAlphabMonth() {
            return new CardInfo("4444444444444441", "ру", generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getSymbMonth() {
            return new CardInfo("4444444444444441", "&&", generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getZeroMonth() {
            return new CardInfo("4444444444444441", "00", generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getOntDigitMonth() {
            return new CardInfo("4444444444444441", generateOneDigitMonth(), generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getEmptyMonth() {
            return new CardInfo("4444444444444441", "", generateValidYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getEmptyYear() {
            return new CardInfo("4444444444444441", generateValidMonth(), "", generateValidCardOwner(), generateCVC());
        }


        public static CardInfo getPastYear() {
            return new CardInfo("4444444444444441", generateValidMonth(), generatePastYear(), generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getEnAlphabYear() {
            return new CardInfo("4444444444444441", generateValidMonth(), "aj", generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getRusAlphabYear() {
            return new CardInfo("4444444444444441", generateValidMonth(), "пф", generateValidCardOwner(), generateCVC());
        }

        public static CardInfo getEmptyCardOwner() {
            return new CardInfo("4444444444444441", generateValidMonth(), generateValidYear(), "", generateCVC());
        }

        public static CardInfo getIncorrectCardOwner() {
            return new CardInfo("4444444444444441", generateValidMonth(), generateValidYear(), generateInValidCardOwner(), generateCVC());
        }

        public static CardInfo getSymbolsCardOwner() {
            return new CardInfo("4444444444444441", generateValidMonth(), generateValidYear(), "!@#$%^& *()><}{", generateCVC());
        }

        public static CardInfo getNumericCardOwner() {
            return new CardInfo("4444444444444441", generateValidMonth(), generateValidYear(), "98745321 23165487", generateCVC());
        }

        public static CardInfo getEmptyCvc() {
            return new CardInfo("4444444444444441", generateValidMonth(), generateValidYear(), generateValidCardOwner(), "");
        }

        public static CardInfo getEnAlphabCvc() {
            return new CardInfo("4444444444444441", generateValidMonth(), generateValidYear(), generateValidCardOwner(), "eng");
        }

        public static CardInfo getRuAlphabCvc() {
            return new CardInfo("4444444444444441", generateValidMonth(), generateValidYear(), generateValidCardOwner(), "рус");
        }

        public static CardInfo getSymbolsCvc() {
            return new CardInfo("4444444444444441", generateValidMonth(), generateValidYear(), generateValidCardOwner(), "12@");
        }

        public static CardInfo getTwoDigitCvc() {
            return new CardInfo("4444444444444441", generateValidMonth(), generateValidYear(), generateValidCardOwner(), "54");
        }

        public static CardInfo getOneDigitCvc() {
            return new CardInfo("4444444444444441", generateValidMonth(), generateValidYear(), generateValidCardOwner(), "9");
        }
    }


