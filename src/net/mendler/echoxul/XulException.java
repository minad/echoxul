package net.mendler.echoxul;

public class XulException extends Exception {

    public static final long serialVersionUID = 0;

    public XulException(String message) {
	super(message);
    }

    public XulException(Throwable cause) {
	super(cause);
    }

    public XulException(String message, Throwable cause) {
	super(message, cause);
    }
}
