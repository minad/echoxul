package net.mendler.echoxul.compiler.ant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.mendler.echoxul.ApplicationOptions;
import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.compiler.IContextFactory;
import net.mendler.echoxul.compiler.IExecutable;

public class AntContext implements IContext {

    public static final long serialVersionUID = 0;

    private ApplicationOptions options;

    private AntContext parent;

    private Map<String, Object> vars = new TreeMap<String, Object>();

    private List<String> definitions;

    private List<String> executables;

    private List<AntContext> children;

    private int nextID;

    private int id;

    public AntContext() {
    }

    public AntContext(ApplicationOptions options, AntContext parent) {
	this.options = options;
	this.parent = parent;
	nextID = 0;
	id = getNextID();
	children = new ArrayList<AntContext>();
	definitions = new ArrayList<String>();
	executables = new ArrayList<String>();
    }

    public Object get(String name) {
	Object val = vars.get(name);
	if (val == null && parent != null)
	    return parent.get(name);
	return val;
    }

    public void set(String name, Object value) {
	vars.put(name, value);
    }

    public IContext getParent() {
	return parent;
    }

    public IContext createChild() {
	AntContext child = new AntContext(options, this);
	children.add(child);
	return child;
    }

    public void compileDefinition(String code) {
	definitions.add(code);
    }

    public IExecutable compileExecutable(String code) throws CompilerException {
	executables.add(code);
	return new AntExecutable(this, executables.size() - 1);
    }

    void execute(int execID) throws Exception {
	AntContext root = this;
	while (root.parent != null)
	    root = root.parent;
	ICompiledContext compiled = AntCompiler.compile(options, root);
	compiled.execute(this, id, execID);
    }

    List<String> getDefinitions() {
	return definitions;
    }

    List<String> getExecutables() {
	return executables;
    }

    List<AntContext> getChildren() {
	return children;
    }

    int getID() {
	return id;
    }

    Set<String> getVars() {
	return vars.keySet();
    }

    private int getNextID() {
	if (parent == null)
	    return nextID++;
	return parent.getNextID();
    }

    public static final IContextFactory Factory = new IContextFactory() {
	public IContext create(ApplicationOptions options) {
	    return new AntContext(options, null);
	}
    };
}
