package net.mendler.echoxul.compiler.javascript;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.Script;

import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.compiler.IExecutable;

public class JsExecutable implements IExecutable {

    public static final long serialVersionUID = 0;

    private JsContext context;
    
    private Script script;
    
    public JsExecutable() {
    }
    
    public JsExecutable(JsContext c, Script s) {
	context = c;
	script = s;
    }
    
    public void execute() throws Exception {
	Context.call(new ContextAction() {
	   public Object run(Context c) {
		script.exec(c, context.getScope());
	        return null;
	    } 
	});
    }

    public IContext getContext() {
	return context;
    }
}
