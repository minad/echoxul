package net.mendler.echoxul;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.ContextFactory;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.IXulContainer;
import net.mendler.echoxul.elements.IXulElement;
import net.mendler.echoxul.elements.XulWindow;
import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Window;
import nextapp.echo2.app.componentxml.ComponentXmlException;
import nextapp.echo2.app.componentxml.StyleSheetLoader;

import org.apache.log4j.Logger;

public class XulApplication extends ApplicationInstance {

    public static final long serialVersionUID = 0;

    private IContext globalContext;

    private Map<String, IXulElement> elementMap;

    private List<IXulElement> elements;

    private XulWindow rootWindow;

    private static final Logger logger = Logger.getLogger(XulApplication.class);

    public XulApplication() {
    }

    public XulApplication(XulWindow root, ApplicationOptions options) {
	rootWindow = root;
	elementMap = new HashMap<String, IXulElement>();
	elements = new ArrayList<IXulElement>();
	globalContext = ContextFactory.create(options);
	walkElements(rootWindow);
	setStyleSheet(options.getStyle());
    }

    @Override
    public Window init() {
	try {
	    rootWindow.init(this);
	    return rootWindow.getWindow();
	} catch (CompilerException ex) {
	    logger.error("Compiler error", ex);
	    throw new RuntimeException(ex);
	}
    }

    public IXulElement getElement(String id) {
	return elementMap.get(id);
    }

    public Collection<IXulElement> getElements() {
	return Collections.unmodifiableCollection(elements);
    }

    public XulWindow getRootWindow() {
	return rootWindow;
    }

    public IContext getGlobalContext() {
	return globalContext;
    }

    public void setStyleSheet(String style) {
	try {
	    setStyleSheet(StyleSheetLoader.load(style, getClass()
		    .getClassLoader()));
	} catch (ComponentXmlException ex) {
	    logger.error("Style loading error", ex);
	}
    }

    private void walkElements(IXulElement element) {
	elements.add(element);

	String id = element.getID();
	if (id != null) {
	    elementMap.put(id, element);
	    globalContext.set(id, element);
	}

	if (element instanceof IXulContainer) {
	    IXulContainer container = (IXulContainer) element;
	    Iterator<IXulElement> i = container.getChildren().iterator();
	    while (i.hasNext())
		walkElements(i.next());
	}
    }
}
