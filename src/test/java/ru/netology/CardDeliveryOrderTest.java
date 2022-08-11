package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {

    private String stringDate;

    @BeforeEach
    void dateToBeSet() {
        LocalDate today = LocalDate.now();
        LocalDate dateToBeSet = today.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        stringDate = dateToBeSet.format(formatter);
    }

    @Test
    void test() {

        Configuration.holdBrowserOpen = true;

        open("http://localhost:9999");
        $$x("//input[@type='text']").get(0).setValue("Москва");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $x("//input[@placeholder='Дата встречи']").setValue(stringDate);
        $x("//input[@name='name']").setValue("Иван Иванов");
        $("[name='phone']").setValue("+79672699504");
        $("[data-test-id='agreement']").click();
        $("[class='button__text']").click();
        $("[data-test-id='notification']").should(visible, Duration.ofSeconds(15));

    }
}
