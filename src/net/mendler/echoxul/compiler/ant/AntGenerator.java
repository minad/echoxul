package net.mendler.echoxul.compiler.ant;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import net.mendler.echoxul.compiler.PackageList;

class AntGenerator {
    private Writer out;

    private AntContext context;

    private String className;

    private int indent;

    public AntGenerator(Writer w, AntContext c, String name) {
	out = new BufferedWriter(w);
	context = c;
	className = name;
	indent = 0;
    }

    public void generate() throws IOException {
	println("package net.mendler.echoxul.compiler.ant;");
	println();
	for (String pkg : PackageList.get())
	    println("import " + pkg + ".*;");

	println();
	println("public class " + className + " implements ICompiledContext {");

	println("public void execute(IContext context, int contextID, int execID) throws Exception {");
	println("switch (contextID) {");
	printCases(context, "(this");
	println("default: throw new IllegalArgumentException(\"Invalid context id\");");
	println('}');
	println('}');

	printContext(context);

	println('}');

	out.flush();
    }

    private void printCases(AntContext c, String parent) throws IOException {
	parent += ".new Context" + c.getID() + "(context)";
	println("case " + c.getID() + ": " + parent
		+ ").execute(execID); break;");

	Iterator<AntContext> j = c.getChildren().iterator();
	while (j.hasNext())
	    printCases(j.next(), parent);
    }

    private void printContext(AntContext c) throws IOException {
	println();
	println("class Context" + c.getID() + " {");

	Iterator<String> i = c.getVars().iterator();
	while (i.hasNext()) {
	    String name = i.next();
	    String clazz = c.get(name).getClass().getName();
	    println(clazz + " " + name + ";");
	}

	println();
	println("Context" + c.getID() + "(IContext context) {");

	i = c.getVars().iterator();
	while (i.hasNext()) {
	    String name = i.next();
	    String clazz = c.get(name).getClass().getName();
	    println(name + " = (" + clazz + ")context.get(\"" + name + "\");");
	}

	println('}');
	println();

	println("// --- BEGIN DEFINITIONS ---");
	i = c.getDefinitions().iterator();
	while (i.hasNext()) {
	    println(i.next());
	}
	println("// --- END DEFINITIONS   ---");
	println();

	println("void execute(int execID) throws Exception {");
	println("switch (execID) {");
	for (int n = 0; n < c.getExecutables().size(); ++n)
	    println("case " + n + ": execute" + n + "(); break;");
	println("default: throw new IllegalArgumentException(\"Invalid executable id\");");
	println('}');
	println('}');

	i = c.getExecutables().iterator();
	for (int n = 0; i.hasNext(); ++n) {
	    println();
	    println("void execute" + n + "() throws Exception {");
	    println("// --- BEGIN EXECUTABLE ---");
	    println(i.next() + ";");
	    println("// --- END EXECUTABLE   ---");
	    println('}');
	}

	Iterator<AntContext> j = c.getChildren().iterator();
	while (j.hasNext())
	    printContext(j.next());

	println('}');
    }

    private void println(String line) throws IOException {
	if (line.charAt(0) == '}')
	    --indent;
	for (int i = 0; i < indent; ++i)
	    out.write('\t');
	out.write(line);
	out.write('\n');
	if (line.indexOf('{') >= 0)
	    ++indent;
	if (line.charAt(0) != '}' && line.indexOf('}') >= 0)
	    --indent;
    }

    private void println(char ch) throws IOException {
	if (ch == '}')
	    --indent;
	for (int i = 0; i < indent; ++i)
	    out.write('\t');
	out.write(ch);
	out.write('\n');
	if (ch == '{')
	    ++indent;
    }

    private void println() throws IOException {
	out.write('\n');
    }
}
