package net.mendler.echoxul.compiler.ant;

import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.compiler.IExecutable;

class AntExecutable implements IExecutable {

    public static final long serialVersionUID = 0;

    private AntContext context;

    private int id;

    public AntExecutable() {
    }
    
    public AntExecutable(AntContext c, int i) {
	context = c;
	id = i;
    }

    public IContext getContext() {
	return context;
    }

    public void execute() throws Exception {
	context.execute(id);
    }
}
