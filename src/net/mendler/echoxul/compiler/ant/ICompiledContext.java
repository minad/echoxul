package net.mendler.echoxul.compiler.ant;

import java.io.Serializable;

import net.mendler.echoxul.compiler.IContext;

public interface ICompiledContext extends Serializable {
    void execute(IContext context, int contextID, int execID) throws Exception;
}
