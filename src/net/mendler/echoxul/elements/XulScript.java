package net.mendler.echoxul.elements;

import java.util.Map;

import net.mendler.echoxul.compiler.CompilerException;

public class XulScript extends XulElement {

    public static final long serialVersionUID = 0;

    public XulScript() {
    }

    public XulScript(Map<String, String> attrs, String content) {
	super(attrs, content);
    }

    @Override
    public void internalInit() throws CompilerException {
	getApplication().getGlobalContext().compileDefinition(getContent());
    }

    public static final IElementFactory Factory = new IElementFactory() {
	public IXulElement create(Map<String, String> attrs, String content) {
	    return new XulScript(attrs, content);
	}
    };
}
