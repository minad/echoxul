package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import nextapp.echo2.app.Component;
import echopointng.ExpandableSection;
import echopointng.TitleBar;

public class XulExpandable extends XulContainer {

    public static final long serialVersionUID = 0;

    private ExpandableSection expandable;

    public XulExpandable() {
    }

    public XulExpandable(Map<String, String> attrs) {
	super(attrs);
    }

    public Component getExpandable() {
	return expandable;
    }

    @Override
    protected void internalAddChild(IXulElement child) {
	if (child.getComponent() != null && expandable != null) {
	    if (child instanceof XulTitleBar) {
		expandable.setTitleBar((TitleBar) child.getComponent());
	    } else if (child.getComponent() != null)
		expandable.add(child.getComponent());
	}
    }

    @Override
    protected void internalRemoveChild(IXulElement child) {
	if (child.getComponent() != null && expandable != null) {
	    if (child instanceof XulTitleBar) {
		expandable.setTitleBar(null);
	    } else if (child.getComponent() != null)
		expandable.remove(child.getComponent());
	}
    }

    @Override
    public Component getComponent() {
	return expandable;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("expandable", expandable);
    }

    @Override
    protected void internalInit() throws CompilerException {
	expandable = new ExpandableSection();

	String value = getAttribute("expanded");
	expandable.setExpanded(Boolean.parseBoolean(value));
    }

    @Override
    public XulExpandable clone() {
	XulExpandable expandable = (XulExpandable) super.clone();
	expandable.expandable = null;
	return expandable;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulExpandable(attrs);
	}
    };
}
