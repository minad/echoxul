package net.mendler.echoxul.compiler.beanshell;

import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.compiler.IExecutable;

class BshExecutable implements IExecutable {

    public static final long serialVersionUID = 0;

    private BshContext context;

    private String code;

    public BshExecutable() {
    }
    
    public BshExecutable(BshContext c, String d) {
	context = c;
	code = d;
    }

    public IContext getContext() {
	return context;
    }

    public void execute() throws Exception {
	context.getInterpreter().eval(code);
    }
}
