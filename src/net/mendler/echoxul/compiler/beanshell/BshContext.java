package net.mendler.echoxul.compiler.beanshell;

import java.io.StringReader;

import net.mendler.echoxul.ApplicationOptions;
import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.compiler.IContextFactory;
import net.mendler.echoxul.compiler.IExecutable;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;

public class BshContext implements IContext {

    public static final long serialVersionUID = 0;

    private BshContext parent;

    private NameSpace nameSpace;

    private Interpreter interpreter;

    private int nextID;

    public BshContext() {
    }

    public BshContext(BshContext parent) {
	this.parent = parent;
	nextID = 0;
	if (parent == null) {
	    nameSpace = new GlobalNameSpace();
	    interpreter = new Interpreter(new StringReader(""), System.out,
		    System.err, false, nameSpace);
	    interpreter.setStrictJava(true);
	} else {
	    BshContext bc = (BshContext) parent;
	    nameSpace = new NameSpace(bc.nameSpace, "local");
	    interpreter = new Interpreter(new StringReader(""), System.out,
		    System.err, false, nameSpace, bc.interpreter, null);
	}
    }

    public Object get(String name) {
	try {
	    return interpreter.get(name);
	} catch (EvalError ex) {
	    throw new RuntimeException(ex);
	}
    }

    public void set(String name, Object value) {
	try {
	    if (value instanceof Boolean)
		interpreter.set(name, ((Boolean) value).booleanValue());
	    else if (value instanceof Double)
		interpreter.set(name, ((Double) value).doubleValue());
	    else if (value instanceof Float)
		interpreter.set(name, ((Float) value).floatValue());
	    else if (value instanceof Integer)
		interpreter.set(name, ((Integer) value).intValue());
	    else if (value instanceof Long)
		interpreter.set(name, ((Long) value).longValue());
	    else if (value instanceof Byte)
		interpreter.set(name, ((Byte) value).byteValue());
	    else if (value instanceof Character)
		interpreter.set(name, ((Character) value).charValue());
	    else if (value instanceof Short)
		interpreter.set(name, ((Short) value).byteValue());
	    else
		interpreter.set(name, value);
	} catch (EvalError ex) {
	    throw new RuntimeException(ex);
	}
    }

    public IContext getParent() {
	return parent;
    }

    public IContext createChild() {
	return new BshContext(this);
    }

    public void compileDefinition(String code) throws CompilerException {
	try {
	    interpreter.eval(code);
	} catch (EvalError ex) {
	    throw new CompilerException(ex);
	}
    }

    public IExecutable compileExecutable(String code) throws CompilerException {
	int id = nextID++;
	compileDefinition("void __execute" + id + "(){" + code + ";}");
	return new BshExecutable(this, "__execute" + id + "();");
    }

    Interpreter getInterpreter() {
	return interpreter;
    }

    public static final IContextFactory Factory = new IContextFactory() {
	public IContext create(ApplicationOptions options) {
	    return new BshContext(null);
	}
    };
}
