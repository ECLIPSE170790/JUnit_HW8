package safron_hw8;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import safron_hw8.domain.MailItem;


import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static safron_hw8.domain.MailItem.*;


public class MailTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.timeout = 20000;
    }

    @EnumSource(MailItem.class)
    @ParameterizedTest()
    void enumTest(MailItem mailItem){

        Configuration.startMaximized = true;
        Selenide.open("https://mail.ru/");
        $("#q").setValue("technodom").pressEnter();
        $$(".DesktopHeader-sectionListItem").findBy(text(mailItem.getDesc()))
                .click();
        switchTo().window(1);
        $$(mailItem.getSelect()).findBy(text(mailItem.getResultSelector())).shouldBe(visible);

    }

    @CsvSource({
            "selenide, many many words of selenide",
            "java, many many words of java",
            "junit, many many words of junit"
    })

    @ParameterizedTest(name = "{0} test")
    void csvTest(String searchQuery, String manyWords) {
        Configuration.startMaximized = true;
        Selenide.open("https://mail.ru/");
        $("#q").setValue(searchQuery).pressEnter();
        $$(".Link-root").shouldBe(CollectionCondition.sizeGreaterThan(0))
                        .findBy(text(searchQuery))
                                .shouldBe(visible);
        System.out.println(searchQuery + " " + manyWords);

    }

    static Stream<Arguments> MethodSource() {
        return Stream.of(
                Arguments.of(
                        "testpochta1", "password1"
                ),
                Arguments.of(
                        "testpochta2", "password2"
                )
        );
    }

    @MethodSource("MethodSource")
    @ParameterizedTest(name = "{0}, {1}")
    void checkMailRu(String email, String password) {
        Configuration.startMaximized = true;
        Selenide.open("https://mail.ru/");
        Configuration.timeout = 10000;

        $(".email-input").click();
        $(".email-input").setValue(email);
        $(".svelte-1tib0qz").click();
        $(".password-input").click();
        $(".password-input").setValue(password);
        $("[data-testid=login-to-mail]").click();
        $(".error.svelte-1tib0qz").shouldHave(Condition.text("Неверное имя или пароль"));
    }

}
