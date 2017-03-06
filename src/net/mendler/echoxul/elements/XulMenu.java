package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Component;
import echopointng.Menu;
import echopointng.MenuBar;

public class XulMenu extends XulContainer {

    public static final long serialVersionUID = 0;

    private Menu menu;

    public XulMenu() {
    }

    public XulMenu(Map<String, String> attrs) {
	super(attrs);
    }

    public Component getMenu() {
	return menu;
    }

    @Override
    public Component getComponent() {
	return menu;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("menu", menu);
    }

    @Override
    protected void internalInit() throws CompilerException {
	menu = new MenuBar();
	Util.setStyle(menu, getAttribute("style"));

	String value = getAttribute("label");
	if (value != null)
	    menu.setText(value);

	menu.setHorizontal(false);
    }

    @Override
    public XulMenu clone() {
	XulMenu menu = (XulMenu) super.clone();
	menu.menu = null;
	return menu;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulMenu(attrs);
	}
    };
}
