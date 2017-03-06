package net.mendler.echoxul.compiler;

import net.mendler.echoxul.ApplicationOptions;

public interface IContextFactory {
    IContext create(ApplicationOptions options);
}
