package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Component;
import echopointng.Separator;

public class XulMenuSeparator extends XulElement {

    public static final long serialVersionUID = 0;

    private Separator separator;

    public XulMenuSeparator() {
    }

    public XulMenuSeparator(Map<String, String> attrs) {
	super(attrs);
    }

    public Component getSeparator() {
	return separator;
    }

    @Override
    public Component getComponent() {
	return separator;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("separator", separator);
    }

    @Override
    public void internalInit() throws CompilerException {
	separator = new Separator();
	Util.setStyle(separator, getAttribute("style"));
    }

    @Override
    public XulMenuSeparator clone() {
	XulMenuSeparator separator = (XulMenuSeparator) super.clone();
	separator.separator = null;
	return separator;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulMenuSeparator(attrs);
	}
    };
}
