package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Row;

public class XulBox extends XulContainer {

    public static final long serialVersionUID = 0;

    private Component box;

    private boolean vertical;

    public XulBox() {
    }

    public XulBox(boolean vert, Map<String, String> attrs) {
	super(attrs);
	vertical = vert;
    }

    public Component getBox() {
	return box;
    }

    @Override
    public Component getComponent() {
	return box;
    }

    @Override
    protected void internalInit() throws CompilerException {
	if (vertical) {
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
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("box", box);
    }

    @Override
    public XulBox clone() {
	XulBox box = (XulBox) super.clone();
	box.box = null;
	return box;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    String orient = attrs.get("orient");
	    return new XulBox(orient == null || orient.equals("vertical"),
		    attrs);
	}
    };

    public static final IElementFactory HBoxFactory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulBox(false, attrs);
	}
    };

    public static final IElementFactory VBoxFactory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulBox(true, attrs);
	}
    };
}
