package net.mendler.echoxul.compiler.javascript;

import net.mendler.echoxul.compiler.PackageList;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ImporterTopLevel;

class Global extends ImporterTopLevel {

    public static final long serialVersionUID = 0;

    public Global(Context cx) {
	initStandardObjects(cx, true);

	for (String pkg : PackageList.get())
	    cx.evaluateString(this, "importPackage(Packages." + pkg + ")", "import",
		    1, null);
    }
}
