package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.XulApplication;
import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import nextapp.echo2.app.Component;

public abstract class XulElement implements IXulElement {

    private String id;

    private IXulElement parent;

    private XulApplication application;

    private IContext localContext;

    private Map<String, String> attrs;

    private String content;

    public XulElement() {
    }

    public XulElement(Map<String, String> attrs) {
	this(attrs, null);
    }

    public XulElement(Map<String, String> attrs, String content) {
	this.attrs = attrs;
	this.content = content;

	String id = getAttribute("id");
	if (id != null)
	    this.id = id;
    }

    public final String getID() {
	return id;
    }

    public final IXulElement getParent() {
	return parent;
    }

    public final void setParent(IXulElement parent) {
	if (this.parent != null && parent != null)
	    throw new RuntimeException("Parent already set");
	this.parent = parent;
    }

    public Component getComponent() {
	return null;
    }

    public XulApplication getApplication() {
	return application;
    }

    public void init(XulApplication app) throws CompilerException {
	application = app;
	internalInit();
    }

    public IXulElement clone() {
	try {
	    XulElement element = (XulElement) super.clone();
	    element.parent = null;
	    element.application = null;
	    element.localContext = null;
	    return element;
	} catch (CloneNotSupportedException ex) {
	    throw new RuntimeException(ex);
	}
    }

    protected final IContext getLocalContext() {
	if (localContext == null) {
	    localContext = application.getGlobalContext().createChild();
	    initLocalContext(localContext);
	}
	return localContext;
    }

    protected void internalInit() throws CompilerException {
    }

    protected void initLocalContext(IContext context) {
	context.set("element", this);
	if (getComponent() != null)
	    context.set("component", getComponent());
    }

    protected final String getAttribute(String key) {
	return attrs.get(key);
    }

    protected final String getContent() {
	return content;
    }
}
