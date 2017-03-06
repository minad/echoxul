package net.mendler.echoxul.elements;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

import net.mendler.echoxul.XulApplication;
import net.mendler.echoxul.compiler.CompilerException;
import nextapp.echo2.app.Component;

public abstract class XulContainer extends XulElement implements IXulContainer {

    private LinkedList<IXulElement> children = new LinkedList<IXulElement>();

    public XulContainer() {
    }

    public XulContainer(Map<String, String> attrs) {
	super(attrs);
    }

    public XulContainer(Map<String, String> attrs, String content) {
	super(attrs, content);
    }

    public final Collection<IXulElement> getChildren() {
	return Collections.unmodifiableCollection(children);
    }

    public final void addChild(IXulElement child) {
	children.add(child);
	child.setParent(this);
	internalAddChild(child);
    }

    public final void removeChild(IXulElement child) {
	children.remove(child);
	child.setParent(null);
	internalRemoveChild(child);
    }

    public int getChildCount() {
	return children.size();
    }

    @Override
    public final void init(XulApplication app) throws CompilerException {
	super.init(app);
	for (IXulElement child : children) {
	    child.init(app);
	    internalAddChild(child);
	}
    }

    @Override
    public XulContainer clone() {
	XulContainer container = (XulContainer) super.clone();
	container.children = new LinkedList<IXulElement>();
	for (IXulElement child : children)
	    container.addChild(child.clone());
	return container;
    }

    protected void internalAddChild(IXulElement child) {
	if (child.getComponent() != null && getContainerComponent() != null)
	    getContainerComponent().add(child.getComponent());
    }

    protected void internalRemoveChild(IXulElement child) {
	if (child.getComponent() != null && getContainerComponent() != null)
	    getContainerComponent().add(child.getComponent());
    }

    protected Component getContainerComponent() {
	return getComponent();
    }
}
