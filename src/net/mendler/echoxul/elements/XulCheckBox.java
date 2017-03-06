package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.ActionHandler;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.ResourceImageReference;

public class XulCheckBox extends XulElement {

    public static final long serialVersionUID = 0;

    private CheckBox checkBox;

    public XulCheckBox() {
    }

    public XulCheckBox(Map<String, String> attrs) {
	super(attrs);
    }

    public CheckBox getCheckBox() {
	return checkBox;
    }

    @Override
    public Component getComponent() {
	return checkBox;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("checkbox", checkBox);
    }

    @Override
    public void internalInit() throws CompilerException {
	checkBox = new CheckBox();

	Util.setStyle(checkBox, getAttribute("style"));

	checkBox.setAlignment(Util.toAlignment(getAttribute("valign"),
		getAttribute("align")));

	String value = getAttribute("label");
	if (value != null)
	    checkBox.setText(value);

	value = getAttribute("tooltip");
	if (value != null)
	    checkBox.setToolTipText(value);

	value = getAttribute("disabled");
	if (value != null)
	    checkBox.setEnabled(Boolean.parseBoolean(value));

	value = getAttribute("src");
	if (value != null)
	    checkBox.setIcon(new ResourceImageReference(value));

	value = getAttribute("width");
	if (value != null)
	    checkBox.setWidth(Util.toExtent(value));

	value = getAttribute("height");
	if (value != null)
	    checkBox.setHeight(Util.toExtent(value));

	value = getAttribute("checked");
	if (value != null)
	    checkBox.setSelected(Integer.parseInt(value) != 0);

	value = getAttribute("oncommand");
	if (value != null) 
	    checkBox.addActionListener(new ActionHandler(value, getLocalContext()));
    }

    @Override
    public XulCheckBox clone() {
	XulCheckBox checkBox = (XulCheckBox) super.clone();
	checkBox.checkBox = null;
	return checkBox;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulCheckBox(attrs);
	}
    };
}
