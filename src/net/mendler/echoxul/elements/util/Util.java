package net.mendler.echoxul.elements.util;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;

public class Util {

    public static Extent toExtent(String s) {
	int unit = Extent.PX;
	if (s.endsWith("px"))
	    unit = Extent.PX;
	else if (s.endsWith("%"))
	    unit = Extent.PERCENT;
	else if (s.endsWith("pt"))
	    unit = Extent.PT;
	else if (s.endsWith("cm"))
	    unit = Extent.CM;
	else if (s.endsWith("mm"))
	    unit = Extent.MM;
	else if (s.endsWith("in"))
	    unit = Extent.IN;
	else if (s.endsWith("em"))
	    unit = Extent.EM;
	else if (s.endsWith("ex"))
	    unit = Extent.EX;
	else if (s.endsWith("pc"))
	    unit = Extent.PC;

	String value = s.substring(0, s.length()
		- (unit == Extent.PERCENT ? 1 : 2));
	return new Extent(Integer.parseInt(value), unit);
    }

    public static Alignment toAlignment(String h, String v) {
	return new Alignment(halign(h), valign(v));
    }

    public static void setStyle(Component component, String style) {
	if (style == null)
	    style = "Default";
	component.setStyleName(style);
    }

    private static int valign(String val) {
	if (val == null)
	    return Alignment.DEFAULT;
	if (val.equals("top"))
	    return Alignment.TOP;
	if (val.equals("middle"))
	    return Alignment.CENTER;
	if (val.equals("baseline"))
	    return Alignment.TOP;
	if (val.equals("bottom"))
	    return Alignment.BOTTOM;
	return Alignment.DEFAULT;
    }

    private static int halign(String val) {
	if (val == null)
	    return Alignment.DEFAULT;
	if (val.equals("left"))
	    return Alignment.LEFT;
	if (val.equals("center"))
	    return Alignment.CENTER;
	if (val.equals("right"))
	    return Alignment.RIGHT;
	return Alignment.DEFAULT;
    }

    private Util() {
    }
}
