package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.elements.util.Handler;
import net.mendler.echoxul.elements.util.Util;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.event.DocumentEvent;
import nextapp.echo2.app.event.DocumentListener;
import echopointng.RichTextArea;

public class XulRichText extends XulElement {

    public static final long serialVersionUID = 0;

    private RichTextArea richText;

    public XulRichText() {
    }

    public XulRichText(Map<String, String> attrs) {
	super(attrs);
    }

    public Component getRichText() {
	return richText;
    }

    @Override
    public Component getComponent() {
	return richText;
    }

    @Override
    protected void initLocalContext(IContext c) {
	super.initLocalContext(c);
	c.set("richtext", richText);
	c.set("document", richText.getDocument());
    }

    @Override
    public void internalInit() throws CompilerException {
	richText = new RichTextArea();
	Util.setStyle(richText, getAttribute("style"));

	String value = getAttribute("onupdate");
	if (value != null) {
	    final Handler handler = new Handler(value,
		    getLocalContext());
	    richText.getDocument().addDocumentListener(new DocumentListener() {
		public static final long serialVersionUID = 0;

		public void documentUpdate(DocumentEvent e) {
		    handler.execute();
		}
	    });
	}
    }

    @Override
    public XulRichText clone() {
	XulRichText text = (XulRichText) super.clone();
	text.richText = null;
	return text;
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulRichText(attrs);
	}
    };
}
