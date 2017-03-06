package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.ActionHandler;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.RadioButton;
import nextapp.echo2.app.ResourceImageReference;

public class XulRadio extends XulElement {

    public static final long serialVersionUID = 0;

    private RadioButton radio;

    public XulRadio() {
    }

    public XulRadio(Map<String, String> attrs) {
	super(attrs);
    }

    public RadioButton getRadio() {
	return radio;
    }

    @Override
    public Component getComponent() {
	return radio;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("radio", radio);
    }

    @Override
    public void internalInit() throws CompilerException {
	radio = new RadioButton();
	Util.setStyle(radio, getAttribute("style"));

	radio.setAlignment(Util.toAlignment(getAttribute("valign"),
		getAttribute("align")));

	String value = getAttribute("label");
	if (value != null)
	    radio.setText(value);

	value = getAttribute("tooltip");
	if (value != null)
	    radio.setToolTipText(value);

	value = getAttribute("disabled");
	if (value != null)
	    radio.setEnabled(Boolean.parseBoolean(value));

	value = getAttribute("src");
	if (value != null)
	    radio.setIcon(new ResourceImageReference(value));

	value = getAttribute("width");
	if (value != null)
	    radio.setWidth(Util.toExtent(value));

	value = getAttribute("height");
	if (value != null)
	    radio.setHeight(Util.toExtent(value));

	value = getAttribute("checked");
	if (value != null)
	    radio.setSelected(Integer.parseInt(value) != 0);

	value = getAttribute("oncommand");
	if (value != null) 
	    radio.addActionListener(new ActionHandler(value, getLocalContext()));
    }

    @Override
    public XulRadio clone() {
	XulRadio radio = (XulRadio) super.clone();
	radio.radio = null;
	return radio;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulRadio(attrs);
	}
    };
}
