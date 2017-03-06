package net.mendler.echoxul.elements.util;

import net.mendler.echoxul.compiler.CompilerException;
import net.mendler.echoxul.compiler.IContext;
import net.mendler.echoxul.compiler.IExecutable;

import org.apache.log4j.Logger;

public class Handler implements IExecutable {

    public static final long serialVersionUID = 0;

    private IExecutable executable;

    private static final Logger logger = Logger.getLogger(Handler.class);

    public Handler(String source, IContext context)
	    throws CompilerException {
	executable = context.compileExecutable(source);
    }

    public void execute() {
	try {
	    executable.execute();
	} catch (Exception ex) {
	    logger.error("Handler failed", ex);
	}
    }
    
    public IContext getContext() {
        return executable.getContext();
    }
}
