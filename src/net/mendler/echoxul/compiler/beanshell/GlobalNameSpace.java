package net.mendler.echoxul.compiler.beanshell;

import net.mendler.echoxul.compiler.PackageList;
import bsh.NameSpace;
import bsh.classpath.ClassManagerImpl;

class GlobalNameSpace extends NameSpace {

    public static final long serialVersionUID = 0;

    public GlobalNameSpace() {
	super(new ClassManagerImpl(), "global");
    }

    public void loadDefaultImports() {
	// Imported in super.loadDefaultImports()
	// but we only want java.lang
	//
	// importClass("bsh.EvalError");
	// importClass("bsh.Interpreter");
	// importPackage("javax.swing.event");
	// importPackage("javax.swing");
	// importPackage("java.awt.event");
	// importPackage("java.awt");
	// importPackage("java.net");
	// importPackage("java.util");
	// importPackage("java.io");
	// importPackage("java.lang");
	// importCommands("/bsh/commands");

	importPackage("java.lang");

	for (String pkg : PackageList.get())
	    importPackage(pkg);
    }
}