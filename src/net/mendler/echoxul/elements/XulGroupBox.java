package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Component;
import echopointng.GroupBox;

public class XulGroupBox extends XulContainer {

    public static final long serialVersionUID = 0;

    private GroupBox groupBox;

    public XulGroupBox() {
    }

    public XulGroupBox(Map<String, String> attrs) {
	super(attrs);
    }

    public Component getGroupBox() {
	return groupBox;
    }

    @Override
    public Component getComponent() {
	return groupBox;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("groupbox", groupBox);
    }

    @Override
    protected void internalInit() throws CompilerException {
	groupBox = new GroupBox();
	Util.setStyle(groupBox, getAttribute("style"));

	String value = getAttribute("width");
	if (value != null)
	    groupBox.setWidth(Util.toExtent(value));

	value = getAttribute("height");
	if (value != null)
	    groupBox.setHeight(Util.toExtent(value));

	value = getAttribute("label");
	if (value != null)
	    groupBox.setTitle(value);
    }

    @Override
    public XulGroupBox clone() {
	XulGroupBox box = (XulGroupBox) super.clone();
	box.groupBox = null;
	return box;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulGroupBox(attrs);
	}
    };
}
