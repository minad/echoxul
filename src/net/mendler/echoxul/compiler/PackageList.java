package net.mendler.echoxul.compiler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PackageList {
    private static final String[] packages = { "net.mendler.echoxul",
	    "net.mendler.echoxul.compiler", "net.mendler.echoxul.elements",
	    "net.mendler.echoxul.util", "nextapp.echo2.app",
	    "nextapp.echo2.app.button", "nextapp.echo2.app.event",
	    "nextapp.echo2.app.layout", "nextapp.echo2.app.list",
	    "nextapp.echo2.app.table", "nextapp.echo2.app.text",
	    "nextapp.echo2.app.update", "nextapp.echo2.app.util",
	    "nextapp.echo2.extras.app", "nextapp.echo2.extras.app.layout",
	    "nextapp.echo2.extras.app.menu", "echopointng", "echopointng.able",
	    "echopointng.command", "echopointng.externalevent",
	    "echopointng.image", "echopointng.layout", "echopointng.model",
	    "echopointng.progressbar", "echopointng.richtext",
	    "echopointng.stylesheet", "echopointng.stylesheet.propertypeer",
	    "echopointng.tabbedpane", "echopointng.table",
	    "echopointng.template", "echopointng.tree",
	    "echopointng.treetable", "echopointng.util",
	    "echopointng.util.collections", "echopointng.util.reflect",
	    "echopointng.util.throwable", "org.apache.log4j", "java.util" };

    private static final List<String> list = Collections
	    .unmodifiableList(Arrays.asList(packages));

    public static List<String> get() {
	return list;
    }

    private PackageList() {
    }
}
