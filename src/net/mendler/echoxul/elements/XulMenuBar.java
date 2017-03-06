package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Component;
import echopointng.MenuBar;

public class XulMenuBar extends XulContainer {

    public static final long serialVersionUID = 0;

    private MenuBar menuBar;

    public XulMenuBar() {
    }

    public XulMenuBar(Map<String, String> attrs) {
	super(attrs);
    }

    public Component getMenuBar() {
	return menuBar;
    }

    @Override
    public Component getComponent() {
	return menuBar;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("menubar", menuBar);
    }

    @Override
    protected void internalInit() throws CompilerException {
	menuBar = new MenuBar();
	Util.setStyle(menuBar, getAttribute("style"));
    }

    @Override
    public XulMenuBar clone() {
	XulMenuBar menuBar = (XulMenuBar) super.clone();
	menuBar.menuBar = null;
	return menuBar;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulMenuBar(attrs);
	}
    };
}
