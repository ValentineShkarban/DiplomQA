package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TravellingOfDay {
    private SelenideElement heading = $$("h2").find(text("Путешествие дня"));
    private SelenideElement paybutton = $$("button").find(exactText("Купить"));
    private SelenideElement payheading = $$("h3").find(text("Оплата по карте"));
    private SelenideElement creditbutton = $$("button").find(exactText("Купить в кредит"));
    private SelenideElement creditheading = $$("h3").find(text("Кредит по данным карты"));
    private SelenideElement cardNumber = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement cardNumberError = $(byText("Номер карты")).parent().$(".input__sub");
    private SelenideElement month = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement monthError = $(byText("Месяц")).parent().$(".input__sub");
    private SelenideElement year = $(byText("Год")).parent().$(".input__control");
    private SelenideElement yearError = $(byText("Год")).parent().$(".input__sub");
    private SelenideElement owner = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement ownerError = $(byText("Владелец")).parent().$(".input__sub");
    private SelenideElement cvc = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement cvcError = $(byText("CVC/CVV")).parent().$(".input__sub");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement notificationOk = $(byText("Успешно")).parent().$(".notification__title");
    private SelenideElement notificationError = $(byText("Ошибка")).parent().$(".notification__title");

    public TravellingOfDay() {
        heading.shouldBe(visible);
    }

    public void cardPayment() {
        paybutton.click();
        payheading.shouldBe(visible);
    }

    public void cardCredit() {
        creditbutton.click();
        creditheading.shouldBe(visible);
    }

    public void sendingDate(DataHelper.CardInfo info) {
        cardNumber.setValue(info.getNumberCard());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        owner.setValue(info.getOwner());
        cvc.setValue(info.getCvc());
        continueButton.click();
    }


    public void bankApproved() {
        notificationOk.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void bankDeclined() {
        notificationError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void inputInvalidFields() {
        cardNumberError.shouldBe(visible);
        monthError.shouldBe(visible);
        yearError.shouldBe(visible);
        ownerError.shouldBe(visible);
        cvcError.shouldBe(visible);
    }

    public void inputInvalidCardNumber() {
        cardNumberError.shouldBe(visible);
    }

    public void inputInvalidMonth() {
        monthError.shouldBe(visible);
    }

    public void inputInvalidYear() {
        yearError.shouldBe(visible);
    }

    public void inputInvalidOwner() {
        ownerError.shouldBe(visible);
    }

    public void inputInvalidCVC() {
        cvcError.shouldBe(visible);
    }
}