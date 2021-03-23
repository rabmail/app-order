package ru.netology.domain;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;


public class CallbackTest {

    @Test
    void shouldTestValidation() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Василий Пупкин");
        $("[data-test-id=phone] input").setValue("+79102280000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestValidationFIO() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79102280000");
        $("[data-test-id=agreement]").click();
        $("button").click();
       // $(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        $$(".input__sub").get(0).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestValidationPhone() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Вася Пупкин");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();

        // $$(".input__sub").shouldHave(size(2));
        $$(".input__sub").get(1).shouldHave(exactText("Поле обязательно для заполнения"));
        //  $$(".input__sub").shouldHave(texts("Укажите точно как в паспорте", "Поле обязательно для заполнения"));
    }
}
