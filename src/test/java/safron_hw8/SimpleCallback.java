package safron_hw8;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SimpleCallback implements BeforeEachCallback, AfterEachCallback {

    private static final String key = "str";

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        if (context.getDisplayName().contains("JDI")) {
            context.getStore(ExtensionContext.Namespace.create(context.getDisplayName()))
                    .put("str", "JDI");
        }
        System.out.println(this.toString());
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println(context.getStore(ExtensionContext.Namespace.create(context.getDisplayName()))
                        .get(key));
    }
}
