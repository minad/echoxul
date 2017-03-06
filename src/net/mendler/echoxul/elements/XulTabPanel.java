package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.ContentPane;

public class XulTabPanel extends XulContainer {

    public static final long serialVersionUID = 0;

    private String label;

    private ContentPane tabPanel;

    public XulTabPanel() {
    }

    public XulTabPanel(Map<String, String> attrs) {
	super(attrs);
	label = getAttribute("label");
    }

    public ContentPane getTabPanel() {
	return tabPanel;
    }

    public String getLabel() {
	return label;
    }

    @Override
    public Component getComponent() {
	return tabPanel;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("tabpanel", tabPanel);
    }

    @Override
    protected void internalInit() throws CompilerException {
	tabPanel = new ContentPane();
    }

    @Override
    public XulTabPanel clone() {
	XulTabPanel panel = (XulTabPanel) super.clone();
	panel.tabPanel = null;
	return panel;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulTabPanel(attrs);
	}
    };
}
