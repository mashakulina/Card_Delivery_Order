package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static ru.netology.DataGenerator.getCorrectDate;
import static ru.netology.DataGenerator.getIncorrectDate;

public class CardDeliveryOrderTest {
    private final String date = getCorrectDate(7);

    private final String invalidDate = getIncorrectDate();

    @Test
    void shouldBeReservation() {

        open("http://localhost:9999");
        $$x("//input[@type='text']").get(0).setValue("Москва");
        $x("//input[@placeholder='Дата встречи']").click();
        $x("//input[@name='name']").setValue("Иван Иванов");
        $("[name='phone']").setValue("+79672699504");
        $("[data-test-id='agreement']").click();
        $("[class='button__text']").click();
        $("[data-test-id='notification']").should(visible, Duration.ofSeconds(15));
    }
    @Test
    void shouldBeReservationOnNewDate() {

        open("http://localhost:9999");
        $$x("//input[@type='text']").get(0).setValue("Москва");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $x("//input[@placeholder='Дата встречи']").setValue(date);
        $x("//input[@name='name']").setValue("Иван Иванов");
        $("[name='phone']").setValue("+79672699504");
        $("[data-test-id='agreement']").click();
        $("[class='button__text']").click();
        $("[data-test-id='notification']").should(visible, Duration.ofSeconds(15));
    }
    @Test
    void shouldBeNotReservationOnInvalidDate() {

        open("http://localhost:9999");
        $$x("//input[@type='text']").get(0).setValue("Москва");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $x("//input[@placeholder='Дата встречи']").setValue(invalidDate);
        $x("//input[@name='name']").setValue("Иван Иванов");
        $("[name='phone']").setValue("+79672699504");
        $("[data-test-id='agreement']").click();
        $("[class='button__text']").click();
        $(byText("Заказ на выбранную дату невозможен")).should(visible, Duration.ofSeconds(15));
    }



}
