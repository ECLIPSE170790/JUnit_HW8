package safron_hw8;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import safron_hw8.domain.MenuItem;
import safron_hw8.page.YandexMainPage;

import java.util.List;
import java.util.stream.Stream;

@ExtendWith({SimpleCallback.class, YandexTestCondition.class})
public class ParallelTest {

    private static String name = "Dima";

    static Stream<Arguments> testWithMethodSource() {
        return  Stream.of(
                Arguments.of(
                        1, name, List.of("Вася", "Петя")
                ),
                Arguments.of(
                        2, name,List.of("Дима", "Саша")
                )
        );
    }

    @MethodSource("testWithMethodSource")
    @ParameterizedTest()
    void testWithMethodSource(int i, String str, List list) {
        doSmth(list);
    }

    private  void doSmth(List names) {
        System.out.println(names);
    }

    @EnumSource(value = MenuItem.class, names = {"SEARCH"}, mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest(name = "{1}")
    //void checkSearchResultForSeveralMenuItems(int i, String str, List<?> List){
    void checkSearchResultForSeveralMenuItems(MenuItem menuItem){
        Configuration.startMaximized = true;
        Selenide.open("http://ya.ru");
        new YandexMainPage().doSearch("selenide")
                .switchToMenuItem(menuItem);
        System.out.println();

    }


    @EnumSource()
    @CsvSource({
            "17, qa.guru, Very complex displayed name",
            "18, selenide, Very complex displayed name"
    })

    @ParameterizedTest(name = "{1}")
    void testWithComplexName(int allureId, String searchQuery, String testName){
        MenuItem menuItem0 = MenuItem.IMAGES;
        MenuItem menuItem1 = MenuItem.SEARCH;

        Configuration.startMaximized = true;
        Selenide.open("http://ya.ru");
        new YandexMainPage().doSearch(searchQuery)
                .checkResults(searchQuery);

    }

    @ValueSource(strings = {
            "qa.guru",
            "selenide",
            "qameta",
            "allure"
    })

    @ParameterizedTest(name = "{0} test")
    void yandexSearchTest(String searchQuery, TestInfo testInfo) {
        Configuration.startMaximized = true;
        Selenide.open("http://ya.ru");
        new YandexMainPage().doSearch(searchQuery)
                .checkResults(searchQuery);

        System.out.println("Config for test: "
                + testInfo.getDisplayName()
                + " "
                + Configuration.startMaximized);
    }

    @DisplayName("JDI test")
    @Test
    void minimizedWindowTest(TestInfo testInfo) {
        Configuration.startMaximized = false;
        Selenide.open("http://ya.ru");
        new YandexMainPage().doSearch("JDI")
                .checkResults("JDI");
        System.out.println("Config for test: "
                + testInfo.getDisplayName()
                + " "
                + Configuration.startMaximized);
    }
}
