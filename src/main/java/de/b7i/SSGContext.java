package de.b7i;

import org.thymeleaf.context.IContext;

import java.util.Locale;
import java.util.Set;

public class SSGContext implements IContext {

    private final String content;

    public SSGContext(String content) {
        this.content = content;
    }

    @Override
    public Locale getLocale() {
        return Locale.US;
    }

    @Override
    public boolean containsVariable(String name) {
        return true;
    }

    @Override
    public Set<String> getVariableNames() {
        return Set.of("content");
    }

    @Override
    public Object getVariable(String name) {
        return content;
    }
}
