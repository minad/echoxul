package net.mendler.echoxul.compiler;

import java.util.HashMap;

import net.mendler.echoxul.ApplicationOptions;
import net.mendler.echoxul.compiler.ant.AntContext;
import net.mendler.echoxul.compiler.beanshell.BshContext;
import net.mendler.echoxul.compiler.javascript.JsContext;

public class ContextFactory {

    private static HashMap<String, IContextFactory> factories;

    static {
	factories = new HashMap<String, IContextFactory>();

	factories.put("bsh", BshContext.Factory);
	factories.put("bshjava", BshContext.Factory);
	factories.put("beanshell", BshContext.Factory);
	
	factories.put("javascript", JsContext.Factory);
	factories.put("js", JsContext.Factory);

	factories.put("ant", AntContext.Factory);
	factories.put("antjava", AntContext.Factory);
	factories.put("java", AntContext.Factory);
    }

    public static IContext create(ApplicationOptions options) {
	if (options.getLanguage() == null)
	    throw new IllegalArgumentException("No language specified");
	IContextFactory factory = factories.get(options.getLanguage());
	if (factory == null)
	    throw new IllegalArgumentException("Context for language "
		    + options.getLanguage() + " not found");
	return factory.create(options);
    }

    private ContextFactory() {
    }
}
