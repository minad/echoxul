package net.mendler.echoxul.compiler.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Wrapper;

import net.mendler.echoxul.ApplicationOptions;
import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.compiler.IContextFactory;
import net.mendler.echoxul.compiler.IExecutable;

public class JsContext implements IContext {

    public static final long serialVersionUID = 0;

    private JsContext parent;

    private Scriptable scope;

    public JsContext() {
    }

    public JsContext(JsContext p) {
	this.parent = p;
	scope = (Scriptable) Context.call(new ContextAction() {
	    public Object run(Context cx) {
		if (parent == null)
		    return new Global(cx);
		return cx.newObject(parent.scope);
	    }
	});
    }

    public void compileDefinition(final String code) throws CompilerException {
	try {
	    Context.call(new ContextAction() {
		public Object run(Context cx) {
		    cx.evaluateString(scope, code, "definition", 1, null);
		    return null;
		}
	    });
	} catch (EvaluatorException ex) {
	    throw new CompilerException(ex);
	}
    }

    public IExecutable compileExecutable(final String code)
	    throws CompilerException {
	try {
	    return (IExecutable) Context.call(new ContextAction() {
		public Object run(Context cx) {
		    return new JsExecutable(JsContext.this, cx.compileString(
			    code, "executable", 1, null));
		}
	    });
	} catch (EvaluatorException ex) {
	    throw new CompilerException(ex);
	}

    }

    public IContext createChild() {
	return new JsContext(this);
    }

    public Object get(final String name) {
	return Context.call(new ContextAction() {
	    public Object run(Context cx) {
		Object value = scope.get(name, scope);
		if (value instanceof Wrapper)
		    return ((Wrapper) value).unwrap();
		return value;
	    }
	});
    }

    public IContext getParent() {
	return parent;
    }

    public void set(final String name, final Object value) {
	Context.call(new ContextAction() {
	    public Object run(Context cx) {
		scope.put(name, scope, Context.javaToJS(value, scope));
		return null;
	    }
	});
    }

    Scriptable getScope() {
	return scope;
    }

    public static final IContextFactory Factory = new IContextFactory() {
	public IContext create(ApplicationOptions options) {
	    return new JsContext(null);
	}
    };
}
