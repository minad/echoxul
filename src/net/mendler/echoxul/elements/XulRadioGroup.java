package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.RadioButton;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.button.ButtonGroup;
import nextapp.echo2.app.event.ChangeEvent;
import nextapp.echo2.app.event.ChangeListener;

public class XulRadioGroup extends XulContainer {

    public static final long serialVersionUID = 0;

    private Component box;

    private ButtonGroup group;

    public XulRadioGroup() {
    }

    public XulRadioGroup(Map<String, String> attrs) {
	super(attrs);
    }

    public ButtonGroup getButtonGroup() {
	return group;
    }

    public Component getBox() {
	return box;
    }

    @Override
    protected void internalAddChild(IXulElement child) {
	Component component = child.getComponent();
	if (component != null && box != null) {
	    box.add(component);
	    if (component instanceof RadioButton) {
		RadioButton button = (RadioButton) component;
		button.setGroup(group);
		button.addChangeListener(new ChangeListener() {
		    public static final long serialVersionUID = 0;

		    public void stateChanged(ChangeEvent e) {
			group.updateSelection((RadioButton) e.getSource());
		    }
		});
	    }
	}
    }

    @Override
    protected void internalRemoveChild(IXulElement child) {
	Component component = child.getComponent();
	if (component != null && box != null) {
	    box.remove(component);
	    if (component instanceof RadioButton) {
		RadioButton button = (RadioButton) component;
		button.setGroup(null);
	    }
	}
    }

    @Override
    public Component getComponent() {
	return box;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("group", group);
	c.set("box", box);
    }

    @Override
    protected void internalInit() throws CompilerException {
	group = new ButtonGroup();

	String value = getAttribute("orient");
	if (value == null || value.equals("vertical")) {
	    box = new Column();
	} else {
	    Row row = new Row();
	    row.setAlignment(Util.toAlignment(getAttribute("valign"),
		    getAttribute("align")));
	    box = row;
	}

	Util.setStyle(box, getAttribute("style"));
    }

    @Override
    public XulRadioGroup clone() {
	XulRadioGroup group = (XulRadioGroup) super.clone();
	group.group = null;
	group.box = null;
	return group;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulRadioGroup(attrs);
	}
    };
}
