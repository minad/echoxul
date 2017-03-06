package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.SplitPane;

public class XulSplitPane extends XulContainer {

    public static final long serialVersionUID = 0;

    private SplitPane splitPane;

    public XulSplitPane() {
    }

    public XulSplitPane(Map<String, String> attrs) {
	super(attrs);
    }

    public Component getSplitPane() {
	return splitPane;
    }

    @Override
    public Component getComponent() {
	return splitPane;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("splitpane", splitPane);
    }

    @Override
    protected void internalInit() throws CompilerException {
	splitPane = new SplitPane();
	Util.setStyle(splitPane, getAttribute("style"));

	String value = getAttribute("resizeable");
	if (value != null)
	    splitPane.setResizable(Boolean.parseBoolean(value));

	value = getAttribute("orient");
	if (value != null)
	    splitPane
		    .setOrientation(value.equals("horizontal") ? SplitPane.ORIENTATION_HORIZONTAL
			    : SplitPane.ORIENTATION_VERTICAL);
    }

    @Override
    public XulSplitPane clone() {
	XulSplitPane pane = (XulSplitPane) super.clone();
	pane.splitPane = null;
	return pane;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulSplitPane(attrs);
	}
    };
}
