package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.ActionHandler;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Component;
import echopointng.MenuItem;

public class XulMenuItem extends XulElement {

    public static final long serialVersionUID = 0;

    private MenuItem menuItem;

    public XulMenuItem() {
    }

    public XulMenuItem(Map<String, String> attrs) {
	super(attrs);
    }

    public Component getMenuItem() {
	return menuItem;
    }

    @Override
    public Component getComponent() {
	return menuItem;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("menuitem", menuItem);
    }

    @Override
    public void internalInit() throws CompilerException {
	menuItem = new MenuItem();
	Util.setStyle(menuItem, getAttribute("style"));

	String value = getAttribute("label");
	if (value != null)
	    menuItem.setText(value);

	value = getAttribute("oncommand");
	if (value != null) 
	    menuItem.addActionListener(new ActionHandler(value, getLocalContext()));
    }

    @Override
    public XulMenuItem clone() {
	XulMenuItem item = (XulMenuItem) super.clone();
	item.menuItem = null;
	return item;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulMenuItem(attrs);
	}
    };
}
