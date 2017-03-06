package net.mendler.echoxul.elements;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.Handler;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Window;

public class XulWindow extends XulContainer {

    public static final long serialVersionUID = 0;

    private Window window;

    public XulWindow() {
    }

    public XulWindow(Map<String, String> attrs) {
	super(attrs);
    }

    public Window getWindow() {
	return window;
    }

    @Override
    protected Component getContainerComponent() {
	return window.getContent();
    }

    @Override
    public Component getComponent() {
	return window;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("window", window);
    }

    @Override
    protected void internalInit() throws CompilerException {
	window = new Window();
	Util.setStyle(window, getAttribute("style"));

	String value = getAttribute("title");
	if (value != null)
	    window.setTitle(value);

	value = getAttribute("onload");
	if (value != null) {
	    final Handler handler = new Handler(value,
		    getLocalContext());
	    window.addPropertyChangeListener(new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent e) {
		    if (e.getPropertyName().equals(
			    Window.VISIBLE_CHANGED_PROPERTY)
			    && (Boolean) e.getNewValue())
			handler.execute();
		}
	    });
	}

	value = getAttribute("onunload");
	if (value != null) {
	    final Handler handler = new Handler(value,
		    getLocalContext());
	    window.addPropertyChangeListener(new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent e) {
		    if (e.getPropertyName().equals(
			    Window.VISIBLE_CHANGED_PROPERTY)
			    && !(Boolean) e.getNewValue())
			handler.execute();
		}
	    });
	}
    }

    @Override
    public XulWindow clone() {
	XulWindow window = (XulWindow) super.clone();
	window.window = null;
	return window;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulWindow(attrs);
	}
    };
}
