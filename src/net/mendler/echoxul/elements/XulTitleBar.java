package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Component;
import echopointng.TitleBar;

public class XulTitleBar extends XulElement {

    public static final long serialVersionUID = 0;

    private TitleBar titleBar;

    public XulTitleBar() {
    }

    public XulTitleBar(Map<String, String> attrs) {
	super(attrs);
    }

    public Component getTitleBar() {
	return titleBar;
    }

    @Override
    public Component getComponent() {
	return titleBar;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("titlebar", titleBar);
    }

    @Override
    public void internalInit() throws CompilerException {
	titleBar = new TitleBar();
	Util.setStyle(titleBar, getAttribute("style"));

	String value = getAttribute("label");
	if (value != null)
	    titleBar.setTitle(value);
    }

    @Override
    public XulTitleBar clone() {
	XulTitleBar title = (XulTitleBar) super.clone();
	title.titleBar = null;
	return title;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulTitleBar(attrs);
	}
    };
}
