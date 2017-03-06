package net.mendler.echoxul.elements;

import java.util.HashMap;
import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;

public class ElementFactory {

    private static HashMap<String, IElementFactory> factories;

    static {
	factories = new HashMap<String, IElementFactory>();
	factories.put("window", XulWindow.Factory);
	factories.put("button", XulButton.Factory);
	factories.put("checkbox", XulCheckBox.Factory);
	factories.put("radio", XulRadio.Factory);
	factories.put("radiogroup", XulRadioGroup.Factory);
	factories.put("script", XulScript.Factory);
	factories.put("box", XulBox.Factory);
	factories.put("hbox", XulBox.HBoxFactory);
	factories.put("vbox", XulBox.VBoxFactory);
	factories.put("groupbox", XulGroupBox.Factory);
	factories.put("menu", XulMenu.Factory);
	factories.put("menubar", XulMenuBar.Factory);
	factories.put("menuitem", XulMenuItem.Factory);
	factories.put("menuseparator", XulMenuSeparator.Factory);
	factories.put("expandable", XulExpandable.Factory);
	factories.put("titlebar", XulTitleBar.Factory);
	factories.put("splitpane", XulSplitPane.Factory);
	factories.put("richtext", XulRichText.Factory);
	factories.put("tabbox", XulTabBox.Factory);
	factories.put("tabpanel", XulTabPanel.Factory);
    }

    public static IXulElement create(String name, Map<String, String> attrs,
	    String content) throws CompilerException {
	IElementFactory factory = factories.get(name);
	if (factory != null)
	    return factory.create(attrs, content);
	throw new RuntimeException("Unknown component " + name);
    }

    private ElementFactory() {
    }
}
