package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Component;
import echopointng.TabbedPane;
import echopointng.tabbedpane.DefaultTabModel;

public class XulTabBox extends XulContainer {

    public static final long serialVersionUID = 0;

    private TabbedPane tabBox;

    public XulTabBox() {
    }

    public XulTabBox(Map<String, String> attrs) {
	super(attrs);
    }

    @Override
    protected void internalAddChild(IXulElement child) {
	if (!(child instanceof XulTabPanel))
	    throw new RuntimeException("Child of tabbox must be tabpanel");
	XulTabPanel panel = (XulTabPanel) child;
	if (tabBox != null && panel.getComponent() != null) {
	    DefaultTabModel model = (DefaultTabModel) tabBox.getModel();
	    model.addTab(panel.getLabel(), panel.getComponent());
	}
    }

    @Override
    protected void internalRemoveChild(IXulElement child) {
	if (!(child instanceof XulTabPanel))
	    throw new RuntimeException("Child of tabbox must be tabpanel");
	XulTabPanel panel = (XulTabPanel) child;
	if (tabBox != null && panel.getComponent() != null) {
	    DefaultTabModel model = (DefaultTabModel) tabBox.getModel();
	    model.removeTabAt(model.indexOfTab(panel.getComponent()));
	}
    }

    @Override
    public Component getComponent() {
	return tabBox;
    }

    public Component getTabBox() {
	return tabBox;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("tabbox", tabBox);
    }

    @Override
    protected void internalInit() throws CompilerException {
	tabBox = new TabbedPane();
	Util.setStyle(tabBox, getAttribute("style"));

	String value = getAttribute("width");
	if (value != null)
	    tabBox.setWidth(Util.toExtent(value));

	value = getAttribute("height");
	if (value != null)
	    tabBox.setHeight(Util.toExtent(value));
    }

    @Override
    public XulTabBox clone() {
	XulTabBox box = (XulTabBox) super.clone();
	box.tabBox = null;
	return box;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulTabBox(attrs);
	}
    };
}
