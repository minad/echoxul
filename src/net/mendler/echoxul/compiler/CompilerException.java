package net.mendler.echoxul.compiler;

import net.mendler.echoxul.XulException;

public class CompilerException extends XulException {

    public static final long serialVersionUID = 0;

    public CompilerException(String message) {
	super(message);
    }

    public CompilerException(Throwable cause) {
	super(cause);
    }

    public CompilerException(String message, Throwable cause) {
	super(message, cause);
    }
}
