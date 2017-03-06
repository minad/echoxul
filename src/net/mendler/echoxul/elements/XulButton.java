package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.ActionHandler;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.ResourceImageReference;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.button.ToggleButton;

public class XulButton extends XulElement {

    public static final long serialVersionUID = 0;

    private AbstractButton button;

    public XulButton() {
    }

    public XulButton(Map<String, String> attrs) {
	super(attrs);
    }

    public Button getButton() {
	return (Button) button;
    }

    public ToggleButton getToggleButton() {
	return (ToggleButton) button;
    }

    @Override
    public Component getComponent() {
	return button;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("button", button);
    }

    @Override
    public void internalInit() throws CompilerException {
	String value = getAttribute("toggled");
	if (value != null) {
	    ToggleButton toggleButton = new CheckBox();
	    toggleButton.setSelected(Integer.parseInt(value) != 0);
	    button = toggleButton;
	} else
	    button = new Button();

	Util.setStyle(button, getAttribute("style"));

	button.setAlignment(Util.toAlignment(getAttribute("valign"),
		getAttribute("align")));

	value = getAttribute("label");
	if (value != null)
	    button.setText(value);

	value = getAttribute("tooltip");
	if (value != null)
	    button.setToolTipText(value);

	value = getAttribute("disabled");
	if (value != null)
	    button.setEnabled(Boolean.parseBoolean(value));

	value = getAttribute("image");
	if (value != null)
	    button.setIcon(new ResourceImageReference(value));

	value = getAttribute("width");
	if (value != null)
	    button.setWidth(Util.toExtent(value));

	value = getAttribute("height");
	if (value != null)
	    button.setHeight(Util.toExtent(value));

	value = getAttribute("onclick");
	if (value != null)
	    button.addActionListener(new ActionHandler(value, getLocalContext()));

	value = getAttribute("oncommand");
	if (value != null)
	    button.addActionListener(new ActionHandler(value, getLocalContext()));
    }

    @Override
    public XulButton clone() {
	XulButton button = (XulButton) super.clone();
	button.button = null;
	return button;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulButton(attrs);
	}
    };
}
