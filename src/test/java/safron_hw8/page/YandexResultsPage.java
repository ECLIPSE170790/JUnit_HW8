package safron_hw8.page;

import com.codeborne.selenide.*;
import safron_hw8.domain.MenuItem;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class YandexResultsPage {

    private ElementsCollection results = $$(".serp-item");

    public void checkResults(String expected) {
        results.shouldBe(CollectionCondition.sizeGreaterThan(0))
                .get(0)
                .shouldHave(Condition.text(expected));
    }

    public YandexResultsPage switchToMenuItem(MenuItem menuItem) {
        $$("li[role='listitem']").find(Condition.text(menuItem.getDesc())).click();
        return this;
    }
}
