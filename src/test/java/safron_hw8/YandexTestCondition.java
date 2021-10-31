package safron_hw8;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class YandexTestCondition implements ExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        if (Configuration.baseUrl.contains("ya.ru"))
            return ConditionEvaluationResult.enabled("Correct url");
        else
            return ConditionEvaluationResult.enabled("Test only for Yandex");
    }
}
