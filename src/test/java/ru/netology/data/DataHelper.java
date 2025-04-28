package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DataHelper {
    private static final String validCard = "0000 0000 0000 0001";
    private static final String invalidCard = "0000 0000 0000 0002";
    private static final String emptyCardNumber = "";
    private static final String emptyMonth = "";
    private static final String emptyYear = "";
    private static final String emptyOwner = "";
    private static final String emptyCVC = "";
    private static final String space = " ";
    private static final String[] simbols = new String[] {".", ",", "/", "!", "'", ";", ":", "!", "@", "#", "$", "%"};
    private static final String[] numbers = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private DataHelper() {
    }

    private static String getIncompleteCardNumber() {
        return validCard.substring(0, 18);
    }

    private static String getValidMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String getOverdueMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    private static String getLessMonth() {
        return "00";
    }

    private static String getMoreMonth() {
        return "13";
    }

    private static String getValidYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String getOverdueYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    private static String getYearOfFuture() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }


    private static String getSimbols() {
        Random random = new Random();
        var firstSimbol = simbols[random.nextInt(12)];
        var secondSimbol = simbols[random.nextInt(12)];
        return firstSimbol + secondSimbol;
    }

    private static String getNumber() {
        Random random = new Random();
        var firstNumber = numbers[random.nextInt(10)];
        var secondNumber = numbers[random.nextInt(9) + 1];
        return firstNumber + secondNumber;
    }

    private static String getOwner() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    private static String getCVC() {
        Random random = new Random();
        var firstNumber = numbers[random.nextInt(10)];
        var secondNumber = getNumber();
        return firstNumber + secondNumber;
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo(validCard, getValidMonth(), getValidYear(), getOwner(), getCVC());
    }

    public static CardInfo getDeclined() {
        return new CardInfo(invalidCard, getValidMonth(), getValidYear(), getOwner(), getCVC());
    }

    public static CardInfo getEmptyForm() {
        return new CardInfo(emptyCardNumber, emptyMonth, emptyYear, emptyOwner, emptyCVC);
    }

    public static CardInfo getWithEmptyCardNumber() {
        return new CardInfo(emptyCardNumber, getValidMonth(), getValidYear(), getOwner(), getCVC());
    }

    public static CardInfo getWithEmptyMonth() {
        return new CardInfo(validCard, emptyMonth, getValidYear(), getOwner(), getCVC());
    }

    public static CardInfo getWithEmptyYear() {
        return new CardInfo(validCard, getValidMonth(), emptyYear, getOwner(), getCVC());
    }

    public static CardInfo getWithEmptyOwner() {
        return new CardInfo(validCard, getValidMonth(), getValidYear(), emptyOwner, getCVC());
    }

    public static CardInfo getWithEmptyCVC() {
        return new CardInfo(validCard, getValidMonth(), getValidYear(), getOwner(), emptyCVC);
    }

    public static CardInfo getCardWithIncompleteNumber() {
        return new CardInfo(getIncompleteCardNumber(), getValidMonth(), getValidYear(), getOwner(), getCVC());
    }

    public static CardInfo getCardWithOverdueMonth() {
        return new CardInfo(validCard, getOverdueMonth(), getValidYear(), getOwner(), getCVC());
    }

    public static CardInfo getCardWithLessMonth() {
        return new CardInfo(validCard, getLessMonth(), getValidYear(), getOwner(), getCVC());
    }

    public static CardInfo getCardWithMoreMonth() {
        return new CardInfo(validCard, getMoreMonth(), getValidYear(), getOwner(), getCVC());
    }

    public static CardInfo getCardWithOverdueYear() {
        return new CardInfo(validCard, getValidMonth(), getOverdueYear(), getOwner(), getCVC());
    }

    public static CardInfo getCardWithYearOfFuture() {
        return new CardInfo(validCard, getValidMonth(), getYearOfFuture(), getOwner(), getCVC());
    }

    public static CardInfo getCardWithSpaceInFieldOwner() {
        return new CardInfo(validCard, getValidMonth(), getValidYear(), space, getCVC());
    }

    public static CardInfo getCardWithSimbolsInFieldOwner() {
        return new CardInfo(validCard, getValidMonth(), getValidYear(), getSimbols(), getCVC());
    }

    public static CardInfo getCardWithNumberInFieldOwner() {
        return new CardInfo(validCard, getValidMonth(), getValidYear(), getNumber(), getCVC());
    }

    public static CardInfo getCardWithIncompleteCVC() {
        return new CardInfo(validCard, getValidMonth(), getValidYear(), getOwner(), getNumber());
    }

    @Value
    public static class CardInfo {
        private String numberCard;
        private String month;
        private String year;
        private String owner;
        private String cvc;
    }

}